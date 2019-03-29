/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.Jupiter2019.subsystems;

import org.usfirst.frc3244.Jupiter2019.Robot;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class RGB_LEDs extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static I2C _Wire = new I2C(Port.kOnboard,4);
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void set_myRGB(String WriteString){
    
    char[] CharArray = WriteString.toCharArray(); //Turns the message into an array
    byte[] WriteData = new byte[CharArray.length]; //Turns the array into a byte
    for (int i = 0; i < CharArray.length; i++) {
        WriteData[i] = (byte) CharArray[i];
    }

    _Wire.writeBulk(WriteData); //Send the message "xxx" to the Arduino

  }
  
}
