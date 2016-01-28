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

	public Naviguy(Pilott p) {
		posi = new OdometryPoseProvider(p.getPilot());
		/// set position
		pose = new Pose(0, 0, 90);
		posi.setPose(pose);
		n = new Navigator(p.getPilot(), posi);
		lines = new Line[3];
		area = new Rectangle(0, 0, 137, 159);
		lines[0] = new Line(38, 65, 85, 65);

		lines[1] = new Line(85, 65, 85, 107);

		lines[2] = new Line(37, 120, 37, 159);
		map = new LineMap(lines, area);
		fwgm = new FourWayGridMesh(map, 10, 5);
		algo = new AstarSearchAlgorithm();
	}

	public FourWayGridMesh getGrid() {
		return fwgm;
	}

	public Path getPath(FourWayGridMesh fwgm) throws DestinationUnreachableException {

		 pf= new NodePathFinder(algo, fwgm);
		switch (waypointNum) {
		case 1:
			w = new Waypoint(120, 2);
			break;
		case 2:
			w = new Waypoint(70, 80);
			break;
		case 3:
			w = new Waypoint(10, 150);
			break;
		case 4:
			w = new Waypoint(0, 0);
			break;
		}
		Path p = pf.findRoute(posi.getPose(), w);
		return p;
	}

	public int getWaypointNum() {
		return waypointNum;
	}

	public void start() throws DestinationUnreachableException {

		while (getWaypointNum() <= 4) {
			n.followPath(getPath(getGrid()));
			n.waitForStop();
			waypointNum++;
		}

	}
}