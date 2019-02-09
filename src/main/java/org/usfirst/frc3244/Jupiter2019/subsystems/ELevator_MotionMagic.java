// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3244.Jupiter2019.subsystems;


import org.usfirst.frc3244.Jupiter2019.Constants;
import org.usfirst.frc3244.Jupiter2019.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class ELevator_MotionMagic extends Subsystem {

    private boolean m_DebugThisSubsystem = false;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_TalonSRX motor = new WPI_TalonSRX(6);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    private double m_targetEncoderValue = 0;

    // member variables for Motors drive
    private static final int kMaxNumberOfMotors = 1;
    private final int m_invertedMotors[] = new int[kMaxNumberOfMotors];
    private static final int kMotorWinch = 0;
    private final double m_encodercountsPerRev = 1706;
    private final double m_maxRevs = 18;
    private final double m_minRevs = -18;
    private final double m_distancePerRev = 1.33*Math.PI;
    private final double m_Tolerance = .2;

    // create objects needed for independent control of each wheel
    private WPI_TalonSRX[] m_talons = new WPI_TalonSRX[kMaxNumberOfMotors];
    private double m_zeroPositions[] = new double[kMaxNumberOfMotors];
    
    // member variables to support closed loop mode
    private int m_CruiseVelocity = 190;
    private int m_Acceleration = 65;
    private double m_motorWinch_Start_Pos = 0;

    

    

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public ELevator_MotionMagic() {

        int talonIndex = 0;

    	// construct the talons
    	m_talons[kMotorWinch] = motor;
    	
    	/* first choose the sensor */
    	for (talonIndex = 0; talonIndex < kMaxNumberOfMotors; talonIndex++) {
    		m_talons[talonIndex].configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    		m_talons[talonIndex].setSensorPhase(true);
    		m_talons[talonIndex].setInverted(false);
    	}
    	
    	/* Set relevant frame periods to be at least as fast as periodic rate*/
    	for (talonIndex = 0; talonIndex < kMaxNumberOfMotors; talonIndex++) {
    		m_talons[talonIndex].setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
    		m_talons[talonIndex].setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);
    	}
    	
    	/* set the peak and nominal outputs, 12V means full */
    	for (talonIndex = 0; talonIndex < kMaxNumberOfMotors; talonIndex++) {
    		m_talons[talonIndex].configNominalOutputForward(0, Constants.kTimeoutMs);
    		m_talons[talonIndex].configNominalOutputReverse(0, Constants.kTimeoutMs);
    		m_talons[talonIndex].configPeakOutputForward(1, Constants.kTimeoutMs);
    		m_talons[talonIndex].configPeakOutputReverse(-1, Constants.kTimeoutMs);
    	}
    	
		/* set acceleration and vcruise velocity - see documentation */
		for (talonIndex = 0; talonIndex < kMaxNumberOfMotors; talonIndex++) {
			m_talons[talonIndex].configMotionCruiseVelocity(m_CruiseVelocity, Constants.kTimeoutMs);
			m_talons[talonIndex].configMotionAcceleration(m_Acceleration, Constants.kTimeoutMs);
		}
		/* zero the sensor */
		for (talonIndex = 0; talonIndex < kMaxNumberOfMotors; talonIndex++) {
			m_talons[talonIndex].setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		}
		
		/* Set The PID Profile Slot */
		for (talonIndex = 0; talonIndex < kMaxNumberOfMotors; talonIndex++) {
			m_talons[talonIndex].selectProfileSlot(0, 0);
		}
    	
 	}
 	
 	public void init() {
		// complete initialization here that can't be performed in constructor
		// (some calls can't be made in constructor because other objects don't
		// yet exist)

		// Set up the TalonSRX closed loop / open loop mode for each wheel
    	setPIDF(); //Normal usage 
    	
    	my_WinchStop();
	}
 	
 	
 	
 	public void setPIDF() {
    	int talonIndex = 0;
		double winchkP = 1.0;///RobotPreferences.getScissorkP();
		double winchkI = 0.0;//RobotPreferences.getScissorkI();
		double winchkD = 0.0;//RobotPreferences.getScissorkD();
		double winchkF = 2.7;//RobotPreferences.getScissorkF();
		
    	/* set closed loop gains in slot0 */
    	for (talonIndex = 0; talonIndex < kMaxNumberOfMotors; talonIndex++) {
    		m_talons[talonIndex].config_kF(Constants.kPIDLoopIdx, winchkF, Constants.kTimeoutMs);
    		m_talons[talonIndex].config_kP(Constants.kPIDLoopIdx, winchkP, Constants.kTimeoutMs);
    		m_talons[talonIndex].config_kI(Constants.kPIDLoopIdx, winchkI, Constants.kTimeoutMs);
    		m_talons[talonIndex].config_kD(Constants.kPIDLoopIdx, winchkD, Constants.kTimeoutMs);
    	}
    }
 	
    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
    	if(m_DebugThisSubsystem) {
    		debug();
    	}
    	diagnostics();

    }
    
    public void my_WinchSetpositionToCurrent() {
    	m_targetEncoderValue = my_get_Winch_Raw_Encoder();
    	m_talons[kMotorWinch].set(ControlMode.MotionMagic, m_targetEncoderValue);

    }
    
    public void my_WinchMotionMagic(double distance) {
    	double rotations = distance / m_distancePerRev;
    	m_targetEncoderValue = clampEncoderValue(rotations * m_encodercountsPerRev);
		    	
    	m_talons[kMotorWinch].set(ControlMode.MotionMagic, m_targetEncoderValue);
    	
    }
    
    private double clampEncoderValue(double value) {
    	if(value > m_maxRevs*m_encodercountsPerRev) { //18 Rev at 1706 counts/rev
    		DriverStation.reportError("Winch m_targetEncoderValue. Out of range MAX: " + value  + ">" + m_maxRevs*m_encodercountsPerRev, false);
    		return m_maxRevs*m_encodercountsPerRev;
    	}else if(value<m_minRevs*m_encodercountsPerRev){
    		DriverStation.reportError("Winch m_targetEncoderValue. Out of range MIN: " + value  + "<" + m_minRevs*m_encodercountsPerRev, false);
    		return m_minRevs*m_encodercountsPerRev;
    	}else {
    		return value;
    	}
    }
    
    public void my_unwind_Motor_Jog() {
    	m_talons[kMotorWinch].set(ControlMode.PercentOutput, 0.3);
    }

    public void my_WinchStop() {
    	m_talons[kMotorWinch].set(ControlMode.PercentOutput, 0.0);
    	
    }
    
    //************ Get Encoders
    
    public double my_get_Winch_Raw_Encoder() {
    	return m_talons[kMotorWinch].getSelectedSensorPosition(0);
    }
    
    public double my_get_Winch_Rotations() {
    	return my_get_Winch_Raw_Encoder()/m_encodercountsPerRev;
    }
    public void my_zeroEncoders() {
    	int talonIndex = 0;
    	/* zero the sensor */
		for (talonIndex = 0; talonIndex < kMaxNumberOfMotors; talonIndex++) {
			m_talons[talonIndex].setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		}	

    }
       
	private void diagnostics() {
		// TODO Auto-generated method stub
		double volts = m_talons[0].getMotorOutputVoltage();
		SmartDashboard.putNumber("Winch Motor Voltage", volts);
		
	}

	private void debug() {
				
		double rawEncioder_window_Hi 	= 		(m_targetEncoderValue/m_encodercountsPerRev) + m_Tolerance;
    	double rawEncioder_window_Low 	= 		(m_targetEncoderValue/m_encodercountsPerRev) - m_Tolerance;
    	double currentRawEncoderPos 	= 		my_get_Winch_Rotations();
    	
    	SmartDashboard.putNumber("Winch Current Pos: ", 	currentRawEncoderPos );
    	SmartDashboard.putNumber("Winch Window Hi", 		rawEncioder_window_Hi );
    	SmartDashboard.putNumber("Winch window Low", 		rawEncioder_window_Low );
    	
	}

	public boolean onTarget() {
		double window_Hi = (m_targetEncoderValue/m_encodercountsPerRev) + m_Tolerance;
    	double window_Low = (m_targetEncoderValue/m_encodercountsPerRev) - m_Tolerance;
    	double currentPos = my_get_Winch_Rotations();
    	
    	if(currentPos > window_Low && currentPos < window_Hi) {
    		return true;
    	}else {
    		return false;
    	}
	}



}


