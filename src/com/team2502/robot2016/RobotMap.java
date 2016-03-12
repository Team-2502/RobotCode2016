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
	 public static final int BUTTON_JOYSTICK = 2;
     public static final int RIGHT_JOYSTICK = 0;
     public static final int LEFT_JOYSTICK = 1;
     
	//Change the id numbers
     public static final int LEFT_MOTOR_SIM_ONE = 6;
     public static final int LEFT_MOTOR_SIM_TWO = 5;
     public static final int LEFT_MOTOR_MINI_SIM = 4;
     
     public static final int RIGHT_MOTOR_SIM_ONE = 3;
     public static final int RIGHT_MOTOR_SIM_TWO = 2;
     public static final int RIGHT_MOTOR_MINI_SIM = 1;
     
     
     public static final int SHOOTER_SOLENOID = 0;
     
     public static final int ACTIVE_SOLENOID = 1;
     public static final int ACTIVE_ROLLER_BAR = 7;  
     
     public static final int POKER_SOLENOID = 2;
     
     public static final int CLIMBER_SOLENOID = 3;
     public static final int CLIMBER_WINCH_ONE = 8;
     public static final int CLIMBER_WINCH_TWO = 9;

     
     
     
     
     public static final int CLIMBER_BUTTON_STICK_BUTTON = 7;
  
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;

    /*
     * If it is set to Integer.MAX_VALUE then the id is unknown.
     */
//    public static final int SHOOTER_MOTOR        = Integer.MAX_VALUE;
//    public static final int SHOOTER_SOLENOID     = Integer.MAX_VALUE;
    public static final int SHOOTER_LIMIT_SWITCH = Integer.MAX_VALUE;
    
    
    
    // Sensor Input Ports
    
//    public static final int CLIMBER_DISTANCE_SENSOR = 0;
    public static final int FRONT_DISTANCE_SENSOR = 0;
    public static double FRONT_DISTANCE_SENSOR_LIMIT = 4.5;

//    public static final int SIDE_DISTANCE_SENSOR = 5;
//    public static final int GYRO_SENSOR = 1;
//    public static final int ACCEL_SENSOR = 2;
//    public static final int COLOR_SENSOR = 3;
//    
//    public static final int MIDDLE_LEFT_DISTANCE = 6;
//    public static final int MIDDLE_RIGHT_DISTANCE = 6;
//    
//    public static final int OUTSIDE_LEFT_DISTANCE = 6;
//    public static final int OUTSIDE_RIGHT_DISTANCE = 6;
    
//    public static final int LEFT_DISTANCE = 6;
//    public static final int RIGHT_DISTANCE = 6;
	public static final int RING_LIGHT = 0;
	public static final int LEFT_BALL_SENSOR = 2;
	public static final int RIGHT_BALL_SENSOR = 1;
	public static final int ULTRASONIC_FRONT_TEST = 3;

	
	public static double BALL_VOLT_ACTIVE = 1.65;
	public static double BALL_VOLT_SHOOTER = 4;
	public static double BALL_MIDDLE_VOLT_ACTIVE = 1.1;
	public static double BALL_NOTHING_VOLT_ACTIVE = 2;



    

}
