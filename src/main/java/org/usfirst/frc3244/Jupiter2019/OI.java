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

import org.usfirst.frc3244.Jupiter2019.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import oi.limelightvision.limelight.frc.LimeLight;
import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc3244.Jupiter2019.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public Joystick xBox_Driver;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public LimeLight limeLight;

    //Xbox game pad Channels
    public static final int GAMEPAD_XBOX_LEFT_X_AXIS = 0;//
    public static final int GAMEPAD_XBOX_LEFT_Y_AXIS = 1;//
    public static final int GAMEPAD_XBOX_LEFT_TRIGGER = 2;//
    public static final int GAMEPAD_XBOX_RIGHT_TRIGGER = 3;//
    public static final int GAMEPAD_XBOX_RIGHT_X_AXIS = 4;//
    public static final int GAMEPAD_XBOX_RIGHT_Y_AXIS = 5;//

    public static final int GAMEPAD_XBOX_A_BUTTON = 1;//
    public static final int GAMEPAD_XBOX_B_BUTTON = 2;//
    public static final int GAMEPAD_XBOX_X_BUTTON = 3;//
    public static final int GAMEPAD_XBOX_Y_BUTTON = 4;//
    public static final int GAMEPAD_XBOX_LEFT_BUTTON = 5;//
    public static final int GAMEPAD_XBOX_RIGHT_BUTTON = 6;//
    public static final int GAMEPAD_XBOX_RESET_BUTTON = 7;//
    public static final int GAMEPAD_XBOX_START_BUTTON = 8;//
    public static final int GAMEPAD_XBOX_LEFT_STICK_BUTTON = 9;
    public static final int GAMEPAD_XBOX_RIGHT_STICK_BUTTON = 10;

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        xBox_Driver = new Joystick(0);
        


        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getxBox_Driver() {
        return xBox_Driver;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS

    public double driveRotation() {
        return stickDeadBand(-xBox_Driver.getRawAxis(GAMEPAD_XBOX_RIGHT_X_AXIS),.2);
      }
      
      public boolean driveTurboMode() {
        return(xBox_Driver.getRawButton(GAMEPAD_XBOX_LEFT_BUTTON) || xBox_Driver.getRawAxis(GAMEPAD_XBOX_LEFT_TRIGGER)>0.2);
        
      }
      public boolean crawlBackward() {
            return(xBox_Driver.getRawButton(GAMEPAD_XBOX_A_BUTTON));
           
        }
      public boolean crawlForward() {
          return(xBox_Driver.getRawButton(GAMEPAD_XBOX_Y_BUTTON));
        }
        
        public boolean crawlLeft() {
          return(xBox_Driver.getRawButton(GAMEPAD_XBOX_B_BUTTON));
        }
        
        public boolean crawlRight() {
          return(xBox_Driver.getRawButton(GAMEPAD_XBOX_X_BUTTON));
        }
        private double stickDeadBand(double value, double deadband){
          if(Math.abs(value)<deadband){
            return 0;
          }else{
            return value;
          }
        }
        public double driveY() {
          return(stickDeadBand(xBox_Driver.getRawAxis(GAMEPAD_XBOX_LEFT_Y_AXIS),.2));
      }
}

