package com.team2502.robot2016.commands.shooter;

import com.team2502.robot2016.OI;
import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.DriveTrain;
import com.team2502.robot2016.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberWinch extends Command {

	private boolean manual = true;
	private boolean up = true;
	
	private Shooter s = Robot.ballShooter;
    public ClimberWinch() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ballShooter);
    }
    
    public ClimberWinch(boolean dir) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ballShooter);
//    	manual = false;
    	this.up = dir;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if (s.getClimberState() && Math.abs(OI.getButtonStick().getY()) > .2) {
//    		s.launchScissors(false);
//    	}
    	
    	
//    	if (manual)
//    	if (s.climberUp) {
//    	if (dir == 0)
    	
    	
    	double direction = (up) ? -OI.getButtonStick().getY() : -.6;
//    	if (OI.getButtonStick().getRawButton(10)) {
//    		direction = OI.getButtonStick().getY();
//    	}
    	
    		s.runClimber(direction);
//    	}
//    	else
//    		s.runClimber(speed);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//        return OI.getButtonStick().getRawButton(12);
    	return !up;
    }

    // Called once after isFinished returns true
    protected void end() {
    	s.stopClimber();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
