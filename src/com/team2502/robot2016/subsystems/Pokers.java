package com.team2502.robot2016.subsystems;

import com.team2502.robot2016.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pokers extends Subsystem
{
    private final Solenoid ballHolderSolenoid;

    public Pokers()
    {
        ballHolderSolenoid = new Solenoid(RobotMap.POKER_SOLENOID);
    }

    @Override
    public void initDefaultCommand()
    {}

    public void setBallHolder(boolean state)
    {
        ballHolderSolenoid.set(state);
    }

    public boolean areBallHoldersOpen()
    {
        return ballHolderSolenoid.get();
    }
}
