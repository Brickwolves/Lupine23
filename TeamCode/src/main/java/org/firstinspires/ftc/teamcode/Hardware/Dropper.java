package org.firstinspires.ftc.teamcode.Hardware;

import org.firstinspires.ftc.teamcode.Controls.ButtonControls;

public class Dropper {

    private ButtonControls.ButtonCheck gripButton, dropButton;
    private enum DropperState {
        HOLD, DROP
    }
    private DropperState state = DropperState.HOLD;

    public Dropper(){

    }

    public void setControls(ButtonControls.ButtonCheck grip, ButtonControls.ButtonCheck drop){
        this.gripButton = grip;
        this.dropButton = drop;
    }

    public void update(){
        boolean grip = gripButton.check();
        boolean drop = dropButton.check();

        switch (state){
            case HOLD:
                if (grip){
                    // blah blah
                }
                else if (drop){
                    // more stuff
                }
                break;

            case DROP:
                if (grip){

                }
        }
    }

    public void drop(){

    }
    public void hold(){

    }
}
