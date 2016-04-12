package com.team2502.robot2016.commands.drive;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankDriveSix extends Command
{

    // private DriveTrain dt = Robot.driveTrain;
    private DriveTrain dt = Robot.driveTrain;

    public TankDriveSix()
    {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize()
    {}

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute()
    {
        dt.driveSix();

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished()
    {
        return false;
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