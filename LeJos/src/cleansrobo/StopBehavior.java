package cleansrobo;

import lejos.robotics.subsumption.Behavior;

public class StopBehavior implements Behavior{
	private volatile boolean suppressed = false;
	Buttoni buttoni;
	Motors motors;

	public StopBehavior(Buttoni b, Motors m){
		buttoni = b;
		motors = m;
	}
	public  boolean takeControl(){
		return buttoni.pressed();
	}
	public void suppress() {
	    suppressed = true;
	  }

	  public void action() {
	    suppressed = false;
	    while(!buttoni.pressed()){
	    motors.stop();
	    }
	    suppressed = true;
	  }
}
