/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.MoveTower;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;


public class Tower extends Subsystem {
  

  public VictorSPX victorSPX;
  public DigitalInput limitUp;
  public DigitalInput limitDown;
  public Encoder heightEncoder;
  

  public Tower(){
  
    victorSPX = RobotMap.Controllers.Tower.ElevatorVictorSPX;

    limitUp = RobotMap.Sensors.Elevator.limitUp;
    limitDown = RobotMap.Sensors.Elevator.limitDown;  
    
    
    //heightEncoder = RobotMap.Sensors.Elevator.heightEncoder;

    //heightEncoder.setDistancePerPulse(.0312);
    //heightEncoder.reset();

  }

  
  public void move(double vel){

    if ((RobotMap.ShuffleBoard.red) || (RobotMap.ShuffleBoard.black)) {
      victorSPX.set(ControlMode.PercentOutput,vel*0.2);
    } else if (RobotMap.ShuffleBoard.green) { 
      stop();
    } else {
      victorSPX.set(ControlMode.PercentOutput, vel);
    }
    
    /*
    if(heightEncoder.getDistance() < 5 && vel < 0){

      if(limitDown.get()){
        stop();
        return;
      }

      victorSP.set(vel * 0.5);

    } else if(heightEncoder.getDistance() > 95 && vel > 0){

      if (limitDown.get()) {
        stop();
        return;
      }

      victorSP.set(vel * 0.5);

    } else {

      victorSP.set(vel);

    }
    */
  }
  
  public void stop() {

    victorSPX.set(ControlMode.PercentOutput, 0);

  }


  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MoveTower()); 
   }
}