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
	
	private final CANTalon climberWinchLeft;
	private final CANTalon climberWinchRight;

	
	public static final double CLIMBER_UP_SPEED = 1;
	public static final double CLIMBER_DOWN_SPEED = -.5;

	public Climber() {
		climberWinchLeft = new CANTalon(RobotMap.CLIMBER_WINCH_LEFT);
		climberWinchRight = new CANTalon(RobotMap.CLIMBER_WINCH_RIGHT);

		climberWinchLeft.enableBrakeMode(true);
		climberWinchRight.enableBrakeMode(true);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void runClimber(double speed) {
    	climberWinchLeft.set(speed);
    	climberWinchRight.set(speed);

    }
    
    public void stopClimber() {
    	climberWinchLeft.set(0);
    	climberWinchRight.set(0);

    }
    
    public void runLeft(double speed) {
    	climberWinchLeft.set(speed);
    }
    
    public void runRight(double speed) {
    	climberWinchRight.set(speed);
    }
    
    public void runBoth(double speedLeft, double speedRight) {
    	climberWinchLeft.set(speedLeft);
    	climberWinchRight.set(speedRight);

    }
}

