package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class OdoWheels {
    public CRServo forwardOdoWheel;
    public CRServo sidewaysOdoWheel;
    public ElapsedTime time = new ElapsedTime();

    public OdoWheels(){
        forwardOdoWheel = hardwareMap.get(CRServo.class, "fowardOdoWheel");
        sidewaysOdoWheel = hardwareMap.get(CRServo.class, "sideOdoWheel");
    }

    public void raiseWheels(){
        if (time.seconds() < 2.0) {
            forwardOdoWheel.setPower(1.0);
            sidewaysOdoWheel.setPower(1.0);
        }
        else{
            forwardOdoWheel.setPower(0.0);
            sidewaysOdoWheel.setPower(0.0);
        }
    }
}
