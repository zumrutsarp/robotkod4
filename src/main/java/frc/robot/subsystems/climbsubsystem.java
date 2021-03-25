/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;

public class climbsubsystem extends SubsystemBase {
  /**
   * Creates a new climbsubsystem.
   */

  public boolean climbstate = false;
  public boolean compressorstate = false;
  private final Compressor compressor = new Compressor();
  private final DoubleSolenoid solenoid = new DoubleSolenoid(ClimbConstants.kclimbsolonoid1,
      ClimbConstants.kclimbsolonoid2);

  public climbsubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void climbukari() {

    solenoid.set(Value.kForward);

  }

  public void climbdown() {

    solenoid.set(kReverse);

}

public void pistondur()
{

solenoid.set(Value.kOff);
  
}
// compressor methodları

  public void  compressoropen()
  {

compressor.setClosedLoopControl(true);

  }


  public void compressorclose()
  
  {

    compressor.setClosedLoopControl(false);
  }
}


