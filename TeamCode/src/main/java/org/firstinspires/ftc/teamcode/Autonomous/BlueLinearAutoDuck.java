package org.firstinspires.ftc.teamcode.Autonomous;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.currentDuckPos;
import static org.firstinspires.ftc.teamcode.Utilities.Constants.IMU_DATUM;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision;
import org.firstinspires.ftc.teamcode.Hardware.Robot;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.Camera;
import org.firstinspires.ftc.teamcode.Utilities.Loggers.Side;


@Autonomous(name="Duck Blue", group="Autonomous Linear Opmode")
public class BlueLinearAutoDuck extends LinearOpMode
{
    Robot robot;
    Camera camera; //declare the camera
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    boolean left = false;

    public void initialize(){
        setOpMode(this);
        Side.setBlue();

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
        multTelemetry.addData("Duck Pos", currentDuckPos);
        multTelemetry.update();

        robot = new Robot();


        waitForStart();

        if (opModeIsActive()) {

            //if the duck is on the left
            if (currentDuckPos == Dash_Vision.DuckPosition.L_BARCODE) {
                left = true;
                robot.scorer.autoLow();
                robot.drivetrain.strafe(.8, 500, 0, 270, robot.gyro);
                robot.drivetrain.strafe(.4, 250, 0, 180, robot.gyro);

            } else {
                left = false;
                robot.scorer.autoHigh();
                robot.drivetrain.strafe(.8, 500, 0, 270, robot.gyro);
                robot.drivetrain.strafe(.4, 300, 0, 180, robot.gyro);
            }
            robot.sleep(.5);
            robot.scorer.autoDeposit();

            robot.drivetrain.strafe(.4,200,0,0,robot.gyro);
            robot.drivetrain.strafe(.6, 2000, 0, 100, robot.gyro, 6, 0.5, 0.1);
            robot.drivetrain.strafe(.4, 200, 0, 0, robot.gyro);
            robot.drivetrain.strafe(.1, 200, 0, 0, robot.gyro);
            robot.duck.backspin();
            robot.drivetrain.strafe(.1,50,0,0,robot.gyro);
            robot.sleep(3);
            robot.duck.stop();
            if (left) {
                robot.drivetrain.strafe(.5, 600, 0, 180, robot.gyro);
            }else{
                robot.drivetrain.strafe(.5, 180, 0, 180, robot.gyro);

            }
            IMU_DATUM = robot.gyro.getAngle();
        }
    }
}

