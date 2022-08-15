package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Utilities.Loggers.Side.red;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.Sensors.IMU;

/**
 * A class for containing an FTC Mecanum robot
 */
public class Robot {

   public Intake intake;
   public Mecanum drivetrain;
   public OdoWheels odoWheels;
   public IMU gyro;
   public DuckSpinner duck;
   public Scoring scorer;
   public ElapsedTime loopTimer1 = new ElapsedTime();
   private ElapsedTime sleepTime = new ElapsedTime();

   public Robot() {
      initRobot();
   }

   public void initRobot() {

      /*
            I N I T   M O T O R S
       */

      //initialized Mecanum
      odoWheels = new OdoWheels();
      intake = new Intake();
      drivetrain = new Mecanum();
      duck = new DuckSpinner("duck");
      scorer = new Scoring();
      //camera = new Camera("webcam");

      gyro = new IMU("imu");


      multTelemetry.addData("Status", "Initialized");
      multTelemetry.update();
   }

   public void cycle(int cycleNo) {

//RED

      if (red) {
         loopTimer1.reset();

         //drive forward until it's been 1 second or you see the white line - doesn't actually see the white line often
         while(drivetrain.flColor.updateRed() < 80 && drivetrain.frColor.updateRed() < 80 && loopTimer1.seconds() < 1){

            drivetrain.foreverDriveStraight(.4, 90, gyro);
         }
         intake.runIntake();

         //while bucket isn't loaded
         while(!scorer.isLoaded()) {
            loopTimer1.reset();
            //if bucket isn't loaded and it hasn't been three seconds and the intake isn't jammed drive forward with intake on
            while (!scorer.isLoaded() && loopTimer1.seconds() < 3 && !intake.jammed()) {
               drivetrain.foreverDriveStraight(.3, 90, gyro);
               intake.updateEncoders();
            }
            //if any of these conditions happen check if it's the bucket one, if it is break loop
            if(scorer.isLoaded()){
               break;
            }
            //if it wasn't loaded then backup
            intake.runIntakeBackwards();
            drivetrain.strafe(.6,150,90,270,gyro);
            //and start over
            multTelemetry.addData("isLoaded", scorer.isLoaded());
            multTelemetry.addData("intake Jammed", intake.jammed());
            multTelemetry.update();
         }



      }
   }

   public void sleep(double seconds){
      while (sleepTime.seconds() <  seconds){

      }

   }
}