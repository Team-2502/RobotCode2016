package com.team2502.robot2016.commands.drive;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.subsystems.DriveTrain;
import com.team2502.robot2016.subsystems.Sensors;
import com.team2502.robot2016.subsystems.Sensors.Sensor;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveToSensorValue extends Command {

	private DriveTrain dt = Robot.driveTrain;
	private Sensors s = Robot.sensors;
	private Sensor sensor;
	private double sensorLimit;
	private double speed = .7;
	private int counter = 0;
	private boolean change = false;
	private double initialReading;
	private double extraTime = 0;
	private double minTime = 0;
	private double initialTime;
	
    public DriveToSensorValue(double speed, Sensor sensor, double sensorValue) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	sensorLimit = sensorValue;
    	this.speed = speed;
    	this.sensor = sensor;
    }
    
    public DriveToSensorValue(double speed, Sensor sensor, double sensorValue, boolean change) {
    	this(speed, sensor, sensorValue);
    	this.change = change;
    }
    
    public DriveToSensorValue(double speed, Sensor sensor, double sensorValue, double extraTime) {
    	this(speed, sensor, sensorValue);
    	this.extraTime = extraTime;
    }
    
    public DriveToSensorValue(double speed, Sensor sensor, double sensorValue, boolean change, double extraTime) {
    	this(speed, sensor, sensorValue);
    	this.extraTime = extraTime;
    	this.change = change;
    }
    
    public DriveToSensorValue(double speed, Sensor sensor, double sensorValue, boolean change, double extraTime, double minTime) {
    	this(speed, sensor, sensorValue);
    	this.extraTime = extraTime;
    	this.change = change;
    	this.minTime = minTime;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initialReading = s.getSensorDistance(sensor);
    	initialTime = System.currentTimeMillis();
//    	if (change) sensorLimit += initialReading;
    	System.out.println("INitial Reading: " + initialReading);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	dt.runMotors(speed, speed);
    	
    	System.out.println("Executing Function: " + s.getSensorDistance(sensor));

    	if (change) {
    		if (Math.abs(s.getSensorDistance(sensor) - initialReading) > sensorLimit) {
    			counter++;
    		}
    		System.out.println(Math.abs(s.getSensorDistance(sensor) - initialReading));
    	} else {
	    	if (Math.abs(s.getSensorDistance(sensor) - sensorLimit) < RobotMap.SENSOR_ZONE_OF_PRECISION) {
	    		counter++;
	
	    	} else {
	    		counter = 0;
	    	}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return counter > 2 && System.currentTimeMillis() - initialTime > minTime * 1000;
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	Timer.delay(SmartDashboard.getNumber("Side Delay", .37));
    	dt.stopDrive();
    	System.out.println("End Function: " + s.getSensorDistance(sensor));
    	Sensors.BEFORE_TURN_VALUE = s.getSensorDistance(sensor);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
