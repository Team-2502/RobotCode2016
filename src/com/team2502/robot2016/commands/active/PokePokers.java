package com.team2502.robot2016.commands.active;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.ActiveIntake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PokePokers extends Command {

	private ActiveIntake ai = Robot.active;
	private boolean state;
	
    public PokePokers(boolean state) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.active);
    	this.state = state;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (state) {
    		ai.openPokers();
    	} else {
    		ai.closerPokers();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
