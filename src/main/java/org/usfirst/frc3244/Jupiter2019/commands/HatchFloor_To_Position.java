/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.Jupiter2019.commands;

import org.usfirst.frc3244.Jupiter2019.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class HatchFloor_To_Position extends Command {

  private double m_Setpoint;

  public HatchFloor_To_Position(double setpoint) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.hatch_Floor_Pick_MM);
    m_Setpoint=setpoint;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.hatch_Floor_Pick_MM.my_Hatch_Floor_Pick_MotionMagic(m_Setpoint);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.hatch_Floor_Pick_MM.get_My_PositionLock(m_Setpoint);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.hatch_Floor_Pick_MM.my_Arm_Stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
