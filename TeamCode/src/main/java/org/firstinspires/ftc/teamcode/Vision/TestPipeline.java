package org.firstinspires.ftc.teamcode.Vision;

import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;

public class TestPipeline extends OpenCvPipeline {
    @Override
    public Mat processFrame(Mat input) {
        return input;
    }
}
