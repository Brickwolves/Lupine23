package org.firstinspires.ftc.teamcode.Diagnostics;

import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.ButtonState.DOWN;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.DPAD_DN;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.DPAD_UP;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.TOUCHPAD;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_DCMotorDiagnostic.MOTOR_ID;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_DCMotorDiagnostic.POWER;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Controls.ButtonControls;
import org.firstinspires.ftc.teamcode.Utilities.OpModeUtils;

@Disabled
@TeleOp(name = "DCMotorDiag TeleOp", group="Linear TeleOp")
public class DCMotorDiagnostic extends LinearOpMode {

    private ButtonControls BC;

    private DcMotor dcMotor;
    private String motor_id = MOTOR_ID;
    private String status = "HOME";


    public void initialize() {
        OpModeUtils.setOpMode(this);
        BC = new ButtonControls(gamepad1);


        dcMotor = OpModeUtils.hardwareMap.get(DcMotor.class, motor_id);
        dcMotor.setDirection(DcMotor.Direction.FORWARD);

        OpModeUtils.multTelemetry.addData("Status", "Initialized");
        OpModeUtils.multTelemetry.addData("Start Keys", "Press [>] to begin");
        OpModeUtils.multTelemetry.addData("Shutdown Keys", "Press [RB] & [LB] simultaneously");
        OpModeUtils.multTelemetry.update();
    }

    public void shutdown(){
        OpModeUtils.multTelemetry.addData("Status", "Shutting Down");
        OpModeUtils.multTelemetry.update();
        sleep(3000);
    }


    @Override
    public void runOpMode() {


        initialize();
        waitForStart();

        while (opModeIsActive()) {

            if (BC.get(DPAD_UP, DOWN)) {
                dcMotor.setPower(POWER);
                status = "FORWARD";
            }
            if (BC.get(DPAD_DN, DOWN)) {
                dcMotor.setPower(-POWER);
                status = "BACKWARD";
            }

            OpModeUtils.multTelemetry.addData("DCMotor ID", motor_id);
            OpModeUtils.multTelemetry.addData("DCMotor Status", status);
            OpModeUtils.multTelemetry.addData("DCMotor Position", dcMotor.getCurrentPosition());
            OpModeUtils.multTelemetry.update();



            // S H U T D O W N     S E Q U E N C E

            if (BC.get(TOUCHPAD, DOWN)){
                shutdown();
                break;
            }
        }
    }
}


