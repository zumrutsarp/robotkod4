/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.shooterconstant;

public class shootersubsystem extends SubsystemBase {
 public boolean  isetpoint=false;// bu kısma tekrar bak

private WPI_VictorSPX shootermotor1=new WPI_VictorSPX(shooterconstant.k_shootercontrollerport1);
private WPI_VictorSPX shootermotor2=new WPI_VictorSPX(shooterconstant.k_shootercontrollerport2);

  public shootersubsystem() {
shootermotor1.setInverted(true);
shootermotor2.setInverted(true);
shootermotor1.follow(shootermotor2);
  }

  @Override
  public void periodic() {}
    // This method will be called once per scheduler run



    public void runshooter(double val )
    {
shootermotor1.set(val);
    }
  
}
