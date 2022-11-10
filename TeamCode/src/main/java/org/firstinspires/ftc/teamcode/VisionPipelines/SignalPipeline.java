package org.firstinspires.ftc.teamcode.VisionPipelines;

import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.DEBUG_MODE;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.GREEN_MAX_CB;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.GREEN_MAX_CR;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.GREEN_MAX_Y;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.GREEN_MIN_CB;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.GREEN_MIN_CR;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.GREEN_MIN_Y;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.ORANGE_MAX_CB;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.ORANGE_MAX_CR;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.ORANGE_MAX_Y;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.ORANGE_MIN_CB;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.ORANGE_MIN_CR;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.ORANGE_MIN_Y;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.PINK_MAX_CB;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.PINK_MAX_CR;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.PINK_MAX_Y;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.PINK_MIN_CB;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.PINK_MIN_CR;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.PINK_MIN_Y;
import static org.firstinspires.ftc.teamcode.Utilities.VisionUtils.sortRectsByMaxOption;
import static org.opencv.core.Core.inRange;
import static org.opencv.imgproc.Imgproc.CHAIN_APPROX_SIMPLE;
import static org.opencv.imgproc.Imgproc.RETR_TREE;
import static org.opencv.imgproc.Imgproc.boundingRect;
import static org.opencv.imgproc.Imgproc.cvtColor;
import static org.opencv.imgproc.Imgproc.drawContours;
import static org.opencv.imgproc.Imgproc.findContours;
import static org.opencv.imgproc.Imgproc.rectangle;

import org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision;
import org.firstinspires.ftc.teamcode.Utilities.VisionUtils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class SignalPipeline extends OpenCvPipeline {
//this is for detecting the signal sleeve at the beginning of auto
//to be used for auto only
//loosely copied from WM23 ColorPicker and LupineBTI DuckPipelineDetect

    private ArrayList<MatOfPoint> greenContours, orangeContours, pinkContours; //empty contour lists to store contour points
    private Mat hierarchy = new Mat();
    private Scalar red = new Scalar(255, 0, 0); //this pipeline outlines in RED
    public Mat output = new Mat(),
            modified = new Mat(),
            greenMat = new Mat(), //separate matrices for each signal color
            orangeMat = new Mat(),
            pinkMat = new Mat();
    private static int IMG_HEIGHT = 0;
    private static int IMG_WIDTH = 0;

    boolean isSignalFound = false;

    public enum SignalSide {

        ONE_GREEN,
        TWO_ORANGE,
        THREE_PINK

    }

    public SignalSide signalSide;


    public enum ParkingSpace {

        ONE_LEFT,
        TWO_MIDDLE,
        THREE_RIGHT

    }

    public ParkingSpace parkingSpace;

    @Override
    public Mat processFrame(Mat input) {


        //convert to YCrCb
        Imgproc.cvtColor(input, modified, Imgproc.COLOR_RGB2YCrCb);

        //copy input to output
        input.copyTo(output);

        //height & width
        IMG_HEIGHT = input.rows();
        IMG_WIDTH = input.cols();

        //Retrieving green, orange, or pink rect
        Rect greenRect = getGreen(modified);
        Rect orangeRect = getOrange(modified);
        Rect pinkRect = getPink(modified);


        if (DEBUG_MODE){
            if (greenRect != null){
                rectangle(output, greenRect, red);
            }

            if (orangeRect != null){
                rectangle(output, orangeRect, red);
            }

            if (pinkRect != null){
                rectangle(output, pinkRect, red);
            }
        }


        //if there was an error checking for any of the rects
        if (greenRect == null || orangeRect == null || pinkRect == null){
            return output;
        }



        switch(signalSide){
            case ONE_GREEN:
                parkingSpace = ParkingSpace.ONE_LEFT;
            case TWO_ORANGE:
                parkingSpace = ParkingSpace.TWO_MIDDLE;
            case THREE_PINK:
                parkingSpace = ParkingSpace.THREE_RIGHT;
        }

        //analysis of position - which color is the signal sleeve and where should we park?
        if (greenRect != null && orangeRect == null && pinkRect == null){
            //if green
            signalSide = SignalSide.ONE_GREEN;
        }
        if (orangeRect != null && greenRect == null && pinkRect == null){
            //if orange
            signalSide = SignalSide.TWO_ORANGE;
        }
        if (pinkRect != null && greenRect == null && orangeRect == null){
            //if pink
            signalSide = SignalSide.THREE_PINK;
        }



        return output;
    }

    /**
     * LOOKING FOR GREEN
     * @param input
     * @return
     */
    private Rect getGreen(Mat input){

        //thresholding
        Scalar GREEN_MAX_THRESH = new Scalar(GREEN_MAX_Y, GREEN_MAX_CR, GREEN_MAX_CB);
        Scalar GREEN_MIN_THRESH = new Scalar(GREEN_MIN_Y, GREEN_MIN_CR, GREEN_MIN_CB);
        inRange(input, GREEN_MIN_THRESH, GREEN_MAX_THRESH, greenMat);

        //finding contour
        greenContours = new ArrayList<>();
        findContours(greenMat, greenContours, hierarchy, RETR_TREE, CHAIN_APPROX_SIMPLE);
        drawContours(modified, greenContours, -1, red, 1);

        //finding rect(s)
        List<Rect> greenRects = new ArrayList<>();
        for (int i = 0; i < greenContours.size(); i++){
            Rect greenRect = boundingRect(greenContours.get(i));
            greenRects.add(greenRect);
            /*
             iterates thru each index in the contour arraylist
             adds that contour to the rectangle arraylist
             then a rectangle is drawn around the contour
            */
        }

        //exception for if no green is found
        if (greenRects.size() < 1){
            isSignalFound = false;
            return null;
        }

        //finding largest rect - this is the one we want!
        return sortRectsByMaxOption(1, VisionUtils.RECT_OPTION.AREA, greenRects).get(0);

    }


    /**
     * LOOKING FOR ORANGE
     * @param input
     * @return
     */
    private Rect getOrange(Mat input){

        //thresholding
        Scalar ORANGE_MAX_THRESH = new Scalar(ORANGE_MAX_Y, ORANGE_MAX_CR, ORANGE_MAX_CB);
        Scalar ORANGE_MIN_THRESH = new Scalar(ORANGE_MIN_Y, ORANGE_MIN_CR, ORANGE_MIN_CB);
        inRange(input, ORANGE_MIN_THRESH, ORANGE_MAX_THRESH, orangeMat);

        //finding contour
        orangeContours = new ArrayList<>();
        findContours(orangeMat, orangeContours, hierarchy, RETR_TREE, CHAIN_APPROX_SIMPLE);
        drawContours(modified, orangeContours, -1, red, 1);

        //finding rect(s)
        List<Rect> orangeRects = new ArrayList<>();
        for (int i = 0; i < orangeContours.size(); i++){
            Rect orangeRect = boundingRect(orangeContours.get(i));
            orangeRects.add(orangeRect);
        }

        //exception for if no orange found
        if (orangeRects.size() < 1){
            isSignalFound = false;
            return null;
        }

        //finding largest rect
        return sortRectsByMaxOption(1, VisionUtils.RECT_OPTION.AREA, orangeRects).get(0);
    }


    /**
     * LOOKING FOR PINK
     * @param input
     * @return
     */
    private Rect getPink(Mat input){

        //thresholding
        Scalar PINK_MAX_THRESH = new Scalar(PINK_MAX_Y, PINK_MAX_CR, PINK_MAX_CB);
        Scalar PINK_MIN_THRESH = new Scalar(PINK_MIN_Y, PINK_MIN_CR, PINK_MIN_CB);
        inRange(input, PINK_MIN_THRESH, PINK_MAX_THRESH, pinkMat);

        //finding contour
        pinkContours = new ArrayList<>();
        findContours(pinkMat, pinkContours, hierarchy, RETR_TREE, CHAIN_APPROX_SIMPLE);
        drawContours(modified, pinkContours, -1, red, 1);

        //finding rect(s)
        List<Rect> pinkRects = new ArrayList<>();
        for (int i = 0; i < pinkContours.size(); i++){
            Rect pinkRect = boundingRect(pinkContours.get(i));
            pinkRects.add(pinkRect);
        }

        //exception for if no pink is found
        if (pinkRects.size() < 1){
            isSignalFound = false;
            return null;
        }

        //finding largest rect
        return sortRectsByMaxOption(1, VisionUtils.RECT_OPTION.AREA, pinkRects).get(0);

    }
}
