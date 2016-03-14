package com.team2502.robot2016.commands.drive;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.subsystems.PIDDriveTrain;
import com.team2502.robot2016.subsystems.Sensors;
import com.team2502.robot2016.subsystems.Sensors.Sensor;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToSensorValue extends Command {

	private PIDDriveTrain dt = Robot.driveTrain;
	private Sensors s = Robot.sensors;
	private Sensor sensor;
	private double sensorLimit;
	private double speed = .7;
	private int counter = 0;
	
    public DriveToSensorValue(double speed, Sensor sensor, double sensorValue) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	sensorLimit = sensorValue;
    	this.speed = speed;
    	this.sensor = sensor;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	dt.runMotors(speed, speed);
    	
    	if (Math.abs(s.getSensorDistance(sensor) - sensorLimit) < RobotMap.SENSOR_ZONE_OF_PRECISION) {
    		counter++;
    	} else {
    		counter = 0;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return counter > 15;
    }

    // Called once after isFinished returns true
    protected void end() {
    	dt.stopDrive();
    	Sensors.BEFORE_TURN_VALUE = s.getSensorDistance(sensor);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
