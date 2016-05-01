package com.team2502.robot2016.commands.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ShootAndReload extends CommandGroup {
    
    public  ShootAndReload() {
    	
//    	addParallel(new DriveSpeed(.2), (Robot.active.getActiveState()) ? 1.8 : .3);
    	addSequential(new ShootBall(), 2.5);
    	addSequential(new WaitCommand(1));
    	addSequential(new ReloadShooter());
    }
}
