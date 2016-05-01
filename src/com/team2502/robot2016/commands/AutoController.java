package com.team2502.robot2016.commands;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.commands.autonomous.DriveAndShoot;
import com.team2502.robot2016.commands.autonomous.DriveSideGoal;
import com.team2502.robot2016.subsystems.Sensors;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoController extends CommandGroup {
    
    public  AutoController() {
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
    	
    	System.out.println("Start Position: " + Robot.getStartPosition());
    	Sensors.ahrs.zeroYaw();
    	int position = Robot.getStartPosition();
    	
    	if (position == 3 || position == 4) {
    		addSequential(new DriveAndShoot());
    	} else if (position == 2 || position == 5) {
    		addSequential(new DriveSideGoal());
    	}
    }
}
