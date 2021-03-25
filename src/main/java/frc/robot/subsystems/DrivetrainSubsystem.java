// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Base64.Encoder;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Driveconstant;

public class DrivetrainSubsystem extends SubsystemBase {
  /** Creates a new DrivetrainSubsystem. */



private final WPI_TalonSRX LeftFrontMotor=new WPI_TalonSRX(Driveconstant. kleftfrontmotorport);
private final WPI_VictorSPX LeftRearMotor=new WPI_VictorSPX(Driveconstant. kleftrearmotorport);// bunlar bizim sol grubumuz olacak 

private final WPI_TalonSRX RightFrontMotor=new WPI_TalonSRX(Driveconstant. krightfrontmotorport);
private final WPI_VictorSPX RightRearMotor=new WPI_VictorSPX(Driveconstant. krightrearmotorport);// bunlar bizim sağ  grubumuz olacak (her kelimnin ilk harfini büyük yapmam zorunlu mu ?)

private final DifferentialDrive m_drive=new DifferentialDrive(LeftFrontMotor, RightFrontMotor);
static private int PIDIDX = 0;

LeftFrontMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,PIDIDX,10);
    RightFrontMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, PIDIDX, 10);
    LeftFrontMotor.setSensorPhase(true);

 
private final SpeedControllerGroup m_leftGroup=new SpeedControllerGroup(  LeftRearMotor, LeftFrontMotor );
private final SpeedControllerGroup m_rightGroup=new SpeedControllerGroup(  RightRearMotor,  RightFrontMotor);

public final  Gyro m_gyro =new ADXRS450_Gyro(){
private final DifferentialDriveOdometry m_odometry;
    @Override
   



  public DrivetrainSubsystem() {

LeftRearMotor.follow(LeftFrontMotor);// arka motorlar ön motorları takip etsin 
RightRearMotor.follow(RightFrontMotor);




m_rightEncoder.setDistancePerPulse(20);// parametreler  randomdur
m_leftEncoder.setDistancePerPulse(20);// devir sayısından ne kadar mesafegitiğii ölçmek 
m_odometry=new DifferentialDriveOdometry(m_gyro.getRotation2d); 
     
  m_odometry=new DifferentialDriveOdometry(m_gyro.getRotation2d);// elimizdeki verileri uzaydaki konumumuza atyoruz 
Encoderreset();}
@Override
  public void periodic() {
    // This method will be called once per scheduler run



m_odometry.update(m_gyro.getRotation2d(), m_leftEncoder.getDistance, m_rightEncoder.getDistance());


}




public Pose2d getpose(){

    return m_odometry.getPoseMeters();// mevcut heaplanan pose erişmek çin 
}

public DifferentialDrive getWHEELSPEEDS(){// tekerlek hızına ulaşmak için 


    return new DifferatialDrive(m_leftEncoder.getRate(),m_rightEncoder.getRate);//getRate=mevcut değişim oranı 
}







public void arcadeDrive(double fwd,double rot){
// ben bunu tam olarak anlamadım fakat wpı yapmış diye ekledim 

    m_drive.arcadeDrive(fwd, rot,true );
}

public void  ResetOdometry(Pose2d m_pose){resetEncoders();
m_odometry.resetPosition(m_pose,m_gyro.getRotation2d());// m_pose diyememizin nedeni pose'u diğer yerlerde de kullanıyor oluşumuz mu

}

public void resetEncoders(){


    m_leftEncoder.reset();
    m_rightEncoder.reset();// encoderi sıfırlarız çünkü
}



public Encoder    getLeftEncoder   ()  {// sol encoderden veri almak 
    return m_leftEncoder;
      
  }
  



public Encoder    getRightEncoder   ()  {// sağ encoderden veri almak 
  return m_rightEncoder;
    
}



@Override
public double getHeading() {//* yön almak 
    // TODO Auto-generated method stub
    return m_gyro.getRotation2d().getDegrees(); 
}



public void  TankDriveVolts( double leftvolts,double rightvolts){
    m_leftGroup.setVoltage(leftvolts);// ani voltaj dalgalanmalarıı önler 
    m_rightGroup.setVoltage(-rightvolts);
m_drive.feed();
}
};







  }
  