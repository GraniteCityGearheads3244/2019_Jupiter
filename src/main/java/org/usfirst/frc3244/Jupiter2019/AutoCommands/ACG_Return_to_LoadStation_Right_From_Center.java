/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.Jupiter2019.AutoCommands;

import org.usfirst.frc3244.Jupiter2019.commandGroups.CG_Elevator_Arm_Reset;
import org.usfirst.frc3244.Jupiter2019.commands.Drive_For_Distance_PID;
import org.usfirst.frc3244.Jupiter2019.commands.Drive_Turn_To_Setpoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ACG_Return_to_LoadStation_Right_From_Center extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ACG_Return_to_LoadStation_Right_From_Center() {
   
    /** Deg Positive Turn to the left */

    addParallel(new CG_Elevator_Arm_Reset());
    addSequential(new Drive_Turn_To_Setpoint(-110),2);
    addSequential(new Drive_For_Distance_PID(.25, 110, -110));
    addSequential(new Drive_Turn_To_Setpoint(-180),1);
    addSequential(new Drive_For_Distance_PID(.25, 24, -180),3);
  }
}
