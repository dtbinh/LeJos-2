package cleansrobo;

import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.*;
import lejos.utility.Delay;

public class Forward implements Behavior {

	static Motors motors;

	static EV3 ev3 = (EV3) BrickFinder.getLocal();
	private volatile boolean suppressed = false;

	public boolean takeControl() {
		return true;
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		suppressed = false;
		motors.forward();

		while (!suppressed)
			Thread.yield();
		motors.stop();

	}

	public static void main(String[] args) {
		Colorsenso c = new Colorsenso();
		IRSensor ir = new IRSensor();
		motors = new Motors();
		Buttoni b = new Buttoni();

		Behavior b1 = new Forward();
		Behavior b2 = new Blocked(ir, motors);
		Behavior b3 = new OutOfBounds(c, motors);
		Behavior b4 = new StopBehavior(b,motors);

		//while(true){
		System.out.println("BAINA MUA");
		while (!b.pressed()) {
		}
		System.out.println("thx m8");
		c.getColor();
		Delay.msDelay(2000);
		Behavior[] bArray = { b1, b2, b3, b4 };

		Arbitrator arby = new Arbitrator(bArray);
		arby.go();
	}

}
