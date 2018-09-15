package com.team2502.robot2016.subsystems;

import com.team2502.robot2016.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SubsystemActiveRoller extends Subsystem
{
    private final WPI_TalonSRX rollerBar;

    public SubsystemActiveRoller()
    {
        rollerBar = new WPI_TalonSRX(RobotMap.ACTIVE_ROLLER_BAR);
    }

    @Override
    public void initDefaultCommand()
    {}

    public void spinActiveRoller()
    {
        rollerBar.set(1);
    }

    public void stopActiveRoller()
    {
        rollerBar.set(0);
    }

    public void setActiveRoller(double speed)
    {
        rollerBar.set(speed);
    }
}
