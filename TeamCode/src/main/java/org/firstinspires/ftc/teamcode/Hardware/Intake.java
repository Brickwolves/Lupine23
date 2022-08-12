package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Input.RIGHT;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Value.X;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.Sensors.Color_Sensor;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.Utilities.PID;

public class Intake {
    String id;
    PID intakePID;
    DcMotor intakeMotor;
    private long previousTime = System.currentTimeMillis();
    double previousChange = 0;
    double updateIteration = 19;




    public Intake(){
        intakeMotor = hardwareMap.get(DcMotor.class, "intake");
        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        intakePID = new PID(0.002, 0, 0);

    }

    double mostRecentAngle;
    double currentAngle;
    public boolean isMoving;

    public void runIntake(){
        intakeMotor.setPower(1);
    }

    public void runIntakeBackwards(){
        intakeMotor.setPower(-1.0);
    }

    public void stopIntake(boolean fancyStop){
        if(fancyStop) {
            intakeMotor.setPower(intakePID.update(MathUtils.closestAngle(0, intakeMotor.getCurrentPosition()) - intakeMotor.getCurrentPosition(), true));
            multTelemetry.addData("intake power", intakeMotor.getPower());
            multTelemetry.update();
        }else{
            intakeMotor.setPower(0);
        }

    }

    public double getTPS(){
        double change = intakeMotor.getCurrentPosition();
        double deltaTime = (System.currentTimeMillis() - previousTime) / 1000.0;
        double deltaChange = change - previousChange;
        double rateOfChange = deltaChange/deltaTime;
        previousTime = System.currentTimeMillis();
        previousChange = change;
        return rateOfChange;
    }

    public void updateEncoders(){
        updateIteration ++;
        if(updateIteration == 20){
            updateIteration = 0;
            currentAngle = intakeMotor.getCurrentPosition();
            isMoving = Math.abs(mostRecentAngle - currentAngle) > 2;
            mostRecentAngle = currentAngle;
        }
    }
    public boolean jammed(){
        if(!isMoving && intakeMotor.getPower() != 0){
            return(true);
        }else{
            return(false);
        }
    }


}
