package org.firstinspires.ftc.teamcode.Controls;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Controls.ButtonControls.ButtonCheck;

/**
 * A class that represents a single button on the controller
 */
public class Button {

    private ElapsedTime time = time = new ElapsedTime();

    // Controls the amount of delay for a button TAP
    private double waitTime = 0.2;

    public boolean pressed_last_cycle;
    public boolean toggle, tap, down, up;

    // The ButtonCheck interface is anonymously instantiated with a reference to the button on
    // the gamepad
    private final ButtonCheck buttonCheck;
    public Button(ButtonCheck buttonCheck) { this.buttonCheck = buttonCheck; }

    /**
     * Retrieves the value for a given button and calculates all button states
     * such as DOWN, UP, TAP, and TOGGLE.
     */
    public void update(){
        down = buttonCheck.check();
        up = !down;
        tap = (!pressed_last_cycle && down && time.seconds() > waitTime);
        if (tap) {
            pressed_last_cycle = true;
            toggle = !toggle;
            time.reset();
        }
        else {
            pressed_last_cycle = false;
        }
    }

}
