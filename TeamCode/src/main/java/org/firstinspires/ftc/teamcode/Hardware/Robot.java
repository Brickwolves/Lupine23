package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.DashConstants.Dash_Vision.CAMERA_OFFSET;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;

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

   // a method that will orient the robot in the direction of the duck for autonomous intake

   /**
    *
    * @param distance2Duck
    * @param setPoint
    * @param correction
    * @param isDuckFound
    * @return
    */
   public double orientToDuck(double distance2Duck, double setPoint, double correction, boolean isDuckFound){
      if (isDuckFound == true){ //if the camera sees the duck
         double distance2Duck = camera.pipeline.distanceToDuck();
         double setPoint = CAMERA_OFFSET + camera.pipeline.degreeError2Duck() + gyro.getAngle(); // calculates distance between robot's current angle and the angle it needs to be at
         double correction = drivetrain.rotationalPID.update(gyro.getAngle() - setPoint, true); // turns the robot so that degreeError2Duck = 0 (robot is exactly on target)
         return drivetrain.getAngle();
      } else if (isDuckFound != true){ //if the camera does not see the duck, it will spin for five seconds until it finds the duck
         //add a wait timer
         ElapsedTime timer = new ElapsedTime();
         timer.reset();
         while (timer.seconds() > 0 && isDuckFound == false){ //robot will spin while timer is still going and the duck has not been found
            drivetrain.turn(360, gyro);
         }

      }

   }

}