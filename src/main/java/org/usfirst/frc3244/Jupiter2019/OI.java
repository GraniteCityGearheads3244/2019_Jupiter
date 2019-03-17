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

import org.usfirst.frc3244.Jupiter2019.commandGroups.CG_Elevator_LVL1_Cargo;
import org.usfirst.frc3244.Jupiter2019.commandGroups.CG_Elevator_LVL1_Hatch;
import org.usfirst.frc3244.Jupiter2019.commandGroups.CG_Elevator_LVL2_Cargo;
import org.usfirst.frc3244.Jupiter2019.commandGroups.CG_Elevator_LVL2_Hatch;
import org.usfirst.frc3244.Jupiter2019.commandGroups.CG_Elevator_LVL3_Cargo;
import org.usfirst.frc3244.Jupiter2019.commandGroups.CG_Elevator_LVL3_Hatch;
import org.usfirst.frc3244.Jupiter2019.commandGroups.CG_HatchDeliver;
import org.usfirst.frc3244.Jupiter2019.commandGroups.CG_HatchDeliverSeqComplete;
import org.usfirst.frc3244.Jupiter2019.commandGroups.CG_HatchGrabSeqComplete;
import org.usfirst.frc3244.Jupiter2019.commandGroups.CG_HatchGrabSeqStart;
import org.usfirst.frc3244.Jupiter2019.commandGroups.CG_Hatch_Pick_From_Floor;
import org.usfirst.frc3244.Jupiter2019.commandGroups.CG_Hatch_Pick_Prepair_From_Floor;
import org.usfirst.frc3244.Jupiter2019.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import oi.limelightvision.limelight.frc.LimeLight;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc3244.Jupiter2019.subsystems.*;
import org.usfirst.frc3244.Jupiter2019.util.AndJoystickButton;
import org.usfirst.frc3244.Jupiter2019.util.JoystickAxisButton;
import org.usfirst.frc3244.Jupiter2019.util.JoystickPOVButton;
import org.usfirst.frc3244.Jupiter2019.util.OrJoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private LimeLight limeLight;
    public Joystick xBox_Driver;
    public Joystick xBox_CoDriver;
    public Joystick launchPad;

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
    

     /**
     * Declare Driver Buttons
     */
    public JoystickButton a_xBox_Driver;
    public JoystickButton b_xBox_Driver;
    public JoystickButton x_xBox_Driver;
    public JoystickButton y_xBox_Driver;
    public JoystickButton lb_xBox_Driver;
    public JoystickButton rb_xBox_Driver;
    public JoystickButton r_Stick_Button_xbox_Driver;
    public JoystickButton l_Stick_Button_xbox_Driver;
    public JoystickButton start_xBox_Driver;
    public JoystickButton reset_xBox_Driver;
     
    public JoystickAxisButton rt_xBox_Driver;
    public JoystickAxisButton lt_xBox_Driver;
     
    public JoystickPOVButton povNorth_xBox_Driver;
    public JoystickPOVButton povSouth_xBox_Driver;
    public JoystickPOVButton povWest_xBox_Driver;
    public JoystickPOVButton povEast_xBox_Driver;
    
    /**
     * Declare CoDriver Buttons
     */
    public JoystickButton a_xBox_CoDriver;
    public JoystickButton b_xBox_CoDriver;
    public JoystickButton x_xBox_CoDriver;
    public JoystickButton y_xBox_CoDriver;
    public JoystickButton lb_xBox_CoDriver;
    public JoystickButton rb_xBox_CoDriver;
    public JoystickButton r_Stick_Button_xbox_CoDriver;
    private JoystickAxisButton rJoystickUpDown_CoDriver;
    public JoystickButton l_Stick_Button_xbox_CoDriver;
    private JoystickAxisButton lJoystickUpDown_CoDriver;
    public JoystickButton start_xBox_CoDriver;
    public JoystickButton reset_xBox_CoDriver;
    public JoystickButton lsb_xBox_CoDriver;

    public JoystickAxisButton rt_xBox_CoDriver;
    public JoystickAxisButton lt_xBox_CoDriver;

    public JoystickPOVButton povNorth_xBox_CoDriver;
    public JoystickPOVButton povSouth_xBox_CoDriver;
    public JoystickPOVButton povWest_xBox_CoDriver;
    public JoystickPOVButton povEast_xBox_CoDriver;
   


    public JoystickButton btn1_launchPad;
    public JoystickButton btn2_launchPad;
    public JoystickButton btn3_launchPad;
    public JoystickButton btn4_launchPad;
    public JoystickButton btn5_launchPad;
    public JoystickButton btn6_launchPad;
    public JoystickButton btn7_launchPad;
    public JoystickButton btn8_launchPad;
    public JoystickButton btn9_launchPad;
    public JoystickButton btn10_launchPad;
    public JoystickButton btn11_launchPad;
    
    public AndJoystickButton btn1_;
    public AndJoystickButton btn5_;
    public AndJoystickButton btn4_;
    
    public OrJoystickButton elevator_Down_BTN;
    public OrJoystickButton scissor_Scale;
    public OrJoystickButton scissor_Switch;
    public OrJoystickButton scissor_Down;
    
    
    
    /*
     *	 	LTa2						RTa3
     * 		LB5							LB5
     * 		  -
     * 	+xa0- ya1 LS9
     * 		  +
     * 									Y4
     * 		N			R6	S7		X3		B2
     * 	w		e						A1
     *		S					  
     *							  -
     *						+xa4- ya5 RS10
     *							  +
     */


    public OI() {

      xBox_Driver = new Joystick(0);
    	xBox_CoDriver = new Joystick(1);
    	launchPad = new Joystick(2);
    	
    	setUp_Controler_xBox_Driver();
    	setUp_Controler_xBox_CoDriver();
    	setUp_Controler_LaunchPad();
    	setUp_AND_Buttons(); //Do this after all joy sticks are declared.
    	setUp_OR_Buttons(); //Do this after all joy sticks are declared. 
    	
    	setUp_SmartDashboard_Buttons();
  
      limeLight = new LimeLight(); 
    }

    private void setUp_Controler_xBox_Driver(){

        a_xBox_Driver = new JoystickButton(xBox_Driver, GAMEPAD_XBOX_A_BUTTON);
        a_xBox_Driver.whenPressed(new Arm_To_Setpoint(Robot.arm_MM.STOWED));

        b_xBox_Driver = new JoystickButton(xBox_Driver, GAMEPAD_XBOX_B_BUTTON);
        //b_xBox_Driver.whenPressed(new Arm_To_Setpoint(Robot.arm_MM.));

        x_xBox_Driver = new JoystickButton(xBox_Driver, GAMEPAD_XBOX_X_BUTTON);
        //x_xBox_Driver.whenPressed(new);

        y_xBox_Driver = new JoystickButton(xBox_Driver, GAMEPAD_XBOX_Y_BUTTON);
        y_xBox_Driver.whileHeld(new Arm_To_Setpoint(Robot.arm_MM.CARGO_PICK_FLOOR));

        lb_xBox_Driver = new JoystickButton(xBox_Driver, GAMEPAD_XBOX_LEFT_BUTTON);
        lb_xBox_Driver.whenPressed(new CG_HatchGrabSeqStart());
        lb_xBox_Driver.whenReleased(new CG_HatchGrabSeqComplete());

        rb_xBox_Driver = new JoystickButton(xBox_Driver, GAMEPAD_XBOX_RIGHT_BUTTON);
        rb_xBox_Driver.whenPressed(new CG_HatchDeliver());
        rb_xBox_Driver.whenReleased(new CG_HatchDeliverSeqComplete());

        start_xBox_Driver = new JoystickButton(xBox_Driver, GAMEPAD_XBOX_START_BUTTON);
        start_xBox_Driver.whenPressed(new CG_Hatch_Pick_Prepair_From_Floor());

        reset_xBox_Driver = new JoystickButton(xBox_Driver, GAMEPAD_XBOX_RESET_BUTTON);
        reset_xBox_Driver.whenPressed(new CG_Hatch_Pick_From_Floor());

        r_Stick_Button_xbox_Driver = new JoystickButton(xBox_Driver, GAMEPAD_XBOX_RIGHT_STICK_BUTTON);
        r_Stick_Button_xbox_Driver.whenPressed(new Drive_Turn_To_Setpoint());

        l_Stick_Button_xbox_Driver = new JoystickButton(xBox_Driver, GAMEPAD_XBOX_LEFT_STICK_BUTTON);
        l_Stick_Button_xbox_Driver.whenPressed(new DriveToggleShifter());

        rt_xBox_Driver = new JoystickAxisButton(xBox_Driver, GAMEPAD_XBOX_RIGHT_TRIGGER);
        rt_xBox_Driver.whileHeld(new Cargo_Intake(1));

        lt_xBox_Driver = new JoystickAxisButton(xBox_Driver, GAMEPAD_XBOX_LEFT_TRIGGER);
        lt_xBox_Driver.whileHeld(new Cargo_Eject(-1));
        
        povNorth_xBox_Driver = new JoystickPOVButton(xBox_Driver, JoystickPOVButton.NORTH);
        //povNorth_xBox_Driver.whenPressed(new );

        povSouth_xBox_Driver = new JoystickPOVButton(xBox_Driver, JoystickPOVButton.SOUTH);
        //povSouth_xBox_Driver.

        povWest_xBox_Driver = new JoystickPOVButton(xBox_Driver, JoystickPOVButton.WEST);
        //povWest_xBox_Driver.

        povEast_xBox_Driver = new JoystickPOVButton(xBox_Driver, JoystickPOVButton.EAST);
        //povEast_xBox_Driver.
    }

    private void setUp_Controler_xBox_CoDriver(){

      //a_xBox_CoDriver = new JoystickButton(xBox_CoDriver, GAMEPAD_XBOX_A_BUTTON);
      //a_xBox_CoDriver.whileHeld(new Elevator_To_Setpoint(Robot.elevator_MM.get_bottom_Position()));
      
      b_xBox_CoDriver = new JoystickButton(xBox_CoDriver, GAMEPAD_XBOX_B_BUTTON);
      b_xBox_CoDriver.whileHeld(new Elevator_To_Setpoint(Robot.elevator_MM.get_Deliver_Hatch_Rocket_Position1()));
      
      x_xBox_CoDriver = new JoystickButton(xBox_CoDriver, GAMEPAD_XBOX_X_BUTTON);
      x_xBox_CoDriver.whileHeld(new Elevator_To_Setpoint(Robot.elevator_MM.get_Deliver_Hatch_Rocket_Position3()));
      
      y_xBox_CoDriver = new JoystickButton(xBox_CoDriver, GAMEPAD_XBOX_Y_BUTTON);
      y_xBox_CoDriver.whileHeld(new Elevator_To_Setpoint(Robot.elevator_MM.get_Deliver_Hatch_Rocket_Position2()));
      
      lb_xBox_CoDriver = new JoystickButton(xBox_CoDriver, GAMEPAD_XBOX_LEFT_BUTTON);
      //lb_xBox_CoDriver.whenPressed(new);
      
      rb_xBox_CoDriver = new JoystickButton(xBox_CoDriver, GAMEPAD_XBOX_RIGHT_BUTTON);
      //rb_xBox_CoDriver.whenPressed(new);
      
      start_xBox_CoDriver = new JoystickButton(xBox_CoDriver, GAMEPAD_XBOX_START_BUTTON);
      start_xBox_CoDriver.whenPressed(new CG_Hatch_Pick_Prepair_From_Floor());
      
      reset_xBox_CoDriver = new JoystickButton(xBox_CoDriver, GAMEPAD_XBOX_RESET_BUTTON);
      reset_xBox_CoDriver.whenPressed(new CG_Hatch_Pick_From_Floor());
      
      rJoystickUpDown_CoDriver = new JoystickAxisButton(xBox_CoDriver, GAMEPAD_XBOX_RIGHT_Y_AXIS);
      rJoystickUpDown_CoDriver.whileHeld(new Elevator_Jog_MotoinMagic(false));
      
      r_Stick_Button_xbox_CoDriver = new JoystickButton(xBox_CoDriver, GAMEPAD_XBOX_RIGHT_STICK_BUTTON);
      //r_Stick_Button_xbox_CoDriver.whenPressed(new);
      
      l_Stick_Button_xbox_CoDriver = new JoystickButton(xBox_CoDriver, GAMEPAD_XBOX_LEFT_STICK_BUTTON);
      //l_Stick_Button_xbox_CoDriver.whenPressed(new);

      rt_xBox_CoDriver = new JoystickAxisButton(xBox_CoDriver, GAMEPAD_XBOX_RIGHT_TRIGGER);
      //rt_xBox_CoDriver.whenPressed(new);
      
      lt_xBox_CoDriver = new JoystickAxisButton(xBox_CoDriver, GAMEPAD_XBOX_LEFT_TRIGGER);
      //lt_xBox_CoDriver.whenPressed(new);
      
      povNorth_xBox_CoDriver = new JoystickPOVButton(xBox_CoDriver, JoystickPOVButton.NORTH);
      //povNorth_xBox_CoDriver.
      
      povSouth_xBox_CoDriver = new JoystickPOVButton(xBox_CoDriver, JoystickPOVButton.SOUTH);
      //povSouth_xBox_CoDriver.
      
      povWest_xBox_CoDriver = new JoystickPOVButton(xBox_CoDriver, JoystickPOVButton.WEST);
      //povWest_xBox_CoDriver.
      
      povEast_xBox_CoDriver = new JoystickPOVButton(xBox_CoDriver, JoystickPOVButton.EAST);
      //povEast_xBox_CoDriver.
    }

    private void setUp_Controler_LaunchPad(){

      btn1_launchPad = new JoystickButton(launchPad,1);
      //btn1_launchPad.whenPressed(new HatchGripper_Grip());

      btn2_launchPad = new JoystickButton(launchPad,2);
      //btn2_launchPad.whenPressed(new HatchGripper_Ungrip());

      btn4_launchPad = new JoystickButton(launchPad,4);
      btn4_launchPad.whileHeld(new CG_Elevator_LVL3_Cargo());

      btn5_launchPad = new JoystickButton(launchPad,5);
      btn5_launchPad.whileHeld(new CG_Elevator_LVL2_Cargo());

      btn6_launchPad = new JoystickButton(launchPad,6);
      btn6_launchPad.whileHeld(new CG_Elevator_LVL1_Cargo());
      
      btn7_launchPad = new JoystickButton(launchPad,7);
      btn7_launchPad.whileHeld(new CG_Elevator_LVL3_Hatch());

      btn8_launchPad = new JoystickButton(launchPad,8);
      btn8_launchPad.whileHeld(new CG_Elevator_LVL2_Hatch());

      btn9_launchPad = new JoystickButton(launchPad,9);
      btn9_launchPad.whileHeld(new CG_Elevator_LVL1_Hatch());

    }

    private void setUp_AND_Buttons(){

    }

    private void setUp_OR_Buttons(){
      elevator_Down_BTN = new OrJoystickButton(xBox_Driver, GAMEPAD_XBOX_X_BUTTON, xBox_CoDriver, GAMEPAD_XBOX_A_BUTTON);
      elevator_Down_BTN.whenPressed(new Elevator_To_Setpoint(Robot.elevator_MM.get_bottom_Position()));
    }

    private void setUp_SmartDashboard_Buttons(){
      //SmartDashboard.putData("Arm UP", new Arm_To_Setpoint(5));
      //SmartDashboard.putData("Arm DWON", new Arm_To_Setpoint(0));
    }



    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getxBox_Driver() {
      return xBox_Driver;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS

    /**
     * Return Positive Value when Pushed UP
     * @return Y axis Value -1 to 1
     */
    public double co_Driver_Right_AxisY() {
    	// The  "Y" Axis of the Co_Driver Gamepad.
    	// However, the joysticks give -1.0 on the Y axis when pushed forward
    	// This method reverses that, so that positive numbers are forward
    	return -xBox_CoDriver.getRawAxis(GAMEPAD_XBOX_RIGHT_Y_AXIS);
    }
    
    public double driveRotation() {
       return stickDeadBand(-xBox_Driver.getRawAxis(GAMEPAD_XBOX_RIGHT_X_AXIS),.2);
    }
      
     public boolean driveTurboMode() {
       return false;//(xBox_Driver.getRawButton(GAMEPAD_XBOX_LEFT_BUTTON) || xBox_Driver.getRawAxis(GAMEPAD_XBOX_LEFT_TRIGGER)>0.2);
        
     }
    public boolean crawlBackward() {
        return false;//(xBox_Driver.getRawButton(GAMEPAD_XBOX_A_BUTTON));
           
     }

    public boolean crawlForward() {
      return false;//(xBox_Driver.getRawButton(GAMEPAD_XBOX_Y_BUTTON));
    }
      
    /*public boolean crawlLeft() {
      return(xBox_Driver.getRawButton(GAMEPAD_XBOX_B_BUTTON));
    }*/
      
    /*public boolean crawlRight() {
      return(xBox_Driver.getRawButton(GAMEPAD_XBOX_X_BUTTON));
    }*/

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

    public LimeLight my_LimeLight(){
      return limeLight;
    }

}

