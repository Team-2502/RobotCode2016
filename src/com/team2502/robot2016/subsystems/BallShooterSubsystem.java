package com.team2502.robot2016.subsystems;

import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.commands.ShootBallCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * TODO: Remove unnecessary Solenoid/Talon
 */
public class BallShooterSubsystem extends Subsystem
{
    private final CANTalon shooterTalon    = new CANTalon(RobotMap.SHOOTER_TALON);
    private final Solenoid shooterSolenoid = new Solenoid(RobotMap.SHOOTER_SOLENOID);

    @Override
    protected void initDefaultCommand()
    {
        super.setDefaultCommand(new ShootBallCommand());
    }

    /**
     * Prepares the shot by either lowering the Solenoid OR pulling down the bar with a Talon.
     */
    public void prepareShot()
    {
        shooterSolenoid.set(false);
        shooterTalon.set(-0.5D);
    }

    /**
     * Stops the talon from lowering bar.
     */
    public void stop()
    {
        shooterTalon.set(0.0D);
    }

    /**
     * Shoots the boulder by either raising the Solenoid OR letting out slack with the Talon.
     */
    public void shoot()
    {
        shooterSolenoid.set(true);
        shooterTalon.set(1.0D);
    }
}