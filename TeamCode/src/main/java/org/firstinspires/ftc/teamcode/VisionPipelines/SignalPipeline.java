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
import static org.opencv.imgproc.Imgproc.COLOR_RGB2YCrCb;
import static org.opencv.imgproc.Imgproc.RETR_TREE;
import static org.opencv.imgproc.Imgproc.boundingRect;
import static org.opencv.imgproc.Imgproc.cvtColor;
import static org.opencv.imgproc.Imgproc.findContours;
import static org.opencv.imgproc.Imgproc.rectangle;

import org.firstinspires.ftc.teamcode.Utilities.VisionUtils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class SignalPipeline extends OpenCvPipeline {
//this is for detecting the signal sleeve at the beginning of auto
//to be used for auto only
//loosely copied from WM23 ColorPicker and LupineBTI DuckPipelineDetect

    private Mat output = new Mat();
    private Mat modified = new Mat();
    private List<MatOfPoint> contours = new ArrayList<>(); //create empty arraylist to store contour points
    private Mat hierarchy = new Mat();
    private Scalar red = new Scalar(255, 0, 0); //this pipeline outlines in RED

    private static int IMG_HEIGHT = 0;
    private static int IMG_WIDTH = 0;

    boolean isSignalFound = false;
    boolean greenFound = false;
    boolean orangeFound = false;
    boolean pinkFound = false;

    public enum SignalSide {
        oneGreen, twoOrange, threePink
    }


    /**
     * what I want to do:
     * search for the signal sleeve
     * if oneGreen is detected, signalSide = oneGreen, etc. - detect by hsv value
     **/

    @Override
    public Mat processFrame(Mat input) {
        IMG_HEIGHT = input.rows() / 2;
        IMG_WIDTH = input.cols() / 2;

        input.copyTo(output);
        cvtColor(input, modified, COLOR_RGB2YCrCb); //convert to YCrCb color space


        /**
         * THRESHOLDING
         */
        //THRESHOLD FOR GREEN
        Scalar GREEN_MAX_THRESH = new Scalar(GREEN_MAX_Y, GREEN_MAX_CR, GREEN_MAX_CB);
        Scalar GREEN_MIN_THRESH = new Scalar(GREEN_MIN_Y, GREEN_MIN_CR, GREEN_MIN_CB);
        inRange(modified, GREEN_MIN_THRESH, GREEN_MAX_THRESH, modified); //threshold image for green

        //THRESHOLD FOR ORANGE
        Scalar ORANGE_MAX_THRESH = new Scalar(ORANGE_MAX_Y, ORANGE_MAX_CR, ORANGE_MAX_CB);
        Scalar ORANGE_MIN_THRESH = new Scalar(ORANGE_MIN_Y, ORANGE_MIN_CR, ORANGE_MIN_CB);
        inRange(modified, ORANGE_MIN_THRESH, ORANGE_MAX_THRESH, modified);

        //THRESHOLD FOR PINK
        Scalar PINK_MAX_THRESH = new Scalar(PINK_MAX_Y, PINK_MAX_CR, PINK_MAX_CB);
        Scalar PINK_MIN_THRESH = new Scalar(PINK_MIN_Y, PINK_MIN_CR, PINK_MIN_CB);
        inRange(modified, PINK_MIN_THRESH, PINK_MAX_THRESH, modified);


        /**
         * FINDING CONTOURS
         */
        contours = new ArrayList<>();
        hierarchy = new Mat();
        findContours(modified, contours, hierarchy, RETR_TREE, CHAIN_APPROX_SIMPLE); //populates contours and hierarchy lists

        /*
         * for oneGreen
         * finding contours
         */
        List<Rect> greenRects = new ArrayList<>();
        for (int i = 0; i < contours.size(); i++){
            Rect greenRect = boundingRect(contours.get(i));
            greenRects.add(greenRect);
            /*
             iterates thru each index in the contour arraylist
             adds that contour to the rectangle arraylist
             then a rectangle is drawn around the contour
            */
        }

        /*
         * for twoOrange
         * finding contours
         */
        List<Rect> orangeRects = new ArrayList<>();
        for (int i = 0; i < contours.size(); i++){
            Rect orangeRect = boundingRect(contours.get(i));
            orangeRects.add(orangeRect);
        }

        /*
         * for threePurple
         * finding contours
         */
        List<Rect> pinkRects = new ArrayList<>();
        for (int i = 0; i < contours.size(); i++){
            Rect pinkRect = boundingRect(contours.get(i));
            pinkRects.add(pinkRect);
        }


        if (greenRects.size() < 1 && orangeRects.size() < 1 && pinkRects.size() < 1){ //if camera has found NO contours
            isSignalFound = false; //if the camera does not see the signal at all, the code will not run
            if (DEBUG_MODE){
                return modified;
            }
            return output;
        }

        isSignalFound = true; //if we have found at least one contour, the signal has been found


        /**
         * DRAWING THE RECTANGLE
         */
        if (greenRects.size() > 0 && orangeRects.size() < 1 && pinkRects.size() < 1){ //if camera only sees green
            /*
             looks for the biggest rectangle in the frame
             sorts by area
             */
            List<Rect> biggestRect = sortRectsByMaxOption(1, VisionUtils.RECT_OPTION.AREA, greenRects);
            Rect signalRect = biggestRect.get(0);
            rectangle(output, signalRect, red, 3); //draws a rectangle around the contour

            SignalSide signalSide = SignalSide.oneGreen; //signal side is one/green!
            greenFound = true;
        }

        if (orangeRects.size() > 0 && greenRects.size() < 1 && pinkRects.size() < 1){ //if camera only sees orange
            List<Rect> biggestRect = sortRectsByMaxOption(1, VisionUtils.RECT_OPTION.AREA, orangeRects);
            Rect signalRect = biggestRect.get(0);
            rectangle(output, signalRect, red, 3); //draws a rectangle around the contour

            SignalSide signalSide = SignalSide.twoOrange; //signal side is two/orange!
            orangeFound = true;
        }

        if (pinkRects.size() > 0 && greenRects.size() < 1 && orangeRects.size() < 1){ //if camera only sees pink
            List<Rect> biggestRect = sortRectsByMaxOption(1, VisionUtils.RECT_OPTION.AREA, pinkRects);
            Rect signalRect = biggestRect.get(0);
            rectangle(output, signalRect, red, 3); //draws a rectangle around the contour

            SignalSide signalSide = SignalSide.threePink; //signal side is three/purple!
            pinkFound = true;
        }




        if (DEBUG_MODE){
            return modified;
        }
        return output;
    }


    public void park(){
    //robot will drive to corresponding parking spot based on the signal sleeve
        if (greenFound){

        }

        if (orangeFound){

        }

        if (pinkFound){

        }
    }

}
