package com.team2502.robot2016.subsystems;

import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.commands.ShootBallCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BallShooterSubsystem extends Subsystem
{
    private final CANTalon shooterTalon         = new CANTalon(RobotMap.SHOOTER_TALON);
    private final Solenoid shooterSolenoidLeft  = new Solenoid(RobotMap.SHOOTER_SOLENOID_LEFT);
    private final Solenoid shooterSolenoidRight = new Solenoid(RobotMap.SHOOTER_SOLENOID_RIGHT);

    @Override
    protected void initDefaultCommand()
    {
        super.setDefaultCommand(new ShootBallCommand());
    }

    public void prepareShot()
    {
        shooterSolenoidLeft.set(false);
        shooterSolenoidRight.set(false);
        shooterTalon.set(-0.5D);
    }

    public void stop()
    {
        shooterTalon.set(0.0D);
    }

    public void shoot()
    {
        shooterSolenoidLeft.set(true);
        shooterSolenoidRight.set(true);
        shooterTalon.set(1.0D);
    }
}