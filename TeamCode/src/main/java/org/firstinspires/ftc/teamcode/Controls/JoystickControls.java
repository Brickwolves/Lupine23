package org.firstinspires.ftc.teamcode.Controls;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.opencv.core.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Input.LEFT;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Input.RIGHT;


public class JoystickControls {

    public enum Input {
        LEFT,
        RIGHT
    }
    public enum Value {
        X, Y,
        SHIFTED_X, SHIFTED_Y,
        INVERT_X, INVERT_Y,
        INVERT_SHIFTED_X, INVERT_SHIFTED_Y
    }

    public interface StickCheck {
        Point check();
    }

    private Map<Input, Joystick> joysticks = new HashMap<>();
    private static List<JoystickControls> instances = new ArrayList<>();


    private Gamepad src;
    public JoystickControls(Gamepad gamepad) {
        src = gamepad;
        instances.add(this);

        joysticks.put(Input.LEFT, new Joystick(() -> new Point(src.left_stick_x, src.left_stick_y)));
        joysticks.put(Input.RIGHT, new Joystick(() -> new Point(src.right_stick_x, src.right_stick_y)));
    }

    public double get(Input input, Value value) {
        Joystick joystick = joysticks.get(input);
        if (joystick == null) throw new Error("Could not find Joystick");
        switch (value) {
            case X:
                return joystick.getX();
            case Y:
                return joystick.getY();
            case SHIFTED_X:
                return joystick.getShiftedX();
            case SHIFTED_Y:
                return joystick.getShiftedY();
            case INVERT_X:
                return joystick.getInvertedX();
            case INVERT_Y:
                return joystick.getInvertedY();
            case INVERT_SHIFTED_X:
                return joystick.getInvertedShiftedX();
            case INVERT_SHIFTED_Y:
                return joystick.getInvertedShiftedY();
        }
        return 0;
    }

    public void setShifted(Input input, double shiftValue) {
        Joystick joystick = joysticks.get(input);
        if (joystick == null) throw new Error("Could not find Joystick");
        Objects.requireNonNull(joysticks.get(input)).setShift(shiftValue);
    }

    public void update(){
        for (Joystick b : joysticks.values()) {
            b.update();
        }
    }

}