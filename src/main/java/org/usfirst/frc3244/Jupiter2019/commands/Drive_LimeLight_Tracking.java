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
import oi.limelightvision.limelight.frc.ControlMode.StreamType;

public class Drive_LimeLight_Tracking extends Command {
  private boolean debug = true;
  private int m_pipeline;
  private boolean targetONS1 = false;
  private boolean targetONS2 = false;

  public Drive_LimeLight_Tracking() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveTrain_1519_MM);
    m_pipeline = 0;
  }

  public Drive_LimeLight_Tracking(int pipeline) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveTrain_1519_MM);
    m_pipeline = pipeline;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //Robot.oi.get_my_LimeLight().setLEDMode(LedMode.kpipeLine);
    Robot.oi.get_my_LimeLight().setPipeline(m_pipeline);
    // If Bandwidth issues then try this
    //Robot.oi.get_my_LimeLight().setStream(StreamType.kPiPMain);
    targetONS1 = false;
    targetONS2 = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double kp = .07;
    double yIn = Robot.oi.driveY();
    double rotation = 0.0;
   
    LimeLight limelight = Robot.oi.get_my_LimeLight();
    if(limelight.getIsTargetFound()){
      if(debug){
        SmartDashboard.putBoolean("Target Found", true);
      }
      yIn = Robot.oi.driveY();
      rotation = (-limelight.getdegRotationToTarget() * kp);

      if(rotation > .5){
        rotation = .5;
      }else if(rotation < -.5){
        rotation = -.5;
      }
      if(!targetONS1){
        Robot.rgb_LEDs.set_myRGB("green2");
        targetONS1 = true;
        targetONS2 = false;
      }
      
      Robot.driveTrain_1519_MM.driveTeleop(yIn, rotation);
    }else{
      if(debug){
        SmartDashboard.putBoolean("Target Found", false);
      }
      //yIn = 0.0;
      rotation = Robot.oi.driveRotation();;
      
      if(!targetONS2){
        Robot.rgb_LEDs.set_myRGB("green1");
        targetONS1 = false;
        targetONS2 = true;
      }
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
    Robot.rgb_LEDs.set_myRGB("off");
    Robot.oi.get_my_LimeLight().setPipeline(0);

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
