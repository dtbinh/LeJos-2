package cleansrobo;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.*;

public class Blocked implements Behavior {
	static RegulatedMotor leftm; 
	static RegulatedMotor rightm; 
	private volatile boolean suppressed = false;
	SensorModes sensor;

	static float[] dsample;

	public Blocked(SensorModes sensor) {
		this.sensor = sensor;

	}

	public boolean takeControl() {
		SampleProvider distProvider = ((EV3IRSensor) sensor).getDistanceMode();
		dsample = new float[distProvider.sampleSize()];
		distProvider.fetchSample(dsample, 0);

		if (dsample[0] <= 15) {
			return true;
		}
		return false;
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		Things.stop();
		Things.turn();
	}

}