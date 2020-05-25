package ssl.demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Security;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocketFactory;

import com.sun.net.ssl.internal.ssl.Provider;

public class Server {

	public static void main(String[] args) throws IOException {
		int port = 35786;

		// SSL part //
		Security.addProvider(new Provider());

		System.setProperty("javax.net.ssl.keyStore", "ssl-server.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "123456");
		//System.setProperty("javax.net.debug", "all");

		ServerSocketFactory serverSocketFactory = (ServerSocketFactory) SSLServerSocketFactory.getDefault();
		ServerSocket sslServerSocket = serverSocketFactory.createServerSocket(port);

		System.out.println("Server Started and ready to accept client connection");
		Socket sslSocket = sslServerSocket.accept();
		// /SSL part //

		DataInputStream dis = new DataInputStream(sslSocket.getInputStream());
		DataOutputStream os = new DataOutputStream(sslSocket.getOutputStream());
		os.writeUTF("Hello world! Say something. Bye to stop.");
		while (true) {
			String receivedMessage = dis.readUTF();
			System.out.println("Client said: " + receivedMessage);
			if (receivedMessage.equalsIgnoreCase("bye")) {
				os.writeUTF("Bye");
				break;
			} else {
				os.writeUTF("You said: " + receivedMessage);
			}
		}
		os.close();
		dis.close();
		sslSocket.close();
		sslServerSocket.close();
	}

}
