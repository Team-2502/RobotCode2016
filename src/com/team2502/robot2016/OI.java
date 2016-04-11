package com.team2502.robot2016;

import com.team2502.robot2016.commands.active.CommandActiveController;
import com.team2502.robot2016.commands.active.SpinActive;
import com.team2502.robot2016.commands.drive.RotateToAngle;
import com.team2502.robot2016.commands.shooter.CommandClimber;
import com.team2502.robot2016.commands.shooter.CommandClimber.MotorMode;
import com.team2502.robot2016.commands.shooter.CommandGShootAndReload;
import com.team2502.robot2016.commands.shooter.CommandRingLight;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

<<<<<<< HEAD
=======
import com.team2502.robot2016.commands.active.PokePokers;
import com.team2502.robot2016.commands.active.SpinActive;
import com.team2502.robot2016.commands.active.ToggleActive;
import com.team2502.robot2016.commands.drive.RotateToAngle;
import com.team2502.robot2016.commands.shooter.ClimberOptions;
import com.team2502.robot2016.commands.shooter.RingLight;
import com.team2502.robot2016.commands.shooter.ShootAndReload;
import com.team2502.robot2016.subsystems.Shooter;

>>>>>>> origin/master
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
    private static OI INSTANCE = null;

    public static OI getInstance()
    {
        if(INSTANCE == null)
        {
            INSTANCE = new OI();
        }
        return INSTANCE;
    }

    private Joystick functionControlStick;
    private Joystick driveLeftStick;
    private Joystick driveRightStick;

    private Button   rollerButtonIn;
    private Button   rollerButtonOut;

    private Button   activeDown;

    private Button   climbLowerButton;
    private Button   climbRaiseButton;

    private Button   shootButton;

    private Button   pokerButton;

    public OI()
    {
        functionControlStick = new Joystick(RobotMap.Joystick.FUNCTION_CONTROL_JOYSTICK);
        driveLeftStick = new Joystick(RobotMap.Joystick.LEFT_JOYSTICK);
        driveRightStick = new Joystick(RobotMap.Joystick.RIGHT_JOYSTICK);

        rollerButtonIn = new JoystickButton(functionControlStick, RobotMap.Joystick.Button.ROLLER_CONTROL_IN);
        rollerButtonOut = new JoystickButton(functionControlStick, RobotMap.Joystick.Button.ROLLER_CONTROL_OUT);
        
        climbLowerButton = new JoystickButton(functionControlStick, 9);
        climbRaiseButton = new JoystickButton(functionControlStick, 7);

        shootButton = new JoystickButton(functionControlStick, 1);

        activeDown = new JoystickButton(functionControlStick, 2);

        pokerButton = new JoystickButton(functionControlStick, 6);

        rollerButtonIn.whenPressed(new SpinActive(-1, false));
        rollerButtonOut.whenPressed(new SpinActive(1, false));

        activeDown.whenPressed(new CommandActiveController(2));

        pokerButton.whenPressed(new CommandActiveController(1));
        pokerButton.whenReleased(new CommandActiveController(0));

        climbRaiseButton.whileHeld(new CommandClimber(MotorMode.BOTH, 1));
        climbLowerButton.whileHeld(new CommandClimber(MotorMode.BOTH, -1));

        shootButton.whenPressed(new CommandGShootAndReload());
    }

    public Joystick getLeftStick()
    {
        return driveLeftStick;
    }

<<<<<<< HEAD
    public Joystick getRightStick()
    {
        return driveRightStick;
    }
=======
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
	
	private static Button testRotateCommand;
	private static Button testRingLight;

	private static Button activeDown;
	
	private static Button climbUpButton;
	private static Button climbDownButton;


	private static Button shootButton;
	
	private static Button pokerButton;
		
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
	
		climbUpButton = new JoystickButton(buttonStick, 11);
		climbDownButton = new JoystickButton(buttonStick, 12);

		shootButton = new JoystickButton(buttonStick, 1);
		
		activeDown = new JoystickButton(buttonStick, 2);
		
		pokerButton = new JoystickButton(buttonStick, 6);
		
		rollerButtonIn.whenPressed(new SpinActive(-1, false));
		rollerButtonOut.whenPressed(new SpinActive(1, false));
		
		testRotateCommand.whenPressed(new RotateToAngle(0));
		
		activeDown.whenPressed(new ToggleActive());
		
		pokerButton.whenPressed(new PokePokers(true));
		pokerButton.whenReleased(new PokePokers(false));
		
		climbUpButton.toggleWhenPressed(new ClimberOptions(Shooter.CLIMBER_UP_SPEED));
		climbDownButton.toggleWhenPressed(new ClimberOptions(Shooter.CLIMBER_DOWN_SPEED));

		shootButton.whenPressed(new ShootAndReload());

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
>>>>>>> origin/master

    public Joystick getButtonStick()
    {
        return functionControlStick;
    }
}
