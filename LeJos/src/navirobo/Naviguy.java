package navirobo;

import lejos.robotics.mapping.*;
import lejos.robotics.navigation.DestinationUnreachableException;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.AstarSearchAlgorithm;
import lejos.robotics.pathfinding.FourWayGridMesh;
import lejos.robotics.pathfinding.NodePathFinder;
import lejos.robotics.pathfinding.Path;
import lejos.robotics.pathfinding.*;

import java.io.IOException;
import java.net.Socket;

import lejos.robotics.geometry.*;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.localization.PoseProvider;

public class Naviguy {
	static Rectangle area = null;

	static Line[] lines;
	static LineMap map;
	static FourWayGridMesh fwgm;
	private int waypointNum = 1;
	Waypoint w;
	Pose pose;
	PoseProvider posi;
	Navigator n;
	AstarSearchAlgorithm algo;
	PathFinder pf;
	EV3End ev3End;
	Datatransfer dt;

	public Naviguy(Pilott p) {
		posi = new OdometryPoseProvider(p.getPilot());
		/// set position
		pose = new Pose(44, 0, 90);
		posi.setPose(pose);
		n = new Navigator(p.getPilot(), posi);
		lines = new Line[8];
		area = new Rectangle(0, 0, 140, 160);
		// obstacle
		lines[0] = new Line(38, 50, 90, 50);

		lines[1] = new Line(90, 50, 90, 107);

		lines[2] = new Line(30, 120, 30, 160);
		// rectangle
		lines[3] = new Line(60, 0, 140, 0);
		lines[4] = new Line(140, 0, 140, 160);
		lines[5] = new Line(0, 160, 140, 160);
		lines[6] = new Line(0, 0, 0, 160);
		lines[7] = new Line(0, 0, 35, 0);

		map = new LineMap(lines, area);
		fwgm = new FourWayGridMesh(map, 8, 3);
		algo = new AstarSearchAlgorithm();
	}

	public FourWayGridMesh getGrid() {
		return fwgm;
	}

	public Path getPath(FourWayGridMesh fwgm) throws DestinationUnreachableException {

		pf = new NodePathFinder(algo, fwgm);
		switch (waypointNum) {
		case 1:
			w = new Waypoint(120, 7);
			break;
		case 2:
			w = new Waypoint(65, 80);
			break;
		case 3:
			w = new Waypoint(17, 140);
			break;
		case 4:
			w = new Waypoint(44, 5);
			break;
		}
		Path p = pf.findRoute(posi.getPose(), w);
		return p;
	}

	public int getWaypointNum() {
		return waypointNum;
	}

	public void start() throws DestinationUnreachableException, IOException, InterruptedException {
		ev3End = new EV3End();
		
		while (getWaypointNum() <= 4) {
			
			
			n.followPath(getPath(getGrid()));
			ev3End.setStringi("sasd");
			n.waitForStop();

			ev3End.setStringi("Waypoint "+getWaypointNum()+ " reached "+w.getX()+","+w.getY());
			//Datatransfer.setString("Waypoint "+getWaypointNum()+ " reached");


			waypointNum++;
		}
		
		ev3End.setStringi("stopperino");
		


	}
}