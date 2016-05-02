package com.team2502.robot2016.commands.drive;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.subsystems.DriveTrain;
import com.team2502.robot2016.subsystems.Sensors;
import com.team2502.robot2016.subsystems.Sensors.Sensor;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraight extends Command implements PIDOutput {
	
	protected DriveTrain dt = Robot.driveTrain;
	protected double angle;
	
	protected PIDController turnController;
	protected double rotateToAngleRate;
    
    /* The following PID Controller coefficients will need to be tuned */
    /* to match the dynamics of your drive system.  Note that the      */
    /* SmartDashboard in Test mode has support for helping you tune    */
    /* controllers by displaying a form where you can enter new P, I,  */
    /* and D constants and test the mechanism.                         */
    
	protected static double kP = 0.04;
	protected static final double kI = 0.00;
	protected static final double kD = 0.00;
	protected static final double kF = 0.00;
    
	protected static final double kToleranceDegrees = 1.0f;
	
	protected double startTime;
	
	protected Sensors s = Robot.sensors;
	protected Sensor sensor;
	protected double sensorLimit;
	protected double speed = .7;
	protected int counter = 0;
	protected boolean change = false;
	protected double initialReading;
	protected double extraTime = 0;
	protected double realSpeed = 0;
	protected double minTime = 0;
	protected boolean done = false;
	
	protected boolean insideRange = true;
	protected int insideRangeCounter = 0;
	protected PIDSource theSource = Sensors.ahrs;
	

    public DriveStraight(double angle, double speed, Sensor sensor, double sensorValue) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	this.angle = angle;
    	
    	sensorLimit = sensorValue;
    	this.speed = speed;
    	this.sensor = sensor;
    	
    	turnController = new PIDController(kP, kI, kD, kF, theSource, this);
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
    
    public DriveStraight(double angle, double speed, Sensor sensor, double sensorValue, boolean change, double extraTime) {
    	this(angle, speed, sensor, sensorValue);
    	this.extraTime = extraTime;
    	this.change = change;
    }
    
    public DriveStraight(double angle, double speed, Sensor sensor, double sensorValue, double extraTime, double minTime) {
    	this(angle, speed, sensor, sensorValue);
    	this.extraTime = extraTime;
    	this.minTime = minTime;
    }
    
    public DriveStraight(double angle, double speed, Sensor sensor, double sensorValue, boolean change, double extraTime, double minTime) {
    	this(angle, speed, sensor, sensorValue);
    	this.extraTime = extraTime;
    	this.minTime = minTime;
    	this.change = change;
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	turnController.setSetpoint(angle);
    	Robot.vision.setTargetAngle(angle);
    	turnController.enable();
    	SmartDashboard.putNumber("P Value", kP);
    	startTime = System.currentTimeMillis();
    	initialReading = s.getSensorDistance(sensor);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    		dt.driveStraight(realSpeed, rotateToAngleRate);
    		System.out.println("Normal Straight");

    	realSpeed += .08;
    	
    	if (realSpeed > speed) realSpeed = speed;
    	System.out.println("Goal: " + sensorLimit);
    	System.out.println("Executing Function: " + s.getSensorDistance(sensor));

//    	if (s.getSensorDistance(sensor) < RobotMap.LONG_SENSOR_RANGE_LIMITS) {
//    		insideRangeCounter++;
//    	} else {
//    		insideRangeCounter = 0;
//    	}
//    	
//    	if (insideRangeCounter > 2) {
//    		insideRange = true;
//    	}
    	
    	System.out.println("In Range: " + insideRange);
    	if (insideRange) {
			if (change) {
				//The getSensorDistance just gets the voltage of a particular sensor.
				//Use an AnalogInput for the sensor.
				if (Math.abs(s.getSensorDistance(sensor) - initialReading) > sensorLimit) {
					counter++;
				}
			} else {
		    	if (s.getSensorDistance(sensor) < sensorLimit) {
		    		counter++;
		
		    	} else {
		    		counter = 0;
		    	}

			}
    	}
//		if (Math.abs(s.getSensorDistance(sensor)) < RobotMap.LONG_SENSOR_RANGE_LIMITS+.1 && Math.abs(s.getSensorDistance(sensor)) > .9) {
//			insideRange = true;
//		} else {
//			insideRange = false;
//		}

    	
    	if (counter > 2) {
    		done = true;
    	}
    		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//        return Sensors.ahrs.getFusedHeading() < 190 || Sensors.ahrs.getFusedHeading() > 170;
    	System.out.println("Counter: " + counter);
    	
    	
    	//HEY LOOK HERE!!!!! TRY UNCOMMENTING THIS LINE TO TEST A SAFETY TO NOT RUN INTO
    	//A WALL - USES SHORT SENSOR, SO NOT SURE WHERE GOING TO BE MOUNTED, BUT HOPEFULLY
    	//THE LINE BELOW THIS WOULD MAKE THE ROBOT NOT CAUSE A DRIVER STATION TO BE
    	//KNOCKED OFF OF THE PLATFORM THINGY
    	//ONLY TRIGGERS IF 5 SECONDS HAVE PASSED JUST TO BE EXTRA SAFE TIME WISE
//    	if (System.currentTimeMillis() - startTime > 5000 && s.getSensorDistance(Sensor.FrontShort) < 1.3 && s.getSensorDistance(Sensor.FrontShort) > .8) return true;
    	
    	
    	return (done && System.currentTimeMillis() - startTime > minTime * 1000);
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	Timer.delay(extraTime);
    	dt.stopDrive();
    	System.out.println("End Function: " + s.getSensorDistance(sensor));
    	Sensors.BEFORE_TURN_VALUE = s.getSensorDistance(sensor);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
        rotateToAngleRate = output;

	}
	
	public void setPIDSource(PIDSource source) {
		theSource = source;
		turnController = new PIDController(kP, kI, kD, kF, source, this);
        turnController.setInputRange(-30.0, 30.0);
        turnController.setOutputRange(-1.0, 1.0);
        turnController.setAbsoluteTolerance(kToleranceDegrees);
        turnController.setContinuous(false);
	}
}
