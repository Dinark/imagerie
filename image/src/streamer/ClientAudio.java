package streamer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.sound.sampled.*;

public class ClientAudio {


	private boolean isActive = false;

	private boolean isReady = false;

	private int port;

	private String adress;


	public void initClient(int port,String adress)
	{
		this.port = port;
		this.adress = adress;
		isReady = true;
	}


	public void readFile(String fileName)
	{
		File soundFile = new File(fileName);
		if (!soundFile.exists() || !soundFile.isFile())
		{
			throw new IllegalArgumentException("not a file: " + soundFile);
		}


		System.out.println("Client: " + soundFile);
			try {
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(soundFile));
				play(in);
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}



	public void readStream()
	{
		if(!isActive && isReady)
		{

			try (Socket socket = new Socket(adress, port)) {

				if (socket.isConnected()) {
					InputStream in = new BufferedInputStream(socket.getInputStream());

					System.out.println(in.toString());
					play(in);

				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}

	}

	private static synchronized void play(final InputStream in) 
			throws UnsupportedAudioFileException, 
			IOException,
			LineUnavailableException {
		AudioInputStream ais = AudioSystem.getAudioInputStream(in);
	/*	try (Clip clip = AudioSystem.getClip()) {
			clip.open(ais);
			clip.start();
		}*/

	}
}
