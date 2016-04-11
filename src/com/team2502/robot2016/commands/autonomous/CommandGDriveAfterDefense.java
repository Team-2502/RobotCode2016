package com.team2502.robot2016.commands.autonomous;

import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.commands.active.CommandActiveController;
import com.team2502.robot2016.commands.drive.CommandDriveStraight;
import com.team2502.robot2016.commands.drive.CommandRotateToAngle;
import com.team2502.robot2016.subsystems.SubsystemSensors.Sensor;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandGDriveAfterDefense extends CommandGroup
{

    public CommandGDriveAfterDefense(int startingPosition)
    {
        double turnAngle = 0;
        Sensor sensor = Sensor.Left;
        double extraTime = 0;

        if(startingPosition == 4)
        {
            turnAngle = -10;
            sensor = Sensor.FrontLong;
        } else if(startingPosition == 2 || startingPosition == 3)
        {
            turnAngle = 90;
            // if (startingPosition == 3) {
            // extraTime = .2;
            // }
            extraTime = .2;

        } else if(startingPosition == 5)
        {
            turnAngle = -90;
            sensor = Sensor.Right;
        }

        // Straighten out to face the back wall - to within 1 degree.
        // 2 second limit so that if not within 1 degree, it would be good
        // enough

        // Drive toward back wall until sensor reads value fron RobotMap for
        // several times in a row
        // addSequential(new DriveToSensorValue(.7, Sensor.FrontLong,
        // RobotMap.FRONT_DISTANCE_SENSOR_TURN_LIMIT));
        // Turn to the right - limit of 2 seconds (command ends if gets to
        // number faster)
        addSequential(new CommandRotateToAngle(turnAngle), 2);

        // Once turned, read side sensor until on front of tower
        if(startingPosition == 4)
        {
            addSequential(new CommandDriveTime(1.2));

        } else
        {
            addSequential(new CommandDriveStraight(turnAngle, .63, sensor, RobotMap.SIDE_DISTANCE_SENSOR_TURN_LIMIT, true, extraTime));
        }
        // Once in front of tower, turn back to straight
        addSequential(new CommandRotateToAngle(0), 2);

        addParallel(new CommandActiveController(2));
        addSequential(new CommandDriveStraight(0, .7, Sensor.FrontShort, RobotMap.TOWER_SENSOR_DISTANCE_LIMIT), 3.3);
    }
}
