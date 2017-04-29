package soundCompresion;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

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
            //AudioFormat format = new AudioFormat(44100, 16, 1, true, false);
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
	
	/*public static byte [] getAudioDataBytes(byte [] sourceBytes, AudioFormat audioFormat) throws UnsupportedAudioFileException, IllegalArgumentException, Exception {
	    if(sourceBytes == null || sourceBytes.length == 0 || audioFormat == null){
	        throw new IllegalArgumentException("Illegal Argument passed to this method");
	    }

	    try (final ByteArrayInputStream bais = new ByteArrayInputStream(sourceBytes);
	         final AudioInputStream sourceAIS = AudioSystem.getAudioInputStream(bais)) {
	        AudioFormat sourceFormat = sourceAIS.getFormat();
	        AudioFormat convertFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sourceFormat.getSampleRate(), 16, sourceFormat.getChannels(), sourceFormat.getChannels()*2, sourceFormat.getSampleRate(), false);
	        try (final AudioInputStream convert1AIS = AudioSystem.getAudioInputStream(convertFormat, sourceAIS);
	             final AudioInputStream convert2AIS = AudioSystem.getAudioInputStream(audioFormat, convert1AIS);
	             final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
	            byte [] buffer = new byte[8192];
	            while(true){
	                int readCount = convert2AIS.read(buffer, 0, buffer.length);
	                if(readCount == -1){
	                    break;
	                }
	                baos.write(buffer, 0, readCount);
	            }
	            return baos.toByteArray();
	        }
	    }
	}*/
	
	public static byte [] getAudioDataBytes(byte [] sourceBytes, AudioFormat audioFormat) throws UnsupportedAudioFileException, IllegalArgumentException, Exception{
        if(sourceBytes == null || sourceBytes.length == 0 || audioFormat == null){
            throw new IllegalArgumentException("Illegal Argument passed to this method");
        }

        ByteArrayInputStream bais = null;
        ByteArrayOutputStream baos = null;
        AudioInputStream sourceAIS = null;
        AudioInputStream convert1AIS = null;
        AudioInputStream convert2AIS = null;

        try{
            bais = new ByteArrayInputStream(sourceBytes);
            sourceAIS = AudioSystem.getAudioInputStream(bais);
            AudioFormat sourceFormat = sourceAIS.getFormat();
            AudioFormat convertFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sourceFormat.getSampleRate(), 16, sourceFormat.getChannels(), sourceFormat.getChannels()*2, sourceFormat.getSampleRate(), false);
            convert1AIS = AudioSystem.getAudioInputStream(convertFormat, sourceAIS);
            convert2AIS = AudioSystem.getAudioInputStream(audioFormat, convert1AIS);

            baos = new ByteArrayOutputStream();

            byte [] buffer = new byte[8192];
            while(true){
                int readCount = convert2AIS.read(buffer, 0, buffer.length);
                if(readCount == -1){
                    break;
                }
                baos.write(buffer, 0, readCount);
            }
            return baos.toByteArray();
        } catch(UnsupportedAudioFileException uafe){
            //uafe.printStackTrace();
            throw uafe;
        } catch(IOException ioe){
            //ioe.printStackTrace();
            throw ioe;
        } catch(IllegalArgumentException iae){
            //iae.printStackTrace();
            throw iae;
        } catch (Exception e) {
            //e.printStackTrace();
            throw e;
        }finally{
            if(baos != null){
                try{
                    baos.close();
                }catch(Exception e){
                }
            }
            if(convert2AIS != null){
                try{
                    convert2AIS.close();
                }catch(Exception e){
                }
            }
            if(convert1AIS != null){
                try{
                    convert1AIS.close();
                }catch(Exception e){
                }
            }
            if(sourceAIS != null){
                try{
                    sourceAIS.close();
                }catch(Exception e){
                }
            }
            if(bais != null){
                try{
                    bais.close();
                }catch(Exception e){
                }
            }
        }
    }
	
	public static AudioInputStream conversionFormat(AudioInputStream source, AudioFormat nouveauFormat)
	{

		
        AudioInputStream convert1AIS = null;
        AudioInputStream res = null;
        
   

        try{
            
        	AudioFormat sourceFormat = source.getFormat();
            AudioFormat convertFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
            		sourceFormat.getSampleRate(), 16, 
            		sourceFormat.getChannels(), 
            		sourceFormat.getChannels()*2, 
            		sourceFormat.getSampleRate(), false);
            convert1AIS = AudioSystem.getAudioInputStream(source.getFormat(), source);
            res = AudioSystem.getAudioInputStream(convertFormat, convert1AIS);

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
			else	
			{
				throw new FileNotFoundException();
			}


		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		



		return ais;
	}

}
