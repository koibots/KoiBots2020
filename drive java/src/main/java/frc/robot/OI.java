/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Add your docs here.
 */
public class OI {
    private static OI INSTANCE;

    public static OI getOI() {
        if (INSTANCE == null) {
          INSTANCE = new OI();
        }
        return INSTANCE;
      }
    
    private Joystick controller = new Joystick(0);

    private OI() {

    }

    public double getLeftThrottle() {
        return -controller.getRawAxis(1);
    }

    public double getRightThrottle() {
        return -controller.getRawAxis(3);
    }
}
