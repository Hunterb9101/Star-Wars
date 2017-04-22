package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkComms {
	Socket sock;
	public NetworkComms(boolean isServer) throws IOException{
		if(isServer){
		    sock = new ServerSocket(3000).accept();   
		}
		else{
			sock = new Socket("127.0.0.1", 3000);
		}
	}
	public void sendMessages(String message) throws IOException{
		PrintWriter pwrite = new PrintWriter(sock.getOutputStream(), true); // receiving from server ( receiveRead  object)
		pwrite.println(message);       // sending to server
		pwrite.flush();                    // flush the data
  }
	
  public String checkMessages() throws IOException{
		String receiveMessage  = new BufferedReader(new InputStreamReader(sock.getInputStream())).readLine();
		return receiveMessage;
	}
}
