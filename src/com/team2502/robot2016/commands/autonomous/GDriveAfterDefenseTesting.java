package com.team2502.robot2016.commands.autonomous;

import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.commands.drive.DriveStraight;
import com.team2502.robot2016.commands.shooter.GShootAndReload;
import com.team2502.robot2016.subsystems.Sensors.Sensor;
import edu.wpi.first.wpilibj.command.CommandGroup;

@Deprecated
public class GDriveAfterDefenseTesting extends CommandGroup
{
    public GDriveAfterDefenseTesting(int startingPosition)
    {
        addSequential(new DriveStraight(0, .7, Sensor.FrontLong, RobotMap.FRONT_DISTANCE_SENSOR_TURN_LIMIT));
        addSequential(new GDriveAfterDefense(startingPosition));
        addSequential(new GShootAndReload());
    }
}
