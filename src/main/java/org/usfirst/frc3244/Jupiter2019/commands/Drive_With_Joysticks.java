/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.Jupiter2019.commands;

import org.usfirst.frc3244.Jupiter2019.Robot;
import org.usfirst.frc3244.Jupiter2019.subsystems.Elevator_MotionMagic.GameMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive_With_Joysticks extends Command {
  private boolean init_Overspeed_Flag;

  public Drive_With_Joysticks() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveTrain_1519_MM);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    double yIn = Robot.oi.driveY();
    if(Math.abs(yIn) > .05 ){
      init_Overspeed_Flag = true;
    }else{
      init_Overspeed_Flag = false;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double yIn = Robot.oi.driveY(); 
    
    if(init_Overspeed_Flag && yIn > .5){
      yIn = yIn *.5;
    }
    else{
      init_Overspeed_Flag = false;
    }

    if(Robot.elevator_MM.getCurrent_GameMode() == GameMode.Cargo.value){ 
    //if(Robot.oi.launchPad.getRawButton(3)){
      yIn = -yIn;
    }
    
    //SmartDashboard.putNumber("Game Mode", Robot.elevator_MM.getCurrent_GameMode());
    double rotation = Robot.oi.driveRotation();
    Robot.driveTrain_1519_MM.driveTeleop(yIn, rotation); 
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain_1519_MM.driveTeleop(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
