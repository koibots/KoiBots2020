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
import edu.wpi.first.wpilibj.Timer;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {

  private final Joystick controller = new Joystick(0) ;
  private VictorSPX Front_Left ;
  private VictorSPX Front_Right ;
  private VictorSPX Back_Left ;
  private VictorSPX Back_Right ;
  private VictorSPX Climber ; 
  private VictorSPX Intake ; 
  private CANSparkMax Lifter ;  
  private final Timer m_timer = new Timer() ;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    Front_Left = new VictorSPX(12);                 // Give all the motor controllers names
    Front_Right = new VictorSPX(10);
    Back_Left = new VictorSPX(13);
    Back_Right = new VictorSPX(11);
    Back_Left.follow(Front_Left);
    Back_Right.follow(Front_Right);
    Climber = new VictorSPX(2);
    Climber.setInverted(false);
    Intake = new VictorSPX(14);
    Lifter = new CANSparkMax (3, MotorType.kBrushed);
  }
  

  /**
   * This function is run once each time the robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {
    m_timer.reset();                  // reset the timer
    m_timer.start();                  // start the timer
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    // Drive for 1.5 seconds
    if (m_timer.get() < 1.5) {
      Front_Left.set(ControlMode.PercentOutput, 0.5); // drive forwards half speed
      Front_Right.set(ControlMode.PercentOutput, 0.5); // drive forwards half speed
    } else {
      Front_Left.set(ControlMode.PercentOutput, 0); // stop robot
      Front_Right.set(ControlMode.PercentOutput, 0); // stop robot
    }
  }

  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {
  }

  /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {
    Front_Left.set(ControlMode.PercentOutput, -controller.getRawAxis(1)); // Code for Tank Drive
    Front_Right.set(ControlMode.PercentOutput, controller.getRawAxis(3));


    if (controller.getPOV() == 0){ 
      Climber.set(ControlMode.PercentOutput, 0.75);        // Code for Hook
    }else{
      Climber.set(ControlMode.PercentOutput, 0);
    }

    if (controller.getRawButton(7)) {
      Intake.set(ControlMode.PercentOutput, -0.75);
    }
    else if (controller.getRawButton(8)){
      Intake.set(ControlMode.PercentOutput, 0.75);
    }
    else{
      Intake.set(ControlMode.PercentOutput, 0);

    }

    if (controller.getRawButton(2)) {                       // A X B button programming
      Front_Left.set(ControlMode.PercentOutput, 0.5);
      Front_Right.set(ControlMode.PercentOutput, 0.5);
    }else if (controller.getRawButton(1)) {
      Front_Left.set(ControlMode.PercentOutput, 0.3);
      Front_Right.set(ControlMode.PercentOutput, 0.3);
    }else if (controller.getRawButton(3)) {
      Front_Left.set(ControlMode.PercentOutput, -0.3);
      Front_Right.set(ControlMode.PercentOutput, -0.3);
    }    

    if (controller.getRawButton(5)) {                      // Lifter for balls
      Lifter.set( -0.30);
    }
    else if (controller.getRawButton(6)){
      Lifter.set(0.30);
    }
    else if (controller.getRawButtonPressed(4)){          // Jigle button
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
