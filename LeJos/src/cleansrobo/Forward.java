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

public class Forward implements Behavior {
    static RegulatedMotor leftm = new EV3LargeRegulatedMotor(MotorPort.B);
    static RegulatedMotor rightm = new EV3LargeRegulatedMotor(MotorPort.C);
    
    
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
        leftm.startSynchronization();

        leftm.forward();
        rightm.forward();
        leftm.endSynchronization();

        while(!suppressed)Thread.yield();
            leftm.startSynchronization();
            leftm.stop();
            rightm.stop();
            leftm.endSynchronization();

    }
    public static void main(String[] args) {
    	leftm.synchronizeWith(new RegulatedMotor []{rightm});
    	 Port port = LocalEV3.get().getPort("S4");
         SensorModes sensor = new EV3IRSensor(port);
         Things things = new Things(leftm,rightm);
          Port port2 = LocalEV3.get().getPort("S1");
     	 SensorModes csensor = new EV3ColorSensor(port2);
         
        Behavior b1 = new Forward();
        Behavior b2 = new Blocked(sensor);
        Behavior [] bArray = {b1, b2};

        Arbitrator arby = new Arbitrator(bArray);
        arby.go();
    }

}
