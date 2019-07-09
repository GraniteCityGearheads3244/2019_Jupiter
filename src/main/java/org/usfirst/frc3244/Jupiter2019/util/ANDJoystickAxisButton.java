/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc3244.Jupiter2019.util;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 *
 * @author Noel
 */
public class ANDJoystickAxisButton extends Button {
    public static final int BOTH_WAYS = 1;
    public static final int POSITIVE_ONLY = 2;
    public static final int NEGATIVE_ONLY = 3;
    
    private static final double AXIS_THRESHOLD = 0.2;
    
    
    
    private Joystick joystick;
    private Joystick.AxisType axis;
    private int axisInt;
    private int direction;
    private Joystick joystick1;
    private int buttonNumber1;
    
    public ANDJoystickAxisButton(Joystick stick1, int buttonNumber1, Joystick stick2, Joystick.AxisType axis) {
        this(stick2, axis, BOTH_WAYS);
    }
    
    public ANDJoystickAxisButton(Joystick stick, Joystick.AxisType axis, int direction) {
        joystick = stick;
        this.axis = axis;
        this.direction = direction;
    }
    
    public ANDJoystickAxisButton(Joystick stick, int axis) {
        this(stick, axis, BOTH_WAYS);
    }
    
    public ANDJoystickAxisButton(Joystick stick, int axis, int direction) {
        joystick = stick;
        this.axis = null;
        axisInt = axis;
        this.direction = direction;
    }
    
    public boolean get() {
        switch (direction) {
            case BOTH_WAYS:
                if (axis != null) {
                    return Math.abs(joystick.getAxis(axis)) > AXIS_THRESHOLD && joystick1.getRawButton(buttonNumber1);
                }
                else {
                    return Math.abs(joystick.getRawAxis(axisInt)) > AXIS_THRESHOLD  && joystick1.getRawButton(buttonNumber1);
                }
            
            case POSITIVE_ONLY:
                if (axis != null) {
                    return joystick.getAxis(axis) > AXIS_THRESHOLD  && joystick1.getRawButton(buttonNumber1);
                }
                else {
                    return joystick.getRawAxis(axisInt) > AXIS_THRESHOLD  && joystick1.getRawButton(buttonNumber1);
                }
                
            case NEGATIVE_ONLY:
                if (axis != null) {
                    return joystick.getAxis(axis) < -AXIS_THRESHOLD  && joystick1.getRawButton(buttonNumber1);
                }
                else {
                    return joystick.getRawAxis(axisInt) < -AXIS_THRESHOLD  && joystick1.getRawButton(buttonNumber1);
                }
        }
        
        return false;
    }
}
