/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.Jupiter2019.commandGroups;

import org.usfirst.frc3244.Jupiter2019.commands.HatchGripper_Grip;
import org.usfirst.frc3244.Jupiter2019.commands.HatchGripper_Retract;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class CG_HatchGrabSeqComplete extends CommandGroup {
  /**
   * Add your docs here.
   */
  public CG_HatchGrabSeqComplete() {
    addSequential(new HatchGripper_Grip());

    addSequential(new WaitCommand(.5));
  

    addSequential(new HatchGripper_Retract());
  }
}
