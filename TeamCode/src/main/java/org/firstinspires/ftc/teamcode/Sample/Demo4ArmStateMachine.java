package org.firstinspires.ftc.teamcode.Sample;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Controls.Controller;

import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.ButtonState.DOWN;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.LB1;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.LB2;


@Disabled
@TeleOp(name="Switch Demo 3", group="Iterative Opmode")
public class Demo4ArmStateMachine extends OpMode {
	
	Controller controller;
	Servo armServo;
	
	private ArmState currentArmState = ArmState.DOWN;
	
	
	@Override
	public void init() {
		armServo = hardwareMap.get(Servo.class, "armservo");
		controller = new Controller(gamepad1);
	}
	
	
	@Override
	public void loop() {
		
		switch (currentArmState){
			
			case DOWN:
				armServo.setPosition(0.1);
				
				if(controller.buttons.get(LB1, DOWN)) currentArmState = ArmState.UP;
				if(controller.buttons.get(LB2, DOWN)) currentArmState = ArmState.FOLDED;
				
				break;
			
				
			case UP:
				armServo.setPosition(0.4);
				
				if(controller.buttons.get(LB1, DOWN)) currentArmState = ArmState.DOWN;
				if(controller.buttons.get(LB2, DOWN)) currentArmState = ArmState.FOLDED;
				
				break;
			
				
			case FOLDED:
				armServo.setPosition(0.9);
				
				if(controller.buttons.get(LB1, DOWN)) currentArmState = ArmState.UP;
				if(controller.buttons.get(LB2, DOWN)) currentArmState = ArmState.DOWN;
				
				break;
				
		}
		
	}
	
	
	private enum ArmState {
		DOWN,
		UP,
		FOLDED
	}
	
}