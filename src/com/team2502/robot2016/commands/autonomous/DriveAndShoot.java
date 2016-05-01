package com.team2502.robot2016.commands.autonomous;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.commands.shooter.ShootAndReload;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveAndShoot extends CommandGroup {
    
    public  DriveAndShoot() {
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
    	System.out.println("Position: " + Robot.getStartPosition());
    	addSequential(new DriveTowerNoShoot(Robot.getStartPosition()));
    	addSequential(new ShootCheck(0));
    	addParallel(new DriveTime(1));
    	addSequential(new ShootAndReload());
    }
}
