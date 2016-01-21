package varieste;

import java.io.File;

import lejos.hardware.*;
import lejos.hardware.ev3.*;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.*;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.*;
import lejos.robotics.ColorDetector;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.*;
import lejos.utility.Delay;

/**
 * Example leJOS EV3 Project with an ant build file
 *
 */

public class varieste {

	static EV3 ev3 = (EV3) BrickFinder.getLocal();
	static RegulatedMotor leftm = new EV3LargeRegulatedMotor(MotorPort.B);
	static RegulatedMotor rightm = new EV3LargeRegulatedMotor(MotorPort.C);
	static Port port = LocalEV3.get().getPort("S4");
	static Port port2 = LocalEV3.get().getPort("S1");
	static Port port3 = LocalEV3.get().getPort("S2");

	//
	static SensorModes sensor = new EV3IRSensor(port);
	static SensorModes csensor = new EV3ColorSensor(port2);
	static SensorModes tsensor = new EV3TouchSensor(port3);


	static float[] csample,tsample,dsample;

	private int angle;
	private static int r, g, b, range=1;
	private static boolean pressed;
	private String dire;
	private static String direction;

	public static void varieste() {
		leftm.setSpeed((int) leftm.getMaxSpeed());
		rightm.setSpeed((int) rightm.getMaxSpeed());
		Sound.beepSequence();

	}

	public static float printDistance() {
		SampleProvider distProvider = ((EV3IRSensor) sensor).getDistanceMode();
		dsample = new float[distProvider.sampleSize()];

		distProvider.fetchSample(dsample, 0);
		return dsample[0];

	}
	public static boolean getTouch() {
		SampleProvider touchProvider = ((EV3TouchSensor) tsensor).getTouchMode();
		tsample = new float[touchProvider.sampleSize()];
		System.out.println("Paina");

		touchProvider.fetchSample(tsample, 0);
		if(tsample[0]>0.5){
			pressed = true;
			return true;

		}return false;

	}

	public static void calibColor() {

		SampleProvider colorProvider = ((EV3ColorSensor) csensor).getRGBMode();
		csample = new float[colorProvider.sampleSize()];
		colorProvider.fetchSample(csample, 0);
		while (!getTouch()) {
			r = Math.round(csample[0] * 765);
			g = Math.round(csample[1] * 765);
			b = Math.round(csample[2] * 765);
		}

		System.out.println("r: " + r + ", g: " + g + ", b: " + b);
		Delay.msDelay(5000);
	}

	public static boolean checkColor() {

		SampleProvider colorProvider = ((EV3ColorSensor) csensor).getRGBMode();
		csample = new float[colorProvider.sampleSize()];
		colorProvider.fetchSample(csample, 0);
		int r2 = Math.round(csample[0] * 765);
		int g2 = Math.round(csample[1] * 765);
		int b2 = Math.round(csample[2] * 765);
		System.out.println("r: " + r2 + ", g: " + g2 + ", b: " + b2);
		if (r2 <= r + range && r2 >= r - range && g2 <= g + range && g2 >= g - range && b2 <= b + range && b2 >= b - range) {
			return true;
		}return false;
	}

	public static void move(String direction) {

		leftm.startSynchronization();
		if (direction.equals("forward")) {
			leftm.forward();
			rightm.forward();
		} else if (direction.equals("backward")) {
			leftm.backward();
			rightm.backward();
		}
		// gun.forward();
		leftm.endSynchronization();
		// Sound.beepSequenceUp();
	}

	public static void turn(int angle, String dire) {
		if (dire.equals("left")) {
			leftm.startSynchronization();
			leftm.forward();
			rightm.backward();
			leftm.endSynchronization();
			Delay.msDelay(angle);
			stop();
		} else {
			leftm.startSynchronization();
			rightm.forward();
			leftm.backward();
			leftm.endSynchronization();
			Delay.msDelay(angle);
			stop();
		}

	}

	public static void stop() {
		leftm.startSynchronization();
		leftm.stop();
		rightm.stop();
		leftm.endSynchronization();
	}

	public static void main(String[] args) {
		// move();
		// Delay.msDelay(10);
		// stop();
		while(!pressed){
			//getTouch();
			System.out.println(pressed);
			calibColor();
		}

		while (printDistance() >= 14) {
			move("forward");

			System.out.println(printDistance());
		}while(!checkColor()){
			move("backward");
			//System.out.println(printDistance());
			}
		stop();
		File soundFile = new File("pacman.wav");
		Sound.playSample(soundFile, 100);
		}
	}

