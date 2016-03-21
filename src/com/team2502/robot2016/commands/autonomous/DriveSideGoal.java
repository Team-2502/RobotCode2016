package com.team2502.robot2016.commands.autonomous;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.commands.active.ToggleActive;
import com.team2502.robot2016.commands.drive.DriveDefense;
import com.team2502.robot2016.commands.drive.DriveStraight;
import com.team2502.robot2016.commands.drive.RotateToAngle;
import com.team2502.robot2016.commands.shooter.ShootAndReload;
import com.team2502.robot2016.subsystems.Sensors.Sensor;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSideGoal extends CommandGroup {
    
    public  DriveSideGoal() {
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
    	
    	int adjustAngle = (Robot.getGoal() == 2) ? 1 : -1;
    	addSequential(new DriveDefense(0, .85, Sensor.FrontLong, SmartDashboard.getNumber("SIDE_GOAL_WALL_DISTANCE", RobotMap.SIDE_GOAL_WALL_DISTANCE)));
    	addSequential(new RotateToAngle(adjustAngle * SmartDashboard.getNumber("SIDE_GOAL_ROTATE_DEGREES", RobotMap.SIDE_GOAL_ROTATE_DEGREES)));
    	addParallel(new ToggleActive());
    	addSequential(new DriveStraight(adjustAngle * SmartDashboard.getNumber("SIDE_GOAL_ROTATE_DEGREES", RobotMap.SIDE_GOAL_ROTATE_DEGREES), 
    			.65, Sensor.FrontShort, 
    			SmartDashboard.getNumber("SIDE_GOAL_TOWER_DISTANCE", RobotMap.SIDE_GOAL_TOWER_DISTANCE),
    			.4), .6);
    	addSequential(new ShootAndReload());
    }
}
