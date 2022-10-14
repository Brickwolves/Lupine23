package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.DashConstants.PositionsAndSpeeds.coneBlue;
import static org.firstinspires.ftc.teamcode.DashConstants.PositionsAndSpeeds.coneRed;
import static org.firstinspires.ftc.teamcode.DashConstants.PositionsAndSpeeds.depositDrop;
import static org.firstinspires.ftc.teamcode.DashConstants.PositionsAndSpeeds.groundJunction;
import static org.firstinspires.ftc.teamcode.DashConstants.PositionsAndSpeeds.highJunction;
import static org.firstinspires.ftc.teamcode.DashConstants.PositionsAndSpeeds.intakeSpeed;
import static org.firstinspires.ftc.teamcode.DashConstants.PositionsAndSpeeds.lowJunction;
import static org.firstinspires.ftc.teamcode.DashConstants.PositionsAndSpeeds.midJunction;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.Sensors.Color_Sensor;
import org.firstinspires.ftc.teamcode.Utilities.Loggers.Side;

public class Grabber {


    public CRServo grab1;
    public CRServo grab2;
    public DcMotor spool;
    public Servo squeezer;
    public Color_Sensor grabColor;

    public boolean wentDown = false;

    public ElapsedTime time = new ElapsedTime();
//    public Color_Sensor grabColor1;
//    public Color_Sensor grabColor2;
//    public DistanceSensor grabDistance;


    public Grabber(){
        grab1 = hardwareMap.get(CRServo.class, "grab1");
        grab2 = hardwareMap.get(CRServo.class, "grab2");

        squeezer = hardwareMap.get(Servo.class, "squeeze");
        squeezer.setPosition(0);

        grabColor = new Color_Sensor();
        grabColor.init("grabColor");

        spool = hardwareMap.get(DcMotor.class, "spool");
        spool.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        spool.setTargetPosition(80);
        spool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        spool.setPower(.2);

//        grabColor1 = new Color_Sensor();
//        grabColor2 = new Color_Sensor();

    }

    public boolean isLoaded(){
        if(Side.red) {
            return grabColor.updateRed() > coneRed;
        }else{
            return grabColor.updateBlue() > coneBlue;
        }
    }

    public void intake(){
        squeezer.setPosition(.1);

        if(!isLoaded()) {
            grab1.setPower(-intakeSpeed);
            grab2.setPower(intakeSpeed);
        }else{
            stopIntake();
        }
    }

    public void stopIntake(){
        grab1.setPower(0);
        grab2.setPower(0);
    }

    public void runIntakeBackwards(){
        grab1.setPower(1);
        grab2.setPower(-1);
    }

    public void high(){
        spool.setPower(.8);
        spool.setTargetPosition(highJunction);
    }

    public void middle(){
        spool.setPower(.8);
        spool.setTargetPosition(midJunction);

    }

    public void low(){
        spool.setPower(.4);
        spool.setTargetPosition(lowJunction);
    }

    public void ground(){
        spool.setPower(.3);
        spool.setTargetPosition(groundJunction);
    }

    public void deposit() {
        spool.setPower(.5);
        if(!wentDown) {
            spool.setTargetPosition((spool.getCurrentPosition() - depositDrop));
            wentDown = true;
        }
        if(time.seconds() > .6) {
            squeezer.setPosition(.5);
        }
    }

    public void down(){
        spool.setPower(1);
        spool.setTargetPosition(80);
    }





}