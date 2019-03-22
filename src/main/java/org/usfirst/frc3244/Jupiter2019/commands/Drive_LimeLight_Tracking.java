/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.Jupiter2019.commands;

import org.usfirst.frc3244.Jupiter2019.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import oi.limelightvision.limelight.frc.LimeLight;
import oi.limelightvision.limelight.frc.ControlMode.LedMode;

public class Drive_LimeLight_Tracking extends Command {
  private boolean debug = true;
  public Drive_LimeLight_Tracking() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveTrain_1519_MM);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.oi.get_my_LimeLight().setLEDMode(LedMode.kforceOn);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double kp = .01;
    double yIn = 0.0;
    double rotation = 0.0;
    LimeLight limelight = Robot.oi.get_my_LimeLight();
    if(limelight.getIsTargetFound()){
      if(debug){
        SmartDashboard.putBoolean("Target Found", true);
      }
      yIn = Robot.oi.driveY();
      rotation = limelight.getdegRotationToTarget() * kp;
      Robot.driveTrain_1519_MM.driveTeleop(yIn, rotation);
    }else{
      if(debug){
        SmartDashboard.putBoolean("Target Found", false);
      }
      yIn = 0.0;
      rotation = 0.0;
      Robot.driveTrain_1519_MM.driveTeleop(yIn, rotation);
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
    //Robot.oi.get_my_LimeLight().setLEDMode(LedMode.kforceOff);
    Robot.driveTrain_1519_MM.driveTeleop(0.0,0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
