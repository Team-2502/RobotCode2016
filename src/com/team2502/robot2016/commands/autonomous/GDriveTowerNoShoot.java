package com.team2502.robot2016.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GDriveTowerNoShoot extends CommandGroup
{
    public GDriveTowerNoShoot(int startingPosition)
    {
        addSequential(new GDriveOverDefense());
        addSequential(new GDriveAfterDefense(startingPosition));
    }
}
