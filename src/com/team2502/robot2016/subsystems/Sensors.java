package com.team2502.robot2016.subsystems;

import com.team2502.robot2016.RobotMap;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Sensors extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private AnalogTrigger distanceFront;
//	private AnalogInput colorFront;
	private AnalogGyro gyro;
//	private AnalogAccelerometer accel;
	
	private AnalogTrigger climberHighEnough;
	
    private DigitalInput shooterLoadedSwitch;
    private DigitalInput shooterShotSwitch;

    
	public Sensors() {
		climberHighEnough = new AnalogTrigger(RobotMap.CLIMBER_DISTANCE_SENSOR);
		gyro = new AnalogGyro(new AnalogInput(RobotMap.GYRO_SENSOR));
		
		distanceFront = new AnalogTrigger(RobotMap.FRONT_DISTANCE_SENSOR);
		
		shooterLoadedSwitch = new DigitalInput(RobotMap.SHOOTER_LIMIT_SWITCH);
	    shooterShotSwitch = new DigitalInput(RobotMap.SHOOTER_LIMIT_SWITCH);
	    
    	gyro.initGyro();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setClimberVoltageLimits(double high) {
    	climberHighEnough.setLimitsVoltage(high - .2, high);
    }
    
    public void setFrontVoltageLimits(double low, double high) {
    	distanceFront.setLimitsVoltage(low, high);
    }
    
    public boolean stillClimbing() {
    	return !climberHighEnough.getTriggerState();
    }
    
    public boolean isCloseEnough() {
    	return distanceFront.getInWindow();
    }
    
    public boolean tooClose() {
    	return distanceFront.getTriggerState();
    }
    
    public double getAngle() {
    	return gyro.getAngle();
    }
    
    public void calibrateSensors() {
    	gyro.calibrate();
    }
    
    public boolean shooterAllTheWayBack() {
    	return shooterLoadedSwitch.get();
    }
    
    public boolean shooterAllTheWayForward() {
    	return shooterShotSwitch.get();
    }
}

