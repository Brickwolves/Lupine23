package org.firstinspires.ftc.teamcode.Sample;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="Servo Demo", group="Iterative Opmode")
public class Demo1Servo extends OpMode {
	
	/*  the declaration of servos is very similar to dc motors, the only difference
		is changing the class from DcMotor to Servo.    */
	
	DcMotor demoMotor;
	
	Servo demoServo;
	
	
	@Override
	public void init() {
		
		/*  initialization for a servo is also very similar to a dc motor, the only thing
			that's different is what class you input in the hardware map.   */
		
		demoMotor = hardwareMap.get(DcMotor.class, "demomotor");
		
		demoServo = hardwareMap.get(Servo.class, "demoservo");
	}
	
	
	@Override
	public void loop() {
		
		/*  for a dc motor you call the setPower() method and input a value from -1 to 1,
			which tells the motor how fast to spin. 1 and -1 are full speed in each direction, 0 is stopped.    */
		
		demoMotor.setPower(1.0);
		
		
		/*  For servos, instead you call the setPosition() and input a value from 0 to 1,
			which tells the servo what position to go to. It has a 300º range of motion */
		
		demoServo.setPosition(1.0);
		
	}
}