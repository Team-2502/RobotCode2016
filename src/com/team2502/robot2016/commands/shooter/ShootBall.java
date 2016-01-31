package com.team2502.robot2016.commands.shooter;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.Shooter;
import edu.wpi.first.wpilibj.command.Command;

public class ShootBall extends Command {
	
    private Shooter bs = Robot.ballShooter;

    public ShootBall() {
    	requires(Robot.ballShooter);
    }
    
    /**
     * Lower the bar if it is not already at the lowest possible point.
     * Lowest Point = limitSwitch.get() == true
     */
    @Override
    protected void initialize() {
    	
    }

    /**
     * Shoot the boulder.
     */
    protected void execute() {
    	bs.setSolenoid(true);
    }

    protected boolean isFinished() {
        return false;
    }

    /**
     * Stop the lowering of the bar.
     */
    protected void end() {
    
    }

    /**
     * Emergency stop the lowering of the bar.
     */
    protected void interrupted() {

    }
}