package com.team2502.robot2016.commands.autonomous;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.commands.shooter.GShootAndReload;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class GDriveAndShoot extends CommandGroup
{
    public GDriveAndShoot()
    {
        System.out.println("Position: " + Robot.getStartPosition());
        addSequential(new GDriveTowerNoShoot(Robot.getStartPosition()));

        addParallel(new DriveTime(1));
        addSequential(new GShootAndReload());
    }
}
