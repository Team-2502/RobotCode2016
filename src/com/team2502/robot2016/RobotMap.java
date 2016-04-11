package com.team2502.robot2016;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{
    public static final class Joystick
    {
        public static final int FUNCTION_CONTROL_JOYSTICK = 2;
        public static final int LEFT_JOYSTICK             = 1;
        public static final int RIGHT_JOYSTICK            = 0;

        public static final class Button
        {
            public static final int ROLLER_CONTROL_IN = 3;
            public static final int ROLLER_CONTROL_OUT = 4;
        }
    }

    // Change the id numbers
    public static final int    LEFT_MOTOR_SIM_ONE               = 6;
    public static final int    LEFT_MOTOR_SIM_TWO               = 5;
    public static final int    LEFT_MOTOR_MINI_SIM              = 4;

    public static final int    RIGHT_MOTOR_SIM_ONE              = 3;
    public static final int    RIGHT_MOTOR_SIM_TWO              = 2;
    public static final int    RIGHT_MOTOR_MINI_SIM             = 1;

    public static final int    SHOOTER_SOLENOID                 = 0;

    public static final int    ACTIVE_SOLENOID                  = 1;
    public static final int    ACTIVE_ROLLER_BAR                = 7;

    public static final int    POKER_SOLENOID                   = 2;

    public static final int    CLIMBER_WINCH_LEFT               = 8;
    public static final int    CLIMBER_WINCH_RIGHT              = 9;

    public static final int    CLIMBER_BUTTON_STICK_BUTTON      = 7;

    /* If it is set to Integer.MAX_VALUE then the id is unknown. */
    public static final int    SHOOTER_LIMIT_SWITCH             = Integer.MAX_VALUE;

    public static final int    RING_LIGHT                       = 0;
    public static final int    LEFT_BALL_SENSOR                 = 1;
    public static final int    RIGHT_BALL_SENSOR                = 0;

    public static final int    FRONT_LONG_DISTANCE              = 0;
    public static final int    FRONT_SHORT_DISTANCE             = 1;
    public static final int    LEFT_DISTANCE                    = 2;
    public static final int    RIGHT_DISTANCE                   = 3;

    public static double       FRONT_DISTANCE_SENSOR_TURN_LIMIT = 4.2;
    public static double       SIDE_DISTANCE_SENSOR_TURN_LIMIT  = .4;
    public static double       TOWER_SENSOR_DISTANCE_LIMIT      = 1.5;
    public static double       SENSOR_ZONE_OF_PRECISION         = .2;
    public static double       TOWER_EXTRA_TIME                 = .33;

    public static final double LONG_SENSOR_RANGE_LIMITS         = 4.6;
    public static final double SHORT_SENSOR_RANGE_LIMITS        = 4.4;

    public static final double SIDE_GOAL_WALL_DISTANCE_LEFT     = 2.6;
    public static final double SIDE_GOAL_ROTATE_DEGREES         = 55;
    public static final double SIDE_GOAL_WALL_DISTANCE_RIGHT    = 3.1;

    public static double       BALL_VOLT_ACTIVE                 = 1.65;
    public static double       BALL_VOLT_SHOOTER                = 4;
    public static double       BALL_MIDDLE_VOLT_ACTIVE          = 1.1;
    public static double       BALL_NOTHING_VOLT_ACTIVE         = 2;

    public static final int    Pressure_Sensor                  = 4;
}