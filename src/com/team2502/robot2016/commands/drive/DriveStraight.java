package com.team2502.robot2016.commands.drive;

import com.team2502.robot2016.OI;
import com.team2502.robot2016.Robot;
import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.subsystems.DriveTrain;
import com.team2502.robot2016.subsystems.Sensors;
import com.team2502.robot2016.subsystems.Sensors.Sensor;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraight extends Command implements PIDOutput {
	
	private DriveTrain dt = Robot.driveTrain;
	private double angle;
	
	private PIDController turnController;
	private double rotateToAngleRate;
    
    /* The following PID Controller coefficients will need to be tuned */
    /* to match the dynamics of your drive system.  Note that the      */
    /* SmartDashboard in Test mode has support for helping you tune    */
    /* controllers by displaying a form where you can enter new P, I,  */
    /* and D constants and test the mechanism.                         */
    
	private static double kP = 0.04;
	private static final double kI = 0.00;
	private static final double kD = 0.00;
	private static final double kF = 0.00;
    
	private static final double kToleranceDegrees = 1.0f;
	
	private double startTime;
	
	private Sensors s = Robot.sensors;
	private Sensor sensor;
	private double sensorLimit;
	private double speed = .7;
	private int counter = 0;
	private boolean change = false;
	private double initialReading;
	private double extraTime = 0;
	private double realSpeed = 0;
	private double minTime = 0;
	

    public DriveStraight(double angle, double speed, Sensor sensor, double sensorValue) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	this.angle = angle;
    	
    	sensorLimit = sensorValue;
    	this.speed = speed;
    	this.sensor = sensor;
    	
    	turnController = new PIDController(kP, kI, kD, kF, Sensors.ahrs, this);
        turnController.setInputRange(-180.0f,  180.0f);
        turnController.setOutputRange(-1.0, 1.0);
        turnController.setAbsoluteTolerance(kToleranceDegrees);
        turnController.setContinuous(true);
    }
    
    public DriveStraight(double angle, double speed, Sensor sensor, double sensorValue, boolean change) {
    	this(angle, speed, sensor, sensorValue);
    	this.change = change;
    }
    
    public DriveStraight(double angle, double speed, Sensor sensor, double sensorValue, double extraTime) {
    	this(angle, speed, sensor, sensorValue);
    	this.extraTime = extraTime;
    }
    
    public DriveStraight(double angle, double speed, Sensor sensor, double sensorValue, double extraTime, double minTime) {
    	this(angle, speed, sensor, sensorValue);
    	this.extraTime = extraTime;
    	this.minTime = minTime;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	turnController.setSetpoint(angle);
    	turnController.enable();
    	SmartDashboard.putNumber("P Value", kP);
    	startTime = System.currentTimeMillis();
    	initialReading = s.getSensorDistance(sensor);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
//    	if ((s.getRoll() > 5.5 && s.getSensorDistance(sensor) < SmartDashboard.getNumber("Outer Short Value", .8))
//    			|| Math.abs(s.getAngle() - angle) > 10) {
//    		double newSpeed = rotateToAngleRate;
//        	newSpeed = .7 * Math.signum(rotateToAngleRate);
//        	if (Math.abs(rotateToAngleRate) < .2) newSpeed = .45 * Math.signum(newSpeed);
////        	newSpeed = (Math.abs(newSpeed) + motorLimit)* Math.signum(newSpeed);
//        	dt.runMotors(newSpeed, -newSpeed);
//        	System.out.println("Rotate Straight");
//    	} else {
    		dt.driveStraight(realSpeed, rotateToAngleRate);
    		System.out.println("Normal Straight");

//    	}
    	realSpeed += .08;
    	
    	if (realSpeed > speed) realSpeed = speed;
    	System.out.println("Executing Function: " + s.getSensorDistance(sensor));

    	if (change) {
    		if (Math.abs(s.getSensorDistance(sensor) - initialReading) > sensorLimit) {
    			counter++;
    		}
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
//        return Sensors.ahrs.getFusedHeading() < 190 || Sensors.ahrs.getFusedHeading() > 170;
    	return (counter > 2 && System.currentTimeMillis() - startTime > minTime * 1000);
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Done");
//    	dt.runMotors(-.1, .1);
//    	dt.disable();
    	turnController.disable();
    	dt.stopDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
//    	end();
    }

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
        rotateToAngleRate = output;
        System.out.println("Output: " + output);

	}
}
