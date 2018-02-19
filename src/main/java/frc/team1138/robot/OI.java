package frc.team1138.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.team1138.robot.commands.ClearStickyFaults;
import frc.team1138.robot.commands.CollectCubeLeft;
import frc.team1138.robot.commands.CollectCubeRight;
import frc.team1138.robot.commands.CycleArm;
import frc.team1138.robot.commands.EjectCube;
import frc.team1138.robot.commands.KickCube;
import frc.team1138.robot.commands.MoveArmToExchange;
import frc.team1138.robot.commands.PositionLift;
import frc.team1138.robot.commands.ShiftBase;
import frc.team1138.robot.commands.ShiftLift;
import frc.team1138.robot.commands.StopLeftCollector;
import frc.team1138.robot.commands.StopRightCollector;
import frc.team1138.robot.commands.TestMotionProfile;
import frc.team1138.robot.commands.ToggleRatchet;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{

	// Limit for Xbox joystick axes to get out of the "dead zone"
	public static final double KXboxDeadZoneLimit = 0.2;

	// Joystick Definitions
	public static final int KLogitechController = 0; // Base driver
	public static final int KXBoxController = 1; // Arms and lifts driver

	// Logitech button definitions - look below for usage
	public static final int KButton1 = 1;
	public static final int KButton7 = 7;
	public static final int KButton2 = 2;
	public static final int KButton3 = 3;
	public static final int KButton4 = 4;
	public static final int KButton6 = 6;

	// XBox button definitions - look below for usage
	public static final int KButtonA = 1;
	public static final int KButtonB = 2;
	public static final int KButtonX = 3;
	public static final int KButtonY = 4;
	public static final int KLeftBumper = 5;
	public static final int KRightBumper = 6;
	public static final int KStartButton = 8;
	
	//Booleans to toggle the collect and eject functions
	public static boolean toggleCollect = false;
	public static boolean toggleEject = false;
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.

	// Define joysticks and joystick buttons
	private Joystick logitechController, xBoxController;
	private JoystickButton btn1, btn7, btn2, btn3, btn4, btn5, btn6, btn8; // Logitech Button
	private JoystickButton btnA, btnB, btnX, btnY, btnLB, btnRB, btnStrt;

	public OI()
	{
		logitechController = new Joystick(KLogitechController);
		xBoxController = new Joystick(KXBoxController);

		// Logitech Buttons
		btn1 = new JoystickButton(logitechController, KButton1);
		btn7 = new JoystickButton(logitechController, KButton7);
		btn2 = new JoystickButton(logitechController, KButton2);
		btn3 = new JoystickButton(logitechController, KButton3);
		btn4 = new JoystickButton(logitechController, KButton4);
		btn5 = new JoystickButton(logitechController, 5);
		btn6 = new JoystickButton(logitechController, KButton6);
		btn8 = new JoystickButton(logitechController, 8);

		// XBox Definitions (the functions of the buttons will change with time)
		btnA = new JoystickButton(xBoxController, KButtonA);
		btnB = new JoystickButton(xBoxController, KButtonB);
		btnX = new JoystickButton(xBoxController, KButtonX);
		btnY = new JoystickButton(xBoxController, KButtonY);
		btnLB = new JoystickButton(xBoxController, KLeftBumper);
		btnRB = new JoystickButton(xBoxController, KRightBumper);
    
		btn1.whenPressed(new TestMotionProfile());
		btnStrt = new JoystickButton(xBoxController, KStartButton);
		btn7.whenPressed(new ClearStickyFaults()); //Clears sticky faults
		// btn2.whenPressed(); //Nothing assigned yet, probably will be when we have the lift mechanism going
		btn3.whenPressed(new ShiftLift()); // Toggles rollers collecting
//		btn4.whenPressed(new EjectCube()); // Toggles rollers ejecting
		btn5.whenPressed(new ShiftBase()); // Shifts the base
		
		btnLB.whenPressed(new CollectCubeLeft());
		btnLB.whenReleased(new StopLeftCollector());
		btnRB.whenPressed(new CollectCubeRight());
		btnRB.whenReleased(new StopRightCollector());
		btnA.whenPressed(new CycleArm()); // Puts the arm through a full cycle
		btnB.whenPressed(new MoveArmToExchange()); // Moves the arm to the exchange position
//		btnX.whenPressed(new PositionLift(5)); // High position TODO test these values
		btnX.whenPressed(new EjectCube());
		btnY.whenPressed(new ToggleRatchet());
//		btnY.whenPressed(new ShiftLift()); // Shifts the lift speed
//		btnRB.whenPressed(new PositionLift(3)); // Middle position
//		btnLB.whenPressed(new PositionLift(1)); // Low position
		btnStrt.whenPressed(new KickCube()); // Kicks the cube when it may be stuck using the plunger
	}

	public double getRightAxis()
	{ // Right axis is right side drive
		if (logitechController.getThrottle() < -KXboxDeadZoneLimit
				|| logitechController.getThrottle() > KXboxDeadZoneLimit)
		{
			return -logitechController.getThrottle(); // TODO check if it's twist for z-rotate axis
		}
		else
		{
			return 0;
		}
	}

	public double getLeftAxis()
	{ // Left controller is left side drive
		if (logitechController.getY() < -KXboxDeadZoneLimit || logitechController.getY() > KXboxDeadZoneLimit)
		{
			return -logitechController.getY();
		}
		else
		{
			return 0;
		}
	}

	public double getLeftTrigger()
	{ // left controller's trigger is currently unused
		return (-xBoxController.getRawAxis(3));
		// Add function here, currently this doesn't do much.
	}

	public double getRightTrigger()
	{ // right controller's trigger engages the shift on the base
		return (-xBoxController.getRawAxis(2));
		// Add function here, currently this doesn't do much.
	}

	public double getLeftXBoxAxis()
	{ // left xbox axis controls the linear lift
		return (-xBoxController.getRawAxis(1));
	}

	public double getRightXBoxAxis()
	{ // right xbox axis controls the dumper arm
		return (-xBoxController.getRawAxis(5));
	}

	public double getXBoxPOV()
	{ // POV left and right is dumper conveyor
		return xBoxController.getRawAxis(6);
	}
	
	public boolean triggerLiftUp()
	{
		return btn6.get();
	}
	
	public boolean triggerLiftDown()
	{
		return btn8.get();
	}
}
