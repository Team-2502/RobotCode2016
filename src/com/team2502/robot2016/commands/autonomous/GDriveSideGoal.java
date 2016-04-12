package com.team2502.robot2016.commands.autonomous;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.commands.active.ActiveController;
import com.team2502.robot2016.commands.drive.DriveDefense;
import com.team2502.robot2016.commands.drive.DriveStraight;
import com.team2502.robot2016.commands.drive.RotateToAngle;
import com.team2502.robot2016.commands.shooter.GShootAndReload;
import com.team2502.robot2016.subsystems.Sensors.Sensor;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class GDriveSideGoal extends CommandGroup
{

    public GDriveSideGoal()
    {
        int adjustAngle = Robot.getGoal() == 2 ? 1 : -1;
        if(adjustAngle > 0)
        {
            addSequential(new DriveDefense(0, .85, Sensor.FrontLong, RobotMap.SIDE_GOAL_WALL_DISTANCE_LEFT));
        } else
        {
            addSequential(new DriveDefense(0, .85, Sensor.FrontShort, RobotMap.SIDE_GOAL_WALL_DISTANCE_RIGHT));

        }
        addSequential(new RotateToAngle(adjustAngle * RobotMap.SIDE_GOAL_ROTATE_DEGREES));
        addParallel(new ActiveController(2));
        addSequential(new DriveStraight(adjustAngle * RobotMap.SIDE_GOAL_ROTATE_DEGREES, .65, Sensor.FrontShort, RobotMap.TOWER_SENSOR_DISTANCE_LIMIT, .4), 1.6);
        addSequential(new GShootAndReload());
    }
}
