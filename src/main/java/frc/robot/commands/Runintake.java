/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intakesubsystem;

public class Runintake extends CommandBase {
private final double m_speed;
private final intakesubsystem m_intake;
  
  public Runintake(double speed,intakesubsystem _intake) {
this.m_speed=speed;
this.m_intake=_intake;
addRequirements(m_intake);

  }


  @Override
  public void initialize() {
  }


  @Override
  public void execute() {

m_intake.startintake(m_speed);

  }

  @Override
  public void end(boolean interrupted) {
m_intake.intakedur();


  }

 
  @Override
  public boolean isFinished() {
    return false;
  }
}
