/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.Jupiter2019.commands;

import org.usfirst.frc3244.Jupiter2019.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;
import oi.limelightvision.limelight.frc.ControlMode.StreamType;

/**
 * Add your docs here.
 */
public class LimeLight_SetPIP extends InstantCommand {
  /**
   * Add your docs here.
   */

   private StreamType m_streamType;

  public LimeLight_SetPIP(StreamType streamType) {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    setRunWhenDisabled(true);
    m_streamType = streamType;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.oi.get_my_LimeLight().setStream(m_streamType);;
  }

}
