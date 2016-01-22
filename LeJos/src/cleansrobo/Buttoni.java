package cleansrobo;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class Buttoni {
	float[] tsample;
	SensorModes tsensor;
	Port port3;


	public Buttoni() {
		port3 = LocalEV3.get().getPort("S2");
		 tsensor = new EV3TouchSensor(port3);

	}

	public boolean pressed() {
		SampleProvider touchProvider = ((EV3TouchSensor) tsensor).getTouchMode();
		tsample = new float[touchProvider.sampleSize()];


		touchProvider.fetchSample(tsample, 0);
		if (tsample[0] > 0.5) {

			return true;

		}
		return false;
	}

}
