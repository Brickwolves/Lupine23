package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.ButtonState.DOWN;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.ButtonState.TAP;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.ButtonState.TOGGLE;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.CIRCLE;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.DPAD_DN;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.DPAD_L;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.DPAD_R;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.DPAD_UP;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.RB1;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.RB2;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.SQUARE;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.TRIANGLE;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Input.LEFT;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Input.RIGHT;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Value.INVERT_SHIFTED_X;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Value.INVERT_SHIFTED_Y;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Value.SHIFTED_X;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Value.SHIFTED_Y;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Value.X;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import org.firstinspires.ftc.teamcode.Controls.Controller;
import org.firstinspires.ftc.teamcode.Hardware.Robot;
import org.firstinspires.ftc.teamcode.Utilities.PID;
import org.firstinspires.ftc.teamcode.Utilities.DuckSpeed;

//@Disabled
@TeleOp(name="Iterative TeleOp", group="Iterative Opmode")
public class IterativeTeleOp extends OpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor demoMotor;
    private PID pid;
    private double setPoint = 0;

    private boolean wasTurning;

    // Declare OpMode members.
    CRServo duck;
    //    DcMotor flmotor;
//    DcMotor frmotor;
//    DcMotor blmotor;
//    DcMotor brmotor;
    Robot greg;
    boolean last = false;
    boolean toggle = false;
    Controller controller;



    /*
     * Code to run ONCE when the driver hits INIT
     */
//    public void toggle(boolean toggle, boolean last, boolean button){ //works by checking the last state of a button
//        if (button && last != gamepad1.triangle){
//            toggle = !toggle;
//            last =true;
//        }
//        else if (!gamepad1.triangle){
//            last = true;
//        }
//    }

    @Override
    public void init() {
        setOpMode(this);

        pid = new PID(0.04, 0, 0.0027);



        greg = new Robot();
        //toggle triangleToggle = toggle();
        controller = new Controller(gamepad1);
        /*
                    Y O U R   C O D E   H E R E
                                                    */


        multTelemetry.addData("Status", "Initialized");
        multTelemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {

        /*
                    Y O U R   C O D E   H E R E
                                                   */


        multTelemetry.addData("Status", "InitLoop");
        multTelemetry.update();
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();


        /*
                    Y O U R   C O D E   H E R E
                                                   */

        multTelemetry.addData("Status", "Started");
        multTelemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */

    @Override
    public void loop() {
        Controller.update();

        double power = 0.8 - gamepad1.left_trigger * 0.55;

        // double closestAngle = MathUtils.closestAngle()

        double correction = pid.update(greg.gyro.getAngle() - setPoint, true);
        double rotation;
        if(!(controller.get(RIGHT, X) == 0)){
            rotation = controller.get(RIGHT, X);
            wasTurning = true;
        }else{
            if(wasTurning){
                setPoint = greg.gyro.getAngle();
                wasTurning = false;
            }
            rotation = correction;
        }
        if(controller.get(CIRCLE, DOWN) && controller.get(RB2, TOGGLE)){
            greg.duck.spin(1);
        }
        else if(controller.get(CIRCLE, DOWN) && !controller.get(RB2, TOGGLE)){
            greg.duck.spin(-1);
        }
        else{
            greg.duck.spin(0);
        }

        if(controller.get(DPAD_R, TAP)){
            setPoint += 90;
        }
        else if(controller.get(DPAD_L, TAP)){
            setPoint += -90;
        }
        else if(controller.get(DPAD_UP, TAP)){
            setPoint += 0;
        }
        else if(controller.get(DPAD_DN, TAP)){
            setPoint += 180;
        }

        if (controller.get(RB1, TOGGLE)){
            greg.dropper.drop(0, 0.97);
        }
        else if (!controller.get(RB1, TOGGLE)){
            greg.dropper.drop(0.35, 0.85);
        }

        if (controller.get(TRIANGLE, TOGGLE)){
            greg.grabber.grab(0.26, 0.875);
        }
        else if (!controller.get(TRIANGLE, TOGGLE)){
            greg.grabber.grab(0.7, 0.35);
        }


        // Sweeper Update method call
        // Sweeper controlled by SQUARE TOGGLE
        greg.sweeper.update(controller.get(SQUARE, TOGGLE));


        controller.setJoystickShift(LEFT, greg.gyro.getAngle());

        double drive = controller.get(LEFT, INVERT_SHIFTED_Y);
        double strafe = controller.get(LEFT, SHIFTED_X);







        greg.drivetrain.setDrivePower(drive, strafe, rotation, power);
    /*
         ----------- L O G G I N G -----------
                                            */
        multTelemetry.addData("Status", "TeleOp Running");
        multTelemetry.addData("Angle", greg.gyro.getAngle());
        multTelemetry.addData("SetPoint", setPoint);
        multTelemetry.addData("toggle", controller.get(RB1, TOGGLE));
        multTelemetry.update();
    }


    @Override
    public void stop(){

        /*
                    Y O U R   C O D E   H E R E
                                                   */

    }
}
