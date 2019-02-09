package org.usfirst.frc3244.Jupiter2019.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3244.Jupiter2019.Robot;

/**
 *
 */
public class Cargo_Run extends Command {
     
    private double m_power;
 
    public Cargo_Run(double power) {
        m_power = power;
        requires(Robot.cargo_Intake);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.cargo_Intake.my_Intake_Run(m_power);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.cargo_Intake.my_Intake_Run(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
