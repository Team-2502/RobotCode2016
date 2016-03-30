package com.team2502.robot2016.commands.autonomous;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.commands.drive.DriveDefense;
import com.team2502.robot2016.subsystems.Sensors.Sensor;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveOverDefense extends CommandGroup {
    
    public  DriveOverDefense() {
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
    	Robot.sensors.zeroGyro();
    	//Drive to Green tape area - about
//    	addSequential(new DriveTime(SmartDashboard.getNumber("Auto Time Beginning", 2.3)));
    	//Straighten out to face the back wall - to within 1 degree.
    	//2 second limit so that if not within 1 degree, it would be good enough
//    	addSequential(new RotateToAngle(0), 2);
    	addSequential(new DriveDefense(0, .85, Sensor.FrontLong, RobotMap.FRONT_DISTANCE_SENSOR_TURN_LIMIT));

    	
    }
}
