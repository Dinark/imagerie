package soundCompresion;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class AudioIO {


	public static void saveFile(byte[] sound ,String fileName, AudioFileFormat.Type type, AudioFormat audFormat )
	throws FileAlreadyExistsException{
	
		if(new File(fileName).exists())
		{
			throw new FileAlreadyExistsException(fileName);
		}
		
		try
		{
			ByteArrayInputStream bais = new ByteArrayInputStream(sound);
			AudioInputStream ais = new AudioInputStream(bais, audFormat, sound.length);
		
			AudioSystem.write(ais, type, new File(fileName));
		
		
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	}
	
	public static  byte[] getBytes(AudioInputStream audio) throws IOException
	{
		byte[] res = null;
		if(audio != null)
		{
			
		res = new byte[audio.available()];
		
		audio.read(res);
		}

		return res;
	}
	
	public static AudioInputStream conversionFormat(AudioInputStream source, AudioFormat nouveauFormat)
	{

		
        AudioInputStream convert1AIS = null;
        AudioInputStream res = null;
        
   

        try{
            

            //AudioFormat convertFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sourceFormat.getSampleRate(), 16, sourceFormat.getChannels(), sourceFormat.getChannels()*2, sourceFormat.getSampleRate(), false);
            convert1AIS = AudioSystem.getAudioInputStream(source.getFormat(), source);
            res = AudioSystem.getAudioInputStream(nouveauFormat, convert1AIS);

            /*
            byte [] buffer = new byte[8192];
            while(true){
                int readCount = convert2AIS.read(buffer, 0, buffer.length);
                if(readCount == -1){
                    break;
                }
                baos.write(buffer, 0, readCount);
            }
            
            
            */

        }
        catch(Exception e)
        {
			e.printStackTrace();

        }                       
        
        return res;


           
	}
	

	public static AudioInputStream getAudioData(String filename) throws IOException
	{
		
		AudioInputStream ais = null;

		try {
			File fileSource = new File(filename);

			if(fileSource.exists())
			{
				ais = AudioSystem.getAudioInputStream(fileSource);
				//res = new byte[ais.available()];
				
				//ais.read(res);
				//ais.getFormat();


			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		



		return ais;
	}

}
