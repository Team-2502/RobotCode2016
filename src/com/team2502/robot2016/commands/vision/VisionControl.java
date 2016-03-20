package com.team2502.robot2016.commands.vision;

import com.team2502.robot2016.Robot;
import com.team2502.robot2016.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class VisionControl extends Command {
	
	private Subsystem dt = Robot.vision;
	
	public VisionControl()
	{
		requires(Robot.vision);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		int value = Robot.serialPort.getBytesReceived();
		
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}
}
