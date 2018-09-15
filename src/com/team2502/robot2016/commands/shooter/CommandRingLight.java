package com.team2502.robot2016.commands.shooter;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.SubsystemSensors;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CommandRingLight extends Command
{

    private SubsystemSensors sensors = Robot.sensors;

    public CommandRingLight()
    {
        requires(Robot.sensors);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize()
    {}

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute()
    {
        sensors.setRingLight(!sensors.ringState);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished()
    {
        return true;
    }

    // Called once after isFinished returns true
    @Override
    protected void end()
    {}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted()
    {}
}
