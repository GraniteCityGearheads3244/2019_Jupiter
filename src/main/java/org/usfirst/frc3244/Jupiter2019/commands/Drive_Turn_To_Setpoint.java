// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3244.Jupiter2019.commands;

import org.usfirst.frc3244.Jupiter2019.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drive_Turn_To_Setpoint extends PIDCommand {

	private static final boolean debug = false;
	
	private static final double kP = 0.007;
	private static final double kI = 0.0;
	private static final double kD = 0.04;
	
    private boolean rotateToAngle;
	private double rotateToAngleRate;
	static final double kToleranceDegrees = 2.0f;
	
    private double m_currentRotationRate;
    private double m_setpoint;
    Timer m_timer = new Timer();
	
	
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public Drive_Turn_To_Setpoint(double setpoint) {
   
        super("turnController", kP, kI, kD, 0.02);
        getPIDController().setInputRange(-180.0f,  180.0f);
        getPIDController().setOutputRange(-0.4, 0.4);
        getPIDController().setAbsoluteTolerance(kToleranceDegrees);
        getPIDController().setContinuous(true);
 
        requires(Robot.driveTrain_1519_MM);
        m_setpoint = setpoint;
        
    }
    
    /**
     * 
     * @param setpoint
     * @param outputrange
     */
    public Drive_Turn_To_Setpoint(double y, double setpoint,double outputrange) {
    	   
        super("turnController", kP, kI, kD, 0.02);
        getPIDController().setInputRange(-180.0f,  180.0f);
        getPIDController().setOutputRange(-outputrange, outputrange);
        getPIDController().setAbsoluteTolerance(kToleranceDegrees);
        getPIDController().setContinuous(true);
 
        requires(Robot.driveTrain_1519_MM);
        m_setpoint = setpoint;
        
    }

    protected double returnPIDInput() {
        
    	return Robot.driveTrain_1519_MM.getHeading();
        //return RobotMap.ahrs.getAngle();
      
    }

    protected void usePIDOutput(double output) {
      
    	rotateToAngleRate = output;
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	if(debug){
    		System.out.println("DriveTurnToSetpoint Current setpoint: " + m_setpoint);
    	}
    	getPIDController().setSetpoint(m_setpoint);
        rotateToAngle = true;
    	Robot.driveTrain_1519_MM.zeroDistanceTraveled();
    	m_timer.reset();
    	m_timer.start();    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//m_y = Robot.oi.driveY();
    	getPIDController().enable();    
    	m_currentRotationRate = rotateToAngleRate;
        Robot.driveTrain_1519_MM.driveTeleop(0.0, m_currentRotationRate);
  
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return getPIDController().onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	getPIDController().disable();
    	// note:  it is important to call mecanumDriveCartesian here, rather than mecanumDriveAutonomous,
    	// to ensure that "heading preservation" isn't activated for the last instruction
    	//Robot.driveTrain_1519_MM.driveAutoInTeleopFinished();
        Robot.driveTrain_1519_MM.driveTeleop(0.0, 0.0);
        Robot.driveTrain_1519_MM.clearDesiredHeading();
    	//SmartDashboard.putNumber("Time", m_timer.get());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
