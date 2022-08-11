package org.firstinspires.ftc.teamcode.Autonomous;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.currentDuckPos;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import org.firstinspires.ftc.teamcode.Hardware.Robot;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.BackCamera;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.Cameras;
import org.firstinspires.ftc.teamcode.Utilities.Loggers.Side;
import org.opencv.core.Mat;


@Autonomous(name="Warehouse Red", group="Autonomous Linear Opmode")
public class RedLinearAutoWarehouse extends LinearOpMode
{
    Robot robot;
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    public void initialize(){
        setOpMode(this);
        Side.setRed();
        robot = new Robot();
        Mat input = new Mat();
        robot.cameras.bcam.back_pipeline.processFrame(input);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void runOpMode()
    {


        initialize();

        multTelemetry.addLine("Waiting for start");
        multTelemetry.addData("Duck Position", currentDuckPos);
        multTelemetry.update();

        robot = new Robot();


        waitForStart();

        if (opModeIsActive()){


            /*
                    Y O U R   C O D E   H E R E
                                                   */

        }
    }
}

