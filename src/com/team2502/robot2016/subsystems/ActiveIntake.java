package com.team2502.robot2016.subsystems;

import com.team2502.robot2016.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ActiveIntake extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private final Solenoid activeSolenoid = new Solenoid(RobotMap.ACTIVE_SOLENOID);
	private final Solenoid pokerSolenoid = new Solenoid(RobotMap.POKER_SOLENOID);
	
	public ActiveIntake() {
		System.out.println("Active Intake");
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    
    public void setActiveState(boolean down) {
    	activeSolenoid.set(down);
    }
    
    public boolean getActiveState() {
    	return activeSolenoid.get();
    }
    
    public void closerPokers() {
    	setPokers(false);
    }
    
    public void openPokers() {
    	setPokers(true);
    }
    
    private void setPokers(boolean state) {
    	pokerSolenoid.set(state);
    }
    
    public boolean pokersOpen() {
    	return pokerSolenoid.get();
    }
} 

