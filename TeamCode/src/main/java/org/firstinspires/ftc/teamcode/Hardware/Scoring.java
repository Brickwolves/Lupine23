package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.DashConstants.PositionsAndSpeeds.blue;
import static org.firstinspires.ftc.teamcode.DashConstants.PositionsAndSpeeds.green;
import static org.firstinspires.ftc.teamcode.DashConstants.PositionsAndSpeeds.lowGoal;
import static org.firstinspires.ftc.teamcode.DashConstants.PositionsAndSpeeds.midGoal;
import static org.firstinspires.ftc.teamcode.DashConstants.PositionsAndSpeeds.red;
import static org.firstinspires.ftc.teamcode.DashConstants.PositionsAndSpeeds.sharedGoal;
import static org.firstinspires.ftc.teamcode.DashConstants.PositionsAndSpeeds.superHeavyRed;
import static org.firstinspires.ftc.teamcode.DashConstants.PositionsAndSpeeds.whiteBlue;
import static org.firstinspires.ftc.teamcode.DashConstants.PositionsAndSpeeds.whiteGreen;
import static org.firstinspires.ftc.teamcode.DashConstants.PositionsAndSpeeds.whiteRed;
import static org.firstinspires.ftc.teamcode.Utilities.Freight.FreightType.BALLS;
import static org.firstinspires.ftc.teamcode.Utilities.Freight.FreightType.NONE;
import static org.firstinspires.ftc.teamcode.Utilities.Freight.FreightType.REGULAR;
import static org.firstinspires.ftc.teamcode.Utilities.Freight.FreightType.SUPERHEAVY;
import static org.firstinspires.ftc.teamcode.Utilities.Freight.freight;
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

        lip.setPosition(0);

    }

    public void scoreHigh(){

        if (time.seconds() < .5) {
            lip.setPosition(0.28);
        }
        if (time.seconds() < 1 && time.seconds() > 0.5) {
            bucket.setPosition(.9);
            spool.setTargetPosition(1800);
        }
        if(time.seconds()>1 && time.seconds()<2)
        bucket.setPosition(0.15);


    }

    public void scoreMid(){

        if (time.seconds() < .5) {
            lip.setPosition(0.28);
        }
        if (time.seconds() < 1 && time.seconds() > 0.5) {
            bucket.setPosition(.9);
            spool.setTargetPosition(midGoal);
        }
        if(time.seconds()>1 && time.seconds()<2)
            bucket.setPosition(0.15);


    }

    public void scoreLow(){

        if (time.seconds() < .5) {
            lip.setPosition(0.28);
        }
        if (time.seconds() < 1 && time.seconds() > 0.5) {
            bucket.setPosition(.9);
            spool.setTargetPosition(lowGoal);
        }
        if(time.seconds()>1 && time.seconds()<2)
            bucket.setPosition(0.15);


    }

    public void scoreShared(){

        if (time.seconds() < .5) {
            lip.setPosition(0.28);
        }
        if (time.seconds() < 1 && time.seconds() > 0.5) {
            bucket.setPosition(.9);
            spool.setTargetPosition(sharedGoal);
        }
        if(time.seconds()>1 && time.seconds()<2)
            bucket.setPosition(0.15);


    }

    public void deposit(){
        if(time.seconds() < 1.2) {
            lip.setPosition(0);
        }
        if(time.seconds() > 1.2 && time.seconds() < 1.8){
            bucket.setPosition(.9);
            spool.setTargetPosition(0);
        }
        if(time.seconds() > 1.8 && time.seconds() < 2){
            bucket.setPosition(1);
        }
    }

    public void checkFreight(){
        if(bucketColor.updateRed() > red && bucketColor.updateGreen() > green && bucketColor.updateBlue() > blue){
            freight = REGULAR;
            if(bucketColor.getRedCacheValue() > superHeavyRed){
                freight = SUPERHEAVY;
            }else if(bucketColor.getRedCacheValue() > whiteRed && bucketColor.getBlueCacheValue() > whiteBlue && bucketColor.greenCacheValue > whiteGreen){
                freight = BALLS;
            }

        }else{
            freight = NONE;
        }
    }

}
