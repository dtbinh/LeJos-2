package navirobo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class EV3End {

	Socket s2;
	Datatransfer dt;

	public EV3End() throws IOException {
		ServerSocket ser = new ServerSocket(1111);
		System.out.println("Waiting for connection");
		s2 = ser.accept();
		while (!s2.isConnected()) {
			s2 = ser.accept();
		}
		System.out.println("Connected");
		dt = new Datatransfer(s2);
		dt.start();
	}

//	public Socket getSocket() {
//		return s2;
//	}

	public void setStringi(String str) {
		dt.setString(str);
	}



}
