package com.team2502.robot2016.subsystems;


import com.team2502.robot2016.OI;
//import com.team2502.robot2016.OI;
import com.team2502.robot2016.RobotMap;
//import com.team2502.robot2016.commands.drive.TankDriveSix;
import com.team2502.robot2016.commands.drive.TankDriveSix;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    

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
	
	
	public enum Motors {
		LEFT_MOTORS, RIGHT_MOTORS, WINCH_MOTORS
	}
	
    // Initialize your subsystem here
    public DriveTrain() {
        
    	leftSimOne = new CANTalon(RobotMap.LEFT_MOTOR_SIM_ONE);
		leftSimTwo = new CANTalon(RobotMap.LEFT_MOTOR_SIM_TWO);
		leftMiniSim = new CANTalon(RobotMap.LEFT_MOTOR_MINI_SIM);

		rightSimOne = new CANTalon(RobotMap.RIGHT_MOTOR_SIM_ONE);
		rightSimTwo = new CANTalon(RobotMap.RIGHT_MOTOR_SIM_TWO);
		rightMiniSim = new CANTalon(RobotMap.RIGHT_MOTOR_MINI_SIM);
		
		//Will need to make a custom RobotDrive for all 6 motors
		simDrive = new RobotDrive(leftSimOne, leftSimTwo, rightSimOne, rightSimTwo);
		miniSimDrive = new RobotDrive(leftMiniSim, rightMiniSim);

				
	    simDrive.setExpiration(.1);
	    miniSimDrive.setExpiration(.1);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TankDriveSix());
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
    
    public void turn(double speed, boolean left) {
    	simDrive.tankDrive(speed * ((left) ? -1: 1), speed * ((left) ? 1: -1));
    	miniSimDrive.tankDrive(speed * ((left) ? -1: 1), speed * ((left) ? 1: -1));

    }
    
    public void stopDrive() {
    	
    	simDrive.tankDrive(-.05, -0.05);
    	miniSimDrive.tankDrive(-.05, -0.05);
    	
    	Timer.delay(.3);
    }
    
    public void brakeMode(boolean on) {
    	leftSimOne.enableBrakeMode(on);
    	leftSimTwo.enableBrakeMode(on);
    	leftMiniSim.enableBrakeMode(on);

    	rightSimOne.enableBrakeMode(on);
    	rightSimTwo.enableBrakeMode(on);
    	rightMiniSim.enableBrakeMode(on);
    }
    
    public void driveAlongRadius(double speed, double radius) {
    	double curve = Math.pow(Math.E, radius/wheelBase);
    	simDrive.drive(speed, curve);
    	miniSimDrive.drive(speed, curve);
    }
    
    public void runMotors(double left, double right) {
    	if (Math.abs(left) > 1) left /= Math.abs(left);
    	if (Math.abs(right) > 1) right /= Math.abs(right);

    	simDrive.tankDrive(left, right);
    	miniSimDrive.tankDrive(left, right);
    }
    
    public void driveStraight(double speed, double turn) {
    	simDrive.drive(speed, turn);
    	miniSimDrive.drive(speed, turn);
    }
}

