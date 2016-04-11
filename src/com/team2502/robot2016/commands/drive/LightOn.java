package com.team2502.robot2016.commands.drive;

import com.team2502.robot2016.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LightOn extends Command
{

    private double time;
    private double startTime;

    public LightOn(double time)
    {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.sensors);
        this.time = time;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize()
    {
        startTime = System.currentTimeMillis();

        // s.setRingLight(true);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute()
    {}

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished()
    {
        return System.currentTimeMillis() - startTime > time * 1000;

    }

    // Called once after isFinished returns true
    @Override
    protected void end()
    {
        // s.setRingLight(false);

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted()
    {}
}
