/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.Jupiter2019.commands;

import org.usfirst.frc3244.Jupiter2019.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Elevator_To_Setpoint extends Command {

  private double  m_setpoint = 0.0;
  public Elevator_To_Setpoint(double setpoint) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.elevator);
    m_setpoint = setpoint;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.elevator.my_ScissorMotionMagic(m_setpoint);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //Robot.elevator.my_ScissorStop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
