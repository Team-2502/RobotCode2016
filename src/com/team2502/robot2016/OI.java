package com.team2502.robot2016;

import com.team2502.robot2016.commands.active.CommandActiveController;
import com.team2502.robot2016.commands.active.CommandActiveFrame;
import com.team2502.robot2016.commands.active.CommandBallPokers;
import com.team2502.robot2016.commands.active.SpinActive;
import com.team2502.robot2016.commands.shooter.CommandClimber;
import com.team2502.robot2016.commands.shooter.CommandClimber.MotorMode;
import com.team2502.robot2016.commands.shooter.CommandGShootAndReload;
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

    private Joystick functionControlStick;
    private Joystick driveLeftStick;
    private Joystick driveRightStick;

    private Button   rollerButtonIn;
    private Button   rollerButtonOut;

    private Button   activeDown;

    private Button   climbLowerButton;
    private Button   climbRaiseButton;
    private Button   climbLeftButton;
    private Button   climbRightButton;

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
        climbRaiseButton = new JoystickButton(functionControlStick, 8);
        climbLeftButton = new JoystickButton(functionControlStick, 11);
        climbRightButton = new JoystickButton(functionControlStick, 12);

        shootButton = new JoystickButton(functionControlStick, 1);
        //shootButton = new JoystickButton(driveLeftStick, 1);

        activeDown = new JoystickButton(functionControlStick, 2);

        pokerButton = new JoystickButton(functionControlStick, 6);

        rollerButtonIn.whenPressed(new SpinActive(-1, false));
        rollerButtonOut.whenPressed(new SpinActive(1, false));

        activeDown.whenPressed(new CommandActiveFrame());

        pokerButton.whenPressed(new CommandBallPokers(true));
        pokerButton.whenReleased(new CommandBallPokers(false));

        climbLowerButton.whileHeld(new CommandClimber(MotorMode.BOTH, -1));
        climbRaiseButton.whileHeld(new CommandClimber(MotorMode.BOTH, 1));
        climbLeftButton.whileHeld(new CommandClimber(MotorMode.LEFT, 1));
        climbRightButton.whileHeld(new CommandClimber(MotorMode.RIGHT, 1));

        shootButton.whenPressed(new CommandGShootAndReload());
    }

    public Joystick getFunctionControlStick()
    {
        return functionControlStick;
    }
    
    public Joystick getLeftStick()
    {
        return driveLeftStick;
    }

    public Joystick getRightStick()
    {
        return driveRightStick;
    }
}
