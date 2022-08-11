package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Input.RIGHT;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Value.X;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.PIDWeights.intakeD;
import static org.firstinspires.ftc.teamcode.Utilities.PIDWeights.intakeI;
import static org.firstinspires.ftc.teamcode.Utilities.PIDWeights.intakeP;

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


    public Intake(){
        intakeMotor = hardwareMap.get(DcMotor.class, "intake");
        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        intakePID = new PID(intakeP, intakeI, intakeD);

    }

    public void runIntake(){
        intakeMotor.setPower(0.8);
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


}
