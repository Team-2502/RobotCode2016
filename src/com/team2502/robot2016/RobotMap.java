package com.team2502.robot2016;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
	
	//Chnage the id numbers
     public static final int LEFT_MOTOR_SIM_ONE = 1;
     public static final int LEFT_MOTOR_SIM_TWO = 2;
     public static final int LEFT_MOTOR_MINI_SIM = 2;
     
     public static final int RIGHT_MOTOR_SIM_ONE = 2;
     public static final int RIGHT_MOTOR_SIM_TWO = 2;
     public static final int RIGHT_MOTOR_MINI_SIM = 2;
     
     public static final int RIGHT_JOYSTICK = 1;
     public static final int LEFT_JOYSTICK = 2;
     
     public static final int CATAPULT_SOLENOID = 0;
     
     public static final int SCISSOR_LIFT_SOLENOID = 1;
     public static final int SCIRSSOR_LIFT_WINCH = 0;
     
     public static final int ACTIVE_ROLLER_BAR = 0;
     
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;

    /*
     * If it is set to Integer.MAX_VALUE then the id is unknown.
     */
    public static final int SHOOTER_TALON        = Integer.MAX_VALUE;
    public static final int SHOOTER_SOLENOID     = Integer.MAX_VALUE;
    public static final int SHOOTER_LIMIT_SWITCH = Integer.MAX_VALUE;
    
    
}
