package frc.robot;

import java.awt.Color;
import java.util.Map;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.Relay;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardComponent;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is used to declare sensors, controllers and the shuffleboard.
 * Don't ask about the shuffleboard. Seriously, just don't.
 */
public class RobotMap {

  private static final int RELAY_PORT = 2;

  private static final int CLIMB_SPARK_RIGHT = 5;

  private static final int CLIMB_SPARK_LEFT = 4;

  private static final int OI_CONTROLLER_MECHANISM = 1;

  /**
   * Joystick port.
   */
  private static final int OI_CONTROLLER_DRIVER = 0;

  /**
   * Drivetrain motor port.
   */
  private static final int MOTOR_SPARK_FRONT_RIGHT = 0;

  /**
   * Drivetrain motor port.
   */
  private static final int MOTOR_SPARK_FRONT_LEFT = 2;

  /**
   * Drivetrain motor port.
   */
  private static final int MOTOR_SPARK_BACK_LEFT =  3;

  /**
   * Drivetrain motor port.
   */
  private static final int MOTOR_SPARK_BACK_RIGHT = 1;

  /**
   * VictorSPX port (cargo deployment).
   */
  private static final int CLAW_CARGO_VICTORSPX = 12;
  /**
   * VictorSPX port (claw mode).
   */
  private static final int CLAW_CONTROL_VICTORSP = 6;
  

  private static final int ELEVATOR_VICTORSPX = 14;

  private static final int CLIMB_SLEDGE_JAGUAR = 8;

  /**
   * Solenoid port (hatch panel deployment).
   */
  private static final int SOLENOID_DEPLOY_HATCH = 1;

  /**
   * Solenoid port (hatch panel deployment).
   */
  private static final int SOLENOID_DETRACT_HATCH = 0;

  private static final int LIMIT_CARGO = 6;

  private static final int LIMIT_HATCH_PANEL = 7;

  private static final int OPTICO = 0b000;
  
  /**
   * All the motor controllers are declared in this class
   */
  public static class Controllers { 

    /**
     * <p> All the motor controllers that should be used in the drivetrain are declared in this class </p>
     */
    public static class Drive { 

      /**
       * <p> All motor controllers for the left part of the drivetrain are declared in this class </p>
       * */ 
      public static class Left { 
        
        /**
         * <p> Left Frontal {@link Spark} </p>
         */
        private static Spark FrontSpark = new Spark(MOTOR_SPARK_FRONT_LEFT);

        /**
         * <p> Left Back {@link Spark} </p>
         */
        private static Spark BackSpark = new Spark(MOTOR_SPARK_BACK_LEFT);
        
        /**
         * <p> This {@link SpeedControllerGroup} makes both motor controllers run at the same time </p>
         * */
        public static final SpeedControllerGroup Group = new SpeedControllerGroup(BackSpark, FrontSpark);

      }

      /**
       * <p> All motor controllers for the right part of the drivetrain are declared in this class </p>
       */
      public static class Right { 
        /**
         * <p> Right Frontal {@link Spark} </p>
         */
        private static Spark FrontSpark = new Spark(MOTOR_SPARK_FRONT_RIGHT);

        /**
         * <p> Right Back {@link Spark} </p>
         */
        private static Spark BackSpark = new Spark(MOTOR_SPARK_BACK_RIGHT);

        /**
         * <p> This {@link SpeedControllerGroup} makes both motor controllers run at the same time </p>
         */
        public static final SpeedControllerGroup Group = 
          new SpeedControllerGroup(BackSpark, FrontSpark); 

      }
    }

    /**
     * <p> All the motor controllers for the climbing mechanism are declared in this class </p>
     */
    public static class Climb { 

      /**
       * <p> Left {@link VictorSPX} </p>
       */
      private static final Spark LeftSpark = new Spark(CLIMB_SPARK_LEFT);

      /**
       * <p> Right {@link VictorSPX} </p>
       */
      private static final Spark RightSpark = new Spark(CLIMB_SPARK_RIGHT);

      //RightSpark.setInverted(true);

      public static final SpeedControllerGroup Group = 
        new SpeedControllerGroup(LeftSpark, RightSpark);

      public static final Jaguar Sledge = new Jaguar(CLIMB_SLEDGE_JAGUAR);

    }

    /**
     * <p> All The motor controllers for the Claw mechanism are declared in this class </p>
    */
    public static class Claw { 
      /**
      * Motor for controlling the cargo mechanism.
      */
      public static final VictorSPX IntakeVictorSPX = new VictorSPX(CLAW_CARGO_VICTORSPX);

      public static final VictorSP  ControlVictorSP = new VictorSP(CLAW_CONTROL_VICTORSP);
	  
	  
      /**
        * <p> {@link DoubleSolenoid} for the Piston </p>
      */
      public static final DoubleSolenoid solenoidHatch = new DoubleSolenoid(SOLENOID_DEPLOY_HATCH, SOLENOID_DETRACT_HATCH);
      
      public static final Relay relaySolenoid = new Relay(RELAY_PORT);
   
	  }

    /**
     * <p> All the motor controllers for the Elevator mechanism are declared in this class </p>
     */
    public static class Tower {

      public static final VictorSPX ElevatorVictorSPX = new VictorSPX(ELEVATOR_VICTORSPX);

    }

  }
  
  /**
   * <p> All robot sensors are declared in this class </p>
   */
  public static class Sensors { 
    /**
     * <p> NavX for mapping the arena </p>
     */
    public static final AHRS navX = new AHRS(SPI.Port.kMXP);

    /**
     * <p> Optical Sensor for detecting the white lines in the arena. </p>
     */
    public static final DigitalInput optical = new DigitalInput(OPTICO);

    public static class Claw{

      	public static final DigitalInput CargoLimit = new DigitalInput(LIMIT_CARGO);
      	public static final DigitalInput HatchPanelLimit = new DigitalInput(LIMIT_HATCH_PANEL);

    }

    public static class Elevator{

      	public static final Encoder heightEncoder = new Encoder(3,2);

        public static final DigitalInput limitUp = new DigitalInput(8);
        public static final DigitalInput limitDown = new DigitalInput(9);

    }

    public static class Drive{

      /*
    	public static final Encoder leftEncoder = new Encoder(new DigitalInput(4),
                                                      new DigitalInput(5),
                                                      new DigitalInput(0b000),
                                                      true);

      public static final Encoder rightEncoder = new Encoder(new DigitalInput(4),
                                                      new DigitalInput(5),
                                                      new DigitalInput(0b000),
                                                      true);                                                 

      public static void initEncoder() {

        leftEncoder.setDistancePerPulse(0.0312);
        rightEncoder.setDistancePerPulse(0.0312);

      }
*/      
    }

    /**
     * <p> Start the {@link Encoder} with the correct distance per pulse. </p> 
     */
    
  }

  /**
   * <p> The {@link Joystick} controller is declared in this class </p>
   * @see frc.robot.OI
   */
  public static class OI { 

    /**
     * <p> {@link Joystick} instance that the driver uses </p>
     * @see frc.robot.OI
     */
    public static final Joystick driverController = new Joystick(RobotMap.OI_CONTROLLER_DRIVER);
    public static final Joystick mechanismController = new Joystick(RobotMap.OI_CONTROLLER_MECHANISM);
  }

  /** 
   * <p> This class holds all the interactions with the pilot and robot interface, using as a base the {@link ShuffleBoard} and the {@link SmartDashBoard} to send the data </p>
  */
  public static class ShuffleBoard { 
    
    private NetworkTableEntry
      BlackEntry = tabOptico.add("Detect Black", false).withWidget("Boolean Box").getEntry(),
      RedEntry = tabOptico.add("Detect Red", false).withWidget("Boolean Box").getEntry(),
      BlueEntry = tabOptico.add("Detect Blue", false).withWidget("Boolean Box").getEntry(),
      GreenEntry = tabOptico.add("Detect Green", false).withWidget("Boolean Box").getEntry();

    public static boolean red, black, green, aluminium;

    public void setColorBooleans() { 
      red = RedEntry.getBoolean(false);
      aluminium = BlueEntry.getBoolean(false);
      green = GreenEntry.getBoolean(false);
      black = BlackEntry.getBoolean(false); 
    }

    /** 
     * <p> Used to adjust the image of the minimap, because of it`s crop having the alliance walls that is not needed for the minimap </p>
     * <p> This number change every year, so is needed to adjust it </p>
     * */ 
    private double[] positionErrorInImage = {0.0,0.0};

    /** 
     * <p> the initial X positions that the robot can be placed </p>
     * <p> This number change every year, so is needed to adjust it </p>
     * */
    private double[] initialsPositionX = { 296.17, 397.77, 425.19, 526.79 };

    /**
    * <p> the initial Y position that the robot can be placed </p>
    * <p> This number change every year, so is needed to adjust it </p>
    * */
    private double[] initialsPositionY = { 121.9 };

    /**
     * <p> A {@link SendableChooser} for the robot start position in x, the start position being the plataform on the habitat </p>
     * <p> In the most of the time, this chooser is the same </p>
     *  */
    private SendableChooser<Double> sChooserPositionX = new SendableChooser<>();

    /**
     * <p> A {@link SendableChooser } with values of x if the robot isn`t aligned correctly with the <code> sChooserPositionX </code> </p>
     * <p> In the most of the time, this chooser is the same </p>
     *  */
    private SendableChooser<Integer> sChooserDistX = new SendableChooser<>();

    /**
     * <p> A {@link SendableChooser} to select the left or right of the robot to adjust with <code> sChooserDistX </code> </p>
     * <p> In the most of the time, this chooser is the same </p>
     *  */
    private SendableChooser<Integer> sChooserDirectionX = new SendableChooser<>();

    /**
     * <p> Create a {@link ShuffleBoardTab} to be used to put coherent data with the tab name </p>
     */
    public static final ShuffleboardTab tabAngles = Shuffleboard.getTab("Angles"), 
                                          tabTest = Shuffleboard.getTab("Map"),
                                          tabVision = Shuffleboard.getTab("Vision"),
                                          tabOptico = Shuffleboard.getTab("Optico");

    /**
     * <p >A tab with data about angle of the white line for autonomous </p>
     * <p> This data changes every year too </p>
     *  */
    public static NetworkTableEntry
        /**
         * 45 angle chooser.
         */
        chooser1 = tabAngles.add("45°", true).getEntry(),
        /**
         * 90 angle chooser.
         */
        chooser2 = tabAngles.add("90°", false).getEntry(), 
        /**
         * 135 angle chooser.
         */
        chooser3 = tabAngles.add("135°", false).getEntry(),
        /**
         * 180 angle chooser.
         */
        chooser4 = tabAngles.add("180°", false).getEntry(), 
        /**
         * Chooser that shows the currently selected angle.
         */
        chooser5 = tabAngles.add("Angle", 45).getEntry();

    /**
     * <p >A tab with data about the distance of the robot in function of time = an distance from point 0</p>
     * <p> This data changes every year too </p>
     *  */
    public static NetworkTableEntry   
        /**
         * Graph for showing the robot's X position on field.
         */
        xPosition = tabTest.add("X", 0.0).withWidget(BuiltInWidgets.kGraph).withProperties(Map.of("min", 0, "max", 1646)).getEntry(),
        /**
         * Graph for showing the robot's Y position on field.
         */
        yPosition = tabTest.add("Y", 0.0).withWidget(BuiltInWidgets.kGraph).withProperties(Map.of("min", 0, "max", 823)).getEntry(),
        /**
         * Simple entry for showing the gyroscope angle (navX).
         */
        gyroAngle = tabTest.add("Angle",0.0).getEntry(),
        /**
         * @todo Understand what this does.
         */
        anglePilot = tabVision.add("anglePilot", 0.0).getEntry();

    private int lastDist;
    /**
     * <p> The constructor will set all the {@link SendableChooser} in the {@link SmartDashboard} and read them in {@link ShuffleBoard} </p>
     * <p> This data change every year too </p>
     */
    public ShuffleBoard() {
      // Set robot initial position options
      sChooserPositionX.addOption("Left Level 2", initialsPositionX[0]);
      sChooserPositionX.addOption("Left Level 3", initialsPositionX[1]);
      sChooserPositionX.addOption("Right Level 3", initialsPositionX[2]);
      sChooserPositionX.addOption("Right Level 2", initialsPositionX[3]);

      // Set robot off set options
      sChooserDistX.setDefaultOption("0", 0);
      sChooserDistX.addOption("1", 1);
      sChooserDistX.addOption("2", 2);
      sChooserDistX.addOption("3", 3);
      sChooserDistX.addOption("4", 4);
      sChooserDistX.addOption("5", 5);
      sChooserDistX.addOption("6", 6);
      sChooserDistX.addOption("7", 7);
      sChooserDistX.addOption("8", 8);
      sChooserDistX.addOption("9", 9);
      sChooserDistX.addOption("10", 10);
      sChooserDistX.addOption("11", 11);
      sChooserDistX.addOption("12", 12);
      sChooserDistX.addOption("13", 13);
      sChooserDistX.addOption("14", 14);
      sChooserDistX.addOption("15", 15);

      // Set robot off set directions options
      sChooserDirectionX.addOption("Direita", 1);
      sChooserDirectionX.addOption("Esquerda", -1);

      //start the encoder
     // Sensors.Drive.initEncoder();

      // Put all in ShuffleBoard with SmartDashboard
      SmartDashboard.putData("RefPos", sChooserPositionX);
      SmartDashboard.putData("RefDist", sChooserDistX);
      SmartDashboard.putData("RefDir", sChooserDirectionX);
     // SmartDashboard.putData("EncLeft", Sensors.Drive.leftEncoder);      
      //SmartDashboard.putData("EncLeft", Sensors.Drive.rightEncoder);      
      //SmartDashboard.putData("EncLeft", Sensors.Elevator.heightEncoder);
      lastDist = 0;  
    }
    /** 
     * <p> The configuration that we need are put by the driver team before the match start. </p>
     * <p> This method will send to ShuffleBoard the robot initial position when the match start.</p>
     * <p> The {@link ShuffleBoard} store these datas and send to robot to calculate the initial postion</p>
    */
    public void getInitialPosition() {
      double X = sChooserPositionX.getSelected() + (sChooserDistX.getSelected() * sChooserDirectionX.getSelected())+positionErrorInImage[0];
      double Y = initialsPositionY[0]+positionErrorInImage[1];
      xPosition.setDouble(X);
      yPosition.setDouble(Y);      
    }
    /**
     * <p> This method returns to the {@link ShuffleBoard} the absolute position from all sensors </p>
     * <p> This method needs to be executed for the entire duration of the match so we don`t lose the encoders reference. </p>
     */
    public void defAbsPosition() {
      double sin = Math.sin(Math.toRadians(Sensors.navX.getPitch()));
      double cos = Math.cos(Math.toRadians(Sensors.navX.getPitch()));
      double dist = 0b000;//Sensors.encoder.getDistance() - lastDist;
      double x = cos * dist;
      xPosition.setDouble(x);
      double y = sin * dist;
      yPosition.setDouble(y);
      gyroAngle.setDouble(Sensors.navX.getPitch());
    }
    /**
     * Defines the absolute angle from the robot to the vision tape (rocket or cargo ship).
     * This is defined by the position the robot started in.
     */
    public void defAbsAngleInTape(){
      double anglefinal = Sensors.navX.getPitch() - chooser5.getNumber(45).doubleValue();
      anglePilot.setDouble(anglefinal);
    }

  }

  
}