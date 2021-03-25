/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static frc.robot.Constants.Driveconstant.kDriveKinematics;

import java.util.Arrays;
import java.util.List;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.Driveconstant;
import frc.robot.Constants.joystickconstants;
import frc.robot.commands.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private static final TrajectoryConfig config = null;
  private final intakesubsystem m_intake = new intakesubsystem();
  private final shootersubsystem m_shooter = new shootersubsystem();
  public Joystick m_driverController = new Joystick(joystickconstants.kdrivercontrollerport);
  public Joystick m_oparatorController = new Joystick(joystickconstants.koparatorcontrollerport);
  public final climbsubsystem m_climb = new climbsubsystem();
  public final hoppersubsystem m_hopper = new hoppersubsystem();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    new JoystickButton(m_driverController, 2).whileHeld(new Runintake(1, m_intake));
    new JoystickButton(m_oparatorController, 2).whileHeld(new Runintake(-0.8, m_intake));

    new JoystickButton(m_driverController, 8).whileHeld(Runshooter(0.5, m_shooter));

    new JoystickButton(m_oparatorController, 8).whileHeld(Runshooter(-0.4, m_shooter));

    final hoppersubsystem m_hopper;
    new JoystickButton(m_driverController, 1).whileHeld(new Runhopper("nothing", m_hopper));
    new JoystickButton(m_driverController, 2).whileHeld(new Runhopper("normal", m_hopper));
    new JoystickButton(m_driverController, 3).whileHeld(new Runhopper("fastnormal", m_hopper));

    // climb'a ait olan buton eşleşmelerimiz

    new JoystickButton(m_oparatorController, 7).whileHeld( new Runclimb(m_climb));// bastığım sürece çalışsın istiyorum fikir
                                                                             // değiştirirsem tırmanmayı bıraksın
    new JoystickButton(m_oparatorController, 6).whileHeld( new closeclimb(m_climb));
  }

  private Command Runclimb(final climbsubsystem m_climb2) {//
    return null;
  }

  private Command Runshooter(final double d, final shootersubsystem m_shooter2) {
    return null;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
        new SimpleMotorFeedforward(Driveconstant.ksVolts, Driveconstant.kvVoltSecondsPerMeter,
            Driveconstant.kaVoltSecondsSquaredPerMeter),
        (DifferentialDriveKinematics) Driveconstant.kDriveKinematics, 10);

    new TrajectoryConfig(AutoConstants.kMaxSpeedMetersPerSecond, AutoConstants.kMaxAccelerationMetersPerSecondSquared)

        .setKinematics((DifferentialDriveKinematics) Driveconstant.kDriveKinematics)

        .addConstraint(autoVoltageConstraint);

  }

  Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
      // Start at the origin facing the +X direction
      new Pose2d(0, 0, new Rotation2d(0)),
      // Pass through these two interior waypoints, making an 's' curve path
      List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
      // End 3 meters straight ahead of where we started, facing forward
      new Pose2d(3, 0, new Rotation2d(0)),
      // Pass config
      config);
  private Subsystem m_robotDrive;


RamseteCommand ramseteCommand = new RamseteCommand(
  exampleTrajectory,
  m_robotDrive::getPose,
  new RamseteController(AutoConstants.kRamseteB, AutoConstants.kRamseteZeta),
  new SimpleMotorFeedforward(Driveconstant.ksVolts,
                             Driveconstant.kvVoltSecondsPerMeter,
                             Driveconstant.kaVoltSecondsSquaredPerMeter),
  DriveConstant.kDriveKinematics,
  m_robotDrive::getWheelSpeeds,
  new PIDController(Driveconstant.kPDriveVel, 0, 0),
  new PIDController(Driveconstant.kPDriveVel, 0, 0),
  // RamseteCommand passes volts to the callback
  m_robotDrive::tankDriveVolts,
  m_robotDrive
);





}