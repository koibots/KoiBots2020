/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


#include "Robot.h"
#include "ctre/Phoenix.h"

VictorSPX driveL1 = {1};
VictorSPX driveR1 = {2};
VictorSPX driveL2 = {4};
VictorSPX driveR2 = {3};

void Robot::RobotInit() {

    driveL1.Set(ControlMode::PercentOutput, 1);
    driveR1.Set(ControlMode::PercentOutput, 1);
    driveL2.Set(ControlMode::PercentOutput, 1);
    driveR2.Set(ControlMode::PercentOutput, 1);
}

void Robot::AutonomousInit() {}
void Robot::AutonomousPeriodic() {}

void Robot::TeleopInit() {}
void Robot::TeleopPeriodic() {}

void Robot::TestInit() {}
void Robot::TestPeriodic() {}

#ifndef RUNNING_FRC_TESTS
int main() { return frc::StartRobot<Robot>(); }
#endif
