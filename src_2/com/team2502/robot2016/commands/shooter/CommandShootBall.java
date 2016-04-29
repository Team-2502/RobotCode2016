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
//    private SubsystemDriveTrain driveTrain  = Robot.driveTrain;
    private boolean      run         = true,
                                        stop = true, end = false;;
    private long endTime = 0;
    
    public CommandShootBall()
    {
        requires(Robot.ballShooter);
        requires(Robot.ballHolder);
//        requires(Robot.sensors);
//        requires(Robot.driveTrain);
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
        Robot.driveTrain.brakeMode(true);
//         Robot.driveTrain.brakeMode(true);
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
//            if(sensors.getAngle() < 3 || sensors.getAngle() > 357 || !Robot.inAuto)
//            {
                 
                if(Robot.inAuto)
                {
                    ballHolder.setBallHolder(true);
//                  driveTrain.brakeMode(true);
                  Timer.delay(.1);
                    Timer.delay(.5);
                }
                // System.err.println("Shot");
                ballShooter.setSolenoid(true);
//            }
                endTime = System.currentTimeMillis();

        } else {
            System.out.println(System.currentTimeMillis() - endTime);
            end = System.currentTimeMillis() - endTime > 750;
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
        return end;
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
//        Timer.delay(1);
        Robot.driveTrain.brakeMode(false);
        if (Robot.inAuto)
            ballHolder.setBallHolder(false);

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