package com.team2502.robot2016.commands.shooter;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.DriveTrain;
import com.team2502.robot2016.subsystems.Sensors;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CorrectDistance extends Command {

    private DriveTrain dt = Robot.driveTrain;
    private Sensors s = Robot.sensors;
    
    private boolean ifLineUpDistance;
    private double farLineUpDistance;
    private double closeLineUpDistance;
    private double speed;

	
    public CorrectDistance(boolean ifLineUpDistance, double speed, double ... lineUpDistances) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	requires(Robot.sensors);
    	this.ifLineUpDistance = ifLineUpDistance;
    	if (ifLineUpDistance) {
	    	this.farLineUpDistance = lineUpDistances[1];
	    	this.closeLineUpDistance = lineUpDistances[0];
	    	this.speed = speed;
    	}
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (ifLineUpDistance) {
    		//Convert to volts here, haven't done that yet. Need to test that
    		double farLineUpVolts = farLineUpDistance;
    		double closeLineUpVolts = closeLineUpDistance;

    		s.setFrontVoltageLimits(farLineUpVolts, closeLineUpVolts);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	int direction = (s.tooClose()) ? -1 : 1;
    	dt.runMotors(speed * direction, speed * direction);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return s.isCloseEnough();
    }

    // Called once after isFinished returns true
    protected void end() {
    	dt.stopDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
//    	end();
    }
}
