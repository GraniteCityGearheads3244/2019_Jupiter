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
public class Drive_Camera_Hatch extends InstantCommand {
  /**
   * Add your docs here.
   */
  public Drive_Camera_Hatch() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    setRunWhenDisabled(true);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.oi.get_my_LimeLight().setStream(StreamType.kPiPMain);
  }

}
