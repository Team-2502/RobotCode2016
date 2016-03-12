package com.team2502.robot2016.subsystems;

import com.team2502.robot2016.OI;
import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.commands.drive.TankDriveSix;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDDriveTrain extends PIDSubsystem {
	
	private final RobotDrive simDrive;
	private final RobotDrive miniSimDrive;
	
	private final CANTalon leftSimOne;
	private final CANTalon leftSimTwo;
	private final CANTalon leftMiniSim;

	private final CANTalon rightSimOne;
	private final CANTalon rightSimTwo;
	private final CANTalon rightMiniSim;
	
	private final int wheelBase = 24;
	public double rotateToAngleRate = 0;
	
	
	private static final double ROBOT_STRAIGHT = 0,
			ROBOT_FACE_LEFT = 60,
			ROBOT_FACE_RIGHT = -60;

	private static final double kToleranceDegrees = 2.0f;
    // Initialize your subsystem here
    public PIDDriveTrain() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	
    	super("Straight", .05, 0, 0);
    	getPIDController().setContinuous(true);
    	getPIDController().setInputRange(-180f, 180f);
    	getPIDController().setOutputRange(-1.0, 1.0);
    	getPIDController().setAbsoluteTolerance(kToleranceDegrees);
    	getPIDController().setSetpoint(ROBOT_STRAIGHT);
    	//This makes the robot turn to become straight when enabled
    	getPIDController().enable();
    	
    	leftSimOne = new CANTalon(RobotMap.LEFT_MOTOR_SIM_ONE);
		leftSimTwo = new CANTalon(RobotMap.LEFT_MOTOR_SIM_TWO);
		leftMiniSim = new CANTalon(RobotMap.LEFT_MOTOR_MINI_SIM);

		System.err.println("Left");
		rightSimOne = new CANTalon(RobotMap.RIGHT_MOTOR_SIM_ONE);
		rightSimTwo = new CANTalon(RobotMap.RIGHT_MOTOR_SIM_TWO);
		rightMiniSim = new CANTalon(RobotMap.RIGHT_MOTOR_MINI_SIM);
		System.err.println("Right");
		
		//Will need to make a custom RobotDrive for all 6 motors
		simDrive = new RobotDrive(leftSimOne, leftSimTwo, rightSimOne, rightSimTwo);
		miniSimDrive = new RobotDrive(leftMiniSim, rightMiniSim);
		
		System.err.println("Drives");
		
		System.err.println("Solenoid");
//		accel = new BuiltInAccelerometer();

		leftSimOne.setPosition(0);
		leftSimTwo.setPosition(0);
		leftMiniSim.setPosition(0);

		rightSimOne.setPosition(0);
		rightSimTwo.setPosition(0);
		rightMiniSim.setPosition(0);
		
		 LiveWindow.addActuator("PIDDriveSystem", "RotateController", getPIDController());
	    simDrive.setExpiration(.1);
	    miniSimDrive.setExpiration(.1);

    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TankDriveSix());
    }
    
    public void driveSix() {
    	
//    	if (getPIDController().isEnabled()) {
//    		getPIDController().disable();
//    	}
    	double leftSpeed = -OI.getLeftStick().getY();
    	double rightSpeed = -OI.getRightStick().getY();
    	
    	if (Math.abs(leftSpeed) < .05)
    		leftSpeed = 0;

    	if (Math.abs(rightSpeed) < .05)
    		rightSpeed = 0;
    	
    	simDrive.tankDrive(leftSpeed, rightSpeed, true);
    	miniSimDrive.tankDrive(leftSpeed, rightSpeed, true);
    }
    
    public void turn(double speed, boolean left) {
    	simDrive.tankDrive(speed * ((left) ? -1: 1), speed * ((left) ? 1: -1));
    	miniSimDrive.tankDrive(speed * ((left) ? -1: 1), speed * ((left) ? 1: -1));
    }
    
    public void stopDrive() {
    	simDrive.tankDrive(0, 0);
    	miniSimDrive.tankDrive(0, 0);
    }
    
    public void driveAlongRadius(double speed, double radius) {
    	double curve = Math.pow(Math.E, radius/wheelBase);
    	simDrive.drive(speed, curve);
    	miniSimDrive.drive(speed, curve);
    }
    
    public void runMotors(double left, double right) {
    	simDrive.tankDrive(left, right);
    	miniSimDrive.tankDrive(left, right);
    }
    
    public void driveStraight(double speed) {
//    	if (!getPIDController().isEnabled()) {
//    		getPIDController().enable();
//    	}
    	
    	simDrive.arcadeDrive(speed, rotateToAngleRate);
    	miniSimDrive.arcadeDrive(speed, rotateToAngleRate);

    	
    }
    
    public void rotateGyro() {
    	simDrive.arcadeDrive(0, rotateToAngleRate);
    	miniSimDrive.arcadeDrive(0, rotateToAngleRate);

    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return Sensors.ahrs.getAngle();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
//    	simDrive.tankDrive(output, -output);
//    	miniSimDrive.tankDrive(output, -output);
    	SmartDashboard.putBoolean("PIDOutput called", true);
    	SmartDashboard.putNumber("Output", output);
    	rotateToAngleRate  = output;
    }
}
