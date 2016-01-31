package com.team2502.robot2016.commands.drive;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.DriveTrain;
import com.team2502.robot2016.subsystems.Sensors;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Climber extends Command {

	public static final int UP = 1;
	public static final int DOWN = -1;
	
	private DriveTrain dt = Robot.driveTrain;
	private Sensors s = Robot.sensors;
	
	private double voltageLimit;
	private double speed;
	
	private boolean ifHooked = false;
	private long time;
	
    public Climber(double speed, double voltageLimit) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	requires(Robot.sensors);
    	this.voltageLimit = voltageLimit;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	s.setClimberVoltageLimits(voltageLimit);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (ifHooked) {
    		if (System.currentTimeMillis() - time > 20) {
    			dt.launchScissors(false);
    		}
        	dt.runClimber(speed);
    	} else {
    		dt.launchScissors(true);
        	time = System.currentTimeMillis();
    		ifHooked = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !s.stillClimbing();
    }

    // Called once after isFinished returns true
    protected void end() {
    	dt.stopClimber();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
