package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.Sensors.IMU;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;

/**
 * A class for containing an FTC Mecanum robot
 */
public class Robot {

   public static ElapsedTime time = new ElapsedTime();

   public Robot(){
      initRobot();
   }

   /**
    * Initializes the robot's necessary subsystems and motors
    */
   public void initRobot() {

      /*
            I N I T   M O T O R S
       */


      multTelemetry.addData("Status", "Initialized");
      multTelemetry.update();
   }

   /**
    * Sets the same power to all four motors
    * @param power
    */
   public void setAllPower(double power){
        /*

                Y O U R   C O D E   H E R E

         */
   }

   /**
    * Sets the power to the motors for driving with a mecanum drivetrain
    * @param power
    */
   public void setDrivePower(double drive, double strafe, double turn, double power){
        /*

                Y O U R   C O D E   H E R E

         */
   }


   /**
    * Translates the robot autonomously a certain distance known as ticks
    * @param ticks
    */
   public void strafe(double ticks){
        /*

                Y O U R   C O D E   H E R E

         */
   }

   /**
    * Rotates the robot autonomously a certain number of degrees with a margin of error
    * @param degrees
    * @param moe
    */
   public void turn(double degrees, double moe){
        /*

                Y O U R   C O D E   H E R E

         */
   }


   /**
    * A mathematical function that optimizes the ramping of power to the motors during autonomous
    * strafes.
    * @param position
    * @param distance
    * @param acceleration
    * @return the coefficient [0, 1] of our power
    */
   public static double powerRamp(double position, double distance, double acceleration){
        /*

                Y O U R   C O D E   H E R E

         */
      return 0;
   }
}