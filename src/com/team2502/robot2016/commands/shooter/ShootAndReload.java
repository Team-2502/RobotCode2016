package com.team2502.robot2016.commands.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootAndReload extends CommandGroup {
    
    public  ShootAndReload() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
 
        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
//    	addParallel(new DriveSpeed(.2), (Robot.active.getActiveState()) ? 1.8 : .3);
    	addSequential(new ShootBall(), 2.5);
    	addSequential(new ReloadShooter());
    	
    }
}
