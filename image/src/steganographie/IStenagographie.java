package steganographie;

import java.awt.image.BufferedImage;

public interface IStenagographie {
	
	

	public BufferedImage Steganofer(BufferedImage img, byte[] data);
	
	
	public byte[] DeSteganofer(BufferedImage img);


}
