package com.team2502.robot2016.commands.shooter;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.ActiveIntake;
import com.team2502.robot2016.subsystems.Sensors;
//import com.team2502.robot2016.subsystems.Sensors;
import com.team2502.robot2016.subsystems.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ShootBall extends Command {
	
    private Shooter bs = Robot.ballShooter;
    private ActiveIntake ai = Robot.active;
    private Sensors s = Robot.sensors;
    
    public ShootBall() {
    	requires(Robot.ballShooter);
//    	requires(Robot.active);
//    	requires(Robot.sensors);
    }
    
    /**
     * Lower the bar if it is not already at the lowest possible point.
     * Lowest Point = limitSwitch.get() == true
     */
    protected void initialize() {
    	Robot.driveTrain.brakeMode(true);
    }

    /**
     * Shoot the boulder.
     */
    protected void execute() {
    	
//    	if (!ai.getActiveState()) {
//    		ai.setActiveState(true);
//    		Timer.delay(1.4);
//    	} else {
//    	}
		
    	
    	
		
		if ((s.getAngle() < 3 || s.getAngle() > 357) || !Robot.inAuto) {
	    	ai.openPokers();
    		Timer.delay(.1);
    		if (Robot.inAuto) Timer.delay(.5);
    		System.err.println("Shot");
			bs.setSolenoid(true);

		}

    	
    }

    protected boolean isFinished() {
//        return s.shooterAllTheWayForward();
        return true;
    }

    /**
     * Stop the lowering of the bar.
     */
    protected void end() {
    	Timer.delay(1);               // REVIEW NJL

    	ai.closerPokers();

    	Robot.driveTrain.brakeMode(false);


    }

    /**
     * Emergency stop the lowering of the bar.
     */
    protected void interrupted() {

    }
}