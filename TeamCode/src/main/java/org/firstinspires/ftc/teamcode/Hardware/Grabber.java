package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import com.qualcomm.robotcore.hardware.Servo;

public class Grabber {
    String rID;
    String lID;
    Servo leftGrabber;
    Servo rightGrabber;

    public Grabber(String rID, String lID){
        leftGrabber = hardwareMap.get(Servo.class, lID);
        rightGrabber = hardwareMap.get(Servo.class, rID);
    }

    public void grab(double lPosistion, double rPosistion){
        leftGrabber.setPosition(lPosistion); // 0.26 0.7
        rightGrabber.setPosition(rPosistion); // 0.825 0.35
    }
}
