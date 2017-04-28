package streamMusic;

import streamer.ClientAudio;

public class mainClient {

	
	
	
	public static void main (String[] args)
	{
			ClientAudio cA = new ClientAudio();
			
			System.out.println("Client read");
			cA.initClient(5555,"127.0.0.1");
			//cA.readStream();
		
			
			cA.readFile("ressources/simon-swerwer_bonecrafter.mp3");
			System.out.println("Client has read");
			
		
	}
}
