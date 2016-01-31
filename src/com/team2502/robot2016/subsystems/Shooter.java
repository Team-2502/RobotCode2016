package com.team2502.robot2016.subsystems;

import com.team2502.robot2016.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * TODO: Remove unnecessary Solenoid/Talon
 */
public class Shooter extends Subsystem
{
//    private final CANTalon shooterMotor = new CANTalon(RobotMap.SHOOTER_MOTOR);
    private final Solenoid shooterSolenoid = new Solenoid(RobotMap.SHOOTER_SOLENOID);

    private DigitalInput limitSwitch = new DigitalInput(RobotMap.SHOOTER_LIMIT_SWITCH);
    
    public void initDefaultCommand() {
//        setDefaultCommand(new ShootBallCommand());
    }

    /**
     * Prepares the shot by either lowering the Solenoid OR pulling down the bar with a Talon.
     */
    public void setSolenoid(boolean state) {
        shooterSolenoid.set(state);
    }
    
//    public void windMotor(double speed) {
//        shooterMotor.set(speed);
//    }

    /**
     * Stops the talon from lowering bar.
     */
//    public void stop() {
//        shooterMotor.set(0);
//    }
}