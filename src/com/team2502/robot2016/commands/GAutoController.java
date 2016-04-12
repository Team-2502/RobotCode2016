package com.team2502.robot2016.commands;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.commands.autonomous.GDriveAndShoot;
import com.team2502.robot2016.commands.autonomous.GDriveSideGoal;
import com.team2502.robot2016.subsystems.Sensors;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GAutoController extends CommandGroup
{

    public GAutoController()
    {
        System.out.println("Start Position: " + Robot.getStartPosition());
        System.out.println("Goal: " + Robot.getGoal());
//        int position = Robot.getStartPosition();
        int goal = Robot.getGoal();
        Sensors.ahrs.zeroYaw();
        if(goal == 1)
        {
            addSequential(new GDriveAndShoot());
        } else if(goal == 2 || goal == 3)
        {
            addSequential(new GDriveSideGoal());
        }
    }
}
