package com.team2502.robot2016.commands.autonomous;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.commands.active.CommandActiveController;
import com.team2502.robot2016.commands.drive.DriveDefense;
import com.team2502.robot2016.commands.drive.DriveStraight;
import com.team2502.robot2016.commands.drive.RotateToAngle;
import com.team2502.robot2016.commands.shooter.CommandGShootAndReload;
import com.team2502.robot2016.subsystems.SubsystemSensors.Sensor;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveSideGoal extends CommandGroup
{

    public DriveSideGoal()
    {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        // addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        // addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.

        int adjustAngle = Robot.getGoal() == 2 ? 1 : -1;
        if(adjustAngle > 0)
        {
            addSequential(new DriveDefense(0, .85, Sensor.FrontLong, RobotMap.SIDE_GOAL_WALL_DISTANCE_LEFT));
        } else
        {
            addSequential(new DriveDefense(0, .85, Sensor.FrontShort, RobotMap.SIDE_GOAL_WALL_DISTANCE_RIGHT));

        }
        addSequential(new RotateToAngle(adjustAngle * RobotMap.SIDE_GOAL_ROTATE_DEGREES));
        addParallel(new CommandActiveController(2));
        addSequential(new DriveStraight(adjustAngle * RobotMap.SIDE_GOAL_ROTATE_DEGREES, .65, Sensor.FrontShort, RobotMap.TOWER_SENSOR_DISTANCE_LIMIT, .4), 1.6);
        addSequential(new CommandGShootAndReload());
    }
}
