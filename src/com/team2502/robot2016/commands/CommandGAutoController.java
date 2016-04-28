package com.team2502.robot2016.commands;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.commands.autonomous.DriveAndShoot;
import com.team2502.robot2016.commands.autonomous.DriveSideGoal;
import com.team2502.robot2016.subsystems.SubsystemSensors;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CommandGAutoController extends CommandGroup
{

    public CommandGAutoController()
    {
        System.out.println("Start Position: " + Robot.getStartPosition());
        System.out.println("Goal: " + Robot.getGoal());
//        int position = Robot.getStartPosition();
        int goal = Robot.getGoal();
        SubsystemSensors.ahrs.zeroYaw();
        if(goal == 1)
        {
            addSequential(new DriveAndShoot());
        } else if(goal == 2 || goal == 3)
        {
            addSequential(new DriveSideGoal());
        }
    }
}
