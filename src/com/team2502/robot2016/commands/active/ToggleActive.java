package com.team2502.robot2016.commands.active;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.SubsystemActiveFrame;
import com.team2502.robot2016.subsystems.SubsystemShooter;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleActive extends Command {

    private SubsystemActiveFrame frame = Robot.activeFrame;
    private SubsystemShooter bs = Robot.ballShooter;
    
    public ToggleActive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.activeFrame);
        requires(Robot.ballShooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//        bs.setSolenoid(false);
        frame.setActivePickupState(!frame.getActivePickupState());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
