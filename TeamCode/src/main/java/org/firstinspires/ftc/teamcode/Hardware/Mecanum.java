package org.firstinspires.ftc.teamcode.Hardware;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_WITHOUT_ENCODER;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER;
import static org.firstinspires.ftc.teamcode.Utilities.MathUtils.angleMode.DEGREES;
import static org.firstinspires.ftc.teamcode.Utilities.MathUtils.cos;
import static org.firstinspires.ftc.teamcode.Utilities.MathUtils.sin;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.isActive;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.telemetry;
import static org.firstinspires.ftc.teamcode.Utilities.PIDWeights.derivativeWeight;
import static org.firstinspires.ftc.teamcode.Utilities.PIDWeights.integralWeight;
import static org.firstinspires.ftc.teamcode.Utilities.PIDWeights.proportionalWeight;

import com.arcrobotics.ftclib.command.OdometrySubsystem;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.kinematics.HolonomicOdometry;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.wolfpackmachina.bettersensors.Sensors.Gyro;

import org.firstinspires.ftc.teamcode.DashConstants.Dash_Odometry;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.Color_Sensor;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.IMU;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.Utilities.PID;
import org.opencv.core.Point;

public class Mecanum {
    /**
     * Initializes the robot's necessary subsystems and motors
     */
    //constructor
    public Mecanum(){
        initMecanum();
    }

    private ElapsedTime timeOut = new ElapsedTime();

    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;
    public MotorEx encoderLeft;
    public MotorEx encoderRight;
    public MotorEx encoderPerp;
    PID rotationalPID;





    public void initMecanum(){

        fl = hardwareMap.get(DcMotor.class, "fl");
        fl.setDirection(DcMotorSimple.Direction.REVERSE);

        fr = hardwareMap.get(DcMotor.class, "fr");
        fr.setDirection(DcMotorSimple.Direction.FORWARD);

        bl = hardwareMap.get(DcMotor.class, "bl");
        bl.setDirection(DcMotorSimple.Direction.REVERSE);

        br = hardwareMap.get(DcMotor.class, "br");
        br.setDirection(DcMotorSimple.Direction.FORWARD);

        encoderLeft = new MotorEx(hardwareMap, "left_encoder");
        encoderRight = new MotorEx(hardwareMap, "right_encoder");
        encoderPerp = new MotorEx(hardwareMap, "center_encoder");

        rotationalPID = new PID(proportionalWeight, integralWeight , derivativeWeight);







    }


    /**
     * Reset the DcMotors using the setMode() method and the STOP_AND_RESET_ENCODERS method
     */
    public void resetMotors(){

        // Example of reseting a motor encoder
        br.setMode(STOP_AND_RESET_ENCODER);
        bl.setMode(STOP_AND_RESET_ENCODER);
        fr.setMode(STOP_AND_RESET_ENCODER);
        fl.setMode(STOP_AND_RESET_ENCODER);

        fr.setMode(RUN_WITHOUT_ENCODER);
        fl.setMode(RUN_WITHOUT_ENCODER);
        br.setMode(RUN_WITHOUT_ENCODER);
        bl.setMode(RUN_WITHOUT_ENCODER);

        // You need to complete these for each of the fr, fl, br, bl motors
    }

    /**
     *
     * @return average of all motor's getCurrentPosition() but each one is absolute valued
     */
    public double getPosition(){
        return (Math.abs(fr.getCurrentPosition()) + Math.abs(fl.getCurrentPosition()) + Math.abs(br.getCurrentPosition()) + Math.abs(bl.getCurrentPosition()))/4.0;
    }

    public Point getPosition(boolean point){
        double yDist = (fr.getCurrentPosition() + fl.getCurrentPosition() + br.getCurrentPosition() + bl.getCurrentPosition()) / 4.0;
        double xDist = (fl.getCurrentPosition() - fr.getCurrentPosition() + br.getCurrentPosition() - bl.getCurrentPosition()) / 4.0;
        return new Point(xDist, yDist);
    }


    /**
     * Sets the same power to all four motors
     * @param power
     */
    public void setAllPower(double power){
        /*

                Y O U R   C O D E   H E R E

         */
        fl.setPower(power);
        fr.setPower(power);
        bl.setPower(power);
        br.setPower(power);
    }

    /**
     * Sets the power to the motors for driving with a mecanum drivetrain
     * @param power
     */
    public void setDrivePower(double drive, double strafe, double turn, double power){
        /*

                Y O U R   C O D E   H E R E

         */
        double frPower = (drive - strafe - turn) * power;
        double flPower = (drive + strafe + turn) * power;
        double brPower = (drive + strafe - turn) * power;
        double blPower = (drive - strafe + turn) * power;

        fl.setPower(flPower);
        fr.setPower(frPower);
        bl.setPower(blPower);
        br.setPower(brPower);
    }


    /**
     * Translates the robot autonomously a certain distance known as ticks
     * @param ticks
     */
    public void strafe(double power, double ticks, double targetAngle, double strafeAngle, IMU gyro, double marginOfError, double acel, double decel){

        // Reset our encoders to 0
        resetMotors();
        timeOut.reset();

        strafeAngle = strafeAngle - 90;

        targetAngle = MathUtils.closestAngle(targetAngle, gyro.getAngle());

        // Calculate our x and y powers
        double xPower = cos(strafeAngle, DEGREES);
        double yPower = sin(strafeAngle, DEGREES);

        // Calculate the distances we need to travel
        double xDist = xPower * ticks;
        double yDist = yPower * ticks;



        // Initialize our current position variables
        Point curPos;
        double curHDist = 0;
        double distanceTraveled;

        while ((curHDist < ticks || gyro.absAngularDist(targetAngle) > marginOfError) && timeOut.seconds() < ticks/500){
            curPos = getPosition(true);
            distanceTraveled = getPosition();

            curHDist = Math.hypot(curPos.x, curPos.y);
            Point shiftedPowers = MathUtils.shift(new Point(xPower, yPower), -gyro.getAngle());

            //sets the power based on an aceleration program
            double currentPower = powerRamp(distanceTraveled, ticks - distanceTraveled, power, acel, decel);

            if(curHDist < ticks){

                setDrivePower(-shiftedPowers.y, -shiftedPowers.x, rotationalPID.update(gyro.getAngle()-targetAngle, false), currentPower);
            }else{
                setDrivePower(0, 0, rotationalPID.update(gyro.getAngle()-targetAngle,false), currentPower);
            }

            multTelemetry.addData("power", currentPower);
            multTelemetry.update();

        }
        setAllPower(0);
    }

    public void strafe(double power, double ticks, double targetAngle, double strafeAngle, IMU gyro){
        strafe(power, ticks, targetAngle, strafeAngle,  gyro, 6, 0.8, 0.5);
    }


    public void foreverDriveStraight(double power, double targetAngle, IMU gyro) {
        setDrivePower(power, 0, rotationalPID.update(gyro.getAngle() - targetAngle, false), 1);
        multTelemetry.addData("angle", gyro.getAngle());
        multTelemetry.addData("target", targetAngle);
        multTelemetry.addData("angle - target", gyro.getAngle() - targetAngle);
        multTelemetry.update();

    }



    public double powerRamp(double distanceTraveled, double distanceFromFinish, double MaxSpeed, double acel, double decel){
        distanceTraveled = distanceTraveled + 0.0001;
        distanceFromFinish = distanceFromFinish + 0.0001;
        double aceleration = (Math.sqrt(distanceTraveled)*acel) + 0.2;
        double deceleration = (Math.sqrt(distanceFromFinish)*decel) + 0.2;
        return Math.min(aceleration, Math.min(deceleration, MaxSpeed));
    }



    /**
     * Rotates the robot autonomously a certain number of degrees with a margin of error
     * @param degrees
     * @param currentAngle
     */
    public void turn(double degrees, IMU currentAngle){
        ElapsedTime timer = new ElapsedTime();
        double targetAngle = MathUtils.closestAngle(degrees, currentAngle.getAngle());

        while(timer.seconds() < 1.5){
            setDrivePower(0.0, 0.0, -rotationalPID.update(targetAngle - currentAngle.getAngle(), true), 1.0);
        }
        setAllPower(0.0);
        /*

                Y O U R   C O D E   H E R E

         */

    }

    //Odometry code, Don't call this, instead call the Waypoint constructor
    // define our trackwidth
    public void
    static final double TRACKWIDTH = 13.7;

    // convert ticks to inches
    static final double TICKS_TO_INCHES = 15.3;

    // create our encoders
    MotorEx encoderLeft, encoderRight;
    encoderLeft = new MotorEx(hardwareMap, "left_encoder");
    encoderRight = new MotorEx(hardwareMap, "right_encoder");

    // create our odometry
    DifferentialOdometry diffOdom = new DifferentialOdometry(
            () -> encoderLeft.getCurrentPosition() * TICKS_TO_INCHES,
            () -> encoderRight.getCurrentPosition() * TICKS_TO_INCHES,
            TRACKWIDTH
    );

// update the initial position
diffOdom.updatePose(new Pose2d(1, 2, 0));

// control loop
while (!isStopRequested()) {
        /* implementation */

        // update the position
        diffOdom.updatePose();
    }

}