package com.team2502.robot2016.subsystems;

import com.team2502.robot2016.OI;
import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.commands.drive.TankDriveSix;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public enum Motors {
		LEFT_MOTORS, RIGHT_MOTORS, WINCH_MOTORS
	}
	
	private static DriveTrain instance;
	
	private final RobotDrive simDrive;
	private final RobotDrive miniSimDrive;
	
	private final CANTalon leftSimOne;
	private final CANTalon leftSimTwo;
	private final CANTalon leftMiniSim;

	private final CANTalon rightSimOne;
	private final CANTalon rightSimTwo;
	private final CANTalon rightMiniSim;
	
	private final CANTalon climberWinch;
	private final Solenoid climberSolenoid;
	
	private final int wheelBase = 24;
	
	private DriveTrain() {
		leftSimOne = new CANTalon(RobotMap.LEFT_MOTOR_SIM_ONE);
		leftSimTwo = new CANTalon(RobotMap.LEFT_MOTOR_SIM_TWO);
		leftMiniSim = new CANTalon(RobotMap.LEFT_MOTOR_MINI_SIM);

		rightSimOne = new CANTalon(RobotMap.RIGHT_MOTOR_SIM_ONE);
		rightSimTwo = new CANTalon(RobotMap.RIGHT_MOTOR_SIM_TWO);
		rightMiniSim = new CANTalon(RobotMap.RIGHT_MOTOR_MINI_SIM);
		
		//Will need to make a custom RobotDrive for all 6 motors
		simDrive = new RobotDrive(leftSimOne, leftSimTwo, rightSimOne, rightSimTwo);
		miniSimDrive = new RobotDrive(leftMiniSim, rightMiniSim);
		
		climberWinch = new CANTalon(RobotMap.SCIRSSOR_LIFT_WINCH);
		climberSolenoid = new Solenoid(RobotMap.CATAPULT_SOLENOID);
		
//		accel = new BuiltInAccelerometer();

		leftSimOne.setPosition(0);
		leftSimTwo.setPosition(0);
		leftMiniSim.setPosition(0);

		rightSimOne.setPosition(0);
		rightSimTwo.setPosition(0);
		rightMiniSim.setPosition(0);
		
		
		
//		simDrive.setInvertedMotor(motor, isInverted);
	}
	
	public static DriveTrain getInstance() {
		if (instance == null) {
			instance = new DriveTrain();
		}
		return instance;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TankDriveSix());
    }
    
    public void driveSix() {
    	simDrive.tankDrive(OI.getLeftStick().getY(), OI.getRightStick().getZ(), true);
    	miniSimDrive.tankDrive(OI.getLeftStick().getY(), OI.getRightStick().getZ(), true);
    }
    
    public void runClimber(double speed) {
    	climberWinch.set(speed);
    }
    
    public void stopClimber() {
    	climberWinch.set(0);
    }
    
    public void launchScissors(boolean state) {
    	climberSolenoid.set(state);
    }
    
    public double getEncoderValue(Motors m) {
    	switch (m)  {
    		case LEFT_MOTORS:
    			return leftSimOne.getPosition();
    			
    		case RIGHT_MOTORS:
    			return rightSimOne.getPosition();
    			
    		case WINCH_MOTORS:
    		default :
    			return climberWinch.getPosition();
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
}

