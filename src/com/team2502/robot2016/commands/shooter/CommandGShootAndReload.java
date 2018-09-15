package com.team2502.robot2016.commands.shooter;

import com.team2502.robot2016.commands.active.SetActiveStateCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Fires the shooter then lowers it back down.
 */
public class CommandGShootAndReload extends CommandGroup
{
    public CommandGShootAndReload()
    {
        //addSequential(new SetActiveStateCommand(true));
        //addSequential(new WaitCommand(1));
        addSequential(new CommandShootBall());
        addSequential(new WaitCommand(1));
        addSequential(new CommandReloadShooter());
    }
}
