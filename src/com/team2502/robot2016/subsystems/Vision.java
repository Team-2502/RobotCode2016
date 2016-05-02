package com.team2502.robot2016.subsystems;

import java.util.HashMap;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.commands.vision.ReadVision;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Vision extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private double calculatedAngle = 0;
	private double targetAngle = 0;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ReadVision());
    	
    }
    
    public void setTargetAngle(double angle) {
    	targetAngle = angle;
    }
    
    public double getTargetAngle() {
    	return targetAngle;
    }
    
    public double getVisionAngle() {
		//Needs to be -15 to 15
    	return calculatedAngle;
    }
    
    public static HashMap<String, Double> parseData(String s) {
    	   	
    	String[] stringData = s.split(",");
    	HashMap<String, Double> parsedData = new HashMap<String, Double>();
    	
    	for (String info : stringData) {
    		String[] data = info.split(":");
    		parsedData.put(data[0], Double.parseDouble(data[1]));
    	}	
    	return parsedData;
    }
    
    public void setVisionAngle(double angle) {
    	calculatedAngle = angle;
    }
    
    public static class PIDVision implements PIDSource {
    	
    	protected PIDSourceType m_pidSource = PIDSourceType.kDisplacement;
    	  
		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			m_pidSource = pidSource;
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return m_pidSource;
		}

		@Override
		public double pidGet() {
			//Needs to be -15 to 15
			return Robot.vision.getVisionAngle();
		}
    	
    }
}

