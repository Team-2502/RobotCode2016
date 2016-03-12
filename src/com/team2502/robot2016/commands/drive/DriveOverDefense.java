package com.team2502.robot2016.commands.drive;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.PIDDriveTrain;
import com.team2502.robot2016.subsystems.Sensors;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveOverDefense extends Command {

	private PIDDriveTrain dt = Robot.driveTrain;
	private Sensors s = Robot.sensors;
	
	private final int BEFORE_DEFENSE = 0;
	private final int IN_DEFENSE = 1;
	private final int AFTER_DEFENSE = 2;
	private int state = 0;

	
    public DriveOverDefense() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch (state) {
    	case BEFORE_DEFENSE :
    		if ((Math.abs(s.getPitch()) > 5) || Math.abs(s.accelZ()) > 1.5) {
    			state++;
    		}
    		dt.driveStraight(.9);
    	case IN_DEFENSE :
    		dt.runMotors(.8, .8);
    		Timer.delay(.4);   // REVIEW NJL
    		
    		if ((Math.abs(s.getPitch()) < 5) || Math.abs(s.accelZ()) < 1.1) {
    			state++;
    		}    	
    	case AFTER_DEFENSE :
//    		dt.runMotors(left, right);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return state == AFTER_DEFENSE;
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
