package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.DashConstants.PostitionsAndSpeeds.duckSpeed;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.Utilities.DuckSpeed;

public class DuckSpinner {
    String id;
    CRServo duck;


    public DuckSpinner(String id){
        duck = hardwareMap.get(CRServo.class, "duck");
    }

    public void spin(){
        duck.setPower(duckSpeed);
    }




}
