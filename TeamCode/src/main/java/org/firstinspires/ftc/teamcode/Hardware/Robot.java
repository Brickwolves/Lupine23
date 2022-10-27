package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;

import org.firstinspires.ftc.teamcode.Hardware.Sensors.IMU;

import java.lang.reflect.GenericArrayType;

/**
 * A class for containing an FTC Mecanum robot
 */
public class Robot {

   public IMU gyro;


   public Robot() {
      initRobot();
   }

   public void initRobot() {

      gyro = new IMU("imu");


      multTelemetry.addData("Status", "Initialized");
      multTelemetry.update();
   }

}