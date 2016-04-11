package com.team2502.robot2016.commands.shooter;

import com.team2502.robot2016.OI;
import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberOptions extends Command {

	private Shooter s = Robot.ballShooter;
	private double speed;
	
    public ClimberOptions(double dir) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.speed = dir;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch (Robot.getClimberOption()) {
    	
    	case "Buttons":
        	s.runClimber(speed);

    		break;
    		
    	case "Joystick" :
    		s.runClimber(OI.getButtonStick().getY());
    		break;
    	}
    	
    	
    	
    	
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	s.stopClimber();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
