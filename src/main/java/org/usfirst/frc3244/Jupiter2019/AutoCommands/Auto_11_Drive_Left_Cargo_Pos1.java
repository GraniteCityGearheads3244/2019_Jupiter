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

public class Auto_11_Drive_Left_Cargo_Pos1 extends CommandGroup {
  /**
   * Add your docs here.
   */
  public Auto_11_Drive_Left_Cargo_Pos1() {
    
    addSequential(new Drive_For_Distance_PID(0.25, 25, 0.0));
    addSequential(new Drive_Turn_To_Setpoint(90),1);
    addSequential(new Drive_For_Distance_PID(0.25, 10, 90));
    addSequential(new Drive_Turn_To_Setpoint(0.0),1);
    addSequential(new Drive_For_Distance_PID(0.25, 10, 0.0));
    addSequential(new Drive_Turn_To_Setpoint(-90),1);
    addSequential(new Drive_For_Distance_PID(0.25, 10, -90.0));
    //addSequential(new Drive_ShiftLow());
  }
}
