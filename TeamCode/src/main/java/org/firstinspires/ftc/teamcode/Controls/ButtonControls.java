package org.firstinspires.ftc.teamcode.Controls;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.CIRCLE;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.CROSS;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.DPAD;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.DPAD_DN;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.DPAD_L;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.DPAD_R;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.DPAD_UP;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.LB1;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.LB2;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.RB1;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.RB2;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.SQUARE;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.TOUCHPAD;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.TRIANGLE;


public class ButtonControls {

    public enum Input {
        TRIANGLE, SQUARE, CROSS, CIRCLE,
        LB1, RB1,
        LB2, RB2,
        DPAD_UP, DPAD_DN, DPAD_L, DPAD_R, DPAD,
        TOUCHPAD
    }

    public enum ButtonState {
        TOGGLE,
        NOT_TOGGLED,
        TAP,
        DOWN,
        UP
    }

    // A HashMap or dictionary associating each Input enum (CROSS, TRIANGLE,...etc) with
    // a Button class that automates all the calculations for Button States such as
    // (TOGGLE, NOT_TOGGLED, TAP, DOWN, UP)
    private Map<Input, Button> buttons = new HashMap<>();

    // An interface for storing references to Gamepad attributes
    public interface ButtonCheck { boolean check(); }

    public Gamepad src;
    public ButtonControls(Gamepad gamepad){
        src = gamepad;

        // Stores every Input enum with a button, and each button stores a reference of the
        // gamepad's button attribute in memory so it always knows where to check
        buttons.put(Input.TRIANGLE,   new Button(() -> src.triangle));
        buttons.put(Input.SQUARE,     new Button(() -> src.square));
        buttons.put(Input.CROSS,      new Button(() -> src.cross));
        buttons.put(Input.CIRCLE,     new Button(() -> src.circle));
        buttons.put(Input.LB1,        new Button(() -> src.left_bumper));
        buttons.put(Input.RB1,        new Button(() -> src.right_bumper));
        buttons.put(Input.DPAD_UP,    new Button(() -> src.dpad_up));
        buttons.put(Input.DPAD_DN,    new Button(() -> src.dpad_down));
        buttons.put(Input.DPAD_L,     new Button(() -> src.dpad_left));
        buttons.put(Input.DPAD_R,     new Button(() -> src.dpad_right));
        buttons.put(Input.DPAD,       new Button(() -> src.dpad_down || src.dpad_left || src.dpad_right || src.dpad_up));
        buttons.put(Input.TOUCHPAD,   new Button(() -> src.touchpad));
        buttons.put(Input.LB2,        new Button(() -> src.left_trigger > 0.75));
        buttons.put(Input.RB2,        new Button(() -> src.right_trigger > 0.75));
    }

    /**
     * Retrieves the given button's current state
     * @param input - an Input enum used to specify what button to check
     * @param buttonState - a ButtonState enum specifying what state to retrieve
     * @return true if the current button and state are being pressed, false otherwise
     */
    public boolean get(Input input, ButtonState buttonState) {
        Button button = buttons.get(input);
        if (button == null) return false;
        switch (buttonState) {
            case DOWN:
                return button.down;
            case UP:
                return button.up;
            case TAP:
                return button.tap;
            case TOGGLE:
                return button.toggle;
            case NOT_TOGGLED:
                return !button.toggle;
        }
        return false;
    }

    /**
     * Updates all buttons in the ButtonController
     */
    public void update(){
        for (Button b : buttons.values()) {
            b.update();
        }
    }

    /**
     * Checks whether the DPAD is being pressed at all
     * @return true if any DPAD buttons are pressed, false otherwise
     */
    public boolean DPADPress() { return src.dpad_down || src.dpad_left || src.dpad_right || src.dpad_up; }
}
