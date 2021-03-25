
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.hopperConstants;

public class hoppersubsystem extends SubsystemBase {

private String hopperState  ="nothing ";
  private final WPI_VictorSPX hoppermot1=new WPI_VictorSPX(hopperConstants.khoppermotorport1           );

  private final WPI_VictorSPX hoppermot2=new WPI_VictorSPX( hopperConstants.khoppermotorport2          );


  public hoppersubsystem() {



    hoppermot1.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }





  public void runhopper( String _mode,double _speed1,double _speed2){

if(_mode=="normal"){
hoppermot1.set(_speed1);// ikisini de aynı yöne(ileriye doğru ) haeket ettir
hoppermot2.set(_speed2);




}

else if  (_mode=="negative"){

hoppermot1.set(-_speed1);// ikisi de ters yöne doğru hareket etsin 
hoppermot2.set(-_speed2);

}

else if (_mode=="fastnormal");// ikiside aynı yöne  (ileriye doğuru) fakat bu sefer  hız atarken daha büyük değer atayacağız 


{


hoppermot1.set(_speed1);
hoppermot2.set(_speed2);
}





hopperState=_mode;

}


  
}