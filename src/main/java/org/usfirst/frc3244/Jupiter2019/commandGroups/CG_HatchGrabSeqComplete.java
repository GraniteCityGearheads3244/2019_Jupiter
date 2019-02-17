/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.Jupiter2019.commandGroups;

import org.usfirst.frc3244.Jupiter2019.commands.Grip;
import org.usfirst.frc3244.Jupiter2019.commands.Retract;
import org.usfirst.frc3244.Jupiter2019.commands.myDelay;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_HatchGrabSeqComplete extends CommandGroup {
  /**
   * Add your docs here.
   */
  public CG_HatchGrabSeqComplete() {
    addSequential(new Grip());

    addSequential(new myDelay(.5));

    addSequential(new Retract());
  }
}
