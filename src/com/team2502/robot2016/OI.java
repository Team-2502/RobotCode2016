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

    public Joystick getRightStick()
    {
        return driveRightStick;
    }

    public Joystick getButtonStick()
    {
        return functionControlStick;
    }
}
