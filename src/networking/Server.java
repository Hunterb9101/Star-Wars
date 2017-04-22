package networking;

import java.io.*;
import java.net.*;
public class Server extends NetworkComms{
	public Server() throws IOException {
		super(true);
	}

  public static void main(String[] args) throws Exception {
      Server s = new Server();                              
      while(true) {
        System.out.println(s.checkMessages());   
        s.sendMessages("Hello");
      }               
    }
}                
