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
    
    public Joystick getLeftStick()
    {
        return driveLeftStick;
    }

    public Joystick getRightStick()
    {
        return driveRightStick;
    }
}
