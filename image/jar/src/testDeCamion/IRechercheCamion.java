package testDeCamion;

import java.awt.Image;
import java.awt.image.BufferedImage;

public interface IRechercheCamion {
	/**
	 * Algorithme de recherche de camion
	 * @param img image de recherche
	 * @return
	 */
	public boolean rechercheCamion(BufferedImage img);
	
	
	public BufferedImage dessinDeConstruction(BufferedImage img);
}
