package com.team2502.robot2016.subsystems;

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

	private static double visionData = 0;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ReadVision());
    	
    }
    
    public void setLatestVision(double turnNum) {
    	visionData = turnNum;
    }
    
    private static double getLatestVision() {
		//Needs to be -15 to 15
    	return visionData;
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
			return getLatestVision();
		}
    	
    }
}

