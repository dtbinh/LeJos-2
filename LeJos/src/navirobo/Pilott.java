package navirobo;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;

@SuppressWarnings("deprecation")
public class Pilott {
	final double d = 2.9;
	final double c = 16.05;
	RegulatedMotor leftm;
	RegulatedMotor rightm;
	DifferentialPilot pilot = null;

	public Pilott() {
		leftm = new EV3LargeRegulatedMotor(MotorPort.B);
		rightm = new EV3LargeRegulatedMotor(MotorPort.C);
		leftm.synchronizeWith(new RegulatedMotor[] { rightm });
		pilot= new DifferentialPilot(d,c,leftm,rightm);
//		pilot.travel(10);
		//pilot.rotate(360);


	}
	public DifferentialPilot getPilot(){
		return pilot;
	}
//	public static void main (String[] args){
//		Pilot pilot = new Pilot();
//	}
}
