package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem; 
 
public class Drivetrain extends CommandBase {



   private final DrivetrainSubsystem m_drive;
   
  public   DoubleSupplier m_forward;
  public   DoubleSupplier m_rotation;


  public Drivetrain(DrivetrainSubsystem  subsystem,DoubleSupplier rotation,DoubleSupplier forward) {
    





    
   m_drive=subsystem;
   m_forward =forward;
    m_rotation = rotation;
    addRequirements(m_drive);
 
 
  }
 
 
 
  @Override
  public void initialize() {
  }
 
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
 
  
 
 
  }
//
 
 
 
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
 
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
 
