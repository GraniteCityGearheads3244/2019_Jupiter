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
public class Drive_DDR_Right_Control extends InstantCommand {
  /**
   * Add your docs here.
   */
  private boolean m_countUP;

  public Drive_DDR_Right_Control(boolean countUP) {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    m_countUP = countUP;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.driveTrain_1519_MM.my_ddr_RightCount(m_countUP);
  }

}
