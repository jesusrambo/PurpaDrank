/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST Team 2035, 2012. All Rights Reserved.                  */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.team2035.meta.MetaTCPVariables;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.templates.commands.*;
/**
 * 
 * @author Team 2035 Programmers
 */
public class OI {
    // Process operator interface input here.
    private static Button Balance;
    private static Button AutoBalance;
    private static Button manualShooter;
    private static MetaTCPVariables mdu;
    private static Button endAutoBalance;
    private static Button RampUp;
    private static Button RampDown;
    private static Button ballCollector;
    private static Button ShooterRealignRight;
    private static Button ShooterRealignLeft;
    private static Button ShooterRealignCenter;
    private static Button speedSetterUp;
    private static Button speedSetterDown;
    private static Button elevatorUpButton;
    private static Button relayTest;
    private static Button elevatorDownButton;
    private static Button ballSpitter;;
    private static Button HorTurretFwd;
    private static Button HorTurretRev;
    private static Button VerTurretFwd;
    private static Button VerTurretRev;
    private static Button autoTargeter;
    private static Button autoTargeterDisable;
    
    
    public static void initialize() {
        
        mdu = new MetaTCPVariables();
        
        Balance = RobotMap.dButton2;
        Balance.whileHeld(new BalancingManual());
        Balance.whenReleased(new BalancingAutomatic(RobotMap.AutoBalKp, RobotMap.AutoBalKi, RobotMap.AutoBalKd));        
        
        //AutoBalance = RobotMap.dButton3;
        //AutoBalance.whenDoublePressed(new BalancingAutomatic(RobotMap.AutoBalKp, RobotMap.AutoBalKi, RobotMap.AutoBalKd));
        
        manualShooter = RobotMap.shootTrigger;
        //manualShooter.whileHeld(new VerticalDefaultCommand());
        //manualShooter.whileHeld(new HorizontalDefaultCommand());
        //manualShooter.whenTriplePressed(new ShooterSpeed(RobotMap.shooterKp, RobotMap.shooterKi, RobotMap.shooterKd));
        
        ballCollector = RobotMap.dButton5;
        ballCollector.whenPressed(new BallCollectionOn());
        ballCollector.whenReleased(new BallCollectionOff());
        
        ballSpitter = RobotMap.dButton4;
        ballSpitter.whenPressed(new BallCollectionReverse());
        ballSpitter.whenReleased(new BallCollectionOff());
        
        elevatorUpButton = RobotMap.shootButton3;
        elevatorUpButton.whenPressed(new ElevatorUp());
        elevatorUpButton.whenReleased(new ElevatorOff());
        
        elevatorDownButton = RobotMap.shootButton2;
        elevatorDownButton.whenPressed(new ElevatorDown());       
        elevatorDownButton.whenReleased(new ElevatorOff());

        ShooterRealignRight = RobotMap.shootButton5;
        ShooterRealignRight.whenReleased(new SetpointRealignment(RobotMap.realignRight));
        
        ShooterRealignLeft = RobotMap.shootButton4;
        ShooterRealignLeft.whenReleased(new SetpointRealignment(RobotMap.realignLeft));
        
        ShooterRealignCenter = RobotMap.shootButton10;
        ShooterRealignRight.whenReleased(new SetpointRealignment(RobotMap.realignCenter));
        
        RampUp = RobotMap.dButton8;
        RampDown = RobotMap.dButton9;
        // below code tried to do this in one button but it wasnt working.
        // so i swapped it out for two buttons.
        //RampUp.whenPressed(new ArmDown());
        //RampUp.whenTriplePressed(new ArmUp());
        RampUp.whileHeld(new ArmUp());
        RampDown.whileHeld(new ArmDown());

        
        speedSetterUp = RobotMap.shootButton6;
        speedSetterDown = RobotMap.shootButton7;
        speedSetterUp.whileHeld(new ChangeShooterSpeed('+'));
        speedSetterDown.whileHeld(new ChangeShooterSpeed('-'));
        speedSetterUp.whenReleased(new ChangeShooterSpeed(' '));
        speedSetterDown.whenReleased(new ChangeShooterSpeed(' '));
        speedSetterUp.whenTriplePressed(new ChangeShooterSpeed('r'));
        speedSetterDown.whenTriplePressed(new ChangeShooterSpeed('r'));
        
        HorTurretFwd = RobotMap.shootButton11;
        HorTurretFwd.whileHeld(new HorizontalTurretRotation(RobotMap.HorTurretKp, RobotMap.HorTurretKi, RobotMap.HorTurretKd));
        HorTurretFwd.whenReleased(new HorizontalTurretRotationManual());
        
        //change thsi to be like horturret, default automatic off, use joysticks
        //Modified to work with joysticks instead of butans
        //VerTurretFwd = RobotMap.shootButton11;
        //VerTurretRev = RobotMap.shootButton10;
        //VerTurretFwd.whileHeld(new VerticalTurretRotationManual(1));
        //VerTurretRev.whileHeld(new VerticalTurretRotationManual(-1));
        
        autoTargeter = RobotMap.shootButton8;
        autoTargeterDisable = RobotMap.shootButton9;
        autoTargeter.whenPressed(new HorizontalTurretRotation(RobotMap.HorTurretKp, RobotMap.HorTurretKi, RobotMap.HorTurretKd));
        autoTargeterDisable.whenPressed(new HorizontalTurretRotationManual());
    }
    
    public static Button getButton3(){
        return AutoBalance;
    }
    
    public static Joystick getJoystick1(){
        return RobotMap.dStick;
    }
    
    public static Joystick getJoystick2(){
        return RobotMap.shootStick;
    }
    
    public static MetaTCPVariables getMdu(){
        return mdu;
    }
    
}