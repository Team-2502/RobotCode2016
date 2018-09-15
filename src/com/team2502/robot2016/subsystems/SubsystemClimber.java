package com.team2502.robot2016.subsystems;

import com.team2502.robot2016.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SubsystemClimber extends Subsystem
{
    private final WPI_TalonSRX liftLeft  = new WPI_TalonSRX(RobotMap.CLIMBER_WINCH_LEFT);
    private final WPI_TalonSRX liftRight = new WPI_TalonSRX(RobotMap.CLIMBER_WINCH_RIGHT);

    @Override
    protected void initDefaultCommand()
    {

    }

    public void setLeft(double speed)
    {
        liftLeft.set(speed);
    }

    public void setRight(double speed)
    {
        liftRight.set(speed);
    }
}
