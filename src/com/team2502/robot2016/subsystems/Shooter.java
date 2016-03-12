package com.team2502.robot2016.subsystems;


import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.commands.shooter.ClimberWinch;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * TODO: Remove unnecessary Solenoid/Talon
 */
public class Shooter extends Subsystem
{
//    private final CANTalon shooterMotor = new CANTalon(RobotMap.SHOOTER_MOTOR);
    private final Solenoid shooterSolenoid = new Solenoid(RobotMap.SHOOTER_SOLENOID);
    
    private final CANTalon climberWinch1;
	private final CANTalon climberWinch2;

	private final Solenoid climberSolenoid;
	
	public boolean climberUp = false;
	
	public Shooter() {
		climberWinch1 = new CANTalon(RobotMap.CLIMBER_WINCH_ONE);
		climberWinch2 = new CANTalon(RobotMap.CLIMBER_WINCH_TWO);

		climberSolenoid = new Solenoid(RobotMap.CLIMBER_SOLENOID);
//		climberSolenoid.set(false);
	}
    
    public void initDefaultCommand() {
//        setDefaultCommand(new ShootBallCommand());
//    	setDefaultCommand(new ClimberWinch());
    }
    
    public void runClimber(double speed) {
    	climberWinch1.set(speed);
    	climberWinch2.set(speed);

    }
    
    public void stopClimber() {
    	climberWinch1.set(0);
    	climberWinch1.set(0);

    }
    
    public void launchScissors(boolean state) {
    	climberSolenoid.set(state);
    	climberUp = true;

    }

    /**
     * Prepares the shot by either lowering the Solenoid OR pulling down the bar with a Talon.
     */
    public void setSolenoid(boolean state) {
        shooterSolenoid.set(state);
    }
    
    public boolean getShooterState() {
    	return shooterSolenoid.get();
    }
    
    public boolean getClimberState() {
    	return climberSolenoid.get();
    }
    
//    public void windMotor(double speed) {
//        shooterMotor.set(speed);
//    }

}