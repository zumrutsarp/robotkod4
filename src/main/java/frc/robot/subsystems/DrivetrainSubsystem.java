// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;




import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;

import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;


import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


import frc.robot.Constants.Driveconstant;

public class DrivetrainSubsystem extends SubsystemBase {
  /** Creates a new DrivetrainSubsystem. */



private final WPI_TalonSRX LeftFrontMotor=new WPI_TalonSRX(Driveconstant. kleftfrontmotorport);
private final WPI_VictorSPX LeftRearMotor=new WPI_VictorSPX(Driveconstant. kleftrearmotorport);// bunlar bizim sol grubumuz olacak 

private final WPI_TalonSRX RightFrontMotor=new WPI_TalonSRX(Driveconstant. krightfrontmotorport);
private final WPI_VictorSPX RightRearMotor=new WPI_VictorSPX(Driveconstant. krightrearmotorport);// bunlar bizim sağ  grubumuz olacak (her kelimnin ilk harfini büyük yapmam zorunlu mu ?)

public final DifferentialDrive m_drive=new DifferentialDrive(LeftFrontMotor, RightFrontMotor);
static private int PIDIDX = 0;


 
private final SpeedControllerGroup m_leftGroup=new SpeedControllerGroup(  LeftRearMotor, LeftFrontMotor );
private final SpeedControllerGroup m_rightGroup=new SpeedControllerGroup(  RightRearMotor,  RightFrontMotor);

private final  ADXRS450_Gyro m_gyro = new ADXRS450_Gyro(10);

private  DifferentialDriveOdometry m_odometry;
   private double angular_velocity ;
   



  public DrivetrainSubsystem() {


    LeftFrontMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,PIDIDX,10);
    RightFrontMotor.configSelectedFeedbackSensor (FeedbackDevice.CTRE_MagEncoder_Relative, PIDIDX, 10);
    LeftFrontMotor.setSensorPhase(true);


LeftRearMotor.follow(LeftFrontMotor);// arka motorlar ön motorları takip etsin 
RightRearMotor.follow(RightFrontMotor);




//m_rightEncoder.setDistancePerPulse(20);// parametreler  randomdur
//m_leftEncoder.setDistancePerPulse(20);// devir sayısından ne kadar mesafegitiğii ölçmek 

     
  m_odometry=new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));// elimizdeki verileri uzaydaki konumumuza atyoruz 
resetEncoders();}


@Override


  public void periodic() {
    // This method will be called once per scheduler run

    angular_velocity = m_gyro.getRate();

m_odometry= new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()))   ;// SÜREKLİ OLARAK ODOMETRİYİ GÜNCELLER


}




public Pose2d getPose(){

    return m_odometry.getPoseMeters();// mevcut heaplanan pose erişmek çin 
}


public DifferentialDriveWheelSpeeds getWHEELSPEEDS(){// tekerlek hızına ulaşmak için 
return new DifferentialDriveWheelSpeeds( LeftFrontMotor.getSelectedSensorVelocity()* (1/4096) *Math.PI*0.1524*10.0
,RightFrontMotor.getSelectedSensorVelocity()* 1/4096 *Math.PI*0.1524 *10.0  ) ;//getRate=mevcut değişim oranı 


}






public void arcadeDrive(double fwd,double rot){
// ben bunu tam olarak anlamadım fakat wpı yapmış diye ekledim 

    m_drive.arcadeDrive(fwd, rot,true );
}

public void  ResetOdometry(Pose2d m_pose){resetEncoders();
m_odometry.resetPosition(m_pose,Rotation2d.fromDegrees(getHeading()));// m_pose diyememizin nedeni pose'u diğer yerlerde de kullanıyor oluşumuz mu

}

public void resetEncoders(){


  LeftFrontMotor.setSelectedSensorPosition(0);
  RightFrontMotor.setSelectedSensorPosition(0);// encoderi sıfırlarız çünkü
}



public  double     getLeftEncoder   ()  {// sol encoderden veri almak 
    return LeftFrontMotor.getSelectedSensorPosition()*20*Math.PI;
      
  }
  



public double     getRightEncoder   ()  {// sağ encoderden veri almak 
  return  RightFrontMotor.getSelectedSensorPosition()*20*Math.PI*1;
      
    
}



public double getHeading() {
 
  return Math.IEEEremainder(m_gyro.getAngle(), 360);

}



public void  TankDriveVolts( double leftvolts,double rightvolts){
    m_leftGroup.setVoltage(leftvolts);// ani voltaj dalgalanmalarıı önler 
    m_rightGroup.setVoltage(-rightvolts);
m_drive.feed();
}








}


  