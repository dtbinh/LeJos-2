package cleansrobo;

import lejos.robotics.subsumption.*;

public class OutOfBounds implements Behavior {

  private volatile boolean suppressed = false;
  Colorsenso colorsenso;
  Motors motors;

  public OutOfBounds(Colorsenso c, Motors m) {
    motors = m;
    colorsenso = c;
  }

  public boolean takeControl() {
    return colorsenso.checkColor();
  }

  public void suppress() {
    suppressed = true;
  }

  public void action() {
    suppressed = false;
    motors.backward();
    motors.turn();
    suppressed = true;
  }
}
