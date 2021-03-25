/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.intakeconstant;

public class intakesubsystem extends SubsystemBase {
 
  
public final  WPI_VictorSPX intakemotor=new WPI_VictorSPX(intakeconstant.kintakemotor);
private boolean intakestate =false ;
  

  @Override
  public void periodic() {}


public void startintake( double _speed)
{


intakemotor.set(_speed);

}

public void intakedur()
{

intakemotor.set(0);
}



}

