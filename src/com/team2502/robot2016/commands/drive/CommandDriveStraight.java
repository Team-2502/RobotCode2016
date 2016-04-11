package com.team2502.robot2016.commands.drive;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.subsystems.SubsystemDriveTrain;
import com.team2502.robot2016.subsystems.SubsystemSensors;
import com.team2502.robot2016.subsystems.SubsystemSensors.Sensor;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CommandDriveStraight extends Command implements PIDOutput
{

    protected SubsystemDriveTrain dt                 = Robot.driveTrain;
    protected SubsystemSensors    sensors            = Robot.sensors;
    protected double              angle;

    protected PIDController       turnController;
    protected double              rotateToAngleRate;

    /* The following PID Controller coefficients will need to be tuned */
    /* to match the dynamics of your drive system. Note that the */
    /* SmartDashboard in Test mode has support for helping you tune */
    /* controllers by displaying a form where you can enter new P, I, */
    /* and D constants and test the mechanism. */

    protected static double       kP                 = 0.04;
    protected static final double kI                 = 0.00;
    protected static final double kD                 = 0.00;
    protected static final double kF                 = 0.00;

    protected static final double kToleranceDegrees  = 1.0f;

    protected double              startTime;

    protected Sensor              sensor;
    protected double              sensorLimit;
    protected double              speed              = .7;
    protected int                 counter            = 0;
    protected boolean             change             = false;
    protected double              initialReading;
    protected double              extraTime          = 0;
    protected double              realSpeed          = 0;
    protected double              minTime            = 0;
    protected boolean             done               = false;

    protected boolean             insideRange        = false;
    protected int                 insideRangeCounter = 0;

    public CommandDriveStraight(double angle, double speed, Sensor sensor, double sensorValue)
    {
        requires(Robot.driveTrain);
        requires(Robot.sensors);
        this.angle = angle;

        sensorLimit = sensorValue;
        this.speed = speed;
        this.sensor = sensor;

        turnController = new PIDController(kP, kI, kD, kF, SubsystemSensors.ahrs, this);
        turnController.setInputRange(-180.0f, 180.0f);
        turnController.setOutputRange(-1.0, 1.0);
        turnController.setAbsoluteTolerance(kToleranceDegrees);
        turnController.setContinuous(true);
    }

    public CommandDriveStraight(double angle, double speed, Sensor sensor, double sensorValue, boolean change)
    {
        this(angle, speed, sensor, sensorValue);
        this.change = change;
    }

    public CommandDriveStraight(double angle, double speed, Sensor sensor, double sensorValue, double extraTime)
    {
        this(angle, speed, sensor, sensorValue);
        this.extraTime = extraTime;
    }

    public CommandDriveStraight(double angle, double speed, Sensor sensor, double sensorValue, boolean change, double extraTime)
    {
        this(angle, speed, sensor, sensorValue);
        this.extraTime = extraTime;
        this.change = change;
    }

    public CommandDriveStraight(double angle, double speed, Sensor sensor, double sensorValue, double extraTime, double minTime)
    {
        this(angle, speed, sensor, sensorValue);
        this.extraTime = extraTime;
        this.minTime = minTime;
    }

    public CommandDriveStraight(double angle, double speed, Sensor sensor, double sensorValue, boolean change, double extraTime, double minTime)
    {
        this(angle, speed, sensor, sensorValue);
        this.extraTime = extraTime;
        this.minTime = minTime;
        this.change = change;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize()
    {
        turnController.setSetpoint(angle);
        turnController.enable();
        SmartDashboard.putNumber("P Value", kP);
        startTime = System.currentTimeMillis();
        initialReading = sensors.getSensorDistance(sensor);

    }

    @Override
    protected void execute()
    {

        // if ((s.getRoll() > 5.5 && s.getSensorDistance(sensor) <
        // SmartDashboard.getNumber("Outer Short Value", .8))
        // || Math.abs(s.getAngle() - angle) > 10) {
        // double newSpeed = rotateToAngleRate;
        // newSpeed = .7 * Math.signum(rotateToAngleRate);
        // if (Math.abs(rotateToAngleRate) < .2) newSpeed = .45 *
        // Math.signum(newSpeed);
        //// newSpeed = (Math.abs(newSpeed) + motorLimit)*
        // Math.signum(newSpeed);
        // dt.runMotors(newSpeed, -newSpeed);
        // System.out.println("Rotate Straight");
        // } else {
        dt.driveStraight(realSpeed, rotateToAngleRate);
        System.out.println("Normal Straight");

        // }
        realSpeed += .08;

        if(realSpeed > speed)
        {
            realSpeed = speed;
        }
        System.out.println("Goal: " + sensorLimit);
        System.out.println("Executing Function: " + sensors.getSensorDistance(sensor));

        if(sensors.getSensorDistance(sensor) < RobotMap.LONG_SENSOR_RANGE_LIMITS)
        {
            insideRangeCounter++;
        } else
        {
            insideRangeCounter = 0;
        }

        if(insideRangeCounter > 2)
        {
            insideRange = true;
        }

        System.out.println("In Range: " + insideRange);
        if(insideRange)
        {
            if(change)
            {
                // The getSensorDistance just gets the voltage of a particular
                // sensor.
                // Use an AnalogInput for the sensor.
                if(Math.abs(sensors.getSensorDistance(sensor) - initialReading) > sensorLimit)
                {
                    counter++;
                }
            } else
            {
                if(Math.abs(sensors.getSensorDistance(sensor) - sensorLimit) < RobotMap.SENSOR_ZONE_OF_PRECISION)
                {
                    counter++;

                } else
                {
                    counter = 0;
                }

            }
        }
        if(Math.abs(sensors.getSensorDistance(sensor)) < RobotMap.LONG_SENSOR_RANGE_LIMITS + .1 && Math.abs(sensors.getSensorDistance(sensor)) > .9)
        {
            insideRange = true;
        } else
        {
            insideRange = false;
        }

        if(counter > 2)
        {
            done = true;
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
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
    @Override
    protected void end()
    {

        Timer.delay(extraTime);
        dt.stopDrive();
        System.out.println("End Function: " + sensors.getSensorDistance(sensor));
        SubsystemSensors.BEFORE_TURN_VALUE = sensors.getSensorDistance(sensor);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        //end()
    }

    @Override
    public void pidWrite(double output)
    {
        rotateToAngleRate = output;
    }
}
