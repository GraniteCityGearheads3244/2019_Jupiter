/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.Jupiter2019.commandGroups;

import org.usfirst.frc3244.Jupiter2019.Robot;
import org.usfirst.frc3244.Jupiter2019.commandGroups.PositionMonitor.Elevator_LVL3_Arm_Swing_Monitor;
import org.usfirst.frc3244.Jupiter2019.commands.Arm_To_Setpoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_Elevator_LVL3_Arm_Swing extends CommandGroup {
  /**
   * Add your docs here.
   */
  public CG_Elevator_LVL3_Arm_Swing() {
    addSequential(new Elevator_LVL3_Arm_Swing_Monitor(Robot.elevator_MM.get_Arm_Clear_Window_TOP()));
    addSequential(new Arm_To_Setpoint(Robot.arm_MM.HATCH_PLACE_ROCKET_LVL3));
  }
}
