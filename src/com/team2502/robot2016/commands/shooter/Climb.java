package com.team2502.robot2016.commands.shooter;

import com.team2502.robot2016.OI;
import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.Climber;
import com.team2502.robot2016.subsystems.Climber.ClimbMode;
import com.team2502.robot2016.subsystems.Sensors;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Climb extends Command {

	private Climber c = Robot.climber;
	private double speed;
	private ClimbMode mode;
	
    public Climb(ClimbMode mode, double dir) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.speed = dir;
    	this.mode = mode;
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch (Robot.getClimberOption()) {
    	
    	case "Buttons":
    		switch(mode) {
                case Left:
                    c.setLeft(speed);
                    break;
                case Right:
                    c.setRight(speed);
                    break;
                case Both:
                    c.setLeft(speed);
                    c.setRight(speed);
                    break;
            }
    		break;
    		
    	case "Joystick" :
    		c.runClimber(OI.getButtonStick().getY());
    		if (Sensors.ahrs.getRoll() < -5) {
    			if (Math.abs(OI.getLeftStick().getY()) > .1 || Math.abs(OI.getRightStick().getY()) > .1) {
    				c.setLeft(OI.getLeftStick().getY());
    				c.setRight(OI.getRightStick().getY());
    			}
    		}
    		break;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        
    	switch(mode)
        {
            case Left:
                return !OI.getButtonStick().getRawButton(11);
            case Right:
                return !OI.getButtonStick().getRawButton(12);
            case Both:
                return !OI.getButtonStick().getRawButton(9) && !OI.getButtonStick().getRawButton(7);
            default:
                return true;
        }    	
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
