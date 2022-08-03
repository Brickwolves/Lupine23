package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.CAMERA_OFFSET;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.DEGREE_RANGE;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.isActive;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Vision.DuckPipeline.isDuckFound;

import org.firstinspires.ftc.teamcode.Hardware.Sensors.Camera;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.IMU;

/**
 * A class for containing an FTC Mecanum robot
 */
public class Robot {

   public static ElapsedTime time = new ElapsedTime();

   public Intake intake;
   public Mecanum drivetrain;
   public Grabber grabber;
   public IMU gyro;
   public Dropper dropper;
   public DuckSpinner duck;
   public Camera camera;
   public Robot(){
      initRobot();
   }

   public void initRobot() {

      /*
            I N I T   M O T O R S
       */

      //initialized Mecanum
      //intake = new Intake();
      //grabber = new Grabber("rightgrabber", "leftgrabber");
      //dropper = new Dropper("rightdropper", "leftdropper");
      drivetrain = new Mecanum();
      duck = new DuckSpinner("duck");
      camera = new Camera("webcam");

      gyro = new IMU( "imu");


      multTelemetry.addData("Status", "Initialized");
      multTelemetry.update();
   }

   /**
    * a method that will orient the robot in the direction of the duck for autonomous intake
    * will attempt to find the duck if it is out of the camera's FOV by turning
    * @return
    */
   //find duck, turn to duck, lock onto duck
   public void orientToDuck(){
      ElapsedTime timer = new ElapsedTime();
      timer.reset(); //timer is 0
      double theta = gyro.getAngle();
      boolean reachedAng1 = false;
      boolean reachedAng2 = false;
      double angle1 = theta + DEGREE_RANGE; // can modify thru dashboard
      double angle2 = theta - DEGREE_RANGE;
      double degreeMOE = 3;
      double timeout = 3;

      while (isActive() && timer.seconds() < timeout){
         if (isDuckFound){ //if the camera sees the duck
            timeout = 3;
            //setPoint = target angle
            double setPoint = CAMERA_OFFSET + camera.pipeline.degreeError2Duck() + gyro.getAngle();
            //calculates the difference between robot's current angle and target angle
            //finds how much farther robot has to turn
            double correction = drivetrain.rotationalPID.update(gyro.getAngle() - setPoint, true);
            // turns the robot so that degreeError2Duck = 0 (robot is exactly on target)
            drivetrain.setDrivePower(0, 0, correction, 0.8);

            multTelemetry.addData("SetPoint", setPoint);
            multTelemetry.addData("Correction", correction);
            multTelemetry.addData("isDuckFound", isDuckFound);

         } else { //if the camera does not see the duck
            double currentAngle = gyro.getAngle();
            if (!reachedAng1){ // if the robot has not reached angle 1 yet
               drivetrain.setDrivePower(0, 0, 0.3, -1);
               timeout = 5;
            }

            if (currentAngle < (angle1 + degreeMOE) && currentAngle > (angle1 - degreeMOE)){ //if the robot has reached (around) angle 1 and still no duck
               reachedAng1 = true;
               timeout = 9;
            }

            if (reachedAng1 && !reachedAng2){ //if the robot has reached angle 1 but not angle 2 and still no duck
               drivetrain.setDrivePower(0, 0, 0.3, 1); //spin opposite direction
            }

            if (currentAngle > (angle2 - degreeMOE) && currentAngle < (angle2 + degreeMOE)){ // if the robot has reached (around) angle 2 and still no duck
               reachedAng2 = true;
            }

            if (reachedAng1 && reachedAng2){ // if the robot has reached both angle 1 and angle 2 and still no duck
               drivetrain.setDrivePower(0, 0, 0.3, 1); //start spinning in a circle
            }
         }
      }
      drivetrain.setDrivePower(0, 0, 0, 0); //stop moving, we didn't find the duckie :(
   }

   //a method that prompts the robot to drive to and intake the duck
   //NOTE: avery's bot doesn't have an intake so as of now it just drives to the duck
   public void intakeDuck(){
      double distance2Duck = camera.pipeline.distanceToDuck();
      if (isDuckFound){
         drivetrain.strafe(distance2Duck * 41.5, 0.1, 0.1, 0.5, gyro, true); //drives to duck
      }
   }

}