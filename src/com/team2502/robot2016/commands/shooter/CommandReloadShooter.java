package com.team2502.robot2016.commands.shooter;

import com.team2502.robot2016.Robot;
//import com.team2502.robot2016.subsystems.Sensors;
import com.team2502.robot2016.subsystems.SubsystemShooter;
import edu.wpi.first.wpilibj.command.Command;

public class CommandReloadShooter extends Command
{

    private SubsystemShooter bs = Robot.ballShooter;

    public CommandReloadShooter()
    {
        requires(Robot.ballShooter);
    }

    /**
     * Lower the bar if it is not already at the lowest possible point. Lowest
     * Point = limitSwitch.get() == true
     */
    @Override
    protected void initialize()
    {}

    @Override
    protected void execute()
    {
        bs.setSolenoid(false);
    }

    @Override
    protected boolean isFinished()
    {
        // return s.shooterAllTheWayBack();
        return true;
    }

    /**
     * Stop the lowering of the bar.
     */
    @Override
    protected void end()
    {

    }

    /**
     * Emergency stop the lowering of the bar.
     */
    @Override
    protected void interrupted()
    {

    }
}