package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import com.qualcomm.robotcore.hardware.Servo;

public class Sweeper {
    String id;
    Servo sweeper;
    double UP_POSITION = 1.0;
    double DOWN_POSITION = 0.525;

    public enum SweeperPos {
        UP, DOWN
    }

    private SweeperPos sweeperPos = SweeperPos.UP;

    public Sweeper(String id){
        sweeper = hardwareMap.get(Servo.class, id);

    }

    public void sweep(double Posistion){
        sweeper.setPosition(Posistion); // 0 0.35

    }


    // controller.get(SQUARE, TOGGLE)
    public void update(boolean square_toggle){

        switch(sweeperPos){

            case UP:

                // set the sweeper position on the servo
                sweeper.setPosition(UP_POSITION);
                if (square_toggle) sweeperPos = SweeperPos.DOWN;

                break;


            case DOWN:

                // set the sweeper position on the servo
                sweeper.setPosition(DOWN_POSITION);
                if (!square_toggle) sweeperPos = SweeperPos.UP;

                break;

        }

    }


}
