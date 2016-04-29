package com.team2502.robot2016.commands.active;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.SubsystemBallHolder;
import edu.wpi.first.wpilibj.command.Command;

public class CommandBallPokers extends Command
{
    private SubsystemBallHolder  m_ballHolder  = Robot.ballHolder;
    private final boolean            m_mode;
    
    public CommandBallPokers(boolean mode)
    {
        requires(Robot.ballHolder);
        m_mode = mode;
    }
    
    @Override
    protected void initialize()
    {}

    @Override
    protected void execute()
    {
        m_ballHolder.setBallHolder(m_mode);
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