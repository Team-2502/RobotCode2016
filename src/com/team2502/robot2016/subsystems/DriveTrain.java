package com.team2502.robot2016.subsystems;

import java.awt.Point;

import com.team2502.robot2016.OI;
import com.team2502.robot2016.Robot;
//import com.team2502.robot2016.OI;
import com.team2502.robot2016.RobotMap;
//import com.team2502.robot2016.commands.drive.TankDriveSix;
import com.team2502.robot2016.commands.drive.TankDriveSix;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public static final int TICKS_PER_REV = 4 * 1023;
	public static final int wheelRadius = 8;
	
//	public static final Point OUTER_LEFT = new Point(0, 0);
//	public static final Point OUTER_MIDDLE = new Point(0, 0);
//	public static final Point OUTER_RIGHT = new Point(0, 0);
//	
//	public static final Point INNER_LEFT = new Point(0, 0);
//	public static final Point INNER_MIDDLE = new Point(0, 0);
//	public static final Point INNER_RIGHT = new Point(0, 0);
	
	
	public enum Motors {
		LEFT_MOTORS, RIGHT_MOTORS, WINCH_MOTORS
	}
	
//	private static DriveTrain instance;
	

	
	private final RobotDrive simDrive;
	private final RobotDrive miniSimDrive;
	
	private final CANTalon leftSimOne;
	private final CANTalon leftSimTwo;
	private final CANTalon leftMiniSim;

	private final CANTalon rightSimOne;
	private final CANTalon rightSimTwo;
	private final CANTalon rightMiniSim;
	
	
	
	private final int wheelBase = 24;
	
//	private Sensors sensors = Robot.sensors;
	
	int i = 0;
	
	public DriveTrain() {

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
		
		
		
//		simDrive.setInvertedMotor(motor, isInverted);
	}
	
//	public static DriveTrain getInstance() {
//		if (instance == null) {
//			instance = new DriveTrain();
//		}
//		System.err.println("Instance");
//		return instance;
//	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TankDriveSix());
    	System.err.println("Default Drive");
    }
    
    public void driveSix() {
    	
    	double leftSpeed = -OI.getLeftStick().getY();
    	double rightSpeed = -OI.getRightStick().getY();
    	
    	if (Math.abs(leftSpeed) < .05)
    		leftSpeed = 0;

    	if (Math.abs(rightSpeed) < .05)
    		rightSpeed = 0;
    	
    	simDrive.tankDrive(leftSpeed, rightSpeed, true);
    	miniSimDrive.tankDrive(leftSpeed, rightSpeed, true);
    }
    
   
    
    public double getEncoderValue(Motors m) {
    	switch (m)  {
    		case LEFT_MOTORS:
    			return leftSimOne.getPosition();
    			
    		case RIGHT_MOTORS:
    			return rightSimOne.getPosition();
    			
    		case WINCH_MOTORS:
    		default :
    			return 0;
//    			return climberWinch1.getPosition();
    	}
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
    	
    	
    	simDrive.arcadeDrive(speed, Sensors.ahrs.getAngle());
    	
    	
    	
    }
    
    

}

