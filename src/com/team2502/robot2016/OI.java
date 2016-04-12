package com.team2502.robot2016;

import com.team2502.robot2016.commands.active.ActiveController;
import com.team2502.robot2016.commands.active.SpinActive;
import com.team2502.robot2016.commands.shooter.Climb;
import com.team2502.robot2016.commands.shooter.Climb.MotorMode;
import com.team2502.robot2016.commands.shooter.GShootAndReload;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

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

    private Joystick buttonStick;
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
        buttonStick = new Joystick(RobotMap.Joystick.FUNCTION_CONTROL_JOYSTICK);
        driveLeftStick = new Joystick(RobotMap.Joystick.LEFT_JOYSTICK);
        driveRightStick = new Joystick(RobotMap.Joystick.RIGHT_JOYSTICK);

        rollerButtonIn = new JoystickButton(buttonStick, RobotMap.Joystick.Button.ROLLER_CONTROL_IN);
        rollerButtonOut = new JoystickButton(buttonStick, RobotMap.Joystick.Button.ROLLER_CONTROL_OUT);

        climbLowerButton = new JoystickButton(buttonStick, 9);
        climbRaiseButton = new JoystickButton(buttonStick, 7);

        shootButton = new JoystickButton(buttonStick, 1);

        activeDown = new JoystickButton(buttonStick, 2);

        pokerButton = new JoystickButton(buttonStick, 6);

        rollerButtonIn.whenPressed(new SpinActive(-1, false));
        rollerButtonOut.whenPressed(new SpinActive(1, false));

        activeDown.whenPressed(new ActiveController(2));

        pokerButton.whenPressed(new ActiveController(1));
        pokerButton.whenReleased(new ActiveController(0));

        climbRaiseButton.whileHeld(new Climb(MotorMode.BOTH, 1));
        climbLowerButton.whileHeld(new Climb(MotorMode.BOTH, -1));

        shootButton.whenPressed(new GShootAndReload());
    }

    public Joystick getButtonStick()
    {
        return buttonStick;
    }
    
<<<<<<< HEAD
    public Joystick getLeftStick()
    {
        return driveLeftStick;
    }
=======
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
	
//	private static Button winchUp;
//	private static Button winchDown;
	
//	private static Button climbButton;
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
>>>>>>> parent of 3562747... more something and pressure switch

    public Joystick getRightStick()
    {
        return driveRightStick;
    }
}
