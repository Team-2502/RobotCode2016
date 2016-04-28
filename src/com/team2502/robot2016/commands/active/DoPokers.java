package com.team2502.robot2016.commands.active;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.SubsystemBallHolder;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DoPokers extends Command {

    private SubsystemBallHolder bh = Robot.ballHolder;
    private int mode = 1;
    public DoPokers(int mode) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.ballHolder);
        this.mode = mode;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (mode == 1) {
            bh.setBallHolder(true);
        } else {
            bh.setBallHolder(false);
        }
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
