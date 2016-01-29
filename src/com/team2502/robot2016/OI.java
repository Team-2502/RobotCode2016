package com.team2502.robot2016;

import com.team2502.robot2016.commands.active.SpinActive;
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
	
	private static Joystick driveStick;
	private static Joystick shooterStick;
	
	private static Button rollerButtonIn;
	private static Button rollerButtonOut;
//	private static Button rollerButton;

	
	public OI() {
		driveStick = new Joystick(RobotMap.RIGHT_JOYSTICK);
		shooterStick = new Joystick(RobotMap.LEFT_JOYSTICK);
		
		rollerButtonIn = new JoystickButton(shooterStick, 2);
		rollerButtonOut = new JoystickButton(shooterStick, 3);

		
		rollerButtonIn.toggleWhenPressed(new SpinActive(ActiveIntake.FORWARD));
		rollerButtonOut.toggleWhenPressed(new SpinActive(ActiveIntake.BACKWARD));
		
//		rollerButtonIn.cancelWhenActive(new SpinActive(0));
//		rollerButtonOut.cancelWhenActive(new SpinActive(0));


	}
	
	public static Joystick getDriveStick() {
		return driveStick;
	}

	public static Joystick getLiftStick() {
		return shooterStick;
	}
	
}

