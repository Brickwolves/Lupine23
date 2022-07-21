package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

public class Dropper {
    String rID;
    String lID;
    Servo leftDropper;
    Servo rightDropper;

    public Dropper(String rID, String lID){
        leftDropper = hardwareMap.get(Servo.class, lID);
        rightDropper = hardwareMap.get(Servo.class, rID);
    }

    public void drop(double lPosistion, double rPosistion){
        leftDropper.setPosition(lPosistion); // 0 0.35
        rightDropper.setPosition(rPosistion); // .97 .85
    }
    /*

        Y O U R    C O D E    H E R E

     */
}
