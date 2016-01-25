package navirobo;



import lejos.robotics.mapping.*;
import lejos.robotics.pathfinding.FourWayGridMesh;
import lejos.robotics.geometry.*;

public class Naviguy {
	static Rectangle area = new Rectangle(0, 0, 150, 100);
	static Line[] lines = new Line[6];

	public Naviguy() {


		lines[0] = new Line(0, 0, 150, 0);

		lines[1] = new Line(0, 0, 0, 100);

		lines[2] = new Line(150, 0, 150, 100);

		lines[3] = new Line(0, 100, 150, 100);

		lines[4] = new Line(30,20,30,60);
		lines[5] = new Line(60,70,100,70);



	}
	public static void main (String[] args){
		Naviguy n = new Naviguy();
		LineMap map = new LineMap(lines, area);
		FourWayGridMesh fwgm = new FourWayGridMesh(map, 10, 10);
	}
}
