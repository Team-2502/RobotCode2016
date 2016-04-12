package com.team2502.robot2016.commands.autonomous;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.commands.drive.DriveDefense;
import com.team2502.robot2016.subsystems.Sensors.Sensor;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class GDriveOverDefense extends CommandGroup
{
    public GDriveOverDefense()
    {
        Robot.sensors.zeroGyro();
        // Drive to Green tape area - about
        // addSequential(new DriveTime(SmartDashboard.getNumber("Auto Time
        // Beginning", 2.3)));
        // Straighten out to face the back wall - to within 1 degree.
        // 2 second limit so that if not within 1 degree, it would be good
        // enough
        // addSequential(new RotateToAngle(0), 2);
        addSequential(new DriveDefense(0, .85, Sensor.FrontLong, RobotMap.FRONT_DISTANCE_SENSOR_TURN_LIMIT));
    }
}