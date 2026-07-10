package frc.robot;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

/**
 * Robot-wide constants used by subsystems and commands.
 */
public final class Constants {
  private Constants() {}

  /** Constants for drivetrain hardware and behavior. */
  public static final class DriveConstants {
    public static final int kLeftFrontCanId = 4;
    public static final int kLeftRearCanId = 3;
    public static final int kRightFrontCanId = 6;
    public static final int kRightRearCanId = 5;

    public static final int kSmartCurrentLimitAmps = 40;
    public static final IdleMode kIdleMode = IdleMode.kBrake;
    public static final boolean kRightSideInverted = true;

    public static final double kDriveSpeedMultiplier = 1.0;
    public static final double kTurnSpeedMultiplier = 1.0;
    public static final double kDeadband = 0.1;

    private DriveConstants() {}
  }

  /** Constants for driver/operator controls. */
  public static final class OperatorConstants {
    public static final int kDriverControllerPort = 0;

    private OperatorConstants() {}
  }
}
