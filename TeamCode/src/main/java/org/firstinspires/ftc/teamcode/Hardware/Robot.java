package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;

import org.firstinspires.ftc.teamcode.Hardware.Sensors.Camera;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.IMU;

/**
 * A class for containing an FTC Mecanum robot
 */
public class Robot {

   public Sweeper sweeper;
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
      sweeper = new Sweeper("sweeper");
      grabber = new Grabber("rightgrabber", "leftgrabber");
      dropper = new Dropper("rightdropper", "leftdropper");
      drivetrain = new Mecanum();
      duck = new DuckSpinner("duck");
      camera = new Camera("webcam");

      gyro = new IMU( "imu");


      multTelemetry.addData("Status", "Initialized");
      multTelemetry.update();
   }


}