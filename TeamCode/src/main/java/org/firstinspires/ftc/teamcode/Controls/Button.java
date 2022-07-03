package org.firstinspires.ftc.teamcode.Controls;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Controls.ButtonControls.ButtonCheck;


public class Button {

    private ElapsedTime time = time = new ElapsedTime();
    private double waitTime = 0.2;

    public boolean pressed_last_cycle;
    public boolean toggle, tap, down, up;

    private final ButtonCheck buttonCheck;
    public Button(ButtonCheck buttonCheck) { this.buttonCheck = buttonCheck; }

    public void update(){
        down = buttonCheck.check();
        up = !down;
        tap = (!pressed_last_cycle && down && time.seconds() > waitTime);
        toggle = tap != toggle;

        if (tap) { time.reset(); }
    }

}
