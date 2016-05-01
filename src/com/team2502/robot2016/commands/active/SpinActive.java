package com.team2502.robot2016.commands.active;

import com.team2502.robot2016.OI;
import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.ActiveBar;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpinActive extends Command {

	private ActiveBar ab = Robot.activeBar;

	private double speed;
	private boolean flipped = false;
	private boolean test = false;
	
    public SpinActive(double speed, boolean test) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.activeBar);
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	ab.setRollerBar(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {

        return flipped || ((!OI.getButtonStick().getRawButton(3) && !OI.getButtonStick().getRawButton(4)) && !test);
    }

    // Called once after isFinished returns true
    protected void end() {
    	ab.stopRollerBar();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end ();
    }
}
