package org.firstinspires.ftc.teamcode.Vision;

import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.DEBUG_MODE;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.MAX_CB;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.MAX_CR;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.MAX_Y;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.MIN_CB;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.MIN_CR;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.MIN_Y;
import static org.firstinspires.ftc.teamcode.Utilities.VisionUtils.IMG_WIDTH;
import static org.firstinspires.ftc.teamcode.Utilities.VisionUtils.IMG_HEIGHT;

import static org.firstinspires.ftc.teamcode.Utilities.VisionUtils.sortRectsByMaxOption;
import static org.opencv.core.Core.inRange;
import static org.opencv.imgproc.Imgproc.CHAIN_APPROX_SIMPLE;
import static org.opencv.imgproc.Imgproc.COLOR_RGB2YCrCb;
import static org.opencv.imgproc.Imgproc.RETR_TREE;

import static org.opencv.imgproc.Imgproc.boundingRect;
import static org.opencv.imgproc.Imgproc.cvtColor;
import static org.opencv.imgproc.Imgproc.drawContours;
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



public class TestPipeline extends OpenCvPipeline {
    private Mat output = new Mat();
    private Mat modified = new Mat();
    private List<MatOfPoint> contours = new ArrayList<>(); //create empty arraylist to store contour points
    private Mat hierarchy = new Mat();
    private Scalar green = new Scalar(0, 255, 0);

    @Override
    public Mat processFrame(Mat input) {
        IMG_HEIGHT = input.rows(); //input image height
        IMG_WIDTH = input.cols(); //input image width
        input.copyTo(output); //copying input image to output
        cvtColor(input, modified, COLOR_RGB2YCrCb); //call to "convert color" method

        Scalar MAX_THRESH = new Scalar(MAX_Y, MAX_CR, MAX_CB);
        Scalar MIN_THRESH = new Scalar(MIN_Y, MIN_CR, MIN_CB);
        inRange(modified, MIN_THRESH, MAX_THRESH, modified); //threshold image

        contours = new ArrayList<>();
        hierarchy = new Mat();
        findContours(modified, contours, hierarchy, RETR_TREE, CHAIN_APPROX_SIMPLE); //populates contours and hierarchy lists

        List<Rect> rects = new ArrayList<>();

        for (int i = 0; i < contours.size(); i++){

            Rect rect = boundingRect(contours.get(i));
            rects.add(rect);
            /*
             iterates thru each index in the contour arraylist
             adds that contour to the rectangle arraylist
             then a rectangle is drawn around the contour (in this case the duck)
            */

            rectangle(output, rect, green, 3); //draws a rectangle around the output/contour (the duck)
        }

        List<Rect> biggestRect = sortRectsByMaxOption(1, VisionUtils.RECT_OPTION.AREA, rects);

        if (DEBUG_MODE){
            return modified;
        }
        return output;


    }



}
