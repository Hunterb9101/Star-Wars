package networking;

import java.io.*;
import java.net.*;
public class Client extends NetworkComms{
	public Client() throws IOException {
		super(false);
	}
	
	public static void main(String[] args) throws Exception {
		Client c = new Client();
        int i = 0;
		while(true){
			c.sendMessages(String.valueOf(i));
			i++;
			c.checkMessages();      
		}               
	} 
}                