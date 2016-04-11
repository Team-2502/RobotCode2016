package com.team2502.robot2016.commands.autonomous;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.commands.shooter.CommandGShootAndReload;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandGDriveAndShoot extends CommandGroup
{
    public CommandGDriveAndShoot()
    {
        System.out.println("Position: " + Robot.getStartPosition());
        addSequential(new CommandGDriveTowerNoShoot(Robot.getStartPosition()));

        addParallel(new CommandDriveTime(1));
        addSequential(new CommandGShootAndReload());
    }
}
