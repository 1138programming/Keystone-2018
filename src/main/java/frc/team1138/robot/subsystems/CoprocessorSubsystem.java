//I'm trying something Eddie gave me, it's down below

//package frc.team1138.robot.subsystems;
//
//import edu.wpi.first.wpilibj.command.Subsystem;
//
//import edu.wpi.first.wpilibj.I2C;
//import edu.wpi.first.wpilibj.I2C.Port;
//
//import java.io.IOException;
//
//
///**
//* The CoprocessorSubsystem class
//*/
//public class CoprocessorSubsystem extends Subsystem
//{
//	public enum LEDModes {
//		Off			((byte) 0),
//		Idle		((byte) 1),
//		Cube		((byte) 2),
//		Rung		((byte) 3);
//		
//		private final byte value;
//		private LEDModes(byte value) {
//			this.value = value;
//		}
//		
//		public byte getValue() {
//			return value;
//		}
//	}
//
//	public enum DeviceByte {
//		LED			((byte) 0),
//		ULTRASONIC	((byte) 1);
//
//		private final byte value;
//		private DeviceByte(byte value) {
//			this.value = value;
//		}
//
//		public byte getValue() {
//			return value;
//		}
//	}
//	
//	public enum LEDResults {
//		Success			((byte) 0),
//		Error			((byte) 1);
//		
//		private final byte value;
//		private LEDResults(byte value) {
//			this.value = value;
//		}
//		
//		public byte getValue() {
//			return value;
//		}
//	}
//	
//	// Initialize the I2C port
//	private final I2C Wire;
//	private byte[] received;
//	
//	public CoprocessorSubsystem()
//	{
//		System.out.println("LED Subsystem Initializing...");
//		received = new byte[2];
//		Wire = new I2C(Port.kMXP, 4);
//		
//		try {
//			setMode(LEDModes.Off);
//			System.out.println("LED Subsystem Initialized!");
//		} catch (IOException e) {
//			System.out.println("LED Subsystem Failed!");
//			System.out.println(e);
//		}
//	}
//	
//	public void initDefaultCommand()
//	{
//		// We don't actually have a default command; leave this blank
//	}
//	
//	public void setMode(LEDModes mode) throws IOException {
//		// Turn the the mode into a byte to send (from the enum declaration)
//		byte[] toSend = new byte[2];
//		toSend[0] = DeviceByte.LED.getValue();
//		toSend[1] = mode.getValue();
//		
//		if (Wire != null && toSend != null) {
//			// Check that we have a proper I2C connection to avoid
//			// NullPointerExceptions
//			Wire.writeBulk(toSend, 2);
//		}
//		
//		// Receive a response to check for an error
//		Wire.readOnly(received,  1);
//		
//		// Do we have an error?
//		if (received[0] == LEDResults.Error.getValue()) {
//			throw new IOException("Error from rioDuino");
//		}
//	}
//
//	public int getUltrasonic() throws IOException {
//		byte[] toSend = new byte[1];
//		toSend[0] = DeviceByte.ULTRASONIC.getValue();
//
//		if (Wire != null && toSend != null) {
//			Wire.writeBulk(toSend, 1);
//		}
//
//		Wire.readOnly(received, 2);
//
//		if (received[0] == LEDResults.Error.getValue()) {
//			throw new IOException("Error from rioDuino");
//		}
//
//		return (int) received[1];
//	}
//}

package frc.team1138.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

import java.io.IOException;


/**
* The LEDSubsystem class
*/
public class CoprocessorSubsystem extends Subsystem
{
	public enum LEDModes {
		Off			((byte) 0),
		Idle		((byte) 1),
		Cube		((byte) 2),
		Rung		((byte) 3);
		
		private final byte value;
		private LEDModes(byte value) {
			this.value = value;
		}
		
		public byte getValue() {
			return value;
		}
	}
	
	public enum LEDResults {
		Success			((byte) 0),
		Error			((byte) 1);
		
		private final byte value;
		private LEDResults(byte value) {
			this.value = value;
		}
		
		public byte getValue() {
			return value;
		}
	}
	
	public enum DeviceByte {
		LED				((byte) 0),
		Ultrasonic		((byte) 1);
		
		private final byte value;
		private DeviceByte(byte value) {
			this.value = value;
		}
		
		public byte getValue() {
			return value;
		}
	}
	
	// Initialize the I2C port
	private final I2C Wire;
	private byte[] received;
	
	public CoprocessorSubsystem()
	{
		System.out.println("LED Subsystem Initializing...");
		received = new byte[3];
		Wire = new I2C(Port.kMXP, 4);
		
		try {
			setMode(LEDModes.Off);
			System.out.println("LED Subsystem Initialized!");
		} catch (IOException e) {
			System.out.println("LED Subsystem Failed!");
			System.out.println(e);
		}
		
	}
	
	public void initDefaultCommand()
	{
		// We don't actually have a default command; leave this blank
	}
	
	public void setMode(LEDModes mode) throws IOException {
		// Turn the the mode into a byte to send (from the enum declaration)
		byte[] toSend = new byte[2];
		toSend[0] = DeviceByte.LED.getValue();
		toSend[1] = mode.getValue();
		
		if (Wire != null && toSend != null) {
			// Check that we have a proper I2C connection to avoid
			// NullPointerExceptions
			Wire.writeBulk(toSend, 2);
		}
		
		// Receive a response to check for an error
		Wire.readOnly(received,  1);
		
		// Do we have an error?
		if (received[0] == LEDResults.Error.getValue()) {
			throw new IOException("Error from rioDuino");
		}
	}
	
	public int ultrasonicValue() {
		// Turn the the mode into a byte to send (from the enum declaration)
		byte[] toSend = new byte[1];
		toSend[0] = DeviceByte.Ultrasonic.getValue();
		
		if (Wire != null && toSend != null) {
			// Check that we have a proper I2C connection to avoid
			// NullPointerExceptions
			Wire.writeBulk(toSend, 1);
		}
		
		// Receive a response to check for an error
		Wire.readOnly(received,  2);
		
		// Do we have an error?
		if (received[0] == LEDResults.Error.getValue()) {
			return -2;
		}
		SmartDashboard.putNumber("Ultrasonic state", received[1]);
		return received[1];
	}
}