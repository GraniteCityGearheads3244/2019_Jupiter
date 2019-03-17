/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.Jupiter2019.commandGroups;

import org.usfirst.frc3244.Jupiter2019.commands.HatchGripper_Extend;
import org.usfirst.frc3244.Jupiter2019.commands.HatchGripper_Grip;
import org.usfirst.frc3244.Jupiter2019.commands.HatchGripper_Ungrip;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class CG_HatchGrabSeqStart extends CommandGroup {
  /**
   * Add your docs here.
   */
  public CG_HatchGrabSeqStart() {

    addSequential(new HatchGripper_Ungrip());

    addSequential(new WaitCommand(.1));

    addSequential(new HatchGripper_Extend());

  }
}
