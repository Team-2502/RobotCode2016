package com.team2502.robot2016;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import com.team2502.robot2016.commands.drive.DriveAndShoot;
import com.team2502.robot2016.commands.drive.DriveStraight;
import com.team2502.robot2016.commands.drive.DriveTime;
import com.team2502.robot2016.commands.drive.DriveTowardTower;
import com.team2502.robot2016.commands.drive.LightOn;
import com.team2502.robot2016.subsystems.ActiveBar;
import com.team2502.robot2016.subsystems.ActiveIntake;
import com.team2502.robot2016.subsystems.DriveTrain;
import com.team2502.robot2016.subsystems.ExampleSubsystem;
import com.team2502.robot2016.subsystems.PIDDriveTrain;
import com.team2502.robot2016.subsystems.Sensors;
//import com.team2502.robot2016.subsystems.Sensors;
import com.team2502.robot2016.subsystems.Shooter;

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

//	public static final DriveTrain driveTrain = new DriveTrain();
	public static final PIDDriveTrain driveTrain = new PIDDriveTrain();
	public static final Shooter ballShooter = new Shooter();
	public static final ActiveIntake active = new ActiveIntake();
	public static final ActiveBar activeBar = new ActiveBar();

	public static final Sensors sensors = new Sensors();
//	public static final Sensors sensors = new Sensors();
	
//	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;

    Command autonomousCommand;
    SendableChooser chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
		System.err.println("Start init");
		oi = new OI();
		System.err.println("OI");
		CameraServer.getInstance().startAutomaticCapture("cam0");
        chooser = new SendableChooser();
        
        chooser.addDefault("Defense Only - forward time", new DriveTime(3));
        chooser.addObject("Spy Bot - forward time 1.5", new DriveTime(1.5));
        chooser.addObject("No Auto", null);
        chooser.addObject("Drive to Tower", new DriveStraight(.9));
        chooser.addObject("Drive to Tower and Shoot", new DriveTowardTower());
//        chooser.addObject("Drive to hdbjhsbdcs and Shoot", new DriveAndShoot());

//        chooser.addObject("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", chooser);
        SmartDashboard.putNumber("BALL_VOLT_ACTIVE", RobotMap.BALL_VOLT_ACTIVE);
		SmartDashboard.putNumber("BALL_VOLT_SHOOTER", RobotMap.BALL_VOLT_SHOOTER);
		SmartDashboard.putNumber("BALL_MIDDLE_VOLT_SHOOTER", RobotMap.BALL_MIDDLE_VOLT_ACTIVE);
		SmartDashboard.putNumber("BALL_NOTHING_VOLT_SHOOTER", RobotMap.BALL_NOTHING_VOLT_ACTIVE);
		SmartDashboard.putNumber("FRONT_DISTANCE_SENSOR_LIMIT", RobotMap.FRONT_DISTANCE_SENSOR_LIMIT);

    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    @Override
    public void disabledInit(){
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
        autonomousCommand = (Command) chooser.getSelected();
        sensors.updateData();
//    	autonomousCommand = new LightOn(10);
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
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
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	
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
