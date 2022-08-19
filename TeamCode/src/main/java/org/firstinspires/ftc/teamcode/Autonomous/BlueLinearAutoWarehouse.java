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


@Autonomous(name="Warehouse Blue", group="Autonomous Linear Opmode")
public class BlueLinearAutoWarehouse extends LinearOpMode
{
    Robot robot;
    Camera camera; //declare the camera
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    public void initialize(){
        setOpMode(this);
        Side.setBlue();

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

            //if the duck is on the left barcode
            if (currentDuckPos == Dash_Vision.DuckPosition.LEFT_BARCODE){

            } else {

            }
        }
    }
}

