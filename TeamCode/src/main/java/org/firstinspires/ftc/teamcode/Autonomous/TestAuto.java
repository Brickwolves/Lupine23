package org.firstinspires.ftc.teamcode.Autonomous;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.kinematics.HolonomicOdometry;
import com.arcrobotics.ftclib.purepursuit.Waypoint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.currentDuckPos;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import org.firstinspires.ftc.teamcode.DashConstants.Dash_Odometry;
import org.firstinspires.ftc.teamcode.Hardware.Mecanum;
import org.firstinspires.ftc.teamcode.Hardware.Robot;
import org.firstinspires.ftc.teamcode.Utilities.Loggers.Side;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;

import java.util.function.DoubleSupplier;


@Autonomous(name="Test Auto", group="Autonomous Linear Opmode")
public class TestAuto extends LinearOpMode {
    Robot robot;
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    public void initialize(){
        setOpMode(this);
        Side.setBlue();

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
        DoubleSupplier leftValue, rightValue, horizontalValue;

        leftValue = () -> MathUtils.convertTicks2Inches(robot.drivetrain.encoderLeft.getCurrentPosition());
        rightValue = () -> MathUtils.convertTicks2Inches(robot.drivetrain.encoderRight.getCurrentPosition());
        horizontalValue = () -> MathUtils.convertTicks2Inches(robot.drivetrain.encoderPerp.getCurrentPosition());
        HolonomicOdometry m_robotOdometry = new HolonomicOdometry(
                leftValue,rightValue,horizontalValue, Dash_Odometry.TRACKWIDTH,Dash_Odometry.CENTER_WHEEL_OFFSET
        );


        waitForStart();

        if (opModeIsActive()){
            multTelemetry.addData("Position", m_robotOdometry.getPose());
            m_robotOdometry.updatePose();
            multTelemetry.update();

        }
    }
}

