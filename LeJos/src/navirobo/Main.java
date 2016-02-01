package navirobo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import lejos.robotics.navigation.DestinationUnreachableException;

public class Main {
	public static void main(String[] args) throws DestinationUnreachableException, IOException, InterruptedException {

		Pilott pilot = new Pilott();
		Naviguy naviG = new Naviguy(pilot);
		naviG.start();
	}
}
