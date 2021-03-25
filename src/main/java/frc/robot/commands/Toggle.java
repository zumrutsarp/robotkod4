// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.climbsubsystem;

public class Toggle extends CommandBase {
  


  private final climbsubsystem m_climb;
  public Toggle(climbsubsystem  _climb) {
    this.m_climb=_climb;
    addRequirements(m_climb);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {



    m_climb.climbstate=!m_climb.climbstate;// açıksa kapa kapalıysa aç 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {


    if(m_climb.climbstate==true){


      m_climb.climbukari();
    }
    else {

      m_climb.climbdown();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  m_climb.pistondur(); 
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
