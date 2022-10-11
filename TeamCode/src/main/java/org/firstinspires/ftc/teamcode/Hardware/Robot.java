package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;

import org.firstinspires.ftc.teamcode.Hardware.Sensors.IMU;

import java.lang.reflect.GenericArrayType;

/**
 * A class for containing an FTC Mecanum robot
 */
public class Robot {

   public Mecanum drivetrain;
   public IMU gyro;
   public Grabber grabber;


   public Robot() {
      initRobot();
   }

   public void initRobot() {

      drivetrain = new Mecanum();
      grabber = new Grabber();

      gyro = new IMU("imu");


      multTelemetry.addData("Status", "Initialized");
      multTelemetry.update();
   }



}