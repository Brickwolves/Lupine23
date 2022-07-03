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
        TAP,
        DOWN,
        UP
    }

    private Map<Input, Button> buttons = new HashMap<>();
    private static List<ButtonControls> instances = new ArrayList<>();
    public interface ButtonCheck { boolean check(); }

    public Gamepad src;
    public ButtonControls(Gamepad gamepad){
        src = gamepad;

        instances.add(this);

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
        }
        return false;
    }

    public static void update(){
        for (ButtonControls c : instances) {
            c.updateInstance();
        }
    }

    private void updateInstance(){
        for (Button b : buttons.values()) {
            b.update();
        }
    }

    public boolean DPADPress() { return src.dpad_down || src.dpad_left || src.dpad_right || src.dpad_up; }
}
