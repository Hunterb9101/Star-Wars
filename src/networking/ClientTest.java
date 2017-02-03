package networking;

public class ClientTest {
	public static void main(String[] args) throws InterruptedException{
		Client c = new Client();
		while(true){
			c.run();
			System.out.println("got past the barrier!");
			c.sendData("Hello Server!");
			Thread.sleep(1000);
		}
	}
}
