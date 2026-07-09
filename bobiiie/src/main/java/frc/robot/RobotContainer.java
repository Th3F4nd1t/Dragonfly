package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ArcadeDrive;
import frc.robot.subsystems.DriveSubsystem;

/**
 * Central container for robot subsystems, commands, and driver interface.
 */
public class RobotContainer {
  // Subsystems.
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();

  // Driver controls.
  private final XboxController m_driverController =
      new XboxController(OperatorConstants.kDriverControllerPort);

  /** Creates the robot container and sets default command bindings. */
  public RobotContainer() {
    configureDefaultCommands();
  }

  private void configureDefaultCommands() {
    m_driveSubsystem.setDefaultCommand(new ArcadeDrive(m_driveSubsystem, m_driverController));
  }

  /**
   * Returns the autonomous command.
   *
   * @return a command for autonomous mode
   */
  public Command getAutonomousCommand() {
    return Commands.none();
  }
}
