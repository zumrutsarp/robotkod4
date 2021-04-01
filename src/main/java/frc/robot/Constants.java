/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {





    public static final class intakeconstant 
    {

public static final int kintakemotor=1; 
    }

public static final class joystickconstants
{

    public static final int kdrivercontrollerport=2;

public static final int koparatorcontrollerport=3;

}





public static final class shooterconstant
 {
public static final int k_shootercontrollerport1=4;
public static final int k_shootercontrollerport2=5;



 }



 public static final class ClimbConstants{





    public static final int kclimbsolonoid1=8;
    
    public static final int kclimbsolonoid2=9;
 }  

  public static final  class AutoConstants{


    public static final double kRamseteB = 2;
     public static final double kRamseteZeta = 0.7;
        public static final double  kMaxSpeedMetersPerSecond=2.0;
        public static final double  kMaxAccelerationMetersPerSecondSquared=2.0;
  }
 public static final class Driveconstant
 {




    



    public static final double ksVolts = 1.86;
    public static final double kvVoltSecondsPerMeter = 3.13;// buradaki değerler sneakysnakes kodundan alınmıştır
    public static final double kaVoltSecondsSquaredPerMeter = 0.815;

    public static final double kPDriveVel = 2;

    public static final  int kleftrearmotorport=8 ;
    public static final  int  kleftfrontmotorport =9; 
    public static final int  krightrearmotorport =10 ;
    
      public static final  int krightfrontmotorport=11;
   public static final double  kEncoderCPR=4096; // neden 4096?

      public static final int ksagencodera=0;
      public static final int ksagencoderb=1;
      
      public static final int ksolencodera=0;

      public static final int ksolencoderb=1;

      public static final int kTrackWidthMeters=42;
	public static final  DifferentialDriveKinematics kDriveKinematics=new DifferentialDriveKinematics(Driveconstant.kTrackWidthMeters);
	
 }



 
public static final class hopperConstants {


    public static final int  khoppermotorport1=5;
    public static final int  khoppermotorport2=6;
    
}


}

