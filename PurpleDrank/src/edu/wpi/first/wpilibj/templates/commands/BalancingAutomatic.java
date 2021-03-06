/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST Team 2035, 2012. All Rights Reserved.                  */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.templates.PurpleDrank;
import edu.wpi.first.wpilibj.templates.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author abbottk
 */
public class BalancingAutomatic extends PIDCommand{

    private DriveTrain DriveTrain;
    private Gyro gyro1;
    
    
    public BalancingAutomatic(double Kp, double Ki, double Kd){
        super("AutoBalancing", Kp, Ki, Kd);
        this.DriveTrain = PurpleDrank.getDriveTrain(); 
        this.gyro1 = this.DriveTrain.getGyro1();
        requires(this.DriveTrain);
        
    }
    protected double returnPIDInput() {
        DriveTrain.getCommandLog().setInputs("" + gyro1.getAngle());
        return -gyro1.getAngle();
        
        
    }

    protected void usePIDOutput(double output) {
        DriveTrain.drive(output);
        
    }

    protected void initialize() {
        DriveTrain.getCommandLog().setCommand(this.getName());
        DriveTrain.disableSafety();
        
        
        
    }

    protected void execute() {
        DriveTrain.setMetaCommandOutputs();
        
    }

    protected boolean isFinished() {
        
        if(RobotMap.dButton3.get() || PurpleDrank.getIsDisabled()){
            return true;
        }    
        return false;
            
    }

    protected void end() {
        DriveTrain.enableSafety();
    }

    protected void interrupted() {
    }
    
}
