package com.team2502.robot2016.subsystems;

import java.util.HashMap;

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

	private static HashMap<String, Double> parsedData = new HashMap<String, Double>();

	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ReadVision());
    	
    }
    
    public void setLatestVision(HashMap<String, Double> visionD) {
    	parsedData = visionD;
    }
    
    private static HashMap<String, Double> getLatestVision() {
		//Needs to be -15 to 15
    	return parsedData;
    }
    
    public static void parseData(String s) {
    	   	
    	String[] stringData = s.split(",");
    	HashMap<String, Double> parsedData = new HashMap<String, Double>();
    	
    	for (String info : stringData) {
    		String[] data = info.split(":");
    		parsedData.put(data[0], Double.parseDouble(data[1]));
    	}	
    }
    
    public static double figureOutTurning() {
    	return 0;
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
			return figureOutTurning();
		}
    	
    }
}

