package org.firstinspires.ftc.teamcode.VisionPipelines;

import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.DEBUG_MODE;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.GREEN_MAX_H;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.GREEN_MAX_S;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.GREEN_MAX_V;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.GREEN_MIN_H;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.GREEN_MIN_S;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.GREEN_MIN_V;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.ORANGE_MAX_H;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.ORANGE_MAX_S;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.ORANGE_MAX_V;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.ORANGE_MIN_H;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.ORANGE_MIN_S;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.ORANGE_MIN_V;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.PURPLE_MAX_H;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.PURPLE_MAX_S;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.PURPLE_MAX_V;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.PURPLE_MIN_H;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.PURPLE_MIN_S;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.PURPLE_MIN_V;
import static org.firstinspires.ftc.teamcode.Utilities.VisionUtils.sortRectsByMaxOption;
import static org.opencv.core.Core.inRange;
import static org.opencv.imgproc.Imgproc.CHAIN_APPROX_SIMPLE;
import static org.opencv.imgproc.Imgproc.COLOR_RGB2HSV;
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
    boolean purpleFound = false;

    public enum SignalSide {
        oneGreen, twoOrange, threePurple
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
        cvtColor(input, modified, COLOR_RGB2HSV); //convert to HSV color space


        /**
         * THRESHOLDING
         */
        //THRESHOLD FOR GREEN
        Scalar GREEN_MAX_THRESH = new Scalar(GREEN_MAX_H, GREEN_MAX_S, GREEN_MAX_V);
        Scalar GREEN_MIN_THRESH = new Scalar(GREEN_MIN_H, GREEN_MIN_S, GREEN_MIN_V);
        inRange(modified, GREEN_MIN_THRESH, GREEN_MAX_THRESH, modified); //threshold image for green

        //THRESHOLD FOR ORANGE
        Scalar ORANGE_MAX_THRESH = new Scalar(ORANGE_MAX_H, ORANGE_MAX_S, ORANGE_MAX_V);
        Scalar ORANGE_MIN_THRESH = new Scalar(ORANGE_MIN_H, ORANGE_MIN_S, ORANGE_MIN_V);
        inRange(modified, ORANGE_MIN_THRESH, ORANGE_MAX_THRESH, modified);

        //THRESHOLD FOR PURPLE
        Scalar PURPLE_MAX_THRESH = new Scalar(PURPLE_MAX_H, PURPLE_MAX_S, PURPLE_MAX_V);
        Scalar PURPLE_MIN_THRESH = new Scalar(PURPLE_MIN_H, PURPLE_MIN_S, PURPLE_MIN_V);
        inRange(modified, PURPLE_MIN_THRESH, PURPLE_MAX_THRESH, modified);


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
        List<Rect> purpleRects = new ArrayList<>();
        for (int i = 0; i < contours.size(); i++){
            Rect purpleRect = boundingRect(contours.get(i));
            purpleRects.add(purpleRect);
        }


        if (greenRects.size() < 1 && orangeRects.size() < 1 && purpleRects.size() < 1){ //if camera has found NO contours
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
        if (greenRects.size() > 0 && orangeRects.size() < 1 && purpleRects.size() < 1){ //if camera only sees green
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

        if (orangeRects.size() > 0 && greenRects.size() < 1 && purpleRects.size() < 1){ //if camera only sees orange
            List<Rect> biggestRect = sortRectsByMaxOption(1, VisionUtils.RECT_OPTION.AREA, orangeRects);
            Rect signalRect = biggestRect.get(0);
            rectangle(output, signalRect, red, 3); //draws a rectangle around the contour

            SignalSide signalSide = SignalSide.twoOrange; //signal side is two/orange!
            orangeFound = true;
        }

        if (purpleRects.size() > 0 && greenRects.size() < 1 && orangeRects.size() < 1){ //if camera only sees purple
            List<Rect> biggestRect = sortRectsByMaxOption(1, VisionUtils.RECT_OPTION.AREA, purpleRects);
            Rect signalRect = biggestRect.get(0);
            rectangle(output, signalRect, red, 3); //draws a rectangle around the contour

            SignalSide signalSide = SignalSide.threePurple; //signal side is three/purple!
            purpleFound = true;
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

        if (purpleFound){

        }
    }

}
