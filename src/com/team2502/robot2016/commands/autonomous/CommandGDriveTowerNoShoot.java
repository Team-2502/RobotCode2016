package com.team2502.robot2016.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandGDriveTowerNoShoot extends CommandGroup
{
    public CommandGDriveTowerNoShoot(int startingPosition)
    {
        addSequential(new CommandGDriveOverDefense());
        addSequential(new CommandGDriveAfterDefense(startingPosition));
    }
}
