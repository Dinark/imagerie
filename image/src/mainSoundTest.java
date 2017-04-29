import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import soundCompresion.ISoundSteganographe;
import soundCompresion.SoundStenagographe;

public class mainSoundTest {

	

	public static void main (String[] args){
		
		
		
		ISoundSteganographe stegano = new SoundStenagographe(1);
		
		
		String fileMusique = "ressources/simon-swerwer_bonecrafter.mp3";

		//String fileMusique = "ressources/audioclip2.wav";

		//String fileCible = "ressources/test.txt";
		//String fileCible = "ressources/toto.pdf";
		//String fileCible = "ressources/camion.jpeg";
		//String fileCible = "ressources/invasionzombie.png";
		//String fileCible = "ressources/audioclip.wav";
		String fileCible = "ressources/eclipse-installer.tar";

        //String fileCible = "ressources/song_game.ogg";



		String fileResult = "ressources/simonTest.wav";
		String fileResultDecode = "ressources/sim";

		File output = new File(fileResult);
		if(output.exists())
		{
			output.delete();
		}

		
		
		try {
			stegano.encode(fileMusique, fileCible, fileResult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			stegano.decode(fileResult, fileResultDecode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		try {
			((SoundStenagographe) stegano).comparaison(Files.readAllBytes(new File(fileCible).toPath()),Files.readAllBytes(new File(fileResultDecode).toPath()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	}
}
