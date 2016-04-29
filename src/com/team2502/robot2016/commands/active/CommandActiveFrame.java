package com.team2502.robot2016.commands.active;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.SubsystemActiveFrame;
import edu.wpi.first.wpilibj.command.Command;

public class CommandActiveFrame extends Command
{
    private SubsystemActiveFrame  m_activeFrame  = Robot.activeFrame;
    
    public CommandActiveFrame()
    {
        requires(Robot.activeFrame);
    }
    
    @Override
    protected void initialize()
    {}

    @Override
    protected void execute()
    {
        m_activeFrame.setActivePickupState(!m_activeFrame.getActivePickupState());
    }

    @Override
    protected boolean isFinished()
    {
        return true;
    }

    @Override
    protected void end()
    {}

    @Override
    protected void interrupted()
    {}
}