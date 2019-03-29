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

public class Arm_Jog_MotionMagic extends Command {
  
  private double m_Setpoint;
  private boolean m_continueToServo;
  
  public Arm_Jog_MotionMagic(boolean continueTOServo) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.arm_MM);
    m_continueToServo = continueTOServo;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(Robot.DIVERSTATION_REPORTS_ENABLED){
      DriverStation.reportError("Arm Jog Target = ", false);
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    double joystick = Robot.oi.co_Driver_Left_AxisY();
    double maxStep = 300;  
    m_Setpoint = (Robot.arm_MM.get_My_CurrentRAW_Postion() + (joystick * maxStep));
    
    if(m_Setpoint > Robot.arm_MM.get_MaxHeight( )){
      m_Setpoint = Robot.arm_MM.get_MaxHeight( );
    }else if(m_Setpoint < Robot.arm_MM.get_minHeight( )){
      m_Setpoint = Robot.arm_MM.get_minHeight( );
    }
      //if(joystick>0) {
    	//	m_Setpoint = Robot.arm_MM.get_MaxHeight( ) * joystick;
    	//}else {
    	//	m_Setpoint = Robot.arm_MM.get_minHeight();
    	//}
      
      //int cruiseVelocity = (int) Math.abs(joystick) * Robot.arm_MM.get_my_CRUISE_VELOCITY();
      //Robot.arm_MM.set_my_CruiseVelocity(cruiseVelocity);
      Robot.arm_MM.my_Arm_MotionMagic(m_Setpoint);
      
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.arm_MM.my_Arm_Stop();
    DriverStation.reportWarning("Arm Jog End()", false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.arm_MM.my_Arm_Stop();
    end();
  }
}
