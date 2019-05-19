/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.Jupiter2019.commandGroups;

import org.usfirst.frc3244.Jupiter2019.commands.Elevator_Shift_To_Release_Hatch;
import org.usfirst.frc3244.Jupiter2019.commands.HatchGripper_Retract;
import org.usfirst.frc3244.Jupiter2019.commands.HatchGripper_Ungrip;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class CG_HatchDeliverSeqComplete extends CommandGroup {
  /**
   * Add your docs here.
   */
  public CG_HatchDeliverSeqComplete() {
   addSequential(new HatchGripper_Ungrip());

   addSequential(new WaitCommand(.5));

   addParallel(new HatchGripper_Retract());
   addSequential(new Elevator_Shift_To_Release_Hatch(true), 1);
  }
}
