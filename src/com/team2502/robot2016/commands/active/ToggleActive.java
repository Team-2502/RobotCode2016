package com.team2502.robot2016.commands.active;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.ActiveIntake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleActive extends Command {

	private ActiveIntake ai = Robot.active;
	
    public ToggleActive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.active);
    	requires(Robot.ballShooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (!ai.pokersOpen() && ai.getActiveState()) {
    		ai.openPokers();
    	}
    	
//    	boolean stateThing
    	
//    	if (!s.getShooterState())
    		ai.setActiveState(!ai.getActiveState());
    	
    	
    	
//    	if (!s.getShooterState()) {
//    		if (!ai.pokersOpen() && ai.getActiveState()) {
//        		ai.openPokers();
//        	}
//    		
//    		if (custom) {
//    			ai.setActiveState(state);
//    		} else {
//	    		if (!flipped) {
//		    		ai.setActiveState(!ai.getActiveState());
//		    		flipped = true;
//	    		}
//    		}
//    	}
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//        return se.getLeftBallSensor() > RobotMap.BALL_VOLT_SHOOTER && flipped;
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	ai.closerPokers();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
