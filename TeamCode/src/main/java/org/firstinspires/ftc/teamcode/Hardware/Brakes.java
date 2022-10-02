package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.DashConstants.PositionsAndSpeeds.brakeDownPos;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Brakes {
    
    public boolean up = true;
    public DcMotor brakes;
    
    public Brakes(){
        brakes = hardwareMap.get(DcMotor.class, "brakes");
        brakes.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brakes.setTargetPosition(0);
        brakes.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        brakes.setPower(.2);
    }

    public void change(){
        if(up) {
            brakes.setTargetPosition(brakeDownPos);
        }else{
            brakes.setTargetPosition(0);
        }
        up = !up;

    }
    
}
