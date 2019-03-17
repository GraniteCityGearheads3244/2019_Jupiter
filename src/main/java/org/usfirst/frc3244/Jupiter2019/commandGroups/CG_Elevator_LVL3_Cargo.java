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

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_Elevator_LVL3_Cargo extends CommandGroup {
  /**
   * Add your docs here.
   */
  public CG_Elevator_LVL3_Cargo() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.

    addSequential(new Elevator_To_Setpoint(Robot.elevator_MM.get_Deliver_Cargo_Rocket_Position3(),false));
    addSequential(new Arm_To_Setpoint(Robot.arm_MM.CARGO_PLACE_ROCKET_LVL3),4);
  }
}
