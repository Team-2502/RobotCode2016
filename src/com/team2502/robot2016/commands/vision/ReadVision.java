package com.team2502.robot2016.commands.vision;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.Vision;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReadVision extends Command {

	private Vision v = Robot.vision;
	
    public ReadVision() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//read vision things and DONT BLOCK
    	double visionVal = 0;
    	boolean newVisionVals = false;
    	if (newVisionVals) {
			//Needs to be -15 to 15
    		v.setLatestVision(visionVal);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
