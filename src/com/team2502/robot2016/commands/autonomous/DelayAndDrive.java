package com.team2502.robot2016.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DelayAndDrive extends CommandGroup {
    public DelayAndDrive() {
        addSequential(new DelayTime(9));
        addSequential(new DriveTime(2));
    }
}
