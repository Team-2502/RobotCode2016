package com.team2502.robot2016.commands.active;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.subsystems.ActiveIntake;
import com.team2502.robot2016.subsystems.Sensors;
import com.team2502.robot2016.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PokerAuto extends Command {

	private ActiveIntake ai = Robot.active;
	private Sensors se = Robot.sensors;
	
    public PokerAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(ai);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	ai.openPokers();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if ((se.getRightBallSensor() > RobotMap.BALL_VOLT_SHOOTER || se.getLeftBallSensor() > RobotMap.BALL_VOLT_SHOOTER)) {
    		ai.closerPokers();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !ai.pokersOpen();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
