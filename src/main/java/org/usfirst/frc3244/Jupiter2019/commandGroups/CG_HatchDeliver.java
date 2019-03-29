/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.Jupiter2019.commandGroups;

import org.usfirst.frc3244.Jupiter2019.commands.HatchGripper_Extend;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_HatchDeliver extends CommandGroup {
  /**
   * Add your docs here.
   */
  public CG_HatchDeliver() {
    addSequential(new HatchGripper_Extend());
  }
}
