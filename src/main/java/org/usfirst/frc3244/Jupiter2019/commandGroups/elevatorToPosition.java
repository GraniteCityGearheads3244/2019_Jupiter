/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.Jupiter2019.commandGroups;

import org.usfirst.frc3244.Jupiter2019.Robot;
import org.usfirst.frc3244.Jupiter2019.commands.Arm_To_Postion;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class elevatorToPosition extends CommandGroup {
  /**
   * Add your docs here.
   */
  private int m_position;

  public elevatorToPosition(int position) {

    m_position = position;
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

    //Put the Arm in a safe position
    addSequential(new Arm_To_Postion(Robot.hatch_Cargo_Arm_PID.get_safe_Travle_Position()));
    //now move to the setpoint
    addSequential(new elevatorToPosition(m_position));
  }
}
