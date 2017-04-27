package soundCompresion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;

public class SoundStenagographe implements ISoundSteganographe {
	
	

	private int bitwise = 1;
	
	@Override
	public File encode(String masquePath, String targetPath,String resultPath) throws IOException {

		
		File target = new File(targetPath);
		
		if(!target.exists())
		{
			throw new FileNotFoundException(targetPath);	
		}
		
		AudioInputStream masque = AudioIO.getAudioData(masquePath);
		
		
		
		
		return null;
	}
	
    public void LSBencode(byte[] message, byte[] audioData, int startIndex)
    {
            for (int audioDataIndex = startIndex, messageIndex = 0; audioDataIndex < startIndex+message.length * 8; audioDataIndex+=8, messageIndex++)
                    for (int j = 0; j < 8; j = j + bitwise )
                    {
                            // System.err.println((audioData[audioDataIndex+j] & ~1) ^ (message[messageIndex] & 1));
                            // (message[messageIndex] & 1) gets the least significant bit of the message
                            // (audioData[audioDataIndex+j] & ~1) gets all of the non-least significant bits of the audioData
                            // Then they are XORed together to combine them
                            audioData[audioDataIndex+j] = (byte) ( (audioData[audioDataIndex+j] & ~bitwise) ^ (message[messageIndex] & bitwise) );
                            message[messageIndex] >>= bitwise;
                    }
    }

	@Override
	public File decode(String masquePath) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getBitwise() {
		return bitwise;
	}

	public void setBitwise(int bitwise) {
		this.bitwise = bitwise;
	}

}
