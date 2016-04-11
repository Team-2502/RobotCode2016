package com.team2502.robot2016.commands.active;

import com.team2502.robot2016.OI;
import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.SubsystemActiveFrame;
import com.team2502.robot2016.subsystems.SubsystemActiveRoller;
import com.team2502.robot2016.subsystems.SubsystemBallHolder;
import com.team2502.robot2016.subsystems.SubsystemSensors;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandSpinActive extends Command
{
    private final SubsystemActiveRoller m_activeRoller = Robot.activeRoller;
    private final SubsystemBallHolder   m_ballHolder   = Robot.ballHolder;
    private final SubsystemActiveFrame  m_activeFrame  = Robot.activeFrame;
    private final SubsystemSensors      m_sensors      = Robot.sensors;

    private double                      m_speed;
    private boolean                     m_flipped      = false;
    private int                         counter        = 0;
    private boolean                     test           = false;

    public CommandSpinActive(double speed, boolean test)
    {
        requires(Robot.activeRoller);
        requires(Robot.ballHolder);
        requires(Robot.activeFrame);
        requires(Robot.sensors);
        m_speed = speed;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize()
    {}

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute()
    {
        m_activeRoller.setActiveRoller(m_speed);
        if(test)
        {
            if(m_sensors.ballInActive() && m_activeFrame.getActivePickupState())
            {
                SmartDashboard.putBoolean("Active Flip Up", true);
                // Timer.delay(.07);
                counter++;

                if(counter > 10)
                {
                    if(m_sensors.ballInActive())
                    {
                        m_flipped = true;
                    }
                } else if(counter > 0 && counter < 10 && !m_sensors.ballInActive())
                {
                    counter = 0;
                }
            }
        }

    }

    @Override
    protected boolean isFinished()
    {
        return m_flipped || !OI.getInstance().getFunctionControlStick().getRawButton(3) && !OI.getInstance().getFunctionControlStick().getRawButton(4) && !test;
    }

    @Override
    protected void end()
    {
        m_activeRoller.stopActiveRoller();
        SmartDashboard.putBoolean("Active Flip Up", false);
    }

    @Override
    protected void interrupted()
    {
        end();
    }
}
