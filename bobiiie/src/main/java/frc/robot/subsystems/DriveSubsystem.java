package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

/**
 * Subsystem for a four-motor differential drivetrain.
 */
public class DriveSubsystem extends SubsystemBase {
  // Front motors act as leaders for each side.
  private final SparkMax m_leftFrontMotor =
      new SparkMax(DriveConstants.kLeftFrontCanId, MotorType.kBrushless);
  private final SparkMax m_leftRearMotor =
      new SparkMax(DriveConstants.kLeftRearCanId, MotorType.kBrushless);
  private final SparkMax m_rightFrontMotor =
      new SparkMax(DriveConstants.kRightFrontCanId, MotorType.kBrushless);
  private final SparkMax m_rightRearMotor =
      new SparkMax(DriveConstants.kRightRearCanId, MotorType.kBrushless);

  // DifferentialDrive uses the two leader motors.
  private final DifferentialDrive m_differentialDrive =
      new DifferentialDrive(m_leftFrontMotor, m_rightFrontMotor);

  /** Configures all Spark MAX controllers and follower relationships. */
  public DriveSubsystem() {
    configureMotors();
  }

  private void configureMotors() {
    // Shared leader configuration: reset to defaults, apply limits/mode/inversion, and persist.
    SparkMaxConfig leftLeaderConfig = new SparkMaxConfig();
    leftLeaderConfig
        .idleMode(DriveConstants.kIdleMode)
        .smartCurrentLimit(DriveConstants.kSmartCurrentLimitAmps)
        .inverted(false);
    m_leftFrontMotor.configure(
        leftLeaderConfig,
        ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);

    SparkMaxConfig rightLeaderConfig = new SparkMaxConfig();
    rightLeaderConfig
        .idleMode(DriveConstants.kIdleMode)
        .smartCurrentLimit(DriveConstants.kSmartCurrentLimitAmps)
        .inverted(DriveConstants.kRightSideInverted);
    m_rightFrontMotor.configure(
        rightLeaderConfig,
        ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);

    // Rear motors follow front motors after restoring defaults and applying limits/mode.
    SparkMaxConfig leftFollowerConfig = new SparkMaxConfig();
    leftFollowerConfig
        .idleMode(DriveConstants.kIdleMode)
        .smartCurrentLimit(DriveConstants.kSmartCurrentLimitAmps)
        .follow(m_leftFrontMotor);
    m_leftRearMotor.configure(
        leftFollowerConfig,
        ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);

    SparkMaxConfig rightFollowerConfig = new SparkMaxConfig();
    rightFollowerConfig
        .idleMode(DriveConstants.kIdleMode)
        .smartCurrentLimit(DriveConstants.kSmartCurrentLimitAmps)
        .follow(m_rightFrontMotor);
    m_rightRearMotor.configure(
        rightFollowerConfig,
        ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);
  }

  /** Drives the robot using arcade controls. */
  public void arcadeDrive(double speed, double rotation) {
    m_differentialDrive.arcadeDrive(-speed, rotation, false);
  }

  /** Stops drivetrain output. */
  public void stop() {
    m_differentialDrive.stopMotor();
  }
}
