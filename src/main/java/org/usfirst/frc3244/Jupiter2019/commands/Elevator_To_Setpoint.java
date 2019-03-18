/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.Jupiter2019.commands;

import javax.swing.text.StyleContext.SmallAttributeSet;

import org.usfirst.frc3244.Jupiter2019.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator_To_Setpoint extends Command {

  private boolean m_continueTOServo;
  private double  m_setpoint = 0.0;
  
  public Elevator_To_Setpoint(double setpoint, boolean continueTOServo) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.elevator_MM);
    m_setpoint = setpoint;
    m_continueTOServo = continueTOServo;
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(Robot.DIVERSTATION_REPORTS_ENABLED){
      DriverStation.reportError("Elevator Target = " + m_setpoint, false);
      SmartDashboard.putNumber("Elevator Target = ", m_setpoint);
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.elevator_MM.my_ScissorMotionMagic(m_setpoint);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.elevator_MM.get_My_PositionLock(m_setpoint);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    if(!m_continueTOServo){
      Robot.elevator_MM.my_ElevatorStop();
    }
    DriverStation.reportWarning("Elevator to Sepoint End()", false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.elevator_MM.my_ElevatorStop();
    end();
  }
}
