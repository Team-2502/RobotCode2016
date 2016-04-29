package com.team2502.robot2016.commands.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * Fires the shooter then lowers it back down.
 */
public class CommandGShootAndReload extends CommandGroup
{
    public CommandGShootAndReload()
    {
        addSequential(new CommandShootBall(), 1);
        addSequential(new CommandReloadShooter());
    }
}
