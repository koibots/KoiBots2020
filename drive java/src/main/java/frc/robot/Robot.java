/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Command_Groups.AutoCG;
import frc.robot.Commands.DriveStraight;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private VictorSPX FrontLeft ;
  private VictorSPX FrontRight ;
  private VictorSPX BackLeft ;
  private VictorSPX BackRight ;
  private VictorSPX Climber;
  private Joystick controller;
  private VictorSPX Intake;
  private CANSparkMax Lifter;

  Command autonomousCommand;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    FrontLeft = new VictorSPX(12);
    FrontRight = new VictorSPX(10);
    BackLeft = new VictorSPX(13);
    BackRight = new VictorSPX(11);
    BackLeft.follow(FrontLeft);
    BackRight.follow(FrontRight);
    controller = new Joystick(0);
    Climber = new VictorSPX(2);
    Climber.setInverted(false);
    Intake = new VictorSPX(14);
    Lifter = new CANSparkMax(3, MotorType.kBrushed);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    //m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    //System.out.println("Auto selected: " + m_autoSelected);

    autonomousCommand = new DriveStraight();
    autonomousCommand.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    // switch (m_autoSelected) {
    //   case kCustomAuto:
    //   timer.start();
    //   if (timer.get() > 3) {
    //     FrontLeft.set(ControlMode.PercentOutput, 0.-5);
    //     FrontRight.set(ControlMode.PercentOutput, 0.-5);
    //   }
    //   else {
    //     FrontLeft.set(ControlMode.PercentOutput, 0);
    //     FrontRight.set(ControlMode.PercentOutput, 0);
    //   }
      

    //     break;
    //   case kDefaultAuto:
    //   default:
    //   timer.start();
    //   if (timer.get() > 3) {
    //     FrontLeft.set(ControlMode.PercentOutput, 0.-5);
    //     FrontRight.set(ControlMode.PercentOutput, 0.-5);
    //   }
    //   else {
    //     FrontLeft.set(ControlMode.PercentOutput, 0);
    //     FrontRight.set(ControlMode.PercentOutput, 0);
    //   }  
    //     break;
    // }
    
  }

  /**
   * This function is called periodically during operator control.
   */

  @Override
  public void teleopInit() {
    // TODO Auto-generated method stub
    if( autonomousCommand != null ){
      autonomousCommand.cancel();
    }
   
  }

  @Override
  public void teleopPeriodic() {
     FrontLeft.set(ControlMode.PercentOutput, -controller.getRawAxis(1));
     FrontRight.set(ControlMode.PercentOutput, controller.getRawAxis(3)); 


    if (controller.getPOV() == 0){ 
      Climber.set(ControlMode.PercentOutput, 0.75);
    }
    else if (controller.getPOV() == 180) {
      Climber.set(ControlMode.PercentOutput, -0.75);
    }else{
      Climber.set(ControlMode.PercentOutput, 0);
    }
/*
    if (controller.getRawButton(2)) {
      FrontLeft.set(ControlMode.PercentOutput, 0.5);
      FrontRight.set(ControlMode.PercentOutput, 0.5);
    }else if (controller.getRawButton(1)) {
      FrontLeft.set(ControlMode.PercentOutput, 0.3);
      FrontRight.set(ControlMode.PercentOutput, 0.3);
    }else if (controller.getRawButton(3)) {
      FrontLeft.set(ControlMode.PercentOutput, -0.3);
      FrontRight.set(ControlMode.PercentOutput, -0.3);
    }
*/
      


    if (controller.getRawButton(7)) {
      Intake.set(ControlMode.PercentOutput, -0.75);
    }
    else if (controller.getRawButton(8)){
      Intake.set(ControlMode.PercentOutput, 0.75);
    }
    else{
      Intake.set(ControlMode.PercentOutput, 0);

    }

    if (controller.getRawButton(5)) {
      Lifter.set( -0.30);
    }
    else if (controller.getRawButton(6)){
      Lifter.set(0.30);
    }
    else if (controller.getRawButtonPressed(4)){
      Lifter.set(-10);

    }else{
      Lifter.set(0);
    }

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
