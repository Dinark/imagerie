package streamMusic;

import streamer.ServerAudio;

public class mainServeur {

	public static void main (String[] args)
	{
		
		ServerAudio server = new ServerAudio();
		
		server.initServer(5555);
		System.out.println("Server send");
		server.sendMusic("ressources/simon-swerwer_bonecrafter.mp3");
		System.out.println("Server has sent");
		
	}

	
}
