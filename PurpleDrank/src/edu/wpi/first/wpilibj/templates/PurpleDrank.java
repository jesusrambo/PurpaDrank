/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST Team 2035, 2012. All Rights Reserved.                  */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.team2035.meta.MetaLog;
import edu.team2035.meta.MetaTCPVariables;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.ExampleCommand;
import edu.wpi.first.wpilibj.templates.commands.ManualBalancing;
import edu.wpi.first.wpilibj.templates.subsystems.*;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import edu.team2035.meta.MetaTimer;
import edu.wpi.first.wpilibj.templates.commands.TargetSorting;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class PurpleDrank extends IterativeRobot {

    Command autonomousCommand;
    private static DriveTrain DriveTrain;
    private static HorizontalTurretAxis HorizontalAxis;
    private static VerticalTurretAxis VerticalAxis;
    private static Shooter shooterController;
    private MetaTimer timer;
    private MetaTCPVariables metaTable;
    private ManualBalancing h;
    private static DriverStationLCD display;
    private static boolean isDisabled;
    
    
    public static DriveTrain getDriveTrain(){
        
        return DriveTrain;
    }
    
    public static HorizontalTurretAxis getHorizontalTurretAxis(){
        
        return HorizontalAxis;
    }
    public static VerticalTurretAxis getVerticalTurretAxis(){
        
        return VerticalAxis;
    }
    
    public static Shooter getShooterController(){
        return shooterController;
    }
    
    public static boolean getIsDisabled(){
        return isDisabled;
    }    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // instantiate the command used for the autonomous period
        timer = new MetaTimer();
        autonomousCommand = new ExampleCommand();
        DriveTrain = new DriveTrain();
        HorizontalAxis = new HorizontalTurretAxis();
        VerticalAxis = new VerticalTurretAxis();   
        shooterController = new Shooter();
        metaTable = new MetaTCPVariables();
        display = DriverStationLCD.getInstance();
        display.updateLCD();
        display.println(Line.kMain6, 1, "Initializing...                ");
        display.println(Line.kUser2, 1, "                               ");
        display.println(Line.kUser3, 1, "                               ");
        display.println(Line.kUser4, 1, "                               ");
        display.println(Line.kUser5, 1, "                               ");
        display.println(Line.kUser6, 1, "                               ");
        display.updateLCD();
        metaTable = OI.getMdu();
        display.updateLCD();

        // Initialize all subsystems
        //CommandBase.init();
    }
    public void disabledInit(){
        
        System.out.println("Entering disabled...");
    }
    public void disabledPeriodic(){
        //metaTable.update();
        MetaLog.closeLog();
        isDisabled = true;
 
        display.updateLCD();
        
        display.println(Line.kUser2, 1, "" + truncate(metaTable.dataMessage[0]) + ", " + metaTable.getConnections() + "            ");
        display.println(Line.kUser3, 1, "" + truncate(metaTable.dataMessage[1]) + ", " + truncate(metaTable.dataMessage[2]) + "            ");
        display.println(Line.kUser4, 1, "" + truncate(metaTable.dataMessage[3]) + ", " + truncate(metaTable.dataMessage[4]) + "            ");
        display.println(Line.kUser5, 1, "" + truncate(metaTable.dataMessage[5]) + ", " + truncate(metaTable.dataMessage[6]) + "            ");
        display.println(Line.kUser6, 1, "" + truncate(metaTable.dataMessage[7]) + ", " + truncate(metaTable.dataMessage[8]) + "            ");
        display.println(Line.kMain6, 1, "Program is running...");
        display.updateLCD();
    }
    public void autonomousInit() {
        // schedule the autonomous command (example)
        autonomousCommand.start();
        System.out.println("Entering Autonomous...");              
        display.println(Line.kUser2, 1, "                               ");
        display.println(Line.kUser3, 1, "                               ");
        display.println(Line.kUser4, 1, "                               ");
        display.println(Line.kUser5, 1, "                               ");
        display.println(Line.kUser6, 1, "                               ");
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to 
		// continue until interrupted by another command, remove
		// this line or comment it out.
		autonomousCommand.cancel();
                OI.initialize();
                TargetSorting t = new TargetSorting();
                //t.start();
                isDisabled = false;
                System.out.println("Entering TeleOp...");              
                display.println(Line.kUser2, 1, "                               ");
                display.println(Line.kUser3, 1, "                               ");
                display.println(Line.kUser4, 1, "                               ");
                display.println(Line.kUser5, 1, "                               ");
                display.println(Line.kUser6, 1, "                               ");
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        MetaLog.update();
        Scheduler.getInstance().run();
        
        display.println(Line.kUser2, 1, "" + truncate(metaTable.dataMessage[0]) + ", " + metaTable.getConnections() + "            ");
        display.println(Line.kUser3, 1, "" + truncate(metaTable.dataMessage[1]) + ", " + truncate(metaTable.dataMessage[2]) + "            ");
        display.println(Line.kUser4, 1, "" + truncate(metaTable.dataMessage[3]) + ", " + truncate(metaTable.dataMessage[4]) + "            ");
        display.println(Line.kUser5, 1, "" + truncate(metaTable.dataMessage[5]) + ", " + truncate(metaTable.dataMessage[6]) + "            ");
        display.println(Line.kUser6, 1, "" + truncate(metaTable.dataMessage[7]) + ", " + truncate(metaTable.dataMessage[8]) + "            ");
        display.println(Line.kMain6, 1, "Program is running...");
        display.updateLCD();
    }
    
    public double truncate(double d){
        
        int temp = (int)(d*1000);
        double result = (double)temp/1000;
        return result;
    }
}
