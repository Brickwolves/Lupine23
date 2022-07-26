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


@Autonomous(name="LinearAuto", group="Autonomous Linear Opmode")
public class LinearAuto extends LinearOpMode
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
            greg.drivetrain.strafe(1000, 0.007, 0.01, 1);

            /*
                    Y O U R   C O D E   H E R E
                                                   */

        }
   }
}
