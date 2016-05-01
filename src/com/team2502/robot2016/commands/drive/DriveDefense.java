package com.team2502.robot2016.commands.drive;

import com.team2502.robot2016.subsystems.Sensors;
import com.team2502.robot2016.subsystems.Sensors.Sensor;


/**
 *
 */
public class DriveDefense extends DriveStraight {

	private static final int BEFORE_DEFENSE = 1;
	private static final int GOING_OVER = 2;
	private static final int FACING_DOWN = 3;
	private static final int FLATTEN_OUT = 4;
	
	private int STATE = 1;
	
	
	public DriveDefense(double angle, double speed, Sensor sensor, double sensorValue) {
		 super(angle, speed, sensor, sensorValue);
	}
    
    public DriveDefense(double angle, double speed, Sensor sensor, double sensorValue, boolean change) {
    	super(angle, speed, sensor, sensorValue, change);
    	
    }
    
    public DriveDefense(double angle, double speed, Sensor sensor, double sensorValue, boolean change, double extraTime) {
    	super(angle, speed, sensor, sensorValue, change, extraTime);
    	
    }
    
    public DriveDefense(double angle, double speed, Sensor sensor, double sensorValue, double extraTime) {
    	super(angle, speed, sensor, sensorValue, extraTime);
    }
    
    @Override
    protected void initialize() {
    	super.initialize();
    	insideRange = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	super.execute();
    	
//    	if (STATE != FLATTEN_OUT) insideRangeCounter = 0;
    	
    	switch (STATE) {
    		case BEFORE_DEFENSE:
    			if (Sensors.ahrs.getRoll() > 5) STATE++;
    			break;
    		case GOING_OVER:
    			if (Sensors.ahrs.getRoll() < -5) STATE++;
    			break;
    		case FACING_DOWN:
    			if (Math.abs(Sensors.ahrs.getRoll()) < 1) STATE++;
    			break;
    		case FLATTEN_OUT:
    			insideRange = true;
    			break;
    	}
    }
}
