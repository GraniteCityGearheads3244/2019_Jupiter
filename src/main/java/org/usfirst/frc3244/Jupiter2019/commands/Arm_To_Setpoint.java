/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.Jupiter2019.commands;

import org.usfirst.frc3244.Jupiter2019.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm_To_Setpoint extends Command {

  private double m_setpoint;

  public Arm_To_Setpoint(double setpoint) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.arm_MM);
    m_setpoint = setpoint;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //Robot.arm_MM.set_my_CruiseVelocity(Robot.arm_MM.get_my_CRUISE_VELOCITY());
    if(Robot.DIVERSTATION_REPORTS_ENABLED){
      DriverStation.reportError("Arm Target = " + m_setpoint, false);
      SmartDashboard.putNumber("Arm Target", m_setpoint);
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.arm_MM.my_Arm_MotionMagic(m_setpoint);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.arm_MM.get_My_PositionLock(m_setpoint);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
//    DriverStation.reportWarning("Arm to Sepoint End()", false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.arm_MM.my_Arm_Stop();
    end();
  }
}
