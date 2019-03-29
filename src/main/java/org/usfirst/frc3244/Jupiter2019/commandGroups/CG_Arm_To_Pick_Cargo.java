/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.Jupiter2019.commandGroups;

import org.usfirst.frc3244.Jupiter2019.Robot;
import org.usfirst.frc3244.Jupiter2019.commands.Arm_To_Setpoint;
import org.usfirst.frc3244.Jupiter2019.commands.Elevator_To_Setpoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_Arm_To_Pick_Cargo extends CommandGroup {
  /**
   * Add your docs here.
   */
  public CG_Arm_To_Pick_Cargo() {
    addParallel(new Arm_To_Setpoint(Robot.arm_MM.CARGO_PICK_FLOOR));
    addSequential(new Elevator_To_Setpoint(Robot.elevator_MM.get_bottom_Position(), false),5);
  }
}
