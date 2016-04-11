package com.team2502.robot2016;

import com.team2502.robot2016.commands.CommandGAutoController;
import com.team2502.robot2016.commands.active.CommandActiveController;
import com.team2502.robot2016.commands.autonomous.DriveAfterDefense;
import com.team2502.robot2016.commands.autonomous.DriveAfterDefenseTesting;
import com.team2502.robot2016.commands.autonomous.DriveTime;
import com.team2502.robot2016.commands.drive.DriveDefense;
import com.team2502.robot2016.commands.drive.DriveStraight;
import com.team2502.robot2016.commands.drive.RotateToAngle;
import com.team2502.robot2016.subsystems.SubsystemActiveFrame;
import com.team2502.robot2016.subsystems.SubsystemActiveRoller;
import com.team2502.robot2016.subsystems.SubsystemBallHolder;
import com.team2502.robot2016.subsystems.SubsystemClimber;
import com.team2502.robot2016.subsystems.SubsystemDriveTrain;
import com.team2502.robot2016.subsystems.SubsystemSensors;
import com.team2502.robot2016.subsystems.SubsystemSensors.Sensor;
import com.team2502.robot2016.subsystems.SubsystemShooter;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{
    public static final SubsystemDriveTrain   driveTrain         = new SubsystemDriveTrain();
    public static final SubsystemShooter      ballShooter        = new SubsystemShooter();
    public static final SubsystemBallHolder   ballHolder         = new SubsystemBallHolder();
    public static final SubsystemActiveRoller activeRoller       = new SubsystemActiveRoller();
    public static final SubsystemActiveFrame  activeFrame        = new SubsystemActiveFrame();
    public static final SubsystemClimber      climber            = new SubsystemClimber();
    public static final SubsystemSensors      sensors            = new SubsystemSensors();

    public static boolean                     inAuto             = false;

    public static Command                     autonomousCommand;

    /**
     * Adds controls to choose which defense the robot will be starting at in
     * autonomous.
     */
    public static final SendableChooser       autoDefenseChooser = new SendableChooser();
    /**
     * Adds controls to choose which goal the robot will head towards in
     * autonomous.
     */
    public static final SendableChooser       autoGoalChooser    = new SendableChooser();
    /**
     * Adds controls to choose whether the robot will be running autonomous.
     */
    public static final SendableChooser       autoModeChooser    = new SendableChooser();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit()
    {
        System.out.println("Initializing Robot.");
        OI.getInstance();
        System.out.println("OI Initialized.");

        autoDefenseChooser.addDefault("Second Position", 2);
        autoDefenseChooser.addObject("Third Position", 3);
        autoDefenseChooser.addObject("Fourth Position", 4);
        autoDefenseChooser.addObject("Fifth Position", 5);
        SmartDashboard.putData("Position Selector", autoDefenseChooser);

        autoGoalChooser.addDefault("Middle Goal", 1);
        autoGoalChooser.addObject("Left Goal", 2);
        autoGoalChooser.addObject("Right Goal", 3);
        SmartDashboard.putData("Goal Selector", autoGoalChooser);

        SmartDashboard.putData("Drive Train", driveTrain);
        SmartDashboard.putData("Shooter", ballShooter);
        SmartDashboard.putData("Active", ballHolder);
        SmartDashboard.putData("Active Bar", activeRoller);

        autoModeChooser.addDefault("Defense Only - forward time", new DriveTime(3));
        autoModeChooser.addObject("Full Auto", new CommandGAutoController());
        autoModeChooser.addObject("Spy Bot - forward time 1.5", new DriveTime(1.5));
        autoModeChooser.addObject("No Auto", null);
        SmartDashboard.putData("Auto mode", autoModeChooser);

        SmartDashboard.putNumber("SIDE_GOAL_ROTATE_DEGREES", RobotMap.SIDE_GOAL_ROTATE_DEGREES);
        SmartDashboard.putNumber("SIDE_GOAL_WALL_DISTANCE", RobotMap.SIDE_GOAL_WALL_DISTANCE_LEFT);
        SmartDashboard.putNumber("TOWER_EXTRA_TIME", RobotMap.TOWER_EXTRA_TIME);
        sensors.zeroGyro();
    }

    private void testAutoParts(int startingPosition)
    {

        SmartDashboard.putData("After Defense", new DriveAfterDefense(startingPosition));

        double turnAngle = 0;
        Sensor sensor = Sensor.Left;

        if(startingPosition == 4)
        {
            turnAngle = -10;
            sensor = Sensor.FrontLong;
        } else if(startingPosition == 2 || startingPosition == 3)
        {
            turnAngle = 90;
        } else if(startingPosition == 5)
        {
            turnAngle = -90;
            sensor = Sensor.Right;
        }

        // SmartDashboard.putData("Drive 3 seconds", new DriveTime(3));
        // SmartDashboard.putData("Rotate to 0", new RotateToAngle(0));
        SmartDashboard.putData("Drive Over Defense to Wall", new DriveDefense(0, .85, Sensor.FrontLong, RobotMap.FRONT_DISTANCE_SENSOR_TURN_LIMIT, .5));
        SmartDashboard.putData("Drive to sensor limit wall", new DriveStraight(0, .85, Sensor.FrontLong, RobotMap.FRONT_DISTANCE_SENSOR_TURN_LIMIT, .5, 1.4));
        SmartDashboard.putData("Rotate sideways", new RotateToAngle(turnAngle));
        SmartDashboard.putData("Drive in front of Tower", new DriveStraight(turnAngle, .63, sensor, RobotMap.SIDE_DISTANCE_SENSOR_TURN_LIMIT, true, .35));
        SmartDashboard.putData("Rotate to forward", new RotateToAngle(5));
        SmartDashboard.putData("Flip Active", new CommandActiveController(2));
        SmartDashboard.putData("Drive to tower", new DriveStraight(0, .7, Sensor.FrontShort, RobotMap.TOWER_SENSOR_DISTANCE_LIMIT, .3));

        SmartDashboard.putData("Drive After Defense", new DriveAfterDefenseTesting(startingPosition));
    }

    public static int getStartPosition()
    {
        return (int) autoDefenseChooser.getSelected();
    }

    public static int getGoal()
    {
        return (int) autoGoalChooser.getSelected();
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit()
    {
        ballHolder.setBallHolder(false);
    }

    @Override
    public void disabledPeriodic()
    {
        Scheduler.getInstance().run();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     *
     * You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit()
    {
        sensors.zeroGyro();
        SubsystemSensors.ahrs.zeroYaw();

        inAuto = true;
        sensors.updateData();
        driveTrain.brakeMode(true);

        RobotMap.TOWER_EXTRA_TIME = SmartDashboard.getNumber("TOWER_EXTRA_TIME", RobotMap.TOWER_EXTRA_TIME);
        SmartDashboard.putNumber("TOWER_EXTRA_TIME", RobotMap.TOWER_EXTRA_TIME);

        int startPosition = (int) SmartDashboard.getNumber("Start Position", 2);
        autoModeChooser.addObject("Full Auto", new CommandGAutoController());

        testAutoParts(startPosition);
        autonomousCommand = (Command) autoModeChooser.getSelected();

        if(autonomousCommand != null)
        {
            autonomousCommand.start();
        }

    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic()
    {
        Scheduler.getInstance().run();
        sensors.updateData();
        driveTrain.brakeMode(true);
    }

    @Override
    public void teleopInit()
    {
        inAuto = false;
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        RobotMap.TOWER_EXTRA_TIME = SmartDashboard.getNumber("TOWER_EXTRA_TIME", RobotMap.TOWER_EXTRA_TIME);

        testAutoParts((int) SmartDashboard.getNumber("Start Position", 2));

        autoModeChooser.addObject("Full Auto", new CommandGAutoController());

        driveTrain.brakeMode(true);
        System.err.println("RSF-teleopinit");
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic()
    {
        Scheduler.getInstance().run();
        sensors.updateData();
        sensors.updateOtherSensors();
    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic()
    {
        LiveWindow.run();
    }
}
