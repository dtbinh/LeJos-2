package navirobo;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {
	Socket s;
	DataInputStream dataIn;
	static boolean contin = true;

	public Connection() {

		try {

			s = new Socket("10.0.1.1", 1111);
			dataIn = new DataInputStream(s.getInputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	public void transfer() {
		while (contin) {
			try {

				if (dataIn.available() > 0) {
					if (dataIn.readUTF().equals("stopperino")) {
						stopperino();

					}
					System.out.println(dataIn.readUTF());

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}
	}

	public static boolean stopperino() {
		return contin = false;
	}

	public static void main(String[] args) {
		Connection c = new Connection();
		c.transfer();

	}
}
