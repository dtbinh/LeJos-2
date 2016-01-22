package cleansrobo;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class Colorsenso {

	static float[] csample;
	SensorModes csensor;
	Port port2;
	private int r2, g2, b2, r, g, b,range = 20;
	private boolean first = true;

	public Colorsenso() {
		port2 = LocalEV3.get().getPort("S1");
		csensor = new EV3ColorSensor(port2);

	}

	public float[] getColor() {
		SampleProvider colorProvider = ((EV3ColorSensor) csensor).getRGBMode();
		csample = new float[colorProvider.sampleSize()];
		colorProvider.fetchSample(csample, 0);
		if (first) {
			r = Math.round(csample[0] * 765);
			g = Math.round(csample[1] * 765);
			b = Math.round(csample[2] * 765);
			first = false;
		}
		return csample;
	}

	public boolean checkColor() {
		csample = getColor();
		r2 = Math.round(csample[0] * 765);
		g2 = Math.round(csample[1] * 765);
		b2 = Math.round(csample[2] * 765);
		if (r2 <= r + range && r2 >= r - range && g2 <= g + range && g2 >= g - range && b2 <= b + range && b2 >= b - range) {
			return true;
		}return false;
	}

}
