package com.team2502.robot2016.commands.autonomous;

import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.commands.active.ToggleActive;
import com.team2502.robot2016.commands.drive.DriveStraight;
import com.team2502.robot2016.commands.drive.DriveToSensorValue;
import com.team2502.robot2016.commands.drive.RotateToAngle;
import com.team2502.robot2016.commands.shooter.ShootAndReload;
import com.team2502.robot2016.subsystems.Sensors.Sensor;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveAfterDefense extends CommandGroup {
    
    public  DriveAfterDefense(int startingPosition) {
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
    	double turnAngle = 0;
    	Sensor sensor = Sensor.Left;
    	double timeMin = 2;
    	
    	if (startingPosition == 4) {
    		turnAngle = -10;
    		sensor = Sensor.FrontLong;
    	} else if (startingPosition == 2 || startingPosition == 3) {
    		turnAngle = 90;
    		if (startingPosition == 3) {
    			timeMin = .3;
    		}
    	} else if (startingPosition == 5) {
    		turnAngle = -90;
    		sensor = Sensor.Right;
    	}
    	
    	
    	//Straighten out to face the back wall - to within 1 degree.
    	//2 second limit so that if not within 1 degree, it would be good enough
    	
    	//Drive toward back wall until sensor reads value fron RobotMap for several times in a row
//    	addSequential(new DriveToSensorValue(.7, Sensor.FrontLong, RobotMap.FRONT_DISTANCE_SENSOR_TURN_LIMIT));
    	//Turn to the right - limit of 2 seconds (command ends if gets to number faster)
    	addSequential(new RotateToAngle(turnAngle), 2);
    	
    	//Once turned, read side sensor until on front of tower
    	addSequential(new DriveToSensorValue(.63, sensor, RobotMap.SIDE_DISTANCE_SENSOR_TURN_LIMIT, true, .35, timeMin));
    	//Once in front of tower, turn back to straight
    	addSequential(new RotateToAngle(5), 2);
    	
    	addParallel(new ToggleActive());
    	addSequential(new DriveStraight(0, .7, Sensor.FrontShort, RobotMap.TOWER_SENSOR_DISTANCE_LIMIT, .5), 3.3);
    
    	
    	addSequential(new ShootAndReload());

    }
}
