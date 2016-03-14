package com.team2502.robot2016.commands.drive;

import com.team2502.robot2016.commands.autonomous.DriveTime;
import com.team2502.robot2016.subsystems.Sensors.Sensor;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveSensorExtra extends CommandGroup {
    
    public  DriveSensorExtra(double speed, Sensor sensor, double sensorValue) {
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
    	
    	//Normal drive to Sensor Value part
    	addSequential(new DriveToSensorValue(speed, sensor, sensorValue));
    	
    	//Instead of ending here, we drive a little bit extra to make sure
    	addSequential(new DriveTime(.2));
    	
    }
}
