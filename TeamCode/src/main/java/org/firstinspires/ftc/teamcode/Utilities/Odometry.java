package org.firstinspires.ftc.teamcode.Utilities;

import com.arcrobotics.ftclib.command.OdometrySubsystem;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.kinematics.HolonomicOdometry;

public class Odometry {
    // define our constants
    static final double TRACKWIDTH = 13.7;
    static final double TICKS_TO_INCHES = 15.3;
    static final double CENTER_WHEEL_OFFSET = 2.4;

    // create our encoders
    MotorEx encoderLeft, encoderRight, encoderPerp;
    encoderLeft = new MotorEx(hardwareMap, "left_encoder");
    encoderRight = new MotorEx(hardwareMap, "right_encoder");
    encoderPerp = new MotorEx(hardwareMap, "center_encoder");

    encoderLeft.setDistancePerPulse(TICKS_TO_INCHES);
    encoderRight.setDistancePerPulse(TICKS_TO_INCHES);
    encoderPerp.setDistancePerPulse(TICKS_TO_INCHES);

    // create the odometry object
    HolonomicOdometry holOdom = new HolonomicOdometry(
            encoderLeft::getDistance,
            encoderRight::getDistance,
            encoderPerp::getDistance,
            TRACKWIDTH, CENTER_WHEEL_OFFSET
    );

    // create the odometry subsystem
    OdometrySubsystem odometry = new OdometrySubsystem(holOdom);
}
