package com.team2502.robot2016.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.team2502.robot2016.OI;
import com.team2502.robot2016.RobotMap;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SubsystemSensors extends Subsystem
{

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    /*
     * private static final int LEFT_ANGLE = 30; private static final int
     * RIGHT_ANGLE = 30;
     *
     * private static final int LEFT_OFFSET = 6; private static final int
     * RIGHT_OFFSET = 6;
     *
     * private static final double ROBOT_WIDTH = 24;
     *
     * private static final double BASE_AREA = 3000; private static final double
     * BASE_DISTANCE = 84; private static final double robotAngleToTower = 0;
     *
     * private static final int AREA = 0; private static final int CENTERX = 1;
     * private static final int CENTERY = 2; private static final int HEIGHT =
     * 3; private static final int WIDTH = 4;
     *
     * private static final int imageCenterX = 150; private static final int
     * imageCenterY = 120;
     *
     * public enum TowerSide { left, middle, right; }
     *
     *
     * private static double[] tableVals = new double[5];
     *
     * private AnalogInput middleLeftDistance; private AnalogInput
     * middleRightDistance;
     *
     * private AnalogInput outsideLeftDistance; private AnalogInput
     * outsideRightDistance;
     *
     *
     *
     * private AnalogInput leftDistance; private AnalogInput rightDistance;
     *
     * private AnalogInput colorFront; private AnalogGyro gyro; private
     * AnalogAccelerometer accel;
     *
     *
     *
     * private AnalogTrigger distanceFront; private AnalogTrigger
     * climberHighEnough;
     *
     * private DigitalInput shooterLoadedSwitch; private DigitalInput
     * shooterShotSwitch;
     */

    private DigitalOutput      ringLight;
    public boolean             ringState      = false;

    public static AHRS         ahrs;

    // private AnalogInput leftBallSensor;
    // private AnalogInput rightBallSensor;
    //
    private AnalogInput        frontLongDistance;
    private AnalogInput        frontShortDistance;
    private AnalogInput        leftDistance;
    private AnalogInput        rightDistance;
    private AnalogInput        Pressure_Sensor;
    // private AnalogInput testSensor;

    public static final double CLOSE_TO_TOWER = 1;

    public enum Sensor
    {
        FrontLong, FrontShort, Left, Right
    }

    public static double LONG_OUTER_LIMIT_VOLTAGE  = 4.5;
    public static double LONG_INNER_LIMIT_VOLTAGE  = .95;

    public static double SHORT_OUTER_LIMIT_VOLTAGE = 4.8;
    public static double SHORT_INNER_LIMIT_VOLTAGE = .95;

    public static double BEFORE_TURN_VALUE         = 4.5;

    // private static NetworkTable table;

    public SubsystemSensors()
    {

        System.out.println("Before initial Sensors");

        try
        {
            /* Communicate w/navX MXP via the MXP SPI Bus. */
            /*
             * Alternatively: I2C.Port.kMXP, SerialPort.Port.kMXP or
             * SerialPort.Port.kUSB
             */
            /*
             * See
             * http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/
             * for details.
             */
            ahrs = new AHRS(SPI.Port.kMXP);

        } catch(RuntimeException ex)
        {
            try
            {
                ahrs = new AHRS(SerialPort.Port.kUSB);

            } catch(RuntimeException ex1)
            {
                DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
                DriverStation.reportError("Error instantiating navX MXP1:  " + ex1.getMessage(), true);
            }
        }

        System.out.println("Navx Board");

        // gyro = new AnalogGyro(1);
        // gyro.initGyro();
        // table = NetworkTable.getTable("GRIP/GRIPConvex");
        ringLight = new DigitalOutput(RobotMap.RING_LIGHT);

        System.out.println("RingLight");

        frontLongDistance = new AnalogInput(RobotMap.FRONT_LONG_DISTANCE);
        frontShortDistance = new AnalogInput(RobotMap.FRONT_SHORT_DISTANCE);
        Pressure_Sensor = new AnalogInput(RobotMap.PRESSURE_SENSOR);
        System.out.println("Fron Distance Sensors");

        leftDistance = new AnalogInput(RobotMap.LEFT_DISTANCE);
        rightDistance = new AnalogInput(RobotMap.RIGHT_DISTANCE);
        System.out.println("Left and right sensors");

        // leftBallSensor = new AnalogInput(RobotMap.LEFT_BALL_SENSOR);
        // rightBallSensor = new AnalogInput(RobotMap.RIGHT_BALL_SENSOR);
        System.out.println("Ball Sensors");

        System.out.println("Sensors");

    }

    @Override
    public void initDefaultCommand()
    {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    public void setRingLight(boolean state)
    {
        ringLight.set(state);
        ringState = state;
    }

    /*
     * public void getNetworkTableValues() { double[] defaultValue = new
     * double[0];
     *
     * double[] areas = table.getNumberArray("area", defaultValue);
     * tableVals[AREA] = areas[0]; double[] heights =
     * table.getNumberArray("height", defaultValue); tableVals[HEIGHT] =
     * heights[0]; double[] widths = table.getNumberArray("width",
     * defaultValue); tableVals[WIDTH] = widths[0]; double[] centerXs =
     * table.getNumberArray("centerX", defaultValue); tableVals[CENTERX] =
     * centerXs[0]; double[] centerYs = table.getNumberArray("centerY",
     * defaultValue); tableVals[CENTERY] = centerYs[0];
     *
     * }
     *
     * public double getDistanceUsingVision() { double ratio = BASE_AREA /
     * tableVals[AREA]; double distance = BASE_DISTANCE / ratio; return
     * distance; }
     *
     * public int getTargetPosition() { if (Math.abs(tableVals[CENTERX] -
     * imageCenterX) < 15) { return -1; } else if (tableVals[CENTERX] <
     * imageCenterX) { return 1; } else if (tableVals[CENTERX] > imageCenterX) {
     * } else { return 0; } }
     *
     * public int getTargetMiddle() { if (tableVals[AREA] != 0) { return
     * getTargetPosition(); } else { if (Math.abs(robotAngleToTower -
     * getAngle()) < 4) return -1; else if (robotAngleToTower - getAngle() < 0)
     * return 1; else return 0; } }
     *
     * public void setClimberVoltageLimits(double high) {
     * climberHighEnough.setLimitsVoltage(high - .2, high); }
     *
     * public void setFrontVoltageLimits(double low, double high) {
     * distanceFront.setLimitsVoltage(low, high); }
     *
     * public boolean stillClimbing() { return
     * !climberHighEnough.getTriggerState(); }
     *
     * public boolean isCloseEnough() { return distanceFront.getInWindow(); }
     *
     * public boolean tooClose() { return distanceFront.getTriggerState(); }
     *
     */

    public double getDistanceFrontLong()
    {
        return frontLongDistance.getVoltage();
    }

    public double getDistanceFrontShort()
    {
        return frontShortDistance.getVoltage();
    }

    public double getDistanceLeft()
    {
        return leftDistance.getVoltage();
    }

    public double getDistanceRight()
    {
        return rightDistance.getVoltage();
    }

    public double getSensorDistance(Sensor sensor)
    {
        switch(sensor)
        {
            case FrontLong:
                return frontLongDistance.getVoltage();
            case FrontShort:
                return frontShortDistance.getVoltage();
            case Left:
                return leftDistance.getVoltage();
            case Right:
                return rightDistance.getVoltage();
        }
        return -1;
    }

    public double getLeftBallSensor()
    {
        // return leftBallSensor.getVoltage();
        return -1;
    }

    public double getRightBallSensor()
    {
        // return rightBallSensor.getVoltage() + .6;
        return -1;
    }

    public boolean ballInActive()
    {
        boolean inMiddle = getRightBallSensor() > RobotMap.BALL_MIDDLE_VOLT_ACTIVE || getLeftBallSensor() > RobotMap.BALL_MIDDLE_VOLT_ACTIVE;
        boolean notBothMiddle = getRightBallSensor() < RobotMap.BALL_VOLT_ACTIVE || getLeftBallSensor() < RobotMap.BALL_VOLT_ACTIVE;

        boolean justLeft = getRightBallSensor() < RobotMap.BALL_NOTHING_VOLT_ACTIVE && getLeftBallSensor() > RobotMap.BALL_VOLT_ACTIVE;
        boolean justRight = getLeftBallSensor() < RobotMap.BALL_NOTHING_VOLT_ACTIVE && getRightBallSensor() > RobotMap.BALL_VOLT_ACTIVE;

        return inMiddle && notBothMiddle || justLeft || justRight;
    }

    public double getAngle()
    {
        return ahrs.getAngle();
    }

    public void calibrateSensors()
    {
        ahrs.reset();
    }

    /*
     *
     * public boolean shooterAllTheWayBack() { return shooterLoadedSwitch.get();
     * }
     *
     * public boolean shooterAllTheWayForward() { return
     * shooterShotSwitch.get(); }
     *
     *
     * public double getMiddleLeftDistance() { return 0; }
     *
     *
     * public double getMiddleRightDistance() { return 0; }
     *
     *
     * public double getOutsideLeftDistance() { return 0; }
     *
     *
     * public double getOutsideRightDistance() { return 0; }
     *
     *
     * public double getLeftDistance() { return 0; }
     *
     *
     * public double getRightDistance() { return 0; }
     *
     * public double getRobotAngleOfRotation() { double angle =
     * Math.atan((getOutsideLeftDistance() - getOutsideRightDistance()) /
     * ROBOT_WIDTH); System.out.println("Degree Rotate: " +
     * Math.toDegrees(angle)); return angle; }
     *
     * public double getDistanceFromTower() { double distanceFromRotation =
     * (ROBOT_WIDTH/2) * Math.sin(getRobotAngleOfRotation()); double
     * sensorDistance = (getOutsideLeftDistance() > getOutsideRightDistance()) ?
     * getOutsideRightDistance() : getOutsideLeftDistance(); double distance =
     * getOutsideLeftDistance() * Math.cos(getRobotAngleOfRotation());
     * System.out.println("Rotation Distance: " + distanceFromRotation);
     * System.out.println("Distance: " + distanceFromRotation);
     * System.out.println(distance - distanceFromRotation); return distance -
     * distanceFromRotation; }
     *
     * public double getLeftAlignment() { double tempL = getLeftDistance() *
     * Math.sin(Math.toRadians(LEFT_ANGLE)); double outsideL = tempL *
     * Math.cos(getRobotAngleOfRotation()); double middleL = (getLeftDistance()
     * * Math.cos(Math.toRadians(LEFT_ANGLE)) - LEFT_OFFSET) *
     * Math.sin(getRobotAngleOfRotation()); double totalL = -((ROBOT_WIDTH / 2)
     * * Math.cos(getRobotAngleOfRotation()) + outsideL + middleL);
     *
     * System.out.println("Outer L: " + outsideL); System.out.println(
     * "Middle L: " + middleL); System.out.println("X Left offset: " + totalL);
     * return totalL; }
     *
     * public double getRightAlignment() { double tempL = getRightDistance() *
     * Math.sin(Math.toRadians(RIGHT_ANGLE)); double outsideL = tempL *
     * Math.cos(getRobotAngleOfRotation()); double middleL = (getRightDistance()
     * * Math.cos(Math.toRadians(RIGHT_ANGLE)) - RIGHT_OFFSET) *
     * Math.sin(getRobotAngleOfRotation()); double totalL = -(-(ROBOT_WIDTH / 2)
     * * Math.cos(getRobotAngleOfRotation()) + outsideL + middleL);
     *
     * System.out.println("Outer L: " + outsideL); System.out.println(
     * "Middle L: " + middleL); System.out.println("X Right offset: " + totalL);
     * return totalL; }
     *
     * public double angleGyroRotate() { return ahrs.getAngle(); }
     *
     *
     */
    public double getX()
    {
        return ahrs.getDisplacementX();
    }

    public double getY()
    {
        return ahrs.getDisplacementY();
    }

    public double getYaw()
    {
        return ahrs.getYaw();
    }

    public double getPitch()
    {
        return ahrs.getYaw();
    }

    public double getRoll()
    {
        return ahrs.getRoll();
    }

    public double accelX()
    {
        return ahrs.getWorldLinearAccelX();
    }

    public double accelY()
    {
        return ahrs.getWorldLinearAccelY();
    }

    public double accelZ()
    {
        return ahrs.getWorldLinearAccelZ();
    }

    public boolean isRotating()
    {
        return ahrs.isRotating();
    }

    public boolean isMoving()
    {
        return ahrs.isMoving();
    }

    public double getVelocityX()
    {
        return ahrs.getVelocityX();
    }

    public double getVelocityY()
    {
        return ahrs.getVelocityY();
    }

    public void zeroGyro()
    {
        ahrs.zeroYaw();
    }

    public void updateData()
    {

        // System.err.println("Updating?");
        // System.out.println("Updating?");
        boolean zero_yaw_pressed = OI.getInstance().getRightStick().getRawButton(2);
        if(zero_yaw_pressed)
        {
            ahrs.zeroYaw();
        }

        // SmartDashboard.putNumber("Ultrasonic Sensor",
        // frontDistance.getVoltage());
        /* Display 6-axis Processed Angle Data */
        SmartDashboard.putBoolean("IMU_Connected", ahrs.isConnected());
        SmartDashboard.putBoolean("IMU_IsCalibrating", ahrs.isCalibrating());
        SmartDashboard.putNumber("IMU_Yaw", ahrs.getYaw());
        SmartDashboard.putNumber("Pitch", ahrs.getPitch());
        SmartDashboard.putNumber("Roll", ahrs.getRoll());

        /* Display tilt-corrected, Magnetometer-based heading (requires */
        /* magnetometer calibration to be useful) */

        SmartDashboard.putNumber("IMU_CompassHeading", ahrs.getCompassHeading());

        /*
         * Display 9-axis Heading (requires magnetometer calibration to be
         * useful)
         */
        SmartDashboard.putNumber("IMU_FusedHeading", ahrs.getFusedHeading());

        /*
         * These functions are compatible w/the WPI Gyro Class, providing a
         * simple
         */
        /* path for upgrading from the Kit-of-Parts gyro to the navx MXP */

        SmartDashboard.putNumber("IMU_TotalYaw", ahrs.getAngle());
        // SmartDashboard.putNumber( "IMU_YawRateDPS", ahrs.getRate());

        /*
         * Display Processed Acceleration Data (Linear Acceleration, Motion
         * Detect)
         */

        SmartDashboard.putNumber("IMU_Accel_X", ahrs.getWorldLinearAccelX());
        SmartDashboard.putNumber("IMU_Accel_Y", ahrs.getWorldLinearAccelY());
        SmartDashboard.putBoolean("IMU_IsMoving", ahrs.isMoving());
        SmartDashboard.putBoolean("IMU_IsRotating", ahrs.isRotating());

        /*
         * Display estimates of velocity/displacement. Note that these values
         * are
         */
        /*
         * not expected to be accurate enough for estimating robot position on a
         */
        /*
         * FIRST FRC Robotics Field, due to accelerometer noise and the
         * compounding
         */
        /*
         * of these errors due to single (velocity) integration and especially
         */
        /* double (displacement) integration. */

        // SmartDashboard.putNumber( "Velocity_X", ahrs.getVelocityX());
        // SmartDashboard.putNumber( "Velocity_Y", ahrs.getVelocityY());
        // SmartDashboard.putNumber( "Displacement_X", ahrs.getDisplacementX());
        // SmartDashboard.putNumber( "Displacement_Y", ahrs.getDisplacementY());

        /* Display Raw Gyro/Accelerometer/Magnetometer Values */
        /*
         * NOTE: These values are not normally necessary, but are made available
         */
        /*
         * for advanced users. Before using this data, please consider whether
         */
        /* the processed data (see above) will suit your needs. */
        // SmartDashboard.putNumber( "RawGyro_X", ahrs.getRawGyroX());
        // SmartDashboard.putNumber( "RawGyro_Y", ahrs.getRawGyroY());
        // SmartDashboard.putNumber( "RawGyro_Z", ahrs.getRawGyroZ());
        // SmartDashboard.putNumber( "RawAccel_X", ahrs.getRawAccelX());
        // SmartDashboard.putNumber( "RawAccel_Y", ahrs.getRawAccelY());
        // SmartDashboard.putNumber( "RawAccel_Z", ahrs.getRawAccelZ());
        // SmartDashboard.putNumber( "RawMag_X", ahrs.getRawMagX());
        // SmartDashboard.putNumber( "RawMag_Y", ahrs.getRawMagY());
        // SmartDashboard.putNumber( "RawMag_Z", ahrs.getRawMagZ());
        // SmartDashboard.putNumber( "IMU_Temp_C", ahrs.getTempC());

        /* Omnimount Yaw Axis Information */
        /*
         * For more info, see
         * http://navx-mxp.kauailabs.com/installation/omnimount
         */
        // AHRS.BoardYawAxis yaw_axis = ahrs.getBoardYawAxis();
        // SmartDashboard.putString( "YawAxisDirection", yaw_axis.up ? "Up" :
        // "Down" );
        // SmartDashboard.putNumber( "YawAxis", yaw_axis.board_axis.getValue()
        // );

        /* Sensor Board Information */
        // SmartDashboard.putString( "FirmwareVersion",
        // ahrs.getFirmwareVersion());

        /* Quaternion Data */
        /*
         * Quaternions are fascinating, and are the most compact representation
         * of
         */
        /*
         * orientation data. All of the Yaw, Pitch and Roll Values can be
         * derived
         */
        /*
         * from the Quaternions. If interested in motion processing, knowledge
         * of
         */
        /* Quaternions is highly recommended. */
        // SmartDashboard.putNumber( "QuaternionW", ahrs.getQuaternionW());
        // SmartDashboard.putNumber( "QuaternionX", ahrs.getQuaternionX());
        // SmartDashboard.putNumber( "QuaternionY", ahrs.getQuaternionY());
        // SmartDashboard.putNumber( "QuaternionZ", ahrs.getQuaternionZ());

        /* Connectivity Debugging Support */
        // SmartDashboard.putNumber( "IMU_Byte_Count", ahrs.getByteCount());
        // SmartDashboard.putNumber( "IMU_Update_Count", ahrs.getUpdateCount());
        // SmartDashboard.putNumber("Test thing", i);
        // i++;

    }

    public void updateOtherSensors()
    {
        // TODO Auto-generated method stub

        // RobotMap.BALL_VOLT_ACTIVE =
        // SmartDashboard.getNumber("BALL_VOLT_ACTIVE",
        // RobotMap.BALL_VOLT_ACTIVE);
        // RobotMap.BALL_VOLT_SHOOTER =
        // SmartDashboard.getNumber("BALL_VOLT_SHOOTER",
        // RobotMap.BALL_VOLT_SHOOTER);
        // RobotMap.BALL_MIDDLE_VOLT_ACTIVE =
        // SmartDashboard.getNumber("BALL_MIDDLE_VOLT_SHOOTER",
        // RobotMap.BALL_MIDDLE_VOLT_ACTIVE);
        // RobotMap.BALL_NOTHING_VOLT_ACTIVE =
        // SmartDashboard.getNumber("BALL_NOTHING_VOLT_SHOOTER",
        // RobotMap.BALL_NOTHING_VOLT_ACTIVE);
        //
        // RobotMap.FRONT_DISTANCE_SENSOR_TURN_LIMIT =
        // SmartDashboard.getNumber("FRONT_DISTANCE_SENSOR_TURN_LIMIT",
        // RobotMap.FRONT_DISTANCE_SENSOR_TURN_LIMIT);
        // RobotMap.SIDE_DISTANCE_SENSOR_TURN_LIMIT =
        // SmartDashboard.getNumber("SIDE_DISTANCE_SENSOR_TURN_LIMIT",
        // RobotMap.SIDE_DISTANCE_SENSOR_TURN_LIMIT);
        // RobotMap.TOWER_SENSOR_DISTANCE_LIMIT =
        // SmartDashboard.getNumber("TOWER_SENSOR_DISTANCE_LIMIT",
        // RobotMap.TOWER_SENSOR_DISTANCE_LIMIT);
        // RobotMap.SENSOR_ZONE_OF_PRECISION =
        // SmartDashboard.getNumber("SENSOR_ZONE_OF_PRECISION",
        // RobotMap.SENSOR_ZONE_OF_PRECISION);

        SmartDashboard.putNumber("Right Ball Sensor", getRightBallSensor());
        SmartDashboard.putNumber("Left Ball Sensor", getLeftBallSensor());

        SmartDashboard.putBoolean("Ball in Left", getLeftBallSensor() > RobotMap.BALL_VOLT_ACTIVE);
        SmartDashboard.putBoolean("Ball in Right", getRightBallSensor() > RobotMap.BALL_VOLT_ACTIVE);

        boolean inMiddle = getRightBallSensor() > RobotMap.BALL_MIDDLE_VOLT_ACTIVE && getLeftBallSensor() > RobotMap.BALL_MIDDLE_VOLT_ACTIVE;
        boolean notBothMiddle = getRightBallSensor() < RobotMap.BALL_VOLT_ACTIVE || getLeftBallSensor() < RobotMap.BALL_VOLT_ACTIVE;

        SmartDashboard.putBoolean("Ball in Middle", inMiddle && notBothMiddle);
        SmartDashboard.putBoolean("Ball Middle Method", ballInActive());
        SmartDashboard.putBoolean("Ball Shooter", ballInActive());
        SmartDashboard.putNumber("Pressure", Pressure_Sensor.getVoltage());
        SmartDashboard.putNumber("Tank Pressure", Pressure_Sensor.getVoltage() * 250 - 25);

        SmartDashboard.putNumber("Front Long Sensor", getDistanceFrontLong());
        SmartDashboard.putNumber("Front Short Sensor", getDistanceFrontShort());
        SmartDashboard.putNumber("Left Sensor", getDistanceLeft());
        SmartDashboard.putNumber("Right Sensor", getDistanceRight());

        SmartDashboard.putNumber("Left Joystick", OI.getInstance().getLeftStick().getY());
        SmartDashboard.putNumber("Right Joystick", OI.getInstance().getRightStick().getY());

        // (se.getRightBallSensor() > RobotMap.BALL_VOLT_SHOOTER ||
        // se.getLeftBallSensor() > RobotMap.BALL_VOLT_SHOOTER)

    }

}
