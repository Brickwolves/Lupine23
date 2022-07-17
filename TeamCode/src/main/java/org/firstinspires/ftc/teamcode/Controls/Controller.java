package org.firstinspires.ftc.teamcode.Controls;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Utilities.Task;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    public Gamepad src;
    public ButtonControls buttons;
    public JoystickControls joysticks;
    public List<Control> controls = new ArrayList<>();
    public static List<Controller> controllerInstances = new ArrayList<>();

    public class Control {
        private ButtonControls.Input input;
        private ButtonControls.ButtonState buttonState;
        private Task task;

        public Control(ButtonControls.Input input, ButtonControls.ButtonState buttonState, Task task){
            this.input = input;
            this.buttonState = buttonState;
            this.task = task;

        }
        public void update(){
            if (buttons.get(input, buttonState)) task.execute();
        }
    }

    public Controller(Gamepad gamepad){
        this.src = gamepad;
        this.buttons = new ButtonControls(gamepad);
        this.joysticks = new JoystickControls(gamepad);
        Controller.controllerInstances.add(this);
    }

    public void add(ButtonControls.Input input, ButtonControls.ButtonState state, Task task){
        this.controls.add(new Control(input, state, task));
    }

    private void updateTasks(){
        for (Control control : controls){
            control.update();
        }
    }

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
}
