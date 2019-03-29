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
public class Disable_All_MotionMagic_Processes extends InstantCommand {
  /**
   * Add your docs here.
   */
  public Disable_All_MotionMagic_Processes() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.elevator_MM.my_ElevatorStop();
    Robot.arm_MM.my_Arm_Stop();
    Robot.hatch_Floor_Pick_MM.my_Arm_Stop();
  }

}
