/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.Jupiter2019.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc3244.Jupiter2019.Constants;
import org.usfirst.frc3244.Jupiter2019.Robot;

import edu.wpi.first.wpilibj.Solenoid;
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
  private Solenoid breakSol;

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
	 private double bottom_Position = 160;
	 //Intake
	 private double Intake_Hatch_Floor_Position = 160;
	 private double Intake_Hatch_Depot_Position = 250;
	 private double Intake_cargo_Floor_Position = 160;
	 private double Intake_cargo_Depot_Position = 160;
	 //Deliver To Cargo Bay
	 private double Deliver_Hatch_Bay_Position = 250;
	 private double Deliver_Cargo_Bay_Position = 195;
	 //Deliver To Rocket
	 private double Deliver_Hatch_Rocket_Position1 = 230; //250; //19"
	 private double Deliver_Hatch_Rocket_Position2 = 560; //45"
	 private double Deliver_Hatch_Rocket_Position3 = 640; //675; //75" with Arm set at 520 units;
	 private double Deliver_Cargo_Rocket_Position1 = 160; // Roller at 34 with Arm set at 330 units;
	 private double Deliver_Cargo_Rocket_Position2 = 375; // Center ball @ 57.5   (Was 410)
	 private double Deliver_Cargo_Rocket_Position3 = 640; //660; // Center ball @ 83" was(710)

   private double maxHeight = 720;
   private double minHeight = 160;
   private double Arm_Monitor_Zone_Top = 580;
   private double Arm_Monitor_Zone_Top_OVERRIDE_UP = 540;
   private double Arm_Monitor_Zone_Bottom_OVERRIDE_DOWN = 250;
   private double Arm_Monitor_Zone_Bottom = 200;
   

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

	 public double get_MaxHeight(){
		 return maxHeight;
	 }

	 public double get_minHeight(){
		 return minHeight;
	 }
	 
	/********************************
	 * START
	 * Game Mode
	 * 
	 * ******************************/
	

  public enum GameMode{
    Empty(0),
    /**
     * Hatch
     */
    Hatch(1),
    /**
     * Cargo
     */
    Cargo(2);

    /**
     * Value of Game mode
     */
    public final int value;

    /**
     * Create GameMode of initValue
     * @param initValue Value of GameMode
     */
    GameMode(int initValue)
    {
        this.value = initValue;
    }
};

	private GameMode current_GameMode =  GameMode.Empty;
	
	public int getCurrent_GameMode(){
		return current_GameMode.value;
	}

	public void setCurrent_GameMode_Empty(){
		this.current_GameMode = GameMode.Empty;
	}

	public void setCurrent_GameMode_Cargo(){
		this.current_GameMode = GameMode.Cargo;
	}

	public void setCurrent_GameMode_Hatch(){
		this.current_GameMode = GameMode.Hatch;
  }
  
	/********************************
	 * END
	 * Game Mode
	 * 
	 * ********************************/

  public Elevator_MotionMagic(){

		breakSol = new Solenoid(0,6);
    	addChild("Extender",breakSol);
	
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
		_talon.configMotionCruiseVelocity(30, Constants.kTimeoutMs);//30, Constants.kTimeoutMs);
    	_talon.configMotionAcceleration(45, Constants.kTimeoutMs);
    
			
		/* Zero the sensor */
		//_talon.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		_talon.configFeedbackNotContinuous(true, Constants.kTimeoutMs);
		//_talon.configClearPositionOnLimitR(true, Constants.kTimeoutMs);

		/* Configure Current Limits */
		_talon.configPeakCurrentLimit(30);
    	_talon.configPeakCurrentDuration(150);
    	_talon.configContinuousCurrentLimit(20);

     // put all Talon SRX into brake mode
		_talon.setNeutralMode(NeutralMode.Brake);

		

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
    double elevatorkP = 4.7;// 3/17 = 1.7///RobotPreferences.getelevatorkP();
    double elevatorkI = 0.003;//RobotPreferences.getelevatorkI();
    double elevatorkD = 100.0;//RobotPreferences.getelevatorkD();
    double elevatorkF = 31.96;//RobotPreferences.getelevatorkF();
  
   /* Set Motion Magic gains in slot0 - see documentation */
		_talon.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		_talon.config_kF(Constants.kSlotIdx, elevatorkF, Constants.kTimeoutMs);//Constants.kGains.kF, Constants.kTimeoutMs);
		_talon.config_kP(Constants.kSlotIdx, elevatorkP, Constants.kTimeoutMs);//Constants.kGains.kP, Constants.kTimeoutMs);
		_talon.config_kI(Constants.kSlotIdx, elevatorkI, Constants.kTimeoutMs);//Constants.kGains.kI, Constants.kTimeoutMs);
		_talon.config_kD(Constants.kSlotIdx, elevatorkD, Constants.kTimeoutMs);//Constants.kGains.kD, Constants.kTimeoutMs);

		// new PIDF Setups
		_talon.config_IntegralZone(Constants.kSlotIdx, 30);
		//_talon.setIntegralAccumulator(20, Constants.kSlotIdx, Constants.kTimeoutMs);
  }

  public void setPIDF_DWN() {
    double elevatorkP = 1.7;///RobotPreferences.getelevatorkP();
    double elevatorkI = 0.003;//RobotPreferences.getelevatorkI();
    double elevatorkD = 100.0;//RobotPreferences.getelevatorkD();
    double elevatorkF = 31.96;//RobotPreferences.getelevatorkF();
  
   /* Set Motion Magic gains in slot0 - see documentation */
		_talon.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		_talon.config_kF(Constants.kSlotIdx, elevatorkF, Constants.kTimeoutMs);//Constants.kGains.kF, Constants.kTimeoutMs);
		_talon.config_kP(Constants.kSlotIdx, elevatorkP, Constants.kTimeoutMs);//Constants.kGains.kP, Constants.kTimeoutMs);
		_talon.config_kI(Constants.kSlotIdx, elevatorkI, Constants.kTimeoutMs);//Constants.kGains.kI, Constants.kTimeoutMs);
		_talon.config_kD(Constants.kSlotIdx, elevatorkD, Constants.kTimeoutMs);//Constants.kGains.kD, Constants.kTimeoutMs);
  }

  public void periodic() {

	SmartDashboard.putNumber("Elevaltor Height", get_My_CurrentRAW_Postion() );
  
  }

    public void my_ScissorMotionMagic(double height) {

		double m_targetEncoderValue = clampEncoderValue(height);
		double currentPOS = get_My_CurrentRAW_Postion();
		//Is Elevator in the arm monitor zone?
		if((currentPOS < Arm_Monitor_Zone_Top) &&
		 	(currentPOS > Arm_Monitor_Zone_Bottom) && 
		 	(!Robot.arm_MM.get_IsArm_CLear_For_Elevator())){

				// Is the elevator above the zone and target in above too?
				if((currentPOS > Arm_Monitor_Zone_Top_OVERRIDE_UP) &&
				(m_targetEncoderValue > Arm_Monitor_Zone_Top)){

					my_SetMotor_Taget_MM(m_targetEncoderValue);

				// Is the elevator Below the zone and target in below too?
				}else if((currentPOS < Arm_Monitor_Zone_Bottom_OVERRIDE_DOWN) &&
				(m_targetEncoderValue < Arm_Monitor_Zone_Bottom)){

					my_SetMotor_Taget_MM(m_targetEncoderValue);

				// Stop Motoin!!!!!!!!!!!!!!!
				}else{

					my_ElevatorStop();

				}
			
		 }else{

			 //This should slow things down when reseting from Pos 3
			 if((currentPOS > Arm_Monitor_Zone_Top_OVERRIDE_UP) &&
			 (m_targetEncoderValue < Arm_Monitor_Zone_Top) && 
		 	 (!Robot.arm_MM.get_IsArm_CLear_For_Elevator())){
				m_targetEncoderValue = currentPOS;
			 }

			my_SetMotor_Taget_MM(m_targetEncoderValue);
		 }

	}
	
	private void my_SetMotor_Taget_MM(double setpoint){
		set_my_Release_Break(true);
		_talon.set(ControlMode.MotionMagic, setpoint);
	}
    
    private double clampEncoderValue(double value) {
      if(value > maxHeight){
        return maxHeight;
      }else if(value < minHeight){
        return minHeight;
      }else{
        return value;
      }
     
     
    }

    
    public void my_ElevatorStop() {
		 _talon.set(ControlMode.PercentOutput, 0.0);
		 set_my_Release_Break(false);
     
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
	public double get_Arm_Clear_Window_TOP() {
		return Arm_Monitor_Zone_Top;
	}

	public boolean get_IsElevator_CLear_For_Arm(){
		double currentPOS = get_My_CurrentRAW_Postion();
		if((currentPOS < Arm_Monitor_Zone_Top) &&
		 	(currentPOS > Arm_Monitor_Zone_Bottom)){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Set True to release Break
	 * @param release
	 */
	public void set_my_Release_Break(boolean release){
		breakSol.set(release);
	}
}
