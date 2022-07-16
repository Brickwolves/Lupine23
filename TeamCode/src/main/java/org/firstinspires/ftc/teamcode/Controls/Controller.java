package org.firstinspires.ftc.teamcode.Controls;

import com.qualcomm.robotcore.hardware.Gamepad;

public class Controller {

    public Gamepad src;
    public ButtonControls buttons;
    public JoystickControls joysticks;

    public Controller(Gamepad gamepad){
        this.src = gamepad;
        this.buttons = new ButtonControls(gamepad);
        this.joysticks = new JoystickControls(gamepad);
    }

    public static void update(){
        ButtonControls.update();
        JoystickControls.update();
    }

    public void rumble(int seconds){
        this.src.rumble(seconds);
    }
}
