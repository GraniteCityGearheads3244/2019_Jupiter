/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.Jupiter2019.commandGroups.PositionMonitor;

import org.usfirst.frc3244.Jupiter2019.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Elevator_LVL3_Arm_Swing_Monitor extends Command {

  private double m_setpoint;
  public Elevator_LVL3_Arm_Swing_Monitor(double setpoint) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    // requires() not this is just a monitor to hold the trigger of the arm
    m_setpoint = setpoint;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.elevator_MM.get_My_CurrentRAW_Postion() > m_setpoint;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
