/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * This command was made to drive the robot.
 */

public class Drive extends Command {

  /**
   * This command was made to drive the robot.
   */
  public Drive() {
    requires(Robot.m_drive);
  }

  /**
   * <p> mo needed initialization </p>
   */
  @Override
  protected void initialize() {

  }

  /**
   * <p> sets the robot`s x and y value to that of the OI </p>
   */
  
  @Override
  protected void execute() {
    double mover = Robot.m_oi.driverController.getY(); 
    double girar = Robot.m_oi.driverController.getX();
     
    // The motor controller values are based on the joystick analog's axis.
    Robot.m_drive.arcadeDrive(mover,girar);
    
  }

  
  /**
   * <p> returns false so it doesn`t execute more than once </p> 
   * @return boolean false so it doesn`t execute more than once 
   */
  @Override
  protected boolean isFinished() {
    return false; 
  }

  /**
   * <p> stop the the motors from running </p>
   * <p> only activates when it`s re-called </p>
   */
  @Override
  protected void end() {
    Robot.m_drive.arcadeDrive(0, 0);
  }

  /**
   * <p> stop the motors from running. </p>
   * <p> this happens to prevent any error or problem that may happen </p>
   */
  @Override
  protected void interrupted() {
    end();
  }
}
