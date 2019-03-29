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
public class LimeLight_SetPipeline extends InstantCommand {
  /**
   * Add your docs here.
   */

   private int m_pipeline;

  public LimeLight_SetPipeline(int pipeline) {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    setRunWhenDisabled(true);
    m_pipeline = pipeline;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.oi.get_my_LimeLight().setPipeline(m_pipeline);
  }

}
