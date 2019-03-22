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

public class Elevator_Jog_MotoinMagic extends Command {

  private double m_Setpoint;
	private boolean m_continueToServo;
  
  
  public Elevator_Jog_MotoinMagic(boolean continueTOServo) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.elevator_MM);
    m_continueToServo = continueTOServo;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(Robot.DIVERSTATION_REPORTS_ENABLED){
      DriverStation.reportError("Elevator Jog Target = ", false);
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double joystick = Robot.oi.co_Driver_Right_AxisY();
    m_Setpoint = get_Jog_Setpoint(joystick);	

    	//if(joystick>0) {
    	//m_Setpoint = Robot.elevator_MM.get_MaxHeight();
    	//}else {
    	//	m_Setpoint = Robot.elevator_MM.get_minHeight();
    	//}
    	
    	Robot.elevator_MM.my_ScissorMotionMagic(m_Setpoint);
  }

  private double get_Jog_Setpoint(double setpoint){
   
    double maxStep = 50;  

    m_Setpoint = (Robot.elevator_MM.get_My_CurrentRAW_Postion() + (setpoint * maxStep));
    
    if(m_Setpoint > Robot.elevator_MM.get_MaxHeight( )){
      return  Robot.elevator_MM.get_MaxHeight( );
    }else if(m_Setpoint < Robot.elevator_MM.get_minHeight( )){
      return Robot.elevator_MM.get_minHeight( );
    }else{
      return m_Setpoint;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.elevator_MM.my_ElevatorStop();
    DriverStation.reportWarning("Elevator Jog End()", false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.elevator_MM.my_ElevatorStop();
    end();
  }
}
