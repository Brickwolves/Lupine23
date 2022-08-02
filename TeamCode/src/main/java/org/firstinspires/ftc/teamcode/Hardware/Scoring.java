package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.DashConstants.PostitionsAndSpeeds.highGoal;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import org.firstinspires.ftc.teamcode.Hardware.Sensors.Color_Sensor;

public class Scoring {
    public DcMotor spool;
    public Servo bucket;
    public Servo lip;
    public Color_Sensor bucketColor;
    public ElapsedTime time = new ElapsedTime();
    public Scoring(){
        spool = hardwareMap.get(DcMotor.class, "spool");
        bucket = hardwareMap.get(Servo.class, "bucket");
        lip = hardwareMap.get(Servo.class, "lip");
        bucketColor = new Color_Sensor();

        bucketColor.init("bucketColor");
        spool.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        spool.setTargetPosition(0);
        spool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        spool.setPower(1);


    }

    public void scoreHigh(){
        if (time.seconds() < 1) {
            spool.setTargetPosition(highGoal);
        }
        if(time.seconds()>2 && time.seconds()<3)
        spool.setTargetPosition(0);


    }

    public void scoreMid(){

    }

    public void scoreLow(){

    }

    public void scoreShared(){

    }
}
