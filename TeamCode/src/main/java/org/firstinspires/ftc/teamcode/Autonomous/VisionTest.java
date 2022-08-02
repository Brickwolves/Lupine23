package org.firstinspires.ftc.teamcode.Autonomous;

import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.CAMERA_OFFSET;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.Robot;
import org.firstinspires.ftc.teamcode.Utilities.Loggers.Side;


@Autonomous(name="VisionTest", group="Autonomous Linear Opmode")
public class VisionTest extends LinearOpMode
{
    // Declare OpMode members.
    public Robot robot;
    private ElapsedTime runtime = new ElapsedTime();



    public void initialize(){
        setOpMode(this);
        robot = new Robot();

        Side.setBlue();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void runOpMode()
    {

        initialize();

        multTelemetry.addLine("Waiting for start");
        multTelemetry.update();

        waitForStart();

        while (opModeIsActive()){

            double distance2Duck = robot.camera.pipeline.distanceToDuck();
            double setPoint = CAMERA_OFFSET + robot.camera.pipeline.degreeError2Duck() + robot.gyro.getAngle(); // calculates distance between robot's current angle and the angle it needs to go to
            double correction = robot.drivetrain.rotationalPID.update(robot.gyro.getAngle() - setPoint, true); //turns robot so that degreeError2Duck() = 0 (robot is exactly on target)

            robot.drivetrain.setDrivePower(0, 0, correction, 0.8);


            multTelemetry.addData("Distance2Duck", distance2Duck);
            multTelemetry.addData("SetPoint", setPoint);
            multTelemetry.addData("Angle", robot.gyro.getAngle());
            multTelemetry.update();

        }
   }
}
