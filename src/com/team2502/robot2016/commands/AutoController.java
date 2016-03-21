package com.team2502.robot2016.commands;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.commands.autonomous.DriveAndShoot;
import com.team2502.robot2016.commands.autonomous.DriveSideGoal;

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
    	
    	int position = Robot.getStartPosition();
    	int goal = Robot.getGoal();
    	
    	if (goal == 1) {
    		
    		addSequential(new DriveAndShoot());
    		
    		
    		
    	} else if (goal == 2 || goal == 3) {
    		
    		addSequential(new DriveSideGoal());
    		
    	}
    	
    	
    	
    }
}
