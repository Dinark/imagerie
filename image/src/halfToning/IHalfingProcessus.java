package halfToning;

import java.awt.image.BufferedImage;

public interface IHalfingProcessus {
	
	
	/**
	 * Permet de transformer un image en version imprimable CMYB
	 * @param imageATrans
	 * @return image transforme en composante cyan magenta jaune noir
	 */
	public BufferedImage convertirImage(BufferedImage imageATrans);
	

}
