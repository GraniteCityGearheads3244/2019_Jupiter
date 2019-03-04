// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3244.Jupiter2019;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc3244.Jupiter2019.commands.*;
import org.usfirst.frc3244.Jupiter2019.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in 
 * the project.
 */
public class Robot extends TimedRobot {

    public static final boolean DEBUG = false;
    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain_1519_MM driveTrain_1519_MM;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Hatch hatch;
    public static Arm arm;
    public static CargoIntake cargoIntake;
    public static Elevator_MotionMagic elevator;
    public static PowerDistributionPanel pdp;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {

        pdp = new PowerDistributionPanel();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain_1519_MM = new DriveTrain_1519_MM();
        //eLevator_MotionMagic = new ELevator_MotionMagic();
        //hatch_Cargo_Arm_PID = new Hatch_Cargo_Arm_PID();
        //floorPick_PID = new FloorPick_PID();
        //cargo_Intake = new Cargo_Intake();
        hatch = new Hatch();
        arm = new Arm();
        cargoIntake = new CargoIntake();
        elevator = new Elevator_MotionMagic();

        driveTrain_1519_MM.init();
        elevator.init();

        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // Add commands to Autonomous Sendable Chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        chooser.setDefaultOption("Autonomous Command", new AutonomousCommand());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        SmartDashboard.putData("Auto mode", chooser);
        CameraServer.getInstance().startAutomaticCapture();


       

    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){

    }

    @Override
    public void disabledPeriodic() {
        diagnaostics();
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    boolean autonomousOnce = false;

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        if (!autonomousOnce)
    	{
    	  DriverStation.reportError("My Autonomou Periodic is running!", false);
    	}
        autonomousOnce = true;
        robotControl();
        //Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {

 

        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    boolean teleopOnce = false;
    
    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        if (!teleopOnce)
    	{
    	  DriverStation.reportError("My Teleop Periodic is running!", false);
    	}
    	teleopOnce = true;
       
        robotControl();
        //Scheduler.getInstance().run();

    }

    private void robotControl(){
        Scheduler.getInstance().run();
        driveTrain_1519_MM.driveTeleop(oi.driveY(), oi.driveRotation()*.5); 
        diagnaostics();
    }

    private void diagnaostics(){
        arm.diagnostics();
    }
}
