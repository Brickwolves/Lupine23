package org.firstinspires.ftc.teamcode.Controls;


import org.opencv.core.Point;

import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.StickCheck;

/**
 * A class for containing a single joystick. Adds features such as driver oriented control
 */
public class Joystick {

    private double rawX;
    private double rawY;
    private double shiftedX;
    private double shiftedY;
    private Point rawPoint;

    // StickCheck interface is necessary because it allows us to store a reference to the Gamepad's
    // joystick value. That way we know where to update our current joystick value.
    StickCheck stickCheck;
    public Joystick(StickCheck stickCheck){
        this.stickCheck = stickCheck;
    }

    /**
     * Updates the given joystick value and Joystick attributes
     */
    public void update(){
        rawPoint = stickCheck.check();
        rawX = rawPoint.x;
        rawY = rawPoint.y;
    }

    /**
     * Gets the current x position
     * @return a double representing the current X position on the joystick
     */
    public double getX() {
        return rawX;
    }

    /**
     * Gets the current y position
     * @return a double representing the current Y position on the joystick
     */
    public double getY() {
        return rawY;
    }

    /**
     * A function for shifting the current X and Y value for driver oriented control
     * @param shiftAngle - a double representing the robot's current heading
     */
    public void setShift(double shiftAngle) {
        this.shiftedX = (this.rawX * Math.cos(Math.toRadians(shiftAngle))) - (this.rawY * Math.sin(Math.toRadians(shiftAngle)));
        this.shiftedY = (this.rawX * Math.sin(Math.toRadians(shiftAngle))) + (this.rawY * Math.cos(Math.toRadians(shiftAngle)));
    }

    /**
     * Gets the current shifted X position
     * @return a double representing the current shifted X position on the joystick for driver
     *         oriented control
     */
    public double getShiftedX() {
        return shiftedX;
    }
    /**
     * Gets the current shifted Y position
     * @return a double representing the current shifted Y position on the joystick for driver
     *         oriented control
     */
    public double getShiftedY() {
        return shiftedY;
    }

    /**
     * Gets the current shifted X position
     * @param shiftAngle - a double representing the robot's current heading
     * @return a double representing the current shifted X position on the joystick for driver
     *         oriented control
     */
    public double getShiftedX(Double shiftAngle) { return (this.rawX * Math.sin(Math.toRadians(shiftAngle))) + (this.rawY * Math.cos(Math.toRadians(shiftAngle))); }

    /**
     * Gets the current shifted Y position
     * @param shiftAngle - a double representing the robot's current heading
     * @return a double representing the current shifted Y position on the joystick for driver
     *         oriented control
     */
    public double getShiftedY(Double shiftAngle) { return (this.rawX * Math.sin(Math.toRadians(shiftAngle))) + (this.rawY * Math.cos(Math.toRadians(shiftAngle))); }

    /**
     * Gets negated X value
     * @return a double representing the rawX value but negated
     */
    public double getInvertedX() {
        return rawX * -1;
    }
    /**
     * Gets negated Y value
     * @return a double representing the rawY value but negated
     */
    public double getInvertedY() {
        return rawY * -1;
    }
    /**
     * Gets negated shifted X value
     * @return a double representing the shifted X value but negated
     */
    public double getInvertedShiftedX() {
        return shiftedX * -1;
    }
    /**
     * Gets negated shifted Y value
     * @return a double representing the shifted Y value but negated
     */
    public double getInvertedShiftedY() {
        return shiftedY * -1;
    }

    /**
     * Gets negated shifted X value
     * @param shiftAngle - a double representing the robot's current heading
     * @return a double representing the shifted X value but negated
     */
    public double getInvertedShiftedX(Double shiftAngle) { return (this.rawX * Math.sin(Math.toRadians(shiftAngle))) + (this.rawY * Math.cos(Math.toRadians(shiftAngle))) * -1; }
    /**
     * Gets negated shifted Y value
     * @param shiftAngle - a double representing the robot's current heading
     * @return a double representing the shifted Y value but negated
     */
    public double getInvertedShiftedY(Double shiftAngle) { return (this.rawX * Math.sin(Math.toRadians(shiftAngle))) + (this.rawY * Math.cos(Math.toRadians(shiftAngle))) * -1; }


}
