package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.hoppersubsystem;


public class Runhopper extends CommandBase {
  /**
   * Creates a new Runhopper.
   */
  private String mode;
private final hoppersubsystem  m_hopper;
  private double m_speed=0.6;
  private double h_speed =0.8;
  private double l_speed=0.4;
  public Runhopper(final String _mode,final hoppersubsystem  _hopper     )
  
  
  {
    // Use addRequirements() here to declare subsystem dependencies.



    this.mode = _mode;
    this.m_hopper = _hopper;//  burası hata verecektir çünki burada  
    addRequirements(_hopper   );//   subsystem ile commandimizi eşleştirdiğimiz kısımfakat buraa 














  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  if (mode =="normal"){


    m_hopper.runhopper("normal",m_speed,m_speed);
  }
else if ( mode=="negative" ){


m_hopper.runhopper("negative",l_speed,l_speed);

}
else if (mode=="fastnormal"){


  m_hopper.runhopper("fastnormal",h_speed,h_speed);
}

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {




m_hopper.runhopper( "nothing",0,0           );





  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }}