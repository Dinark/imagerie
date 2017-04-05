package steganographie;

import java.awt.image.BufferedImage;

public interface IStenagographie {
	
	
	
	public BufferedImage Steganofer(BufferedImage Amasque,BufferedImage cible);
	
	
	public BufferedImage DeSteganofer(BufferedImage cible);


}
