package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.DashConstants.PositionsAndSpeeds.duckSpeed;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import com.qualcomm.robotcore.hardware.CRServo;

public class DuckSpinner {
    String id;
    CRServo duck;


    public DuckSpinner(String id){
        duck = hardwareMap.get(CRServo.class, "duck");
    }

    public void spin(){
        duck.setPower(duckSpeed);
    }
    public void backspin(){duck.setPower(-duckSpeed);}
    public void stop(){duck.setPower(0);}




}
