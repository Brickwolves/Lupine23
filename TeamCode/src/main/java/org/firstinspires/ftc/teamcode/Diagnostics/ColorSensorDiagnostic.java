package org.firstinspires.ftc.teamcode.Diagnostics;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Controls.Controller;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.Color_Sensor;
import org.firstinspires.ftc.teamcode.Utilities.OpModeUtils;
import org.firstinspires.ftc.teamcode.Controls.ButtonControls;

import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.DPAD_DN;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.DPAD_UP;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_ColorSensorDiagnostic.colorSensorID;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_ServoDiagnostic.SERVO_HOME;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_ServoDiagnostic.SERVO_ID;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_ServoDiagnostic.SERVO_MAX;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_ServoDiagnostic.SERVO_MIN;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.ButtonState.DOWN;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.TOUCHPAD;

import android.graphics.Color;


@TeleOp(name = "ColorSensorDiagnostic TeleOp", group="Linear TeleOp")
public class ColorSensorDiagnostic extends LinearOpMode {

    private Controller controller;

    private Color_Sensor colorSensor;
    private String sensor_id = colorSensorID;


    public void initialize() {
        OpModeUtils.setOpMode(this);
        controller = new Controller(gamepad1);


        colorSensor = new Color_Sensor();
        colorSensor.init(sensor_id);

        OpModeUtils.multTelemetry.addData("Status", "Initialized");
        OpModeUtils.multTelemetry.addData("Start Keys", "Press [>] to begin");
        OpModeUtils.multTelemetry.addData("Shutdown Keys", "Press [RB] & [LB] simultaneously");
        OpModeUtils.multTelemetry.update();
    }

    public void shutdown(){
        OpModeUtils.multTelemetry.addData("Status", "Shutting Down");
        OpModeUtils.multTelemetry.update();
    }


    @Override
    public void runOpMode() {


        initialize();
        waitForStart();

        while (opModeIsActive()) {
            Controller.update();

            OpModeUtils.multTelemetry.addData("Red", colorSensor.updateRed());
            OpModeUtils.multTelemetry.addData("Green", colorSensor.updateGreen());
            OpModeUtils.multTelemetry.addData("Blue", colorSensor.updateBlue());
            OpModeUtils.multTelemetry.addData("Alpha", colorSensor.colorSensor.alpha());
            OpModeUtils.multTelemetry.update();



            // S H U T D O W N     S E Q U E N C E

            if (controller.get(TOUCHPAD, DOWN)){
                shutdown();
                break;
            }
        }
    }
}


