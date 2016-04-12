package com.team2502.robot2016.commands.shooter;

import com.team2502.robot2016.OI;
import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.Climber;
import com.team2502.robot2016.subsystems.Sensors;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberOptions extends Command {

	private Climber c = Robot.climber;
	private double speed;
	
    public ClimberOptions(double dir) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.speed = dir;
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch (Robot.getClimberOption()) {
    	
    	case "Buttons":
        	c.runClimber(speed);
    		break;
    		
    	case "Joystick" :
    		c.runClimber(OI.getButtonStick().getY());
    		if (Sensors.ahrs.getRoll() < -5) {
    			if (Math.abs(OI.getLeftStick().getY()) > .1 || Math.abs(OI.getRightStick().getY()) > .1) {
    				c.runBoth(OI.getLeftStick().getY(), OI.getRightStick().getY());
    			}
    		}
    		break;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	c.stopClimber();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
