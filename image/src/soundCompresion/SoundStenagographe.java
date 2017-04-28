package soundCompresion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundStenagographe implements ISoundSteganographe {
	
	

	public  int bitwise = 1;
	
	
	public  SoundStenagographe(int bitwise) {
		this.bitwise = bitwise;

	}
	
	@Override
	public File encode(String masquePath, String targetPath,String resultPath) throws IllegalArgumentException, UnsupportedAudioFileException,Exception  {

		
		File target = new File(targetPath);
		
		if(!target.exists())
		{
			throw new FileNotFoundException(targetPath);	
		}
		
		AudioInputStream masque = AudioIO.getAudioData(masquePath);
        AudioFormat convertFormat = new AudioFormat(
        		AudioFormat.Encoding.PCM_SIGNED, 
        		masque.getFormat().getSampleRate(), 
        		16, 
        		masque.getFormat().getChannels(), 
        		masque.getFormat().getChannels()*2, 
        		masque.getFormat().getSampleRate(), 
        		false);
        
		//AudioInputStream masqueConverti = AudioIO.conversionFormat(masque,convertFormat);
		
		byte[] masqueBytes = AudioIO.getAudioDataBytes(AudioIO.getBytes(masque),convertFormat);

		byte[] message = Files.readAllBytes(target.toPath());
        byte[] messageLength = new byte[4];
        
        
        int messageLengthInt = message.length;
        for (int i = 3; i >= 0; i--)
        {
                messageLength[i] = (byte)messageLengthInt; 
                messageLengthInt = messageLengthInt >> 8;  
        }
        
        //byte[] musicToWork = increaseMusicLenght(message.length ,masqueBytes);
        
        this.LSBencode(messageLength, masqueBytes, 0);
        this.LSBencode(message, masqueBytes, 32);
		
        //AudioIO.saveFile(masqueBytes, resultPath, AudioFileFormat.Type.WAVE, convertFormat);
        //AudioIO.saveFile(masqueBytes, resultPath,AudioFileFormat.Type.WAVE , masque.getFormat());
        AudioIO.saveFile(masqueBytes, resultPath,AudioFileFormat.Type.WAVE ,convertFormat);
        
		return new File(resultPath);
	}
	


	@Override
	public File decode(String masquePath,String resultPath) throws IOException {
		
		AudioInputStream masque = AudioIO.getAudioData(masquePath);
		byte bytesMasque[] = AudioIO.getBytes(masque);
		
       byte[] data =  LSBdecode(bytesMasque);        
        
       /*
        FileOutputStream fos = new FileOutputStream(resultPath);
        fos.write(data);
        fos.close();
		*/
       Path path = Paths.get(resultPath);
       Files.write(path, data);
       
		return new File(resultPath);
	}
	public byte[] LSBdecode( byte[] audioData)
    {
        int messageLength = 0;
        
        messageLength += audioData[0] & 1;
		   for (int i = 1; i < 32; i++)
           {
                   messageLength <<= 1;
                   messageLength += audioData[i] & 1;
           }
          
           byte[] data = new byte[messageLength];
           for (int audioIndex = 32, dataIndex = 0; audioIndex < messageLength + 32; audioIndex+=8, dataIndex++)
           {
                   data[dataIndex] += audioData[audioIndex] & bitwise;
                   for (int i = 8-bitwise; i >= 0; i = i -bitwise)
                   {
                           data[dataIndex] <<= bitwise;
                           data[dataIndex] += audioData[audioIndex + i] & bitwise;
                   }
                  
           }
           
           return data;
    }
	
	public void LSBencode(byte[] message, byte[] audioData, int startIndex)
    {
            for (int audioDataIndex = startIndex, messageIndex = 0; audioDataIndex < startIndex+message.length * 8; audioDataIndex+=8, messageIndex++)
                    for (int j = 0; j < 8; j = j + bitwise )
                    {
                            audioData[audioDataIndex+j] = (byte) ( (audioData[audioDataIndex+j] & ~bitwise) ^ (message[messageIndex] & bitwise) );
                            message[messageIndex] >>= bitwise;
                    }
    }
	
	
	
	/*
	private byte[] increaseMusicLenght(int tailleMessage,byte[] musiqueold)
	{
		if(tailleMessage*8+32 < musiqueold.length)
		{
			return musiqueold;
		}
		else
		{
			int rapport = ((tailleMessage*8+32)/musiqueold.length) +1 ;
			byte[] res = new byte[rapport*musiqueold.length];
			
			for(int i = 0 ; i < rapport  ; i++  )
			{
				for(int j = 0 ; j < musiqueold.length ; j++)
				{
					res[i*j + j] = musiqueold[j]; 
				}
			}
				
				return res;
			}
			 
			
		}*/
	
	
	public  int getBitwise() {
		return bitwise;
	}
	
	
	

	public  void setBitwise(int bitwise) {
		this.bitwise = bitwise;
	}

}
