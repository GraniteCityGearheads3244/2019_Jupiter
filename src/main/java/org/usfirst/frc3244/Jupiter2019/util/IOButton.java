package org.usfirst.frc3244.Jupiter2019.util;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.buttons.Button;


/**
 *
 */
public class IOButton extends Button {

	private DigitalInput button;
	
	
	public IOButton(DigitalInput digitalInput){
		button = digitalInput;
	
	}
	
	/**
	 * Returns the status of a Normaly Open Switch connected to the RoboRio
	 */
    public boolean get() {
        return !button.get();
    }
}
