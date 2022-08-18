package org.firstinspires.ftc.teamcode.Autonomous;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.DashConstants.DuckPos.left;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import org.firstinspires.ftc.teamcode.Hardware.Robot;
import org.firstinspires.ftc.teamcode.Utilities.Loggers.Side;


@Autonomous(name="Duck Red", group="Autonomous Linear Opmode")
public class RedLinearAutoDuck extends LinearOpMode
{
    Robot robot;
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    public void initialize(){
        setOpMode(this);
        Side.setRed();
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
            if(left){
                robot.scorer.autoLow();
                robot.drivetrain.strafe(.8,830,0,90, robot.gyro);
                robot.drivetrain.strafe(.4,280,0,180, robot.gyro);

            }else{
                robot.scorer.autoHigh();
                robot.drivetrain.strafe(.8,800,0,90, robot.gyro);
                robot.drivetrain.strafe(.4,300,0,180, robot.gyro);
            }
            robot.sleep(.5);
            robot.scorer.autoDeposit();
            robot.drivetrain.strafe(.8,400,315,315,robot.gyro);
            robot.drivetrain.strafe(.5,1000,315,270,robot.gyro);
        }
    }
}

