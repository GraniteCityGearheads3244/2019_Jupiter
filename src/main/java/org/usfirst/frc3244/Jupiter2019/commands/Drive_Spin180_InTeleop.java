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
public class Drive_Spin180_InTeleop extends PIDCommand {

	private static final boolean debug = false;
	
	private static final double kP = 0.01;
	private static final double kI = 0.0;
	private static final double kD = 0.02;
	
    private boolean rotateToAngle;
	private double rotateToAngleRate;
	static final double kToleranceDegrees = 2.0f;
	
	
	private double m_x;
    private double m_y;
    private double m_currentRotationRate;
    private double m_setpoint;
    //Timer m_timer = new Timer();
    private boolean HeadingReadOnce = false;
	
	
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public Drive_Spin180_InTeleop() {
   
        super("turnController", kP, kI, kD, 0.02);
        getPIDController().setInputRange(-180.0f,  180.0f);
        getPIDController().setOutputRange(-0.75, 0.75);
        getPIDController().setAbsoluteTolerance(kToleranceDegrees);
        getPIDController().setContinuous(true);
 
        requires(Robot.driveTrain_1519_MM);
        
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

        HeadingReadOnce = false;

    	if(debug){
    		System.out.println("tCurrent setpoint: " + m_setpoint);
        }
        
    	getPIDController().setSetpoint(m_setpoint);
        rotateToAngle = true;
    	Robot.driveTrain_1519_MM.zeroDistanceTraveled();
    	//m_timer.reset();
    	//m_timer.start();    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
        if (!HeadingReadOnce){
            m_setpoint = Robot.driveTrain_1519_MM.getHeading() + 180;
    	}
        HeadingReadOnce = true;
        
        m_y = Robot.oi.driveY();
        getPIDController().setSetpoint(m_setpoint);
    	getPIDController().enable();    
        m_currentRotationRate = rotateToAngleRate;

        Robot.driveTrain_1519_MM.driveAutoInTeleop(m_y, m_currentRotationRate);
  
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	getPIDController().disable();
    	// note:  it is important to call mecanumDriveCartesian here, rather than mecanumDriveAutonomous,
    	// to ensure that "heading preservation" isn't activated for the last instruction
    	//Robot.driveTrain_1519_MM.driveAutoInTeleopFinished();
    	Robot.driveTrain_1519_MM.driveCartesian(0.0, 0.0);
        //SmartDashboard.putNumber("Time", m_timer.get());
        HeadingReadOnce = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
