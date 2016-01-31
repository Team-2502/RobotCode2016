package com.team2502.robot2016.commands.drive;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.DriveTrain;
import com.team2502.robot2016.subsystems.Sensors;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FaceTower extends Command {
	
	public enum TowerWall {
		
		LEFT 	(30), 
		RIGHT	(120), 
		MIDDLE  (0);
		
		public double angle;
		
		private TowerWall(double angle) {
			this.angle = angle;
		}
	}
	
	private DriveTrain dt = Robot.driveTrain;
	private Sensors s = Robot.sensors;
	private TowerWall wall;
	private double speed;
	
    public FaceTower(TowerWall w, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.sensors);
    	requires(Robot.driveTrain);
    	wall = w;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	dt.turn(speed, wall.angle > (s.getAngle() % 360) + 180);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(wall.angle - (s.getAngle() % 360)) < 1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	dt.stopDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
