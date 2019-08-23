/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.Jupiter2019.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class DriveTrainSetMaxSpeed extends InstantCommand {
  /**
   * Add your docs here.
   */
  private double m_maxspeed;
  public DriveTrainSetMaxSpeed(double maxSpee) {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    m_maxspeed = maxSpee;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {

    SmartDashboard.putNumber("MaxDriveSpeed", m_maxspeed);
  }

}
