/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.Jupiter2019.commands;

import org.usfirst.frc3244.Jupiter2019.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Add your docs here.
 */
public class RGB_Select extends InstantCommand {
  /**
   * Add your docs here.
   */
  private String m_RGB_Select;

  public RGB_Select(String RGB_Select) {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.rgb_LEDs);
    m_RGB_Select = RGB_Select;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.rgb_LEDs.set_myRGB(m_RGB_Select);
  }

}
