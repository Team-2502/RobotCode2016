package com.team2502.robot2016;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import com.team2502.robot2016.commands.active.AutoActive;
import com.team2502.robot2016.commands.active.PokePokers;
import com.team2502.robot2016.commands.active.PokerTest;
import com.team2502.robot2016.commands.active.SpinActive;
import com.team2502.robot2016.commands.active.ToggleActive;
import com.team2502.robot2016.commands.drive.RotateToAngle;
import com.team2502.robot2016.commands.shooter.Climber;
import com.team2502.robot2016.commands.shooter.ClimberEnd;
import com.team2502.robot2016.commands.shooter.ClimberWinch;
import com.team2502.robot2016.commands.shooter.ReloadShooter;
import com.team2502.robot2016.commands.shooter.RingLight;
import com.team2502.robot2016.commands.shooter.ShootAndReload;
import com.team2502.robot2016.commands.shooter.ShootBall;
//import com.team2502.robot2016.commands.active.SpinActive;
//import com.team2502.robot2016.commands.drive.Climber;
//import com.team2502.robot2016.commands.shooter.ShootAndReload;
//import com.team2502.robot2016.subsystems.ActiveIntake;
import com.team2502.robot2016.subsystems.ActiveIntake;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
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
	private static Joystick buttonStick;
	
	private static Button rollerButtonIn;
	private static Button rollerButtonOut;
	
	private static Button testRollerButtonIn;
	private static Button testRollerButtonOut;
	
	private static Button testRotateCommand;
	private static Button testRingLight;

	private static Button activeDown;
	
//	private static Button winchUp;
//	private static Button winchDown;
	
//	private static Button climbButton;
	private static Button shootButton;
	
	private static Button pokerButton;
	private static Button pokerTest;
		
//	private static Button rollerButton;

	
	public OI() {
		
		buttonStick = new Joystick(RobotMap.BUTTON_JOYSTICK);
		driveLeftStick = new Joystick(RobotMap.LEFT_JOYSTICK);
		driveRightStick = new Joystick(RobotMap.RIGHT_JOYSTICK);
		
		rollerButtonIn = new JoystickButton(buttonStick, 3);
		rollerButtonOut = new JoystickButton(buttonStick, 4);
		
//		testRollerButtonIn = new JoystickButton(buttonStick, 11);
//		testRollerButtonOut = new JoystickButton(buttonStick, 12);
		
		testRotateCommand = new JoystickButton(driveRightStick, 8);
		testRingLight = new JoystickButton(buttonStick, 7);

		
//		winchUp = new JoystickButton(buttonStick, 12);
//		winchDown = new JoystickButton(buttonStick, 10);
//				
//		climbButton = new JoystickButton(buttonStick, 11);
		shootButton = new JoystickButton(buttonStick, 1);
		
		activeDown = new JoystickButton(buttonStick, 2);
		
		pokerButton = new JoystickButton(buttonStick, 6);
		
//		pokerTest = new JoystickButton(buttonStick, 12);
		
//		pokerTest.whenPressed(new PokerTest());

		rollerButtonIn.whenPressed(new SpinActive(-1, false));
		rollerButtonOut.whenPressed(new SpinActive(1, false));
		
//		testRollerButtonIn.whenPressed(new AutoActive());
		testRotateCommand.whenPressed(new RotateToAngle(0));
		testRingLight.whenPressed(new RingLight());
		
//		testRollerButtonOut.whenPressed(new SpinActive(1, true));
		
//		winchUp.whenPressed(new Climber(Climber.UP, 4));
//		winchDown.whenPressed(new Climber(Climber.DOWN, .3));
		
//		winchUp.whileHeld(new Climber(Climber.UP, 4, true));
//		winchDown.whileHeld(new Climber(Climber.DOWN, .3, true));
		
//		winchUp.whenPressed(new ClimberWinch(true));
//		winchDown.whileHeld(new ClimberWinch(false));
		
		
		activeDown.whenPressed(new ToggleActive());
		
		pokerButton.whenPressed(new PokePokers(true));
		pokerButton.whenReleased(new PokePokers(false));
		
		//This one for auto climbing
//		climbButton.whenPressed(new Climber());
		
		//This one for hold button to hook and then climb
//		climbButton.whileHeld(new Climber(Climber.UP, 4.8));

//		shootButton.whenPressed(new ShootAndReload());
//		shootButton.whenPressed(new ShootBall());
		shootButton.whenPressed(new ShootAndReload());

//		shootButton.whenReleased(new ReloadShooter());
		
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
	
	public static Joystick getButtonStick() {
		return buttonStick;
	}
}

