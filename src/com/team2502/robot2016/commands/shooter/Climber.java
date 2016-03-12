package com.team2502.robot2016.commands.shooter;

import com.team2502.robot2016.OI;
import com.team2502.robot2016.Robot;
import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.subsystems.DriveTrain;
//import com.team2502.robot2016.subsystems.Sensors;
import com.team2502.robot2016.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Climber extends Command {

	public static final int UP = 1;
	public static final int DOWN = -1;
	
	private Shooter s = Robot.ballShooter;
//	private Sensors s = Robot.sensors;
	
	private double voltageLimit;
	private double speed;
	
	private boolean ifHooked = false;
	private long time;
	private boolean manual;
	private boolean launch = false;
	
    public Climber() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ballShooter);
//    	requires(Robot.sensors);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	s.setClimberVoltageLimits(voltageLimit);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
//    	if (OI.getButtonStick().getRawButton(7) && OI.getLeftStick().getRawButton(6))
//    		launch = true;
    	
    		s.launchScissors(!s.getClimberState());
    	
//    	if (OI.getButtonStick().getRawButton(RobotMap.CLIMBER_BUTTON_STICK_BUTTON) || ifHooked) {
//	    	dt.launchScissors(OI.getButtonStick().getRawButton(RobotMap.CLIMBER_BUTTON_STICK_BUTTON));
	    	
//	    	if (ifHooked) {
////	    		if (System.currentTimeMillis() - time > 500) {
//	    		if (!OI.getButtonStick().getRawButton(RobotMap.CLIMBER_BUTTON_STICK_BUTTON)) {
//	    			dt.launchScissors(false);
//	    		}
//	    		if (manual) {
//	    			dt.runClimber(OI.getButtonStick().getY());
//	    		} else {
//	    			dt.runClimber(speed);
//	    		}
//	    	} else {
//	    		dt.launchScissors(true);
//	        	time = System.currentTimeMillis();
//	    		ifHooked = true;
//	    	}
//    	} else {
//	    	}
//    		if (!OI.getButtonStick().getRawButton(7)) dt.launchScissors(false);
//    		if (ifHooked) {
//    			dt.runClimber(OI.getButtonStick().getY());
//    		} else {
//    			dt.runClimber(0);
//    		}
//    		dt.runClimber(OI.getButtonStick().getY());
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//        return !s.stillClimbing();
//    	return !OI.getButtonStick().getRawButton(11);
//    	return s.getClimberState() && !OI.getButtonStick().getRawButton(11);
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
//    	dt.stopClimber();
//    	s.launchScissors(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
//    end();
    }
}
