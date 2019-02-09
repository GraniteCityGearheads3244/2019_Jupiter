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

import org.usfirst.frc3244.Jupiter2019.commands.*;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class FloorPick_PID extends PIDSubsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private Spark motor_LIft;
    private AnalogPotentiometer pot_Position;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // Initialize your subsystem here
    public FloorPick_PID() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID
        super("FloorPick_PID", 1.0, 0.0, 0.0);
        setAbsoluteTolerance(0.2);
        getPIDController().setContinuous(false);
        getPIDController().setName("FloorPick_PID", "PIDSubsystem Controller");
        LiveWindow.add(getPIDController());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        motor_LIft = new Spark(1);
        addChild("Motor_LIft",motor_LIft);
        motor_LIft.setInverted(false);
        
        pot_Position = new AnalogPotentiometer(1, 270.0, 0.0);
        addChild("Pot_Position",pot_Position);
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS


        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    @Override
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SOURCE
        return pot_Position.get();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SOURCE
    }

    @Override
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=OUTPUT
        motor_LIft.pidWrite(output);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=OUTPUT
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

}
