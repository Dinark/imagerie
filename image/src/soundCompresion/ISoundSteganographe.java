package soundCompresion;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

public interface ISoundSteganographe {
	
	
	public File encode(String masquePath,String targetPath,String resultPath) throws IOException, IllegalArgumentException, UnsupportedAudioFileException, Exception ;
	

	public File decode(String masquePath, String resultPath) throws IOException;
}
