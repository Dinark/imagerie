import java.io.IOException;

import soundCompresion.ISoundSteganographe;
import soundCompresion.SoundStenagographe;

public class mainSoundTest {

	

	public static void main (String[] args){
		
		
		
		ISoundSteganographe stegano = new SoundStenagographe(1);
		
		
		String fileMusique = "ressources/simon-swerwer_bonecrafter.mp3";

		String fileCible = "ressources/test.txt";
		
		String fileResult = "ressources/simonTest.wav";
		

		String fileResultDecode = "ressources/sim.out";
		
		try {
			stegano.encode(fileMusique, fileCible, fileResult);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
