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
import org.usfirst.frc3244.Jupiter2019.commands.LimeLight_SetPipeline;
import org.usfirst.frc3244.Jupiter2019.commands.LimeLight_SetPIP;
import oi.limelightvision.limelight.frc.ControlMode.StreamType;
import edu.wpi.first.wpilibj.command.CommandGroup;


public class CG_Elevator_LVL1_Cargo_Ship extends CommandGroup {
  /**
   * Add your docs here.
   */
  public CG_Elevator_LVL1_Cargo_Ship() {
   

    addParallel(new LimeLight_SetPIP(StreamType.kPiPSecondary));
    addParallel(new LimeLight_SetPipeline(0));
    addParallel(new Arm_To_Setpoint(Robot.arm_MM.CARGO_PLACE_CARGOBAY),4);
    addSequential(new Elevator_To_Setpoint(Robot.elevator_MM.get_Deliver_Cargo_Bay_Position(),true));
    
  }
}
