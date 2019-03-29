/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.Jupiter2019.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc3244.Jupiter2019.Constants;
import org.usfirst.frc3244.Jupiter2019.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Arm_MM extends Subsystem {

	public double STOWED = 7;

  public double CARGO_PICK_DEPOT = 100;
	public double CARGO_PICK_FLOOR = 675;
	public double HATCH_PICK_FLOOR = 7;
	public double HATCH_PICK_DEPOT = 7;

	// BALLS
	public double CARGO_PLACE_CARGOBAY = 30;
	public double CARGO_PLACE_ROCKET_LVL1 = 340; //330/
	public double CARGO_PLACE_ROCKET_LVL2 = 10; //
	public double CARGO_PLACE_ROCKET_LVL3 = 10; //

	// HATCH PANLES
	public double HATCH_PLACE_CARGOBAY = 20;
	public double HATCH_PLACE_ROCKET_LVL1 = 16.5;
	public double HATCH_PLACE_ROCKET_LVL2 = 16.5;
	public double HATCH_PLACE_ROCKET_LVL3 = 520;

  private double m_ARM_0_DEG = 10;
  private double m_ARM_90_DEG = 615;
  private double maxSetPoint = 675;
	private double minSetPoint = 10;
	private double ARM_CLEAR_ELEVATOR = 390;  //Any position Less then is ok for full Elevator Travle
	
  
  /* Hardware */
  TalonSRX _talon = new TalonSRX(5);

  public Arm_MM(){
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
		_talon.configPeakOutputForward(0.75, Constants.kTimeoutMs);
		_talon.configPeakOutputReverse(-0.5, Constants.kTimeoutMs);

		/* Set Motion Magic gains in slot0 - see documentation */
		_talon.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		_talon.config_kF(Constants.kSlotIdx, 5.1, Constants.kTimeoutMs);//Constants.kGains.kF, Constants.kTimeoutMs);
		_talon.config_kP(Constants.kSlotIdx, 4.0, Constants.kTimeoutMs);//Constants.kGains.kP, Constants.kTimeoutMs);
		_talon.config_kI(Constants.kSlotIdx, 0.0, Constants.kTimeoutMs);//Constants.kGains.kI, Constants.kTimeoutMs);
		_talon.config_kD(Constants.kSlotIdx, 70.0, Constants.kTimeoutMs);//Constants.kGains.kD, Constants.kTimeoutMs);

		/* Set acceleration and vcruise velocity - see documentation */
		_talon.configMotionCruiseVelocity( 100, Constants.kTimeoutMs);
		_talon.configMotionAcceleration(75, Constants.kTimeoutMs);

		/* Zero the sensor */
		//_talon.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		_talon.configFeedbackNotContinuous(true, Constants.kTimeoutMs);
		//_talon.configClearPositionOnLimitR(true, Constants.kTimeoutMs);

		/* Configure Current Limits */
		_talon.configPeakCurrentLimit(30);
    _talon.configPeakCurrentDuration(150);
    _talon.configContinuousCurrentLimit(20);

    /* Configure Neutral Mode*/
    _talon.setNeutralMode(NeutralMode.Coast);

  }
  
  
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void periodic() {
//			 SmartDashboard.putNumber("Arm Position", get_My_CurrentRAW_Postion());
			 SmartDashboard.putBoolean("Arm Safe for Elevator", get_IsArm_CLear_For_Elevator());
  }

  public void my_Arm_MotionMagic(double setpoint) {
    double targetPos = clampEncoderValue(setpoint);
    //double m_targetEncoderValue = height;

    double arbfeedFwdTerm = getFeedForward(.15); //.15 * COS(Theda)

    _talon.set(ControlMode.MotionMagic, targetPos, DemandType.ArbitraryFeedForward, arbfeedFwdTerm);

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


  private double my_getCurrentArmAngle(){
		double m_fullRange = 150;
		double m_offset = -2.3;
		double m_sensorPos = _talon.getSelectedSensorPosition(0);
    double m_angle = (m_sensorPos / 1024) * m_fullRange + m_offset;
    if(Robot.DEBUG){
		  SmartDashboard.putNumber("m_SensorPos",m_sensorPos);
      SmartDashboard.putNumber("m_angle", m_angle);
    }
		return m_angle;
	}

	private double getFeedForward(double horizontalHoldOutput) {

		// get the radians of the arm
		// getAngle() returns degrees
		double theta = Math.toRadians(90 - my_getCurrentArmAngle());
    
		// get a range of 0 to 1 to multiply by feedforward.
		// when in horizontal position, value should be 1
		// when in vertical up or down position, value should be 0 
		double gravityCompensation = Math.cos(theta);
	  
		// horizontalHoldOutput is the minimum power required to hold the arm up when horizontal
		// this is a range of 0-1, in our case it was .125 throttle required to keep the arm up
		double arb_feedForward = gravityCompensation * horizontalHoldOutput;
	  
    if(Robot.DEBUG){
      SmartDashboard.putNumber("Angle", my_getCurrentArmAngle());
      SmartDashboard.putNumber("Gravity Compensation", gravityCompensation);
      SmartDashboard.putNumber("Arb Feed Forward", arb_feedForward);
    }
			  
	   return arb_feedForward;
	  
		}
		
		public double get_My_CurrentRAW_Postion(){
			return _talon.getSelectedSensorPosition(0);
		}
	
		public boolean get_My_PositionLock(double setpoint){
			double positionLockTollerence = 10;
			double error = get_My_CurrentRAW_Postion() - setpoint;
	
			//SmartDashboard.putNumber("Arm Error", error);
	
			if(Math.abs(error)<=positionLockTollerence){
				return true;
			}else{
				return false;
			}
		}

		public boolean get_IsArm_CLear_For_Elevator(){
			double clearPosition = ARM_CLEAR_ELEVATOR;
			if((get_My_CurrentRAW_Postion() < clearPosition) || Robot.oi.launchPad.getRawButton(11)){
				return true;
			}else{
				return false;
			}
		}


	public void diagnostics() {

	}


	public double get_MaxHeight() {
		return maxSetPoint;
	}

	public double get_minHeight() {
		return minSetPoint;
	}


	public void set_armToCurrent() {
		_talon.set(ControlMode.Position, get_My_CurrentRAW_Postion());
	}

}
