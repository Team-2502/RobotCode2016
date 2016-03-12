package com.team2502.robot2016.commands.drive;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.PIDDriveTrain;
import com.team2502.robot2016.subsystems.Sensors;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {

	private PIDDriveTrain dt = Robot.driveTrain;
	private Sensors s = Robot.sensors;
	private double speed;
	
	private int numTimesClose = 0;
	
	
    public DriveStraight(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	dt.setSetpoint(0);
    	dt.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	dt.driveStraight(speed);
    	if (s.getDistanceFront() < Sensors.CLOSE_TO_TOWER)
    		numTimesClose++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return s.getDistanceFront() < Sensors.CLOSE_TO_TOWER;
    }

    // Called once after isFinished returns true
    protected void end() {
    	dt.stopDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
