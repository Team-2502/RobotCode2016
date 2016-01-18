package com.team2502.robot2016.subsystems;

import com.team2502.robot2016.OI;
import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.commands.drive.TankDriveSix;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private static DriveTrain instance;
	
	private final RobotDrive simDrive;
	private final RobotDrive miniSimDrive;
	
	private final CANTalon leftSimOne;
	private final CANTalon leftSimTwo;
	private final CANTalon leftMiniSim;

	private final CANTalon rightSimOne;
	private final CANTalon rightSimTwo;
	private final CANTalon rightMiniSim;
	
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
    	simDrive.arcadeDrive(OI.getDriveStick().getY(), OI.getDriveStick().getZ(), true);
    	miniSimDrive.arcadeDrive(OI.getDriveStick().getY(), OI.getDriveStick().getZ(), true);
    }
}

