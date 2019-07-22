/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.Jupiter2019.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc3244.Jupiter2019.Constants;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Hatch_Floor_Pick_MM extends Subsystem {

	public double PERSET_STOWED = 150;
	public double PERSET_PRE_HANDOFF = 450;
  public double PERSET_PICK = 925;
  
  private double maxSetPoint = 950;
  private double minSetPoint = 150;

  /* Hardware */
  TalonSRX _talon = new TalonSRX(4);
  
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Hatch_Floor_Pick_MM(){

    /* Factory default hardware to prevent unexpected behavior */
		_talon.configFactoryDefault();

		/* Configure Sensor Source for Pirmary PID */
		_talon.configSelectedFeedbackSensor(FeedbackDevice.Analog,
											Constants.kPIDLoopIdx, 
											Constants.kTimeoutMs);

		/**
		 * Configure Talon SRX Output and Sesnor direction accordingly
		 * Invert Motor to have green LEDs when driving Talon Forward / Requesting Postiive Output
		 * Phase sensor to have positive increment when driving Talon Forward (Green LED)
		 */
		_talon.setSensorPhase(true);
		_talon.setInverted(true);

		/* Set relevant frame periods to be at least as fast as periodic rate */
		_talon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
		_talon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);

		/* Set the peak and nominal outputs */
		_talon.configNominalOutputForward(0, Constants.kTimeoutMs);
		_talon.configNominalOutputReverse(0, Constants.kTimeoutMs);
		_talon.configPeakOutputForward(1, Constants.kTimeoutMs);
		_talon.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		/* Set Motion Magic gains in slot0 - see documentation */
		_talon.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		_talon.config_kF(Constants.kSlotIdx, 20.46, Constants.kTimeoutMs);//Constants.kGains.kF, Constants.kTimeoutMs);
		_talon.config_kP(Constants.kSlotIdx, 6.0, Constants.kTimeoutMs);//Constants.kGains.kP, Constants.kTimeoutMs);
		_talon.config_kI(Constants.kSlotIdx, 0.002, Constants.kTimeoutMs);//Constants.kGains.kI, Constants.kTimeoutMs);
		_talon.config_kD(Constants.kSlotIdx, 0.0, Constants.kTimeoutMs);//Constants.kGains.kD, Constants.kTimeoutMs);

		/* Set acceleration and vcruise velocity - see documentation */
		_talon.configMotionCruiseVelocity(50, Constants.kTimeoutMs);
		_talon.configMotionAcceleration(500, Constants.kTimeoutMs);

		/* Zero the sensor */
		//_talon.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		_talon.configFeedbackNotContinuous(true, Constants.kTimeoutMs);
		//_talon.configClearPositionOnLimitR(true, Constants.kTimeoutMs);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void periodic() {
       
  }

  public void my_Hatch_Floor_Pick_MotionMagic(double setpoint) {
    double targetPos = clampEncoderValue(setpoint);
    //double m_targetEncoderValue = height;

    _talon.set(ControlMode.MotionMagic, targetPos);

  }

  private double clampEncoderValue(double value) {
    if(value > maxSetPoint){
      return maxSetPoint;
    }else if(value < minSetPoint){
      return minSetPoint;
    }else{
      return value;
    }   
  }

  public void my_Arm_Stop() {
    _talon.set(ControlMode.PercentOutput, 0.0);
	}
	
	public double get_My_CurrentRAW_Postion(){
		return _talon.getSelectedSensorPosition(0);
	}

	public boolean get_My_PositionLock(double setpoint){
		double positionLockTollerence = 20;
		double error = get_My_CurrentRAW_Postion() - setpoint;

		//SmartDashboard.putNumber("Arm Error", error);

		if(Math.abs(error)<=positionLockTollerence){
			return true;
		}else{
			return false;
		}
	}
}
