package org.firstinspires.ftc.teamcode.Autonomous;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.currentDuckPos;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision;
import org.firstinspires.ftc.teamcode.Hardware.Robot;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.Camera;
import org.firstinspires.ftc.teamcode.Utilities.Loggers.Side;


@Autonomous(name="Duck Red", group="Autonomous Linear Opmode")
public class RedLinearAutoDuck extends LinearOpMode
{
    Robot robot;
    Camera camera; //declare the camera
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    boolean left = false;

    public void initialize(){
        setOpMode(this);
        Side.setRed();

        camera = new Camera("webcam"); //camera starts streaming

        multTelemetry.addData("duck position", currentDuckPos);
        multTelemetry.update();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void runOpMode()
    {

        initialize();

        multTelemetry.addLine("Waiting for start");
        multTelemetry.addData("Duck Pos is not", currentDuckPos);
        multTelemetry.update();

        robot = new Robot();


        waitForStart();

        if (opModeIsActive()) {

            //if the duck is on the left
            if (currentDuckPos == Dash_Vision.DuckPosition.L_BARCODE) {
                left = true;
                robot.scorer.autoLow();
                robot.drivetrain.strafe(.8, 800, 0, 90, robot.gyro);
                robot.drivetrain.strafe(.4, 300, 0, 180, robot.gyro);

            } else {
                left = false;
                robot.scorer.autoHigh();
                robot.drivetrain.strafe(.8, 800, 0, 90, robot.gyro);
                robot.drivetrain.strafe(.4, 320, 0, 180, robot.gyro);
            }
            robot.sleep(.5);
            robot.scorer.autoDeposit();

            robot.drivetrain.strafe(.4,200,0,0,robot.gyro);
            robot.drivetrain.strafe(.6, 2000, 0, 260, robot.gyro, 6, 0.5, 0.1);
            robot.drivetrain.strafe(.4, 200, 0, 0, robot.gyro);
            robot.drivetrain.strafe(.1, 200, 0, 0, robot.gyro);
            robot.duck.spin();
            robot.sleep(3);
            robot.duck.stop();
            if (left) {
                robot.drivetrain.strafe(.5, 700, 0, 180, robot.gyro);
            }else{
                robot.drivetrain.strafe(.5, 180, 0, 180, robot.gyro);

            }
        }
    }
}

