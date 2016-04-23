package com.team2502.robot2016;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import com.team2502.robot2016.commands.AutoController;
import com.team2502.robot2016.commands.active.SetActive;
import com.team2502.robot2016.commands.active.ToggleActive;
import com.team2502.robot2016.commands.autonomous.DriveAfterDefense;
import com.team2502.robot2016.commands.autonomous.DriveAfterDefenseTesting;
import com.team2502.robot2016.commands.autonomous.DriveTime;
import com.team2502.robot2016.commands.drive.DriveDefense;
import com.team2502.robot2016.commands.drive.DriveStraight;
import com.team2502.robot2016.commands.drive.RotateToAngle;
import com.team2502.robot2016.subsystems.*;
import com.team2502.robot2016.subsystems.Shooter;
import com.team2502.robot2016.subsystems.Sensors.Sensor;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final DriveTrain driveTrain = new DriveTrain();
	public static final Shooter ballShooter = new Shooter();
	public static final ActiveIntake active = new ActiveIntake();
	public static final ActiveBar activeBar = new ActiveBar();
	public static final Climber climber = new Climber();
	public static final Sensors sensors = new Sensors();
	
	public static final Vision vision = new Vision();
	
	public static boolean inAuto = false;
	
	public static OI oi;

    Command autonomousCommand;
    SendableChooser autoChooser;
    public static SendableChooser positionSelector;
    public static SendableChooser goalSelector;
    public static SendableChooser climberOption;



    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
    	
		System.err.println("Start init");
		oi = new OI();
		System.err.println("OI");

		positionSelector = new SendableChooser();
        positionSelector.addDefault("Second Position", 2);
        positionSelector.addObject("Third Position", 3);
        positionSelector.addObject("Fourth Position", 4);
        positionSelector.addObject("Fifth Position", 5);
        SmartDashboard.putData("Position Selector", positionSelector);
        
        goalSelector = new SendableChooser();
        goalSelector.addDefault("Middle Goal", 1);
        goalSelector.addObject("Left Goal", 2);
        goalSelector.addObject("Right Goal", 3);
        SmartDashboard.putData("Goal Selector", goalSelector);

        climberOption = new SendableChooser();
		climberOption.addDefault("Buttons 11 (up) and 12 (down)", "Buttons");
		climberOption.addObject("Button and Joystick Control", "Joystick");
        SmartDashboard.putData("Climber Options Chooser", climberOption);
        
		SmartDashboard.putData("Drive Train", driveTrain);
		SmartDashboard.putData("Shooter", ballShooter);
		SmartDashboard.putData("Active", active);
		SmartDashboard.putData("Active Bar", activeBar);
        
		
//		CameraServer.getInstance().startAutomaticCapture("cam0");
        autoChooser = new SendableChooser();		
        autoChooser.addDefault("Defense Only - forward time", new DriveTime(3));
        autoChooser.addObject("Full Auto", new AutoController());
        autoChooser.addObject("Spy Bot - forward time 1.5", new DriveTime(1.5));
        autoChooser.addObject("No Auto", null);
        SmartDashboard.putData("Auto mode", autoChooser);
        
        
        SmartDashboard.putNumber("SIDE_GOAL_ROTATE_DEGREES", RobotMap.SIDE_GOAL_ROTATE_DEGREES);
        SmartDashboard.putNumber("SIDE_GOAL_WALL_DISTANCE", RobotMap.SIDE_GOAL_WALL_DISTANCE_LEFT);
        
        SmartDashboard.putNumber("TOWER_EXTRA_TIME", RobotMap.TOWER_EXTRA_TIME);
		
		sensors.zeroGyro();
    }
    
    private void testAutoParts(int startingPosition) {
    	
    	SmartDashboard.putData("After Defense", new DriveAfterDefense(startingPosition));
    	
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
    	
		SmartDashboard.putData("Drive Over Defense to Wall", new DriveDefense(0, .85, Sensor.FrontLong, RobotMap.FRONT_DISTANCE_SENSOR_TURN_LIMIT, .5));
    	SmartDashboard.putData("Drive to sensor limit wall", new DriveStraight(0, .85, Sensor.FrontLong, RobotMap.FRONT_DISTANCE_SENSOR_TURN_LIMIT, .5, 1.4));
		SmartDashboard.putData("Rotate sideways", new RotateToAngle(turnAngle));
		SmartDashboard.putData("Drive in front of Tower", new DriveStraight(turnAngle, .63, sensor, RobotMap.SIDE_DISTANCE_SENSOR_TURN_LIMIT, true, .35));
		SmartDashboard.putData("Rotate to forward", new RotateToAngle(5));
		SmartDashboard.putData("Flip Active", new ToggleActive());
		SmartDashboard.putData("Drive to tower", new DriveStraight(0, .7, Sensor.FrontShort, RobotMap.TOWER_SENSOR_DISTANCE_LIMIT, .3));
		
		
		SmartDashboard.putData("Drive After Defense", new DriveAfterDefenseTesting(startingPosition));
    }
    
    public static int getStartPosition() {
    	return (int) positionSelector.getSelected();
    }
    
    public static int getGoal() {
    	return (int) goalSelector.getSelected();
    }
    
    public static String getClimberOption() {
    	return (String) climberOption.getSelected();
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    @Override
    public void disabledInit(){
    	Scheduler.getInstance().add(new SetActive(false));
    }
	
    @Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    @Override
    public void autonomousInit() {
        sensors.zeroGyro();
        Sensors.ahrs.zeroYaw();
        
    	inAuto = true;
        sensors.updateData();
        driveTrain.brakeMode(true);
        
    	// schedule the autonomous command (example)
		RobotMap.TOWER_EXTRA_TIME = SmartDashboard.getNumber("TOWER_EXTRA_TIME", RobotMap.TOWER_EXTRA_TIME);
		SmartDashboard.putNumber("TOWER_EXTRA_TIME", RobotMap.TOWER_EXTRA_TIME);

        int startPosition = (int) SmartDashboard.getNumber("Start Position", 2);
        autoChooser.addObject("Full Auto", new AutoController());

		testAutoParts(startPosition);
        autonomousCommand = (Command) autoChooser.getSelected();

        if (autonomousCommand != null) autonomousCommand.start();

    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        sensors.updateData();
    }

    @Override
    public void teleopInit() {
    	inAuto = false;
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
		RobotMap.TOWER_EXTRA_TIME = SmartDashboard.getNumber("TOWER_EXTRA_TIME", RobotMap.TOWER_EXTRA_TIME);

		int startPosition = (int) SmartDashboard.getNumber("Start Position", 2);

    	testAutoParts(startPosition);

        autoChooser.addObject("Full Auto", new AutoController());

    	driveTrain.brakeMode(true);
    	System.err.println("RSF-teleopinit");
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        sensors.updateData();
        sensors.updateOtherSensors();
    }
    
    

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
        LiveWindow.run();
    }
}

