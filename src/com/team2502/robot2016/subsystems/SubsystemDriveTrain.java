package com.team2502.robot2016.subsystems;

import com.team2502.robot2016.OI;
//import com.team2502.robot2016.OI;
import com.team2502.robot2016.RobotMap;
//import com.team2502.robot2016.commands.drive.TankDriveSix;
import com.team2502.robot2016.commands.drive.TankDriveSix;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SubsystemDriveTrain extends Subsystem
{

    private final RobotDrive simDrive;
    private final RobotDrive miniSimDrive;

    private final WPI_TalonSRX   leftSimOne;
    private final WPI_TalonSRX   leftSimTwo;
    private final WPI_TalonSRX   leftMiniSim;

    private final WPI_TalonSRX   rightSimOne;
    private final WPI_TalonSRX   rightSimTwo;
    private final WPI_TalonSRX   rightMiniSim;

    private final int        wheelBase         = 24;
    public double            rotateToAngleRate = 0;

    public enum Motors
    {
        LEFT_MOTORS, RIGHT_MOTORS, WINCH_MOTORS
    }

    private static final double ROBOT_STRAIGHT    = 0,
                                        ROBOT_FACE_LEFT = 60, ROBOT_FACE_RIGHT = -60;

    private static final double kToleranceDegrees = 2.0f;

    // Initialize your subsystem here
    public SubsystemDriveTrain()
    {
        // Use these to get going:
        // setSetpoint() - Sets where the PID controller should move the system
        // to
        // enable() - Enables the PID controller.

        leftSimOne = new WPI_TalonSRX(RobotMap.LEFT_MOTOR_SIM_ONE);
        leftSimTwo = new WPI_TalonSRX(RobotMap.LEFT_MOTOR_SIM_TWO);
        leftMiniSim = new WPI_TalonSRX(RobotMap.LEFT_MOTOR_MINI_SIM);

        System.err.println("Left");
        rightSimOne = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_SIM_ONE);
        rightSimTwo = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_SIM_TWO);
        rightMiniSim = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_MINI_SIM);
        System.err.println("Right");

        // Will need to make a custom RobotDrive for all 6 motors
        simDrive = new RobotDrive(leftSimOne, leftSimTwo, rightSimOne, rightSimTwo);
        miniSimDrive = new RobotDrive(leftMiniSim, rightMiniSim);

        System.err.println("Drives");

        System.err.println("Solenoid");
        // accel = new BuiltInAccelerometer();

        simDrive.setExpiration(.1);
        miniSimDrive.setExpiration(.1);
        System.out.println("Expiration");
    }

    @Override
    public void initDefaultCommand()
    {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new TankDriveSix());
    }

    public void driveSix()
    {

        // if (getPIDController().isEnabled()) {
        // getPIDController().disable();
        // }
        double leftSpeed = -OI.getInstance().getLeftStick().getY();
        double rightSpeed = -OI.getInstance().getRightStick().getY();

        if(Math.abs(leftSpeed) < .05)
        {
            leftSpeed = 0;
        }

        if(Math.abs(rightSpeed) < .05)
        {
            rightSpeed = 0;
        }

        simDrive.tankDrive(leftSpeed, rightSpeed, true);
        miniSimDrive.tankDrive(leftSpeed, rightSpeed, true);
    }

    public void turn(double speed, boolean left)
    {
        simDrive.tankDrive(speed * (left ? -1 : 1), speed * (left ? 1 : -1));
        miniSimDrive.tankDrive(speed * (left ? -1 : 1), speed * (left ? 1 : -1));
    }

    public void stopDrive()
    {

        // brakeMode(true);

        simDrive.tankDrive(-.05, -0.05);
        miniSimDrive.tankDrive(-.05, -0.05);

        Timer.delay(.3);
        // brakeMode(false);
    }

    public void brakeMode(boolean on)
    {
    }

    public void driveAlongRadius(double speed, double radius)
    {
        double curve = Math.pow(Math.E, radius / wheelBase);
        simDrive.drive(speed, curve);
        miniSimDrive.drive(speed, curve);
    }

    public void runMotors(double left, double right)
    {
        if(Math.abs(left) > 1)
        {
            left /= Math.abs(left);
        }
        if(Math.abs(right) > 1)
        {
            right /= Math.abs(right);
        }

        simDrive.tankDrive(left, right);
        miniSimDrive.tankDrive(left, right);
    }

    public void driveStraight(double speed, double turn)
    {
        simDrive.drive(speed, turn);
        miniSimDrive.drive(speed, turn);

    }

}
