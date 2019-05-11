package frc.robot;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap.ShuffleBoard;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Tower;


public class Robot extends TimedRobot {

 
  public static Drivetrain m_drive;
  public static Climber m_climber;
  public static Claw m_claw;
  public static Tower m_tower;
  public static OI m_oi;

  Thread visionThread = new Thread(() -> { 
    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    camera.setResolution(640, 320);
  
    CvSink cvSink = CameraServer.getInstance().getVideo();
    CvSource outStream = CameraServer.getInstance().putVideo("Alinhamento", 640, 320);
    
    boolean draw = true; // não condiz com a realidade, apenas teste
    int angle = 10; // não condiz com a realidade e será usado apenas para testes
    Mat mat = new Mat();
    
    while (!Thread.interrupted()) { 
      if (cvSink.grabFrame(mat) == 0) {
        // Send the output the error.
        outStream.notifyError(cvSink.getError());
        // skip the rest of the current iteration
        continue;
      }
      // inserir todo o código de alinhamento.
      outStream.putFrame(mat);
    }
  });
  public ShuffleBoard m_ShuffleBoard;

  @Override
  public void robotInit() {

    m_drive = new Drivetrain();
    m_climber = new Climber();
    m_tower = new Tower();
    m_claw = new Claw();
    m_oi = new OI();

    m_ShuffleBoard = new ShuffleBoard();

    RobotMap.Sensors.navX.reset();
  }

  @Override
  public void robotPeriodic() {
    m_ShuffleBoard.defAbsPosition();
    m_ShuffleBoard.defAbsAngleInTape();

  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {

  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    m_ShuffleBoard.setColorBooleans(); // updates color booleans for stopping tower's motor

    // data for the ShuffleBoard
    SmartDashboard.putNumber("x", RobotMap.Sensors.navX.getDisplacementX());
    SmartDashboard.putNumber("Z", RobotMap.Sensors.navX.getDisplacementZ());
    SmartDashboard.putNumber("Y", RobotMap.Sensors.navX.getDisplacementY());
    SmartDashboard.putNumber("Angle", RobotMap.Sensors.navX.getAngle());
    SmartDashboard.putNumber("pitch", RobotMap.Sensors.navX.getPitch());
    SmartDashboard.putNumber("yaw", RobotMap.Sensors.navX.getYaw());
    SmartDashboard.putNumber("roll", RobotMap.Sensors.navX.getRoll());

    SmartDashboard.putNumber("setpoint", Robot.m_drive.getSetpoint());
    SmartDashboard.putBoolean("optico", RobotMap.Sensors.optical.get());
    SmartDashboard.putNumber("DirAxis", m_oi.driverController.getRawAxis(5));
    SmartDashboard.putBoolean("LimitUp", RobotMap.Sensors.Elevator.limitUp.get());
    SmartDashboard.putBoolean("LimitDOWN", RobotMap.Sensors.Elevator.limitDown.get());

    SmartDashboard.putBoolean("LimitCargo", RobotMap.Sensors.Claw.CargoLimit.get());
    SmartDashboard.putBoolean("LimitHatch", RobotMap.Sensors.Claw.HatchPanelLimit.get());

    SmartDashboard.putNumber("eixo", Robot.m_oi.driverController.getRawAxis(5));

  }

  @Override
  public void testInit(){
  }

  @Override
  public void testPeriodic() {
    m_ShuffleBoard.setColorBooleans();
  }
}
