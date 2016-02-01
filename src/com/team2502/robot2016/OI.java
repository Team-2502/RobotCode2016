package com.team2502.robot2016;

import com.team2502.robot2016.commands.active.SpinActive;
import com.team2502.robot2016.commands.drive.Climber;
import com.team2502.robot2016.commands.shooter.ShootAndReload;
import com.team2502.robot2016.subsystems.ActiveIntake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	private static Joystick driveLeftStick;
	private static Joystick driveRightStick;
	
	private static Button rollerButtonIn;
	private static Button rollerButtonOut;
	
	private static Button winchUp;
	private static Button winchDown;
	
	private static Button climbButton;
	private static Button shootButton;
		
//	private static Button rollerButton;

	
	public OI() {
		driveLeftStick = new Joystick(RobotMap.LEFT_JOYSTICK);
		driveRightStick = new Joystick(RobotMap.RIGHT_JOYSTICK);
		
		rollerButtonIn = new JoystickButton(driveRightStick, 2);
		rollerButtonOut = new JoystickButton(driveRightStick, 3);
		
		winchUp = new JoystickButton(driveLeftStick, 2);
		winchDown = new JoystickButton(driveLeftStick, 3);
				
		climbButton = new JoystickButton(driveRightStick, 4);
		shootButton = new JoystickButton(driveLeftStick, 4);

		rollerButtonIn.toggleWhenPressed(new SpinActive(ActiveIntake.FORWARD));
		rollerButtonOut.toggleWhenPressed(new SpinActive(ActiveIntake.BACKWARD));
		
		winchUp.whenPressed(new Climber(Climber.UP, 4));
		winchDown.whenPressed(new Climber(Climber.DOWN, .3));
		
		//This one for auto climbing
		climbButton.whenPressed(new Climber(Climber.UP, 4));
		
		//This one for hold button to hook and then climb
//		climbButton.whileHeld(new Climber(Climber.UP, 4.8));

		shootButton.whenPressed(new ShootAndReload());
		
//		towerClimb.whenActive(new Climber(Climber.UP, 4));
		
//		rollerButtonIn.cancelWhenActive(new SpinActive(0));
//		rollerButtonOut.cancelWhenActive(new SpinActive(0));


	}
	
	public static Joystick getLeftStick() {
		return driveLeftStick;
	}

	public static Joystick getRightStick() {
		return driveRightStick;
	}
	
}