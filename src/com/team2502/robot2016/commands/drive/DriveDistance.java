package com.team2502.robot2016.commands.drive;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.DriveTrain;
import com.team2502.robot2016.subsystems.DriveTrain.Motors;
import com.team2502.robot2016.subsystems.PIDDriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistance extends Command {

//	private DriveTrain dt = Robot.driveTrain;
	private PIDDriveTrain dt = Robot.driveTrain;

	
	private double distance;
	private double initialPosition;
	private double finalPosition;
	private double speed;
	
    public DriveDistance(double distance, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	this.distance = distance;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	initialPosition = dt.getEncoderValue(Motors.LEFT_MOTORS);
//    	double ticksUntilDone = distance / (2 * DriveTrain.wheelRadius * Math.PI) * DriveTrain.TICKS_PER_REV;
//    	
//    	finalPosition = initialPosition + ticksUntilDone;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	dt.runMotors(speed, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//        return Math.abs(finalPosition - dt.getEncoderValue(Motors.LEFT_MOTORS)) < 30;
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	dt.stopDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
