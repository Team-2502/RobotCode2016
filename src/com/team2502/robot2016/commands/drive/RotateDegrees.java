package com.team2502.robot2016.commands.drive;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.DriveTrain;
import com.team2502.robot2016.subsystems.Sensors;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateDegrees extends Command {

	private DriveTrain dt = Robot.driveTrain;
	private Sensors s = Robot.sensors;
	
	private double initialDegrees;
	
	private double degrees;
	private double speed;
	
    public RotateDegrees(double degrees, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	requires(Robot.sensors);
    	
    	this.degrees = degrees;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initialDegrees = s.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	dt.turn(speed, degrees > 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(s.getAngle() - initialDegrees - degrees) < 3;
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
