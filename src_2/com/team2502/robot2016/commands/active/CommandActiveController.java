package com.team2502.robot2016.commands.active;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.SubsystemActiveFrame;
import com.team2502.robot2016.subsystems.SubsystemBallHolder;
import edu.wpi.first.wpilibj.command.Command;

public class CommandActiveController extends Command
{
    private SubsystemBallHolder  m_ballHolder  = Robot.ballHolder;
    private SubsystemActiveFrame m_activeFrame = Robot.activeFrame;
    private final int            m_mode;

    public CommandActiveController(int mode)
    {
        requires(Robot.ballHolder);
        requires(Robot.activeFrame);
        m_mode = mode;
    }

    @Override
    protected void initialize()
    {}

    @Override
    protected void execute()
    {
        switch(m_mode)
        {
            case 0:
                m_ballHolder.setBallHolder(false);
            case 1:
                m_ballHolder.setBallHolder(true);
            case 2:
                if(!m_ballHolder.areBallHoldersOpen() && m_activeFrame.getActivePickupState())
                {
                    m_ballHolder.setBallHolder(false);
                }
                m_activeFrame.setActivePickupState(!m_activeFrame.getActivePickupState());
        }
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
