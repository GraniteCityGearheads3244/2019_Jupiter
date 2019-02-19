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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Elevator_MotionMagic extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  /* Hardware */
  TalonSRX _talon = new TalonSRX(0);
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  /********************************
	 * 
	 * Predefined setpoints
	 * 
	 * ******************************/

	 //home
	 private double bottom_Position = 0;
	 //Intake
	 private double Intake_Hatch_Floor_Position = 1;
	 private double Intake_Hatch_Depot_Position = 20;
	 private double Intake_cargo_Floor_Position = 1;
	 private double Intake_cargo_Depot_Position = 1;
	 //Deliver Cargo Bay
	 private double Deliver_Hatch_Bay_Position = 1;
	 private double Deliver_Cargo_Bay_Position = 1;
	 //Deliver Rocket
	 private double Deliver_Hatch_Rocket_Position1 = 100;
	 private double Deliver_Hatch_Rocket_Position2 = 450;
	 private double Deliver_Hatch_Rocket_Position3 = 500;
	 private double Deliver_Cargo_Rocket_Position1 = 5;
	 private double Deliver_Cargo_Rocket_Position2 = 6;
	 private double Deliver_Cargo_Rocket_Position3 = 6;

   private double maxHeight = 550;
   private double minHeight = 0;

	 //***    Getters    */
	 //home
	 public double get_bottom_Position(){
		return bottom_Position;
	 }
	 //Intake
	 public double get_Intake_Hatch_Floor_Position(){
		return Intake_Hatch_Floor_Position;
	 }
	 public double get_Intake_Hatch_Depot_Position(){
		return Intake_Hatch_Depot_Position;
	 }
	 public double get_Intake_cargo_Floor_Position(){
		return Intake_cargo_Floor_Position;
	 }
	 public double get_Intake_cargo_Depot_Position(){
		return Intake_cargo_Depot_Position;
	 }
	 //Deliver Cargo Bay
	 public double get_Deliver_Hatch_Bay_Position(){
		return Deliver_Hatch_Bay_Position;
	 }
	 public double get_Deliver_Cargo_Bay_Position(){
		return Deliver_Cargo_Bay_Position;
	 }
	 //Deliver Rocket
	 public double get_Deliver_Hatch_Rocket_Position1(){
		return Deliver_Hatch_Rocket_Position1;
	 }
	 public double get_Deliver_Hatch_Rocket_Position2(){
		return Deliver_Hatch_Rocket_Position2;
	 }
	 public double get_Deliver_Hatch_Rocket_Position3(){
		return Deliver_Hatch_Rocket_Position3;
	 }
	 public double get_Deliver_Cargo_Rocket_Position1(){
		return Deliver_Cargo_Rocket_Position1;
	 }
	 public double get_Deliver_Cargo_Rocket_Position2(){
		return Deliver_Cargo_Rocket_Position2;
	 }
	 public double get_Deliver_Cargo_Rocket_Position3(){
		return Deliver_Cargo_Rocket_Position3;
	 }
	 
	/********************************
	 * START
	 * Game Mode
	 * 
	 * ******************************/
	

	public static enum gameMode{
		EMPTY, CARGO, HATCH;
	}

	private gameMode current_GameMode =  gameMode.EMPTY;
	
	public String getCurrent_GameMode(){
		return current_GameMode.toString();
	}

	public void setCurrent_GameMode_Empty(){
		this.current_GameMode = gameMode.EMPTY;
	}

	public void setCurrent_GameMode_Cargo(){
		this.current_GameMode = gameMode.CARGO;
	}

	public void setCurrent_GameMode_Hatch(){
		this.current_GameMode = gameMode.HATCH;
	}
	/********************************
	 * END
	 * Game Mode
	 * 
	 * ********************************/

  public Elevator_MotionMagic(){
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
		_talon.setSensorPhase(false);
		_talon.setInverted(false);

		/* Set relevant frame periods to be at least as fast as periodic rate */
		_talon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
		_talon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);

		/* Set the peak and nominal outputs */
		_talon.configNominalOutputForward(0, Constants.kTimeoutMs);
		_talon.configNominalOutputReverse(0, Constants.kTimeoutMs);
		_talon.configPeakOutputForward(1, Constants.kTimeoutMs);
		_talon.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		/* Set acceleration and vcruise velocity - see documentation */
		_talon.configMotionCruiseVelocity(20, Constants.kTimeoutMs);
		_talon.configMotionAcceleration(20, Constants.kTimeoutMs);

		/* Zero the sensor */
    _talon.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    _talon.configClearPositionOnLimitR(true, Constants.kTimeoutMs);
  }

  public void init() {
		// complete initialization here that can't be performed in constructor
		// (some calls can't be made in constructor because other objects don't
		// yet exist)

		// Set up the TalonSRX closed loop / open loop mode for each wheel
    	
    	setPIDF_UP();
    	//setPIDF_DWN();
  }
  
  public void setPIDF_UP() {
    double elevatorkP = 1.7;///RobotPreferences.getelevatorkP();
    double elevatorkI = 0.0;//RobotPreferences.getelevatorkI();
    double elevatorkD = 0.0;//RobotPreferences.getelevatorkD();
    double elevatorkF = 46.2;//RobotPreferences.getelevatorkF();
  
   /* Set Motion Magic gains in slot0 - see documentation */
		_talon.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		_talon.config_kF(Constants.kSlotIdx, elevatorkF, Constants.kTimeoutMs);//Constants.kGains.kF, Constants.kTimeoutMs);
		_talon.config_kP(Constants.kSlotIdx, elevatorkP, Constants.kTimeoutMs);//Constants.kGains.kP, Constants.kTimeoutMs);
		_talon.config_kI(Constants.kSlotIdx, elevatorkI, Constants.kTimeoutMs);//Constants.kGains.kI, Constants.kTimeoutMs);
		_talon.config_kD(Constants.kSlotIdx, elevatorkD, Constants.kTimeoutMs);//Constants.kGains.kD, Constants.kTimeoutMs);
  }

  public void setPIDF_DWN() {
    double elevatorkP = 1.7;///RobotPreferences.getelevatorkP();
    double elevatorkI = 0.0;//RobotPreferences.getelevatorkI();
    double elevatorkD = 0.0;//RobotPreferences.getelevatorkD();
    double elevatorkF = 46.2;//RobotPreferences.getelevatorkF();
  
   /* Set Motion Magic gains in slot0 - see documentation */
		_talon.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		_talon.config_kF(Constants.kSlotIdx, elevatorkF, Constants.kTimeoutMs);//Constants.kGains.kF, Constants.kTimeoutMs);
		_talon.config_kP(Constants.kSlotIdx, elevatorkP, Constants.kTimeoutMs);//Constants.kGains.kP, Constants.kTimeoutMs);
		_talon.config_kI(Constants.kSlotIdx, elevatorkI, Constants.kTimeoutMs);//Constants.kGains.kI, Constants.kTimeoutMs);
		_talon.config_kD(Constants.kSlotIdx, elevatorkD, Constants.kTimeoutMs);//Constants.kGains.kD, Constants.kTimeoutMs);
  }

  public void periodic() {
       
    /*
    m_Left_FWD_LimitSwitch = my_get_LEFT_FWD_LimitSwitch();
    m_Left_REV_LimitSwitch = my_get_LEFT_REV_LimitSwitch();
    m_Right_FWD_LimitSwitch = my_get_RIGHT_FWD_LimitSwitch();
    m_Right_REV_LimitSwitch = my_get_RIGHT_REV_LimitSwitch();
    
    diagnostics();
    
    if(m_DebugThisSubsystem) {
      debug();
    }
    */
  }

  //Set the PID Profile Slot
    /**
     * 
     * @param isClimb
     */
    public void my_set_ControlProfile(boolean isClimb) {
    	int talonIndex;
    	/*
    	if(isClimb) {
    		for (talonIndex = 0; talonIndex < kMaxNumberOfMotors; talonIndex++) {
    			m_talons[talonIndex].selectProfileSlot(1, 0);
    		}
    		for (talonIndex = 0; talonIndex < kMaxNumberOfMotors; talonIndex++) {
    			m_talons[talonIndex].configMotionCruiseVelocity(m_CruiseVelocityClimb, Constants.kTimeoutMs);
    			m_talons[talonIndex].configMotionAcceleration(m_AccelerationClimb, Constants.kTimeoutMs);
    		}
    	}else {
    		for (talonIndex = 0; talonIndex < kMaxNumberOfMotors; talonIndex++) {
    			m_talons[talonIndex].selectProfileSlot(0, 0);
    		}
    		for (talonIndex = 0; talonIndex < kMaxNumberOfMotors; talonIndex++) {
    			m_talons[talonIndex].configMotionCruiseVelocity(m_CruiseVelocity, Constants.kTimeoutMs);
    			m_talons[talonIndex].configMotionAcceleration(m_Acceleration, Constants.kTimeoutMs);
    		}
      }
      */
    }

    public void my_ScissorMotionMagic(double height) {
    	double m_targetEncoderValue = clampEncoderValue(height);
    	//double m_targetEncoderValue = height;

    	_talon.set(ControlMode.MotionMagic, m_targetEncoderValue);
  
    }
    
    private double clampEncoderValue(double value) {
      if(value > maxHeight){
        return maxHeight;
      }else if(value < minHeight){
        return minHeight;
      }else{
        return value;
      }
      /*
      double rotatinToEncoder_Limit = 21.5*4096; // 18*4096 for 5/8 lead Screw,, 21.5*4096 for 1/2 lead Screw
    	if(value > rotatinToEncoder_Limit) { //18 Rev at 4096 counts/rev
    		DriverStation.reportError("Scissor m_targetEncoderValue. Out of range MAX: " + value  + ">" + rotatinToEncoder_Limit, false);
    		return rotatinToEncoder_Limit
    				;
    	}else if(value<0){
    		DriverStation.reportError("Scissor m_targetEncoderValue. Out of range MIN: " + value  + "<" + 0, false);
    		return 0;
    	}else {
    		
    		return value;
      }
      */
     
    }

    
    // public void my_jog_Left_Down_Motor() {
    // 	m_talons[kMotorLeft].set(ControlMode.PercentOutput, -0.1);
    // }
    // public void my_Jog_Right_Down_Motor() {
    // 	m_talons[kMotorRight].set(ControlMode.PercentOutput, -0.1);
    // }
    
    public void my_ScissorStop() {
     	_talon.set(ControlMode.PercentOutput, 0.0);
     
    }
   
    // //************ Get Encoders ************
    // public double my_get_LEFT_Raw_Encoder() {
    // 	return m_talons[kMotorLeft].getSelectedSensorPosition(0);
    // }
    // public double my_get_RIGHT_Raw_Encoder() {
    // 	return m_talons[kMotorRight].getSelectedSensorPosition(0);
    // }
    
    // public double my_get_Current_Height() {
    // 	return ((my_get_LEFT_Raw_Encoder() + my_get_RIGHT_Raw_Encoder()) / 2) / m_convertion;
    // }
    
    // //************ Get Limit Switches
    // public boolean my_get_LEFT_FWD_LimitSwitch() {
    // 	return m_talons[kMotorLeft].getSensorCollection().isFwdLimitSwitchClosed();
    // }
    // public boolean my_get_LEFT_REV_LimitSwitch() {
    // 	return m_talons[kMotorLeft].getSensorCollection().isRevLimitSwitchClosed();
    // }
    // public boolean my_get_RIGHT_FWD_LimitSwitch() {
    // 	return m_talons[kMotorRight].getSensorCollection().isFwdLimitSwitchClosed();
    // }
    // public boolean my_get_RIGHT_REV_LimitSwitch() {
    // 	return m_talons[kMotorRight].getSensorCollection().isRevLimitSwitchClosed();
    // }
    
    // public void my_zeroEncoders() {
    // 	int talonIndex = 0;
    // 	/* zero the sensor */
		// for (talonIndex = 0; talonIndex < kMaxNumberOfMotors; talonIndex++) {
		// 	m_talons[talonIndex].setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		// }
    // }
    
    // public void my_referenc_Encoders_to_LimitSwitch() {
    // 	int homeDir = -1;
    // 	double homeSpeed = .1;
    // 	double syncError;
    	
    // 	if(Robot.oi.launchPad.getRawButton(11)){
    // 		homeSpeed = .7;
    // 	}else {
    // 		homeSpeed = .3;
    // 	}
    	
    // 	syncError = my_get_LEFT_Raw_Encoder() - my_get_RIGHT_Raw_Encoder();
    // 	System.out.println("syncError= " + syncError);
   
    // 	if(!m_Left_REV_LimitSwitch) {
    // 		m_talons[kMotorLeft].set(ControlMode.PercentOutput, (homeSpeed)*homeDir);
    // 	}else {
    // 		m_talons[kMotorLeft].set(ControlMode.PercentOutput,0.0);
    // 	}
    	
    // 	if(!m_Right_REV_LimitSwitch) {
    // 		m_talons[kMotorRight].set(ControlMode.PercentOutput, (homeSpeed)*homeDir);
    // 	}else {
    // 		m_talons[kMotorRight].set(ControlMode.PercentOutput,0.0);
    // 	}
    // }
    
    // public void my_reference_Encoders_off_LimitSwitch() {
    // 		m_talons[kMotorLeft].set(ControlMode.MotionMagic, 1024);
    // 		m_talons[kMotorRight].set(ControlMode.MotionMagic, 1024);
    // }
    
    // public boolean onTarget() {
    // 	double window_Hi = m_targetEncoderValue + (m_Tolerance*m_encoderUnitsPerRev);
    // 	double window_Low = m_targetEncoderValue - (m_Tolerance*m_encoderUnitsPerRev);
    // 	double currentPos = (my_get_LEFT_Raw_Encoder()+my_get_RIGHT_Raw_Encoder())/2;
    	
    // 	if(currentPos > window_Low && currentPos < window_Hi) {
    // 		return true;
    // 	}else {
    // 		return false;
    // 	}	
    // }
    
    // public boolean getOvertraveles() {
    // 	if(m_Left_FWD_LimitSwitch || m_Left_REV_LimitSwitch || m_Right_FWD_LimitSwitch || m_Right_REV_LimitSwitch) {
    // 		return true;
    // 	}else {
    // 		return false;
    // 	}
    // }
    
    // boolean report_ONS[] = new boolean[4];
    
    // public void diagnostics() {
    	
    	
    // 	if(m_Left_FWD_LimitSwitch) {
    // 		if(!report_ONS[0]) {
    // 			DriverStation.reportError("Scissor Left FWD Limit Switch activated ", false);
    // 			report_ONS[0] = true;
    // 		}
    // 	}else {
    // 		report_ONS[0] = false;
    // 	}
    	
    // 	if(m_Left_REV_LimitSwitch) {
    // 		if(!report_ONS[1]) {
    // 			DriverStation.reportError("Scissor Left REV Limit Switch activated ", false);
    // 			report_ONS[1] = true;
    // 		}
    // 	}else {
    // 		report_ONS[1] = false;
    // 	}
    	
    // 	if(m_Right_FWD_LimitSwitch) {
    // 		if(!report_ONS[2]) {
    // 			DriverStation.reportError("Scissor Right FWD Limit Switch activated ", false);
    // 			report_ONS[2] = true;
    // 		}
    // 	}else {
    // 		report_ONS[2] = false;
    // 	}
    	
    // 	if(m_Right_REV_LimitSwitch) {
    // 		if(!report_ONS[3]) {
    // 			DriverStation.reportError("Scissor Right REV Limit Switch activated ", false);
    // 			report_ONS[3] = true;
    // 		}
    // 	}else {
    // 		report_ONS[3] = false;
    // 	}
    // }
    
    // private void debug() {
    // 	SmartDashboard.putNumber("TargetPos", 			m_targetEncoderValue);
    // 	SmartDashboard.putNumber("AccPos kMotorLeft", 	my_get_LEFT_Raw_Encoder());
    // 	SmartDashboard.putNumber("AccPos kMotorRight", 	my_get_RIGHT_Raw_Encoder());
    	
    // 	double rawEncioder_window_Hi 	= 		m_targetEncoderValue 		+ 	(m_Tolerance*m_encoderUnitsPerRev);
    // 	double rawEncioder_window_Low 	= 		m_targetEncoderValue 		- 	(m_Tolerance*m_encoderUnitsPerRev);
    // 	double currentRawEncoderPos 	= 		(my_get_LEFT_Raw_Encoder() 	+ 	my_get_RIGHT_Raw_Encoder()) / 2;
    	
    // 	SmartDashboard.putNumber("Current Pos: ", 	currentRawEncoderPos / (m_convertion));
    // 	SmartDashboard.putNumber("Window Hi", 		rawEncioder_window_Hi / (m_convertion));
    // 	SmartDashboard.putNumber("window Low", 		rawEncioder_window_Low / (m_convertion));
    	
    // 	SmartDashboard.putString("Motor Left ControlMode", 		m_talons[kMotorLeft].getControlMode().toString());
    // 	SmartDashboard.putString("Motor Right ControlMode", 	m_talons[kMotorRight].getControlMode().toString());
    // }
    
}
