package com.team2502.robot2016.commands.drive;

import com.team2502.robot2016.OI;
import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.DriveTrain;
import com.team2502.robot2016.subsystems.Sensors;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RotateToAngle extends Command implements PIDOutput {
	
	private DriveTrain dt = Robot.driveTrain;
	private double angle;
	
	private PIDController turnController;
	private double rotateToAngleRate;
    
    /* The following PID Controller coefficients will need to be tuned */
    /* to match the dynamics of your drive system.  Note that the      */
    /* SmartDashboard in Test mode has support for helping you tune    */
    /* controllers by displaying a form where you can enter new P, I,  */
    /* and D constants and test the mechanism.                         */
    
	private static double kP = 0.02;
	private static final double kI = 0.00;
	private static final double kD = 0.00;
	private static final double kF = 0.00;
    
	private static final double kToleranceDegrees = 1.0f;
	
	private double startTime;

    public RotateToAngle(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	this.angle = angle;
    	
    	turnController = new PIDController(kP, kI, kD, kF, Sensors.ahrs, this);
        turnController.setInputRange(-180.0f,  180.0f);
        turnController.setOutputRange(-1.0, 1.0);
        turnController.setAbsoluteTolerance(kToleranceDegrees);
        turnController.setContinuous(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	turnController.setSetpoint(angle);
    	turnController.enable();
    	SmartDashboard.putNumber("P Value", kP);
    	startTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	dt.runMotors(.1, -.1);
    	double motorLimit = SmartDashboard.getNumber("Motor Limit", .3);
    	kP = SmartDashboard.getNumber("P Value", kP);
    	turnController.setPID(kP, kI, kD);
    	System.out.println("Rotate to Angle: " + rotateToAngleRate);
    	
    	double newSpeed = rotateToAngleRate;
    	
    	
    	newSpeed = .7 * Math.signum(rotateToAngleRate);
    	if (Math.abs(rotateToAngleRate) < .2) newSpeed = .45 * Math.signum(newSpeed);
    	
//    	newSpeed = (Math.abs(newSpeed) + motorLimit)* Math.signum(newSpeed);
    	
    	dt.runMotors(newSpeed, -newSpeed);
    	

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//        return Sensors.ahrs.getFusedHeading() < 190 || Sensors.ahrs.getFusedHeading() > 170;
    	return turnController.onTarget() || System.currentTimeMillis() - startTime > 1800;
    	
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

	}
}
