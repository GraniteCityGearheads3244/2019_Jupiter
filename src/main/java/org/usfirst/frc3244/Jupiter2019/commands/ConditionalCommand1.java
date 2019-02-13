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

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import org.usfirst.frc3244.Jupiter2019.Robot;


/**
 *
 */
public class ConditionalCommand1 extends ConditionalCommand {


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public ConditionalCommand1() {
      super(new Cargo_Run(1), new Hatch_Slide(true));

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR

    }

    @Override
    protected boolean condition(){
        return Robot.eLevator_MotionMagic.getCurrent_GameMode() == "CARGO";//TODO: Auto Generated method stub
    }
}
