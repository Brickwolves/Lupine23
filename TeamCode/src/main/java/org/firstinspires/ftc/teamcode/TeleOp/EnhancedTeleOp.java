package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.ButtonState.DOWN;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.ButtonState.TAP;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.ButtonState.TOGGLE;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.CIRCLE;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.CROSS;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.DPAD_DN;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.DPAD_L;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.DPAD_R;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.DPAD_UP;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.LB1;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.LB2;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.RB1;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.RB2;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.SQUARE;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Input.LEFT;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Input.RIGHT;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Value.INVERT_SHIFTED_Y;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Value.SHIFTED_X;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Value.X;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Value.Y;
import static org.firstinspires.ftc.teamcode.DashConstants.PositionsAndSpeeds.rateOfChange;
import static org.firstinspires.ftc.teamcode.Utilities.Constants.IMU_DATUM;
import static org.firstinspires.ftc.teamcode.Utilities.MathUtils.odoToTiles;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;
import static org.firstinspires.ftc.teamcode.Utilities.PIDWeights.derivativeWeight;
import static org.firstinspires.ftc.teamcode.Utilities.PIDWeights.integralWeight;
import static org.firstinspires.ftc.teamcode.Utilities.PIDWeights.proportionalWeight;

import org.firstinspires.ftc.teamcode.Controls.ButtonControls;
import org.firstinspires.ftc.teamcode.Controls.Controller;
import org.firstinspires.ftc.teamcode.Hardware.Robot;
import org.firstinspires.ftc.teamcode.Utilities.Loggers.Side;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.Utilities.PID;
import org.opencv.core.Point;

//@Disabled
@TeleOp(name="Enhanced TeleOp", group="Iterative Opmode")
public class EnhancedTeleOp extends OpMode {

    // Declare OpMode members.
    private final ElapsedTime runtime = new ElapsedTime();
    private PID pid;
    private double setPoint = 0;
    private boolean wasTurning;
    private boolean pid_on = false;
    private boolean pid_on_last_cycle = false;
    private boolean KETurns = false;
    private boolean enhancedMode = false;
    private Point targetPosOnField = new Point(0,0);

    private Point exampleOdoPoint = new Point(0,0);



    public enum SlidesState {HIGH, MIDDLE, LOW, GROUND, DOWN, DEPOSIT}


    public SlidesState slidesState = SlidesState.DOWN;

    // Declare OpMode members.
    Robot robot;
    Controller controller;
    Controller controller2;



    /*
     * Code to run ONCE when the driver hits INIT
     */

    @Override
    public void init() {
        setOpMode(this);





        pid = new PID(proportionalWeight, integralWeight, derivativeWeight);


        robot = new Robot();
        controller = new Controller(gamepad1);
        controller2 = new Controller(gamepad2);
        /*
                    Y O U R   C O D E   H E R E
                                                    */


        robot.gyro.setDatum(IMU_DATUM);


        multTelemetry.addData("Status", "Initialized");
        multTelemetry.addData("imu datum", IMU_DATUM);
        multTelemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {


        multTelemetry.addData("Status", "InitLoop");
        multTelemetry.addData("imu datum", IMU_DATUM);
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

        double power;

        //Scoring
        if(controller2.get(DPAD_UP, TAP) && slidesState != SlidesState.HIGH){
            slidesState = SlidesState.HIGH;
            robot.grabber.time.reset();
        }

        if(controller2.get(DPAD_L, TAP) && slidesState != SlidesState.MIDDLE){
            slidesState = SlidesState.MIDDLE;
            robot.grabber.time.reset();
        }

        if(controller2.get(DPAD_R, TAP) && slidesState != SlidesState.LOW){
            slidesState = SlidesState.LOW;
            robot.grabber.time.reset();
        }

        if(controller2.get(DPAD_DN, TAP) && slidesState != SlidesState.GROUND){
            slidesState = SlidesState.GROUND;
            robot.grabber.time.reset();
        }

        if(controller2.get(CIRCLE,TAP) && slidesState != SlidesState.DEPOSIT){
            robot.grabber.wentDown = false;
            slidesState = SlidesState.DEPOSIT;
            robot.grabber.time.reset();
        }

        if(controller2.get(SQUARE, TAP) && slidesState != SlidesState.DOWN){
            slidesState = SlidesState.DOWN;
            robot.grabber.time.reset();
        }

        if(controller.get(RB2, DOWN)){
            slidesState = SlidesState.DOWN;
            robot.grabber.intake();
        }else if (controller.get(RB1, DOWN)){
            robot.grabber.runIntakeBackwards();
        }else{
            robot.grabber.stopIntake();
        }

        switch(slidesState){
            case HIGH:
                robot.grabber.high();
                break;
            case MIDDLE:
                robot.grabber.middle();
                break;
            case LOW:
                robot.grabber.low();
                break;
            case GROUND:
                robot.grabber.ground();
                break;
            case DEPOSIT:
                robot.grabber.deposit();
                break;
            case DOWN:
                robot.grabber.down();
        }

        //PID and Kinetic Turning
        double rotation = controller.get(RIGHT, X);

        // Turn off PID if we manually turn
        // Turn on PID if we're not manually turning and the robot's stops rotating
        double currentRateOfChange = robot.gyro.rateOfChange();
        if (rotation != 0){ pid_on = false;}
        else if (currentRateOfChange <= rateOfChange) pid_on = true;


        //IMU RESET
        if(controller.get(CROSS, TAP)){
            robot.gyro.reset();
        }

        //TURN WRAPPING
        if (controller.get(DPAD_R, TAP)) {
            setPoint = MathUtils.closestAngle(270, robot.gyro.getAngle());
            pid_on = true;
            KETurns = false;
        } else if (controller.get(DPAD_L, TAP)) {
            setPoint = MathUtils.closestAngle(90, robot.gyro.getAngle());
            pid_on = true;
            KETurns = false;
        } else if (controller.get(DPAD_UP, TAP)) {
            setPoint = MathUtils.closestAngle(0, robot.gyro.getAngle());
            pid_on = true;
            KETurns = false;
        } else if (controller.get(DPAD_DN, TAP)) {
            setPoint = MathUtils.closestAngle(180, robot.gyro.getAngle());
            pid_on = true;
            KETurns = false;

        }


        // Lock the heading if we JUST turned PID on
        // Correct our heading if the PID has and is still on
        if (pid_on && !pid_on_last_cycle) setPoint = robot.gyro.getAngle();
        else if (pid_on) rotation = pid.update(robot.gyro.getAngle() - setPoint, true);

        // Update whether the PID was on or not
        pid_on_last_cycle = pid_on;


        //DRIVING
        controller.setJoystickShift(LEFT, robot.gyro.getAngle());


        //SWITCH ENHANCED MODE
        if (controller.get(LB1, TAP) && controller.get(RB1, TAP)) {
            enhancedMode = !enhancedMode;
            robot.drivetrain.setAllPower(0);

            //FIND POSITION VIA ODO
            targetPosOnField.x = odoToTiles(exampleOdoPoint).x;
            targetPosOnField.y = odoToTiles(exampleOdoPoint).y;


        }


        //NON ENHANCED POWER DISTRIBUTION
        if(!enhancedMode) {
            double drive = controller.get(LEFT, INVERT_SHIFTED_Y);
            double strafe = controller.get(LEFT, SHIFTED_X);
            double turn = controller.get(RIGHT, X);


            if (controller.get(LB1, ButtonControls.ButtonState.DOWN) || controller.get(LB2, DOWN)) {
                power = 0.3;
            } else {
                power = 0.8;
            }


            robot.drivetrain.setDrivePower(drive, strafe, rotation, power);
        }

        //ENHANCED MODE
        if(enhancedMode){

            //Change target position
            if(controller.get(DPAD_L, TAP) && targetPosOnField.x != 1){
                targetPosOnField.x -= 1;
            }
            if(controller.get(DPAD_UP, TAP) && targetPosOnField.y != 6){
                targetPosOnField.y += 1;

            }if(controller.get(DPAD_R, TAP) && targetPosOnField.x != 6){
                targetPosOnField.x += 1;
            }

            if(controller.get(DPAD_DN, TAP)  && targetPosOnField.y != 1){
                targetPosOnField.y -= 1;
            }

            //Go to target position
                //?????????


            //Automatic switch out of enhanced
            if(controller.get(LEFT, X) != 0 || controller.get(RIGHT, X) != 0 || controller.get(LEFT, Y) != 0 || controller.get(RIGHT, Y) != 0){
                enhancedMode = false;
            }






        }



        //SIDE
        Side.red = !controller2.get(RB1, TOGGLE);
    /*
         ----------- L O G G I N G -----------
                                            */
        multTelemetry.addData("Target", robot.grabber.spool.getTargetPosition());
        multTelemetry.addData("Current", robot.grabber.spool.getCurrentPosition());
        multTelemetry.update();
    }


    @Override
    public void stop(){

        /*
                    Y O U R   C O D E   H E R E
                                                   */

    }
}
