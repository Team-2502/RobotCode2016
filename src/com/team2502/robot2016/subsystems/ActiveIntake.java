package com.team2502.robot2016.subsystems;

import com.team2502.robot2016.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ActiveIntake extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private final CANTalon rollerBar = new CANTalon(RobotMap.ACTIVE_ROLLER_BAR);
	public static final int FORWARD = 1;
	public static final int BACKWARD = -FORWARD;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void spinRollerBar() {
    	rollerBar.set(1);
    }
    
    public void stopRollerBar() {
    	rollerBar.set(0);
    }
    
    public void setRollerBar(double speed) {
    	rollerBar.set(speed);
    }
}

