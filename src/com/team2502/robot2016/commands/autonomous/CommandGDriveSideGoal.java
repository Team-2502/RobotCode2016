package com.team2502.robot2016.commands.autonomous;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.commands.active.CommandActiveController;
import com.team2502.robot2016.commands.drive.CommandDriveDefense;
import com.team2502.robot2016.commands.drive.CommandDriveStraight;
import com.team2502.robot2016.commands.drive.CommandRotateToAngle;
import com.team2502.robot2016.commands.shooter.CommandGShootAndReload;
import com.team2502.robot2016.subsystems.SubsystemSensors.Sensor;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandGDriveSideGoal extends CommandGroup
{

    public CommandGDriveSideGoal()
    {
        int adjustAngle = Robot.getGoal() == 2 ? 1 : -1;
        if(adjustAngle > 0)
        {
            addSequential(new CommandDriveDefense(0, .85, Sensor.FrontLong, RobotMap.SIDE_GOAL_WALL_DISTANCE_LEFT));
        } else
        {
            addSequential(new CommandDriveDefense(0, .85, Sensor.FrontShort, RobotMap.SIDE_GOAL_WALL_DISTANCE_RIGHT));

        }
        addSequential(new CommandRotateToAngle(adjustAngle * RobotMap.SIDE_GOAL_ROTATE_DEGREES));
        addParallel(new CommandActiveController(2));
        addSequential(new CommandDriveStraight(adjustAngle * RobotMap.SIDE_GOAL_ROTATE_DEGREES, .65, Sensor.FrontShort, RobotMap.TOWER_SENSOR_DISTANCE_LIMIT, .4), 1.6);
        addSequential(new CommandGShootAndReload());
    }
}
