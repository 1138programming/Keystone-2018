package frc.team1138.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1138.robot.OI;
import frc.team1138.robot.Robot;

/**
 *
 */
public class MoveArmWithJoysticks extends Command
{
	public MoveArmWithJoysticks()
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.ARM);
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
		Robot.ARM.moveArm(Robot.oi.getLeftXBoxAxis());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
//		return Robot.ARM.onTarget();
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end()
	{
//		Robot.ARM.setGoal(Robot.ARM.getPosition());
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
		end();
	}
}
