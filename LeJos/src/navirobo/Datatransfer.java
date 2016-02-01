package navirobo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Datatransfer extends Thread {
	Socket s;
	String str;
	DataOutputStream out;

	public Datatransfer(Socket s) throws IOException {
		out = new DataOutputStream(s.getOutputStream());
		setDaemon(true);
	}

	public void setString(String str2) {
		str = str2;
	}

	public void run() {
		System.out.println("I will try to transmit data");
		str = "0";
		while (true) {
			try {

				if (!str.equals("0")) {
					out.writeUTF(str);

					out.flush();
					sleep(20);
					str = "0";

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// println(e + " row 60");
				System.out.print("asd");
			}

		}
	}

}