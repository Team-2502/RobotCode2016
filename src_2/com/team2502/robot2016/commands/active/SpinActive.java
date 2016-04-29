package com.team2502.robot2016.commands.active;

import com.team2502.robot2016.OI;
import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.SubsystemActiveFrame;
import com.team2502.robot2016.subsystems.SubsystemActiveRoller;
import com.team2502.robot2016.subsystems.SubsystemBallHolder;
import com.team2502.robot2016.subsystems.SubsystemSensors;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SpinActive extends Command
{
    private final SubsystemActiveRoller m_activeRoller = Robot.activeRoller;
    private final SubsystemSensors      m_sensors      = Robot.sensors;

    private double                      m_speed;
    private boolean                     m_flipped      = false;
    private int                         counter        = 0;
    private boolean                     test           = false;

    public SpinActive(double speed, boolean test)
    {
        requires(Robot.activeRoller);
        
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
