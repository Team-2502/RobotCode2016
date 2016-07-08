package com.team2502.robot2016.commands.shooter;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.SubsystemBallHolder;
import com.team2502.robot2016.subsystems.SubsystemDriveTrain;
import com.team2502.robot2016.subsystems.SubsystemSensors;
//import com.team2502.robot2016.subsystems.Sensors;
import com.team2502.robot2016.subsystems.SubsystemShooter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class CommandShootBall extends Command
{

    private SubsystemShooter    ballShooter = Robot.ballShooter;
    private SubsystemBallHolder ballHolder  = Robot.ballHolder;
    private SubsystemSensors    sensors     = Robot.sensors;
    private SubsystemDriveTrain driveTrain  = Robot.driveTrain;

    public CommandShootBall()
    {
        requires(Robot.ballShooter);
        requires(Robot.ballHolder);
        requires(Robot.sensors);
        requires(Robot.driveTrain);
    }

    /**
     * Lower the bar if it is not already at the lowest possible point. Lowest
     * Point = limitSwitch.get() == true
     */
    @Override
    protected void initialize()
    {
        // Robot.driveTrain.brakeMode(true);
    }

    /**
     * Shoot the boulder.
     */
    @Override
    protected void execute()
    {
        if(sensors.getAngle() < 3 || sensors.getAngle() > 357 || !Robot.inAuto)
        {
            ballShooter.setSolenoid(true);
        }
    }

    @Override
    protected boolean isFinished()
    {
        return true;
    }

    /**
     * Stop the lowering of the bar.
     */
    @Override
    protected void end()
    {
        ballShooter.setSolenoid(false);
    }

    /**
     * Emergency stop the lowering of the bar.
     */
    @Override
    protected void interrupted()
    {
//        driveTrain.brakeMode(false);
    }
}