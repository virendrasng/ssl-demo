package ssl.demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.Security;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import com.sun.net.ssl.internal.ssl.Provider;

public class Client {

	public static void main(String[] args) throws IOException {
		int port = 35786;
		String serverName = "localhost";

		// SSL part //
		Security.addProvider(new Provider());

		System.setProperty("javax.net.ssl.trustStore", "ssl-server.jts");
		System.setProperty("javax.net.ssl.trustStorePassword", "123456");
		// System.setProperty("javax.net.debug", "all");

		SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(serverName, port);

		// /SSL part //
		// Create output stream to send data to server
		DataOutputStream outPutStream = new DataOutputStream(sslSocket.getOutputStream());
		// Create input stream to read message sent by the server
		DataInputStream inputStream = new DataInputStream(sslSocket.getInputStream());
		// Read the first message sent by the server after connection
		System.out.println("At Client - " + inputStream.readUTF());

		// Keep sending the message
		while (true) {
			System.out.println("Write a message. Bye to stop.");
			String messageTosend = System.console().readLine();
			outPutStream.writeUTF(messageTosend);
			System.err.println("Message from server - " + inputStream.readUTF());
			if (messageTosend.equalsIgnoreCase("bye")) {
				break;
			}
		}
		outPutStream.close();
		inputStream.close();
		sslSocket.close();
	}

}
