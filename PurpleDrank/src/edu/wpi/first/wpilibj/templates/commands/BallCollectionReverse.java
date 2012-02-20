/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.templates.subsystems.BallCollector;
import edu.wpi.first.wpilibj.templates.PurpleDrank;

/**
 * in case the robot picks up more than 3 balls, this command will reverse
 * the direction of the motor to eject a ball. 
 *
 * @author plevinson
 */
public class BallCollectionReverse extends CommandBase {
    private BallCollector ballCollection;
    
    public BallCollectionReverse() {
        super("Ball Collector Reverse");
        ballCollection = PurpleDrank.getBallCollector();
        requires(ballCollection);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        BallCollector.spitBalls();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
