package com.team2502.robot2016.commands.shooter;

import com.team2502.robot2016.OI;
import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.Climber;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Climb extends Command
{

    private final Climber m_climber = Robot.climber;
    private final MotorMode        m_motor;
    private final double           m_speed;

    /**
     * @param motor
     *            Which set of motors to use.
     * @param speed
     *            The speed at which they run (Positive = Raise & Negative =
     *            Lower)
     */
    public Climb(MotorMode motor, double speed)
    {
        m_motor = motor;
        m_speed = speed;
        requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize()
    {}

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute()
    {
        switch(m_motor)
        {
            case LEFT:
                m_climber.setLeft(m_speed);
                break;
            case RIGHT:
                m_climber.setRight(m_speed);
                break;
            case BOTH:
                m_climber.setLeft(m_speed);
                m_climber.setRight(m_speed);
                break;
        }
    }

    /**
     * Verifies that the button(s) that run the current motor setup are
     * released. If this function returns true {@link end()} will be called.
     */
    @Override
    protected boolean isFinished()
    {
        switch(m_motor)
        {
            case LEFT:
                return !OI.getInstance().getButtonStick().getRawButton(11);
            case RIGHT:
                return !OI.getInstance().getButtonStick().getRawButton(12);
            case BOTH:
                return !OI.getInstance().getButtonStick().getRawButton(9) && !OI.getInstance().getButtonStick().getRawButton(7);
            default:
                return true;
        }
    }

    // Called once after isFinished returns true
    @Override
    protected void end()
    {
        switch(m_motor)
        {
            case LEFT:
                m_climber.setLeft(0);
                break;
            case RIGHT:
                m_climber.setRight(0);
                break;
            case BOTH:
                m_climber.setLeft(0);
                m_climber.setRight(0);
                break;
            default:
                m_climber.setLeft(0);
                m_climber.setRight(0);
                break;
        }
    }

    /**
     * If something goes wrong (is interrupted) end climbing immediately no
     * matter what.
     */
    @Override
    protected void interrupted()
    {
        end();
    }

    /**
     * LEFT: Left motors only, RIGHT: Right motors only, BOTH: Both motors;
     */
    public static enum MotorMode
    {
        LEFT, RIGHT, BOTH;
    }
}
