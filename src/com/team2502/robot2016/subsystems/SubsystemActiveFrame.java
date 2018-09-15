package com.team2502.robot2016.subsystems;

import com.team2502.robot2016.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SubsystemActiveFrame extends Subsystem
{
    private final Solenoid activeSolenoid;

    public SubsystemActiveFrame()
    {
        activeSolenoid = new Solenoid(RobotMap.ACTIVE_SOLENOID);
    }

    @Override
    protected void initDefaultCommand()
    {}

    public void setActivePickupState(boolean down)
    {
        activeSolenoid.set(down);
    }

    public boolean getActivePickupState()
    {
        return activeSolenoid.get();
    }
}