package org.firstinspires.ftc.teamcode.Hardware.Sensors;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Vision.DuckPipelineDetect;
import org.firstinspires.ftc.teamcode.Vision.DuckPipelineLR;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class Camera {
    //creates a camera

    private OpenCvCamera webcam;
    private String id;
    private int viewportID;
    public DuckPipelineLR back_pipeline = new DuckPipelineLR();
    public DuckPipelineDetect front_pipeline = new DuckPipelineDetect();


    public Camera(String id, int viewportID){
        this.id = id;
        this.viewportID = viewportID;

        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, id), viewportID);

        if (this.id.equals("frontCam")){ //assigns a camera to a pipeline based on its String id
            webcam.setPipeline(front_pipeline);
        } else {
            webcam.setPipeline(back_pipeline);
        }

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {

                webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });
    }


}
