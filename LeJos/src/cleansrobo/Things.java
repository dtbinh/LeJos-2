package cleansrobo;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.utility.Delay;

public class Things {

	static RegulatedMotor leftm,rightm;
	public Things(RegulatedMotor leftm, RegulatedMotor rightm){
		this.leftm = leftm;
		this.rightm=rightm;
	}
    public static void stop() {
        leftm.startSynchronization();
        leftm.stop();
        rightm.stop();
        leftm.endSynchronization();
    }

    public static void turn() {
        int x = (int)(Math.random() * 4000 + 2000);
        //x=x*1000;
        System.out.println(x);
        if (x>=3000) {
			leftm.startSynchronization();
			leftm.forward();
			rightm.backward();
			leftm.endSynchronization();
			Delay.msDelay(x);
			stop();
		} else {
			leftm.startSynchronization();
			rightm.forward();
			leftm.backward();
			leftm.endSynchronization();
			Delay.msDelay(x);
			stop();
		}
    }
}
