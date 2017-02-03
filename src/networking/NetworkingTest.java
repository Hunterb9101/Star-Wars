package networking;

public class NetworkingTest {
	public static void main(String[] args) throws InterruptedException{
		Server s = new Server();
		while(true){
			s.run();
			s.sendData("Hello");
			Thread.sleep(1000);
		}
	}
}
