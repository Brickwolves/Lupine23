package org.firstinspires.ftc.teamcode.Autonomous;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision;
import org.firstinspires.ftc.teamcode.Hardware.Robot;
import org.firstinspires.ftc.teamcode.Hardware.Vision.Camera;
import org.firstinspires.ftc.teamcode.Utilities.Loggers.Side;
import org.firstinspires.ftc.teamcode.VisionPipelines.SignalPipeline;

@Autonomous(name="TestVisionAuto", group="Autonomous Linear Opmode")
public class TestVisionAuto extends LinearOpMode {

    //declare OpMode members
    Robot robot;
    Camera signalCam;
    private ElapsedTime runtime = new ElapsedTime();
    public SignalPipeline.SignalSide signalSide;


    public void initialize(){
        setOpMode(this);
        Side.setBlue();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void runOpMode()
    {

        initialize();

        multTelemetry.addLine("Waiting for start");
        multTelemetry.update();

        robot = new Robot();
        signalCam = new Camera("junkCam", true);

        /**
         * I want this to tell me which side of the signal it is recognizing
         * so I can know my code works
         */
        multTelemetry.addData("signal side", signalSide);

        waitForStart();

        if (opModeIsActive()){

        }


    }




}
