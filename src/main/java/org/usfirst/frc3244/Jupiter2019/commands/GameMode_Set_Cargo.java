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
public class GameMode_Set_Cargo extends InstantCommand {
  /**
   * Add your docs here.
   */
  public GameMode_Set_Cargo() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    setRunWhenDisabled(true);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.elevator_MM.setCurrent_GameMode_Cargo();
    // If Bandwidth issues then try this
    Robot.oi.get_my_LimeLight().setStream(StreamType.kPiPSecondary);
  }

}
