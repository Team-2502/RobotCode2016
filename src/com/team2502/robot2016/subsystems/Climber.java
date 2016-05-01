package com.team2502.robot2016.subsystems;

import com.team2502.robot2016.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private final CANTalon climberWinchLeft  = new CANTalon(RobotMap.CLIMBER_WINCH_LEFT);
    private final CANTalon climberWinchRight = new CANTalon(RobotMap.CLIMBER_WINCH_RIGHT);

    public static final double CLIMB_UP_SPEED = 1;
    public static final double CLIMB_DOWN_SPEED = -1;

    
    public enum ClimbMode {
    	Left, Right, Both
    }
    	
    @Override
    protected void initDefaultCommand() {

    }

    public void setLeft(double speed) {
        climberWinchLeft.set(speed);
    }

    public void setRight(double speed) {
        climberWinchRight.set(speed);
    }
    
    public void runClimber(double speed) {
    	setLeft(speed);
    	setRight(speed);
    }
    
    public void stopClimber() {
    	climberWinchLeft.set(0);
    	climberWinchRight.set(0);

    }
}

