package com.team2502.robot2016.commands;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.subsystems.BallShooterSubsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

public class ShootBallCommand extends Command
{
    private BallShooterSubsystem ballShooter = Robot.ballShooter;
    private DigitalInput         limitSwitch = new DigitalInput(RobotMap.SHOOTER_LIMIT_SWITCH);

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

    @Override
    protected void end()
    {
        ballShooter.stop();
    }

    @Override
    protected void interrupted()
    {
        ballShooter.stop();
    }
}