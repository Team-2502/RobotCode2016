package com.team2502.robot2016.commands.shooter;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.Pokers;
import com.team2502.robot2016.subsystems.DriveTrain;
import com.team2502.robot2016.subsystems.Sensors;
//import com.team2502.robot2016.subsystems.Sensors;
import com.team2502.robot2016.subsystems.Shooter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class ShootBall extends Command
{

    private Shooter    ballShooter = Robot.ballShooter;
    private Pokers ballHolder  = Robot.ballHolder;
    private Sensors    sensors     = Robot.sensors;
    private DriveTrain driveTrain  = Robot.driveTrain;
    private static boolean      run         = true,
                                        stop = true, end = false;;

    public ShootBall()
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
        run = true;
        stop = true;
        end = false;
        // Robot.driveTrain.brakeMode(true);
    }

    /**
     * Shoot the boulder.
     */
    @Override
    protected void execute()
    {

        // if (!ai.getActiveState()) {
        // ai.setActiveState(true);
        // Timer.delay(1.4);
        // } else {
        // }
        System.out.println("ShootBall.execute()");
        if(run)
        {
            System.out.println("ShootBall.execute() Run");

            run = false;
            if(sensors.getAngle() < 3 || sensors.getAngle() > 357 || !Robot.inAuto)
            {
                // ai.openPokers();
//                driveTrain.brakeMode(true);
                Timer.delay(.1);
                if(Robot.inAuto)
                {
                    Timer.delay(.5);
                }
                // System.err.println("Shot");
                ballShooter.setSolenoid(true);
            }
        }
//        driveTrain.brakeMode(false);
    }

    @Override
    protected boolean isFinished()
    {
        // return s.shooterAllTheWayForward();
        // if(stop)
        // {
        // checkEnd();
        // }
        // return end;
        return true;
    }

    public void checkEnd()
    {
        for(int i = 0; i < 20; ++i)
        {
            Scheduler.getInstance().run();
            Timer.delay(0.05);
        }
        end = true;
    }

    /**
     * Stop the lowering of the bar.
     */
    @Override
    protected void end()
    {
        System.out.println("ShootBall.end()");

        // if(stop)
        // {
        // System.out.println("ShootBall.end() Run");
        // stop = false;
//        driveTrain.brakeMode(true);
        Timer.delay(1);
//        driveTrain.brakeMode(false);

        // REVIEW NJL
        // long time = System.currentTimeMillis();
        // while(true)
        // {
        // Scheduler.getInstance().run();
        // if(System.currentTimeMillis() - time >= 1000)
        // {
        // break;
        // }
        // for(int i = 0; i < 50; ++i)
        // {}
        // }

        // for(int i = 0; i < 20; ++i)
        // {
        // Scheduler.getInstance().run();
        // Timer.delay(0.05);
        // }
        // }
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