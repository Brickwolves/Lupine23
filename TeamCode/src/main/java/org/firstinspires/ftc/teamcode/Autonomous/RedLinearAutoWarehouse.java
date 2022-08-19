package org.firstinspires.ftc.teamcode.Autonomous;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.DashConstants.Dash_AutoDistances.angle1;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_AutoDistances.dist1;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_AutoDistances.dist2;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.currentDuckPos;
import static org.firstinspires.ftc.teamcode.DashConstants.DuckPos.left;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision;
import org.firstinspires.ftc.teamcode.Hardware.Robot;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.Camera;
import org.firstinspires.ftc.teamcode.Utilities.Loggers.Side;


@Autonomous(name="Warehouse Red", group="Autonomous Linear Opmode")
public class RedLinearAutoWarehouse extends LinearOpMode {
    Robot robot;
    Camera camera; //declare the camera
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    public void initialize(){
        setOpMode(this);
        Side.setRed();

        camera = new Camera("webcam"); //camera starts streaming
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void runOpMode()
    {

        initialize();

        multTelemetry.addLine("Waiting for start");
        multTelemetry.update();

        robot = new Robot();


        waitForStart();

        if (opModeIsActive()){

            if(currentDuckPos == Dash_Vision.DuckPosition.LEFT_BARCODE){
                robot.scorer.autoLow();
                robot.drivetrain.strafe(.8,500,0,270, robot.gyro);
                robot.drivetrain.strafe(.4,270,10,180, robot.gyro);

            }else{
                robot.scorer.autoHigh();
                robot.drivetrain.strafe(.8,500,0,270, robot.gyro);
                robot.drivetrain.strafe(.4,320,10,180, robot.gyro);


            }
            robot.sleep(.5);
            robot.scorer.autoDeposit();
            robot.drivetrain.strafe(.8, 700, 90,0,robot.gyro);
            robot.cycle(1);
            robot.drivetrain.strafe(.8,500,90,0,robot.gyro);
            robot.cycle(2);
            robot.drivetrain.strafe(.8,500,90,0,robot.gyro);
            robot.drivetrain.strafe(.8,1000,90,80, robot.gyro);
            robot.drivetrain.strafe(.6,200,180,180, robot.gyro, 1);

        }
    }
}

