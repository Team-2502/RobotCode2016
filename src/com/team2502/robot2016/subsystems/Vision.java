package com.team2502.robot2016.subsystems;

import com.team2502.robot2016.commands.vision.VisionControl;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Parity;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.SerialPort.StopBits;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Vision extends Subsystem {

	/**
	 *  For communication between RaspberryPi (Vision) and RoboRio
	 */
	public static final SerialPort serialPort = new SerialPort(9600, Port.kOnboard, 8, Parity.kNone, StopBits.kOne);
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new VisionControl());
	}
}
