package cleansrobo;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class Colorsenso {
	
	
	static float[] csample;
	SensorModes csensor;
	public Colorsenso(SensorModes csensor){
		this.csensor = csensor;
		
	}
	
	public boolean takeControl(){
		SampleProvider colorProvider = ((EV3ColorSensor) csensor).getRGBMode();
		csample = new float[colorProvider.sampleSize()];
		colorProvider.fetchSample(csample, 0);
		r = Math.round(csample[0] * 765);
		g = Math.round(csample[1] * 765);
		b = Math.round(csample[2] * 765);
	}

}
