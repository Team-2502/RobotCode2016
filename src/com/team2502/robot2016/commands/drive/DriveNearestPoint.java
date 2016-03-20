package com.team2502.robot2016.commands.drive;

import java.awt.Point;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.DriveTrain;
import com.team2502.robot2016.subsystems.DriveTrain;
import com.team2502.robot2016.subsystems.Sensors;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveNearestPoint extends Command {
	
	public enum Location {
		inside, outside
	}

	private Location loc;
	private Point nearestPoint;
	private double shortestDistance = Integer.MAX_VALUE;
	
	private DriveTrain dt = Robot.driveTrain;
	private Sensors s = Robot.sensors;
	
    public DriveNearestPoint(Location l) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	requires(Robot.sensors);
    	loc = l;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
//    	if (loc == Location.outside) {
//	    	shorterDistance(DriveTrain.OUTER_LEFT);
//	    	shorterDistance(DriveTrain.OUTER_RIGHT);
//	    	shorterDistance(DriveTrain.OUTER_MIDDLE);
//    	} else {
//	    	shorterDistance(DriveTrain.INNER_LEFT);
//	    	shorterDistance(DriveTrain.INNER_RIGHT);
//	    	shorterDistance(DriveTrain.INNER_MIDDLE);
//    	}
    }
    
    private void shorterDistance(Point p) {
    	double d = distance(s.getX(), s.getY(), p);
    	if (d < shortestDistance) {
    		nearestPoint = (Point) p.clone();
    		shortestDistance = d;
    	}
    }
    
    private double distance(double x, double y, Point p) {
    	return Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double angle = Math.atan((nearestPoint.y - s.getY())/(nearestPoint.x - s.getX()));
    	if (nearestPoint.x < s.getX()) angle = Math.PI - angle;
    	
    	if (Math.toDegrees(Math.abs(s.getAngle() - angle)) > 3 ) {
    		dt.turn(.9, s.getAngle() - angle < Math.PI);
    		
    	} else if (distance(s.getX(), s.getY(), nearestPoint) > 3) {
    		dt.runMotors(.9, .9);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return distance(s.getX(), s.getY(), nearestPoint) <= 3;
    }

    // Called once after isFinished returns true
    protected void end() {
    	dt.runMotors(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
