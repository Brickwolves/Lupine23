package org.firstinspires.ftc.teamcode.Sample;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Controls.Controller;

import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.ButtonState.DOWN;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.CIRCLE;


@TeleOp(name="Switch Demo 2", group="Iterative Opmode")
public class Demo3SwitchConditionals extends OpMode {
	
	int demoInt = 0;
	
	Controller controller;
	
	@Override
	public void init() {
		
		controller = new Controller(gamepad1);
		
	}
	
	
	@Override
	public void loop() {
		
		switch (demoInt){
			
			case 1:
				telemetry.addData("case", 1);
				
				if (controller.buttons.get(CIRCLE, DOWN)) demoInt = 2;
				
				break;
			
				
			case 2:
				telemetry.addData("case", 2);
				
				if(controller.buttons.get(CIRCLE, DOWN)) demoInt = 3;
				
				break;
			
				
			case 3:
				telemetry.addData("case", 3);
				
				if (controller.buttons.get(CIRCLE, DOWN)) demoInt = 1;
				
				break;
			
			
		}
		
	}
}