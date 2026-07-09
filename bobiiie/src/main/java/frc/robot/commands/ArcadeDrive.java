package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;

/**
 * Default drive command for arcade drive control.
 */
public class ArcadeDrive extends Command {
  private final DriveSubsystem m_driveSubsystem;
  private final XboxController m_driverController;

  /** Creates a command that continuously reads driver sticks and drives the robot. */
  public ArcadeDrive(DriveSubsystem driveSubsystem, XboxController driverController) {
    m_driveSubsystem = driveSubsystem;
    m_driverController = driverController;

    addRequirements(driveSubsystem);
  }

  @Override
  public void execute() {
    // Read raw joystick values (invert Y so forward stick gives positive forward speed).
    double rawSpeed = -m_driverController.getLeftY();
    double rawRotation = m_driverController.getRightX();

    // Apply deadband and square inputs for finer low-speed control.
    double processedSpeed = squareWithSign(MathUtil.applyDeadband(rawSpeed, DriveConstants.kDeadband));
    double processedRotation =
        squareWithSign(MathUtil.applyDeadband(rawRotation, DriveConstants.kDeadband));

    // Scale and clamp outputs before sending to drivetrain.
    double clampedSpeed =
        MathUtil.clamp(
            processedSpeed * DriveConstants.kDriveSpeedMultiplier,
            -1.0,
            1.0);
    double clampedRotation =
        MathUtil.clamp(
            processedRotation * DriveConstants.kTurnSpeedMultiplier,
            -1.0,
            1.0);

    m_driveSubsystem.arcadeDrive(clampedSpeed, clampedRotation);
  }

  @Override
  public void end(boolean interrupted) {
    m_driveSubsystem.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  private double squareWithSign(double value) {
    return Math.copySign(value * value, value);
  }
}
