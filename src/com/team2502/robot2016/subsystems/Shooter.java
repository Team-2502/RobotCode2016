package com.team2502.robot2016.subsystems;


import com.team2502.robot2016.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * TODO: Remove unnecessary Solenoid/Talon
 */
public class Shooter extends Subsystem
{
//    private final CANTalon shooterMotor = new CANTalon(RobotMap.SHOOTER_MOTOR);
    private final Solenoid shooterSolenoid = new Solenoid(RobotMap.SHOOTER_SOLENOID);
    	
    
	public boolean climberUp = false;
	
    public void initDefaultCommand() {
//        setDefaultCommand(new ShootBallCommand());
//    	setDefaultCommand(new ClimberWinch());
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
    
    
    
//    public void windMotor(double speed) {
//        shooterMotor.set(speed);
//    }

}