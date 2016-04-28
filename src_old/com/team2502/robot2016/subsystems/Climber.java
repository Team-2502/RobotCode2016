package com.team2502.robot2016.subsystems;

import com.team2502.robot2016.RobotMap;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    private final CANTalon liftLeft  = new CANTalon(RobotMap.CLIMBER_WINCH_LEFT);
    private final CANTalon liftRight = new CANTalon(RobotMap.CLIMBER_WINCH_RIGHT);

    @Override
    protected void initDefaultCommand() {

    }

    public void setLeft(double speed) {
        liftLeft.set(speed);
    }

    public void setRight(double speed) {
        liftRight.set(speed);
    }
}