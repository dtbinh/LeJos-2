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

	private volatile boolean suppressed = false;
	IRSensor irsenso;
	Motors motors;


	public Blocked(IRSensor ir, Motors m) {
		irsenso = ir;
		motors = m;

	}

	public boolean takeControl() {
		return irsenso.checkDistance();
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		motors.backward();
		motors.turn();
	}

}