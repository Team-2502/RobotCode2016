package com.team2502.robot2016.commands.shooter;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.subsystems.Shooter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

public class ShootBallCommand extends Command
{
    private Shooter ballShooter = Robot.ballShooter;
    private DigitalInput         limitSwitch = new DigitalInput(RobotMap.SHOOTER_LIMIT_SWITCH);

    /**
     * Lower the bar if it is not already at the lowest possible point.
     * Lowest Point = limitSwitch.get() == true
     */
    @Override
    protected void initialize()
    {
        if(!limitSwitch.get())
        {
            ballShooter.prepareShot();
            while(!limitSwitch.get())
            {
            }
            ballShooter.stop();
        }
    }

    /**
     * Shoot the boulder.
     */
    @Override
    protected void execute()
    {
        ballShooter.shoot();
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }

    /**
     * Stop the lowering of the bar.
     */
    @Override
    protected void end()
    {
        ballShooter.stop();
    }

    /**
     * Emergency stop the lowering of the bar.
     */
    @Override
    protected void interrupted()
    {
        ballShooter.stop();
    }
}