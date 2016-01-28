package navirobo;

import lejos.robotics.navigation.DestinationUnreachableException;

public class Main {
	public static void main (String[] args) throws DestinationUnreachableException{
		Pilott pilot = new Pilott();
		Naviguy naviG = new Naviguy(pilot);
		naviG.start();
	}
}
