package cleansrobo;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Motors {

  RegulatedMotor leftm;
  RegulatedMotor rightm;

  public Motors() {
    leftm = new EV3LargeRegulatedMotor(MotorPort.B);
    rightm = new EV3LargeRegulatedMotor(MotorPort.C);
    leftm.synchronizeWith(new RegulatedMotor []{rightm});

  }

  public void forward() {
    leftm.startSynchronization();
    leftm.forward();
    rightm.forward();
    leftm.endSynchronization();
  }
  public void backward() {
    leftm.startSynchronization();
    leftm.backward();
    rightm.backward();
    leftm.endSynchronization();
    Delay.msDelay(2000);
    stop();
  }
  public void turn() {
    int x = (int)(Math.random() * 3000 + 1000);
    //leftm.startSynchronization();
    if (x<=1500) {
      leftm.forward();
      rightm.backward();
      Delay.msDelay(x);
      stop();
    }else{
      leftm.backward();
      rightm.forward();
      Delay.msDelay(x);
      stop();
    }
   // leftm.endSynchronization();

  }
  public void stop() {
    leftm.startSynchronization();
    leftm.stop();
    rightm.stop();
    leftm.endSynchronization();

  }

}
