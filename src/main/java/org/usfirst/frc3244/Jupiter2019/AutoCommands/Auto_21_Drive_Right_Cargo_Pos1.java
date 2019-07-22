/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.Jupiter2019.AutoCommands;


import org.usfirst.frc3244.Jupiter2019.commands.Drive_For_Distance_PID;
import org.usfirst.frc3244.Jupiter2019.commands.Drive_ShiftLow;
import org.usfirst.frc3244.Jupiter2019.commands.Drive_Turn_To_Setpoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auto_21_Drive_Right_Cargo_Pos1 extends CommandGroup {
  /**
   * Add your docs here.
   */
  public Auto_21_Drive_Right_Cargo_Pos1() {
    
    /** Deg Positive Turn to the left */
    
    addSequential(new Drive_For_Distance_PID(0.75, 170, -5.0));
    addSequential(new Drive_Turn_To_Setpoint(85),2);
    //addSequential(new Drive_ShiftLow());
  }
}
