package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.DashConstants.Dash_AutoDistances.angle1;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_AutoDistances.angle2;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_AutoDistances.dist1;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_AutoDistances.dist2;
import static org.firstinspires.ftc.teamcode.Utilities.Loggers.Side.red;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.Sensors.Camera;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.IMU;

/**
 * A class for containing an FTC Mecanum robot
 */
public class Robot {

   public Intake intake;
   public Mecanum drivetrain;
   //public OdoWheels odoWheels;
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
      //odoWheels = new OdoWheels();
      intake = new Intake();
      drivetrain = new Mecanum();
      duck = new DuckSpinner("duck");
      scorer = new Scoring();

      gyro = new IMU("imu");


      multTelemetry.addData("Status", "Initialized");
      multTelemetry.update();
   }

   public void cycle(int cycleNo) {

//RED

      if (red) {
         loopTimer1.reset();

         //drive forward a bit
         drivetrain.strafe(.7,600,90,80,gyro);



         //while bucket isn't loaded
         while(!scorer.isLoaded()) {
            loopTimer1.reset();
            intake.updateEncoders();
            intake.runIntake();
            //if bucket isn't loaded and it hasn't been three seconds drive forward with intake on -ADD INTAKE JAM MAYBE
            while (!scorer.isLoaded() && loopTimer1.seconds() < 3) {
               drivetrain.foreverDriveStraight(.3, 90, gyro);

            }
            //if any of these conditions happen check if it's the bucket one, if it is break loop
            if(scorer.isLoaded()){
               break;
            }
            //if it wasn't loaded then backup
            intake.runIntakeBackwards();
            drivetrain.strafe(.7,300,90,270,gyro);
            //and start over
            multTelemetry.addData("isLoaded", scorer.isLoaded());
            multTelemetry.addData("intake Jammed", intake.jammed());
            multTelemetry.update();
         }

         //Loaded now, intake backwards and reverse
         intake.runIntakeBackwards();
         drivetrain.strafe(.6,275,90,310,gyro);
         //Crossing White Line
         loopTimer1.reset();
         while(drivetrain.brColor.updateRed() < 80 && drivetrain.blColor.updateRed() < 80){
            drivetrain.foreverDriveStraight(-.2,90, gyro);
         }
         drivetrain.strafe(.6,400,90,280,gyro);
         scorer.autoHigh();
         drivetrain.strafe(.6,200, 90,200, gyro);
         drivetrain.strafe(.6,350, 10,200, gyro);
         scorer.autoDeposit();




      }
   }

   public void sleep(double seconds){
      sleepTime.reset();
      while (sleepTime.seconds() <  seconds){

      }

   }
}