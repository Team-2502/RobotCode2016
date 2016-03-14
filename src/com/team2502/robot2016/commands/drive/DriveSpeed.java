package com.team2502.robot2016.commands.drive;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.PIDDriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveSpeed extends Command {

	private PIDDriveTrain dt = Robot.driveTrain;
	private double speed;
	
    public DriveSpeed(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	dt.runMotors(speed, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	dt.stopDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	dt.stopDrive();
    }
}
