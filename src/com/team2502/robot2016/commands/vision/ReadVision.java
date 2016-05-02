package com.team2502.robot2016.commands.vision;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.Sensors;
import com.team2502.robot2016.subsystems.Vision;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReadVision extends Command {
	
	//200 pixels at 100 inches
	public static final double WIDTH_SCALE = .5;
	private Vision v = Robot.vision;
	private final int port = 21012;
	private DatagramSocket socket;
	private Thread receiver;
	private PIDOutput relayOutput;
	private HashMap<String, Double> parsedData = new HashMap<String, Double>();
	private boolean newData = false;
	private double angleToTurn = 0;
	
    public ReadVision() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    			
		try {
			socket = new DatagramSocket(port);
			receiver = new ReceiverThread(socket);
		    receiver.start();
		    
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	//read vision things and DONT BLOCK
//    	double visionVal = 0;
//    	boolean newVisionVals = false;
//    	if (newVisionVals) {
//			//Needs to be -15 to 15
//    		v.setLatestVision(visionVal);
//    	}
    	if (newData) {
    		v.setVisionAngle(angleToTurn);
    		newData = false;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	socket.close();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
    public double turnAngleCalcs(HashMap<String, Double> data) {
    	
    	double angle = Sensors.ahrs.getAngle();
    	double targetAngle = v.getTargetAngle();
    	
    	
    	double boxWidth = data.get("bw");
    	double approxDist = boxWidth * WIDTH_SCALE;
    	
    	double centerX = data.get("cx");
    	double centerY = data.get("cy");
    	
    	double virtualCenterTarget = 240;
    	if (angle > targetAngle) {
    		virtualCenterTarget -= 18;
    	} else {
    		virtualCenterTarget += 18;
    	}
    	
    	double angleCalc = ((centerX - virtualCenterTarget) / 240) * 27;

    	
    	if (Math.abs(angle - targetAngle) > 10) {
    		angleCalc += Math.signum(angleCalc) * 15;
    		if (Math.abs(angleCalc) > 30) {
    			angleCalc = Math.signum(angleCalc) * 30;
    		}
    	}
    	
    	newData = true;
    	System.out.println("Angle Calculated: " + angleCalc);
    	angleToTurn = angleCalc;
    	return angleCalc;
    }
    
    class ReceiverThread extends Thread {
		  DatagramSocket socket;

		  private boolean stopped = false;

		  public ReceiverThread(DatagramSocket ds) throws SocketException {
		    this.socket = ds;
		  }

		  public void halt() {
		    this.stopped = true;
		  }

		  public void run() {
		    byte[] buffer = new byte[1024];
		    while (true) {
		      if (stopped)
		        return;
		      DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
		      try {
		        socket.receive(dp);
		        String s = new String(dp.getData(), 0, dp.getLength());
		        System.out.println(s);
		        HashMap<String, Double> parsedData = Vision.parseData(s);
		        turnAngleCalcs(parsedData);
		        Thread.yield();
		      } catch (IOException ex) {
		        System.err.println(ex);
		        stopped = true;
		      }
		    }
		  }
    }
}
