package com.team2502.robot2016.commands.autonomous;

import com.team2502.robot2016.RobotMap;
import com.team2502.robot2016.commands.active.ToggleActive;
import com.team2502.robot2016.commands.drive.DriveStraight;
import com.team2502.robot2016.commands.drive.DriveToSensorValue;
import com.team2502.robot2016.commands.drive.RotateToAngle;
import com.team2502.robot2016.subsystems.Sensors.Sensor;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTowerSensors extends CommandGroup {
    
    public  DriveTowerSensors(int startingPosition) {
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
    	
    	if (startingPosition == 4) {
    		turnAngle = -10;
    		sensor = Sensor.FrontLong;
    	} else if (startingPosition == 2 || startingPosition == 3) {
    		turnAngle = 90;
    	} else if (startingPosition == 5) {
    		turnAngle = -90;
    		sensor = Sensor.Right;
    	}
    	
    	
    	addSequential(new DriveOverDefense());
    	addSequential(new DriveAfterDefense(startingPosition));
    	
    	
    }
}
