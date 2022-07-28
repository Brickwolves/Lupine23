package org.firstinspires.ftc.teamcode.Autonomous;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import org.firstinspires.ftc.teamcode.Hardware.Robot;


@Autonomous(name="BlueLinearAuto", group="Autonomous Linear Opmode")
public class BlueLinearAuto extends LinearOpMode
{
    Robot greg;
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    public void initialize(){
        setOpMode(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void runOpMode()
    {

        initialize();

        multTelemetry.addLine("Waiting for start");
        multTelemetry.update();

        greg = new Robot();


        waitForStart();

        if (opModeIsActive()){

            greg.drivetrain.strafe(50, 0.007, 0.01, 1, greg.gyro, true);
            greg.drivetrain.turn(90, greg.gyro);
            greg.drivetrain.strafe(-350, 0.007, 0.01, 1, greg.gyro, true);
            ElapsedTime timer = new ElapsedTime();
            while(timer.seconds() < 4){
                greg.duck.spin(-1);
            }
            greg.drivetrain.strafe(800, 0.007, 0.01, 1, greg.gyro, false);
            greg.drivetrain.strafe(-350, 0.007, 0.01, 1, greg.gyro, true);
            greg.drivetrain.strafe(-30, 0.007, 0.01, 1, greg.gyro, false);


            /*
                    Y O U R   C O D E   H E R E
                                                   */

        }
   }
}
