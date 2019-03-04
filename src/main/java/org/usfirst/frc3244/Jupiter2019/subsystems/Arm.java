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

import org.usfirst.frc3244.Jupiter2019.Robot;
import org.usfirst.frc3244.Jupiter2019.commands.*;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;


/**
 *
 */
public class Arm extends PIDSubsystem {

    public double UP = 3.3;
    public double HATCH_PICK = 1.9;
    public double DOWN = 0.2;
    
    private Spark motor;
    private AnalogPotentiometer analogPotentiometer1;

    private double current_Angle_Raw;

    // Initialize your subsystem here
    public Arm() {
    
        super("Arm", 0.01, 0.0, 0.0, 5.0);
        setAbsoluteTolerance(0.2);
        getPIDController().setContinuous(false);
        getPIDController().setName("Arm", "PIDSubsystem Controller");
        LiveWindow.add(getPIDController());
        getPIDController().setInputRange(0.9,4.1);//(-11.2, 90);
        getPIDController().setOutputRange(-0.45, 0.6);

   
        motor = new Spark(5);
        addChild("Motor",motor);
        motor.setInverted(false);
        
        analogPotentiometer1 = new AnalogPotentiometer(0,5.0,0.0);//(0, 160, -44.5);
        addChild("Analog Potentiometer 1",analogPotentiometer1);

        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }

    @Override
    public void initDefaultCommand() {
      
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    @Override
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;

        current_Angle_Raw = analogPotentiometer1.get();
       
        return current_Angle_Raw;
    }

    @Override
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
        diagnostics();
       calculateFF(current_Angle_Raw);

        motor.pidWrite(output);

    }

    public void my_Dissable_PID(){
        getPIDController().disable();
    }

    public void my_Enable_PID(){
        getPIDController().enable();
    }

    private void calculateFF(double currentAngle){
        double armLenght = 10.0; //inches
        double armWeight = 10.0; //lbs
        
        //Redline motor
        double mortorOhms = .112; //internal Resistance
        double motorTorqe = 5.55; //Inch*lbs
        double motorStallCurrent = 106.9; //amps
        
        //Total Gear Reduction
        double gearBox = 200;
        double Kt = (motorTorqe/motorStallCurrent);

        double kF = ( (armLenght*armWeight*mortorOhms) / (Kt*gearBox) ) * Math.cos(Math.toRadians(currentAngle));
        
        SmartDashboard.putNumber("CalculateFF", currentAngle);
        //getPIDController().setF(kF);
    }

    public void diagnostics(){
        SmartDashboard.putNumber("Current Feedback Value",  analogPotentiometer1.get());
    }
}
