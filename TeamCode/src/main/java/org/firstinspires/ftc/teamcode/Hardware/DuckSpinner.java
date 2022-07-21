package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.Utilities.DuckSpeed;

public class DuckSpinner {
    String id;
    CRServo duck;

    /** TODO: Declare your attributes up here
     * - a String named id
     * - a CRServo named crservo
     */

    public DuckSpinner(String id){
        duck = hardwareMap.get(CRServo.class, id);
    }
    /** TODO: Write your Constructor here!
     * PARAMETERS: a String named id
     * FUNCTIONALITY: Should instantiate the CRServo using the OpModeUtils.hardwareMap
     *                Specifically, it should use the hardwareMaps get() method, if you forget
     *                what the the parameters are just check out the FTC documentation
     */

    public void spin(double power){
        duck.setPower(power);
    }
    /** TODO: Write a method called spin()
     * FUNCTIONALITY: spin() is a public method that can be used to spin the CRServo forwards
     *                or backwards depending on a power.
     *                HINT: Check out FTC documentation on how to set the power to a CRServo
     * PARAMETERS: a double named power
     * RETURN TYPE: none
     */

}
