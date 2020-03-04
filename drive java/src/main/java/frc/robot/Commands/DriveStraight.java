/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Subsystems.Drivetrain;

public class DriveStraight extends Command {
  Timer m_timer;

  public DriveStraight() {
    m_timer = new Timer();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Drivetrain.getDrivetrain());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    m_timer.reset();
    m_timer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Drivetrain.getDrivetrain().setPowerOutput(-0.25, 0.25);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return m_timer.get() > 2;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Drivetrain.getDrivetrain().setPowerOutput(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
