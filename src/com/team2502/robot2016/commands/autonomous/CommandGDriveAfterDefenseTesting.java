package com.team2502.robot2016.commands.autonomous;

import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.commands.drive.CommandDriveStraight;
import com.team2502.robot2016.commands.shooter.CommandGShootAndReload;
import com.team2502.robot2016.subsystems.SubsystemSensors.Sensor;
import edu.wpi.first.wpilibj.command.CommandGroup;

@Deprecated
public class CommandGDriveAfterDefenseTesting extends CommandGroup
{
    public CommandGDriveAfterDefenseTesting(int startingPosition)
    {
        addSequential(new CommandDriveStraight(0, .7, Sensor.FrontLong, RobotMap.FRONT_DISTANCE_SENSOR_TURN_LIMIT));
        addSequential(new CommandGDriveAfterDefense(startingPosition));
        addSequential(new CommandGShootAndReload());
    }
}
