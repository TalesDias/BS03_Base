package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.commands.*;
/**
 * <p> the  {@link OI} is used to map the joystic to the controller </p>
 */
public class OI {

    public final Joystick driverController = RobotMap.OI.driverController;
    public final Joystick mechanismController = RobotMap.OI.mechanismController;

    // We named the variables based on the xbox controller buttons
    Button buttonA = new JoystickButton(mechanismController, 1);
    Button buttonB = new JoystickButton(mechanismController, 2);
    Button buttonX = new JoystickButton(mechanismController, 3);
    Button buttonY = new JoystickButton(mechanismController, 4); 
    Button buttonLB = new JoystickButton(mechanismController, 5);
    Button buttonRB = new JoystickButton(mechanismController, 6);
    Button buttonBack = new JoystickButton(mechanismController, 7);
    
    private static int angle = 45;

    public static void setAngle(int angle) {
        OI.angle = angle;
    } 

    public static int getAngle(){
        return OI.angle;
    }

    /**
     * <p> Sets the {@link Button} Config and what method it activates in what state </p>
     */
    public OI(){

        //Escalada
        buttonRB.whileHeld(new Ascend());
        buttonRB.whenReleased(new StopClimb());

        buttonLB.whileHeld(new Descend());
        buttonLB.whenReleased(new StopClimb());

        buttonBack.whenPressed(new ReleaseSki());

        //Garra
        buttonY.whileHeld(new ForwardRelay());
        buttonY.whenReleased(new BackwardsRelay());
        
        buttonB.whileHeld(new DropCargo()); 
        buttonB.whenReleased(new StopCargo());

        buttonA.whileHeld(new PullCargo());  
        buttonA.whenReleased(new StopCargo());
    }
}
