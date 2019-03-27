package org.usfirst.frc3244.Jupiter2019.commands;


import org.usfirst.frc3244.Jupiter2019.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drive_For_Distance extends Command {
	 private double m_y;
	 private double m_rotation;
	 private double m_distance;
	 private double m_heading;
	 private boolean m_CurrentHeading;
	 //Timer m_timer = new Timer();
	 
	 /**
	  * Drive For Distance at current robot Heading
	  * Added constructor CPA
	  * @param y
	  * @param rotation
	  * @param distance
	  */
	 public Drive_For_Distance(double y, double rotation, double distance) {
	    	requires(Robot.driveTrain_1519_MM);
	    	m_y = y;
	    	m_rotation = rotation;
	    	m_distance = distance;
	    	m_heading = 0;
	    	m_CurrentHeading = true;
	    }
	 
	 /**
	  * Drive For Distance at specified Heading
	  * Original FRC #1519 (Mechanical Mayhem)
	  * @param y
	  * @param rotation
	  * @param distance
	  * @param heading
	  */
	 public Drive_For_Distance(double y, double rotation, double distance, double heading) {
	    	requires(Robot.driveTrain_1519_MM);
	    	m_y = y;
	    	m_rotation = 0.0; //Can Not have rotation and heading
	    	m_distance = distance;
	    	m_heading = heading;
	    	m_CurrentHeading = false;
	    }

    // Called just before this Command runs the first time
    protected void initialize() {
		Robot.driveTrain_1519_MM.clearDesiredHeading();
    	Robot.driveTrain_1519_MM.zeroDistanceTraveled();
    	if(m_CurrentHeading){
    		m_heading = Robot.driveTrain_1519_MM.getHeading();
    	}
    	//m_timer.reset();
    	//m_timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain_1519_MM.driveAutonomous(m_y, m_rotation, m_heading);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.driveTrain_1519_MM.getDistanceTraveled() >= m_distance);
    }

    // Called once after isFinished returns true
    protected void end() {
    	// note:  it is important to call mecanumDriveCartesian here, rather than mecanumDriveAutonomous,
    	// to ensure that "heading preservation" isn't activated for the last instruction
    	Robot.driveTrain_1519_MM.driveCartesian(0.0, 0.0);
    	//SmartDashboard.putNumber("Time", m_timer.get());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	// note:  it is important to call mecanumDriveCartesian here, rather than mecanumDriveAutonomous,
    	// to ensure that "heading preservation" isn't activated for the last instruction
    	Robot.driveTrain_1519_MM.driveCartesian(0.0, 0.0);
    	//SmartDashboard.putNumber("Time", m_timer.get());
    }
}
