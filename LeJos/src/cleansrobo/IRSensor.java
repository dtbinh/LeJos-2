package cleansrobo;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class IRSensor {

  float[] dsample;
  SensorModes dsensor;
  Port port;
  public IRSensor() {

    port = LocalEV3.get().getPort("S4");
    dsensor = new EV3IRSensor(port);
  }
  public boolean checkDistance() {

    SampleProvider distProvider = ((EV3IRSensor) dsensor).getDistanceMode();
    dsample = new float[distProvider.sampleSize()];

    distProvider.fetchSample(dsample, 0);
    if (dsample[0] <= 14) {
      return true;
    }
    return false;
  }
}