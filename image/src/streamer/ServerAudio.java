package streamer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerAudio {

	private boolean isActive = false;

	private boolean isReady = false;

	private int port;

	public void initServer(int port)
	{
		this.port = port;
		isReady = true;
	}


	public void  sendMusic(String fileName)
	{
		if(isReady && !isActive)
		{
			File soundFile = new File(fileName);
			if (!soundFile.exists() || !soundFile.isFile())
			{
				throw new IllegalArgumentException("[ServerAudio] sendMusic : not a file" + soundFile);
			}
		
			
			try {
				ServerSocket serverSocket = new ServerSocket(port);
				FileInputStream in = new FileInputStream(soundFile);


				if(serverSocket.isBound() ){
					Socket clientSocket =  serverSocket.accept();

					OutputStream clientOutput = clientSocket.getOutputStream();

					byte buffer[] = new byte[2048];
					int count;
					while ((count = in.read(buffer)) != -1)
						clientOutput.write(buffer, 0, count);
				}
				
				serverSocket.close();

			}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}






}
}

