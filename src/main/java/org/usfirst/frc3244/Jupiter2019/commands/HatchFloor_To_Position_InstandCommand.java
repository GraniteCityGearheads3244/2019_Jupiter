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
public class HatchFloor_To_Position_InstandCommand extends InstantCommand {
  
  private double m_Setpoint;
  
  /**
   * Add your docs here.
   */
  public HatchFloor_To_Position_InstandCommand(double setpoint) {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.hatch_Floor_Pick_MM);
    m_Setpoint = setpoint;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.hatch_Floor_Pick_MM.my_Hatch_Floor_Pick_MotionMagic(m_Setpoint);
  }

}
