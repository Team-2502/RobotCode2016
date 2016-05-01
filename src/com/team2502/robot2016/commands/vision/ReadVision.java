package com.team2502.robot2016.commands.vision;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.Vision;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReadVision extends Command {

	private Vision v = Robot.vision;
	private final int port = 21012;
	
    public ReadVision() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    			
		try {
			DatagramSocket socket = new DatagramSocket(port);
			Thread receiver = new ReceiverThread(socket);
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
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    
    static class ReceiverThread extends Thread {
		  DatagramSocket socket;

		  private boolean stopped = false;

		  public ReceiverThread(DatagramSocket ds) throws SocketException {
		    this.socket = ds;
		  }

		  public void halt() {
		    this.stopped = true;
		  }

		  public void run() {
		    byte[] buffer = new byte[32];
		    while (true) {
		      if (stopped)
		        return;
		      DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
		      try {
		        socket.receive(dp);
		        String s = new String(dp.getData(), 0, dp.getLength());
		        System.out.println(s);
		        Vision.parseData(s);
		        Thread.yield();
		      } catch (IOException ex) {
		        System.err.println(ex);
		      }
		    }
		  }
		}
}
