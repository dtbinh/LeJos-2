package navirobo;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;

@SuppressWarnings("deprecation")
public class Pilott {
	final double d = 3.12;
	final double c = 15.95;
	RegulatedMotor leftm;
	RegulatedMotor rightm;
	DifferentialPilot pilot = null;

	public Pilott() {
		leftm = new EV3LargeRegulatedMotor(MotorPort.B);
		rightm = new EV3LargeRegulatedMotor(MotorPort.C);
		leftm.synchronizeWith(new RegulatedMotor[] { rightm });
		pilot= new DifferentialPilot(d,c,leftm,rightm);
		//pilot.travel(100);
		pilot.rotate(720);


	}
	public DifferentialPilot getPilot(){
		return pilot;
	}
	public static void main (String[] args){
		Pilott pilot = new Pilott();
	}
}
