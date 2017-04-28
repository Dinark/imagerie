import java.io.File;
import java.io.IOException;

import soundCompresion.ISoundSteganographe;
import soundCompresion.SoundStenagographe;

public class mainSoundTest {

	

	public static void main (String[] args){
		
		
		
		ISoundSteganographe stegano = new SoundStenagographe(1);
		
		
		String fileMusique = "ressources/simon-swerwer_bonecrafter.mp3";

		//String fileMusique = "ressources/audioclip2.wav";

		String fileCible = "ressources/test.txt";
		
		String fileResult = "ressources/simonTest.wav";
		//String fileResult = "ressources/simonTest.mp3";

		File output = new File(fileResult);
		if(output.exists())
		{
			output.delete();
		}

		String fileResultDecode = "ressources/sim";
		
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
		
		
	}
}
