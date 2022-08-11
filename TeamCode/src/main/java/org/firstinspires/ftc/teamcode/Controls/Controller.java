package org.firstinspires.ftc.teamcode.Controls;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Utilities.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * A wrapper class for the Gamepad that may look complicated but I swear it actually
 * simplifies your life 10-fold
 */
public class Controller {

    public Gamepad src;
    public ButtonControls buttons;
    public JoystickControls joysticks;
    public List<Control> controls = new ArrayList<>();
    public static List<Controller> controllerInstances = new ArrayList<>();

    /**
     * An innerclass for associating each Input and ButtonState with a Task
     */
    public class Control {
        private final ButtonControls.Input input;
        private final ButtonControls.ButtonState buttonState;
        private final Task task;

        public Control(ButtonControls.Input input, ButtonControls.ButtonState buttonState, Task task){
            this.input = input;
            this.buttonState = buttonState;
            this.task = task;

        }

        /**
         * Executes the given task only if the current button's state is true
         */
        public void update(){
            if (buttons.get(input, buttonState)) task.execute();
        }
    }

    /**
     * Constructor for the Controller class
     * @param gamepad - a Gamepad from an OpMode
     */
    public Controller(Gamepad gamepad){
        this.src = gamepad;

        // Instantiate new ButtonControl and JoystickControls so we can have added functionality
        // for our controller
        this.buttons = new ButtonControls(gamepad);
        this.joysticks = new JoystickControls(gamepad);

        // Add the current controller instance to a static list so that all we may call
        // Controller.update() once each TeleOp loop to automatically update all controllers
        Controller.controllerInstances.add(this);
    }

    /**
     * Sets the shift angle for a given stick
     * @param input - a JoystickControls.Input to shift
     * @param shiftAngle - a double representing the angle to shift by
     */
    public void setJoystickShift(JoystickControls.Input input, double shiftAngle){
        this.joysticks.setShifted(input, shiftAngle);
    }

    /**
     * Associates the given Input and that input's ButtonState with a Task to be completed
     * @param input - an Input specifying what button to check
     * @param state - a ButtonState specifying what state to check on the input
     * @param task - a Task implementation specifying what task to be completed every time the
     *               button and state are found to be true
     */
    public void add(ButtonControls.Input input, ButtonControls.ButtonState state, Task task){
        this.controls.add(new Control(input, state, task));
    }

    /**
     * Gets the given input and input's state from the buttons
     * @param input - a ButtonControls.Input specifying what button to check
     * @param buttonState - a ButtonState specifying what state to check on the input
     * @return true if the specified button is pressed, false otherwise
     */
    public boolean get(ButtonControls.Input input, ButtonControls.ButtonState buttonState){
        return buttons.get(input, buttonState);
    }

    /**
     * Gets the given input and input's state from the joysticks
     * @param input - a JoystickControls.Input specifying what joystick to check
     * @param value - a Value specifying what value of the joystick to check on the input
     * @return a double representing the value of the given input
     */
    public double get(JoystickControls.Input input, JoystickControls.Value value){
        return joysticks.get(input, value);
    }

    /**
     * Executes each stored task only if the associated button and state are true
     */
    private void updateTasks(){
        for (Control control : controls){
            control.update();
        }
    }

    /**
     * Updates every instantiated controller by updating its buttons, joysticks, and then completing
     * any tasks depending upon its new state
     */
    public static void update(){
        for (Controller controller : controllerInstances){
            controller.buttons.update();
            controller.joysticks.update();
            controller.updateTasks();
        }
    }

    public void rumble(int seconds){
        this.src.rumble(seconds);
    }

    public boolean dpadPress(){
        return this.buttons.DPADPress();
    }
}
