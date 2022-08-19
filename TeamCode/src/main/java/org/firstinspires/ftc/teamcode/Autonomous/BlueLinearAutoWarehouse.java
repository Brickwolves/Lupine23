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


@Autonomous(name="Warehouse Blue", group="Autonomous Linear Opmode")
public class BlueLinearAutoWarehouse extends LinearOpMode {
    Robot robot;
    //Camera camera; //declare the camera
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    public void initialize(){
        setOpMode(this);
        Side.setBlue();

        //camera = new Camera("webcam"); //camera starts streaming
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

        if (opModeIsActive()){
//
//            if(currentDuckPos == Dash_Vision.DuckPosition.L_BARCODE){
//                robot.scorer.autoLow();
//                robot.drivetrain.strafe(.8,700,0,90, robot.gyro);
//                robot.drivetrain.strafe(.4,200,350,180, robot.gyro);
//
//            }else{
                robot.scorer.autoHigh();
                robot.drivetrain.strafe(.8,900,0,90, robot.gyro);
                robot.drivetrain.strafe(.4,250,350,180, robot.gyro);


 //           }
            robot.sleep(.5);
            robot.scorer.autoDeposit();
            robot.drivetrain.strafe(.8, 900, 270,0,robot.gyro);

            robot.drivetrain.strafe(.4,900,270,270,robot.gyro);
            robot.drivetrain.strafe(.4,600,180,270,robot.gyro);
//            robot.cycle(1);
//            robot.drivetrain.strafe(.8,650,270,0,robot.gyro);
//            robot.cycle(2);
//            robot.drivetrain.strafe(.8,650,270,0,robot.gyro);
//            robot.drivetrain.strafe(.8,700,270,260, robot.gyro);
            IMU_DATUM = 0;
        }
    }
}

