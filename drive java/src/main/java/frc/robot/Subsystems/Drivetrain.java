/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Commands.TeleopDrive;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private VictorSPX FrontLeft;
  private VictorSPX FrontRight;
  private VictorSPX BackLeft;
  private VictorSPX BackRight;

  private static Drivetrain INSTANCE = null;

  public static Drivetrain getDrivetrain() {
    if (INSTANCE == null) {
      INSTANCE = new Drivetrain();
    }
    return INSTANCE;
  }

  private Drivetrain() {
    FrontLeft = new VictorSPX(12);
    FrontRight = new VictorSPX(10);
    BackLeft = new VictorSPX(13);
    BackRight = new VictorSPX(11);
    BackLeft.follow(FrontLeft);
    BackRight.follow(FrontRight);
  }

  public void setPowerOutput(double lPower, double rPower) {
    FrontLeft.set(ControlMode.PercentOutput, lPower);
    FrontRight.set(ControlMode.PercentOutput, rPower);
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new TeleopDrive());
  }
}
