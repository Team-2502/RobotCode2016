package com.team2502.robot2016.subsystems;

import com.team2502.robot2016.RobotMap;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ActiveBar extends Subsystem
{
    private final CANTalon rollerBar;

    public ActiveBar()
    {
        rollerBar = new CANTalon(RobotMap.ACTIVE_ROLLER_BAR);
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
