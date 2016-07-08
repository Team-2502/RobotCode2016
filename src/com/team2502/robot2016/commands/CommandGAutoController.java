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
//        int position = Robot.getStartPosition();
        SubsystemSensors.ahrs.zeroYaw();
        int position = Robot.getStartPosition();
        if(position == 3 || position == 4)
        {
            addSequential(new DriveAndShoot());
        } else if(position == 2 || position == 5)
        {
            addSequential(new DriveSideGoal());
        }
    }
}
