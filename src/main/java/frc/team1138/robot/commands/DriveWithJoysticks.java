package frc.team1138.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1138.robot.Robot;

/**
 *
 */
public class DriveWithJoysticks extends Command
{
	public DriveWithJoysticks()
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.DRIVE_BASE);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		//Experimental Stuff Goes Here
		Robot.DRIVE_BASE.tankDrive(Robot.oi.getLeftAxis(), Robot.oi.getRightAxis());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end()
	{
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
	}
}
