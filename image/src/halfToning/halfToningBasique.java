package halfToning;

import java.awt.Color;
import java.awt.image.BufferedImage;


/**
 * Converti l image en grille de couleurs CYMB 
 * augmente la taille de image par 4 
 * chaque quadruplet de pixels contient les valeurs suivantes
 * 			haut gauche : cyan
 * 			bas gauche : jaune
 * 			haut droit : magenta
 * 			bas droit : noir
 * @author dinar
 *
 */
public class halfToningBasique implements IHalfingProcessus {

	@Override
	public BufferedImage convertirImage(BufferedImage imageATrans) {


		int tailleX = imageATrans.getWidth();
		int tailleY = imageATrans.getHeight();


		BufferedImage res = new BufferedImage(tailleX*2, tailleY*2, BufferedImage.TYPE_INT_RGB);

		for(int x = 0; x < tailleX;x++)
		{
			for(int y = 0; y < tailleY;y++)
			{

				Color pixel =  new Color(imageATrans.getRGB(x, y));

				int cyan = 0;
				int magenta = 0;
				int yellow = 0;
				int black = 255 - Math.max(pixel.getBlue(), 
						Math.max(pixel.getGreen(),pixel.getRed()));
				
				System.out.println("RGB  = "+ pixel.getRed()+" "+pixel.getGreen()
						+" "+ pixel.getBlue());
				
				if(black != 255)
				{
					cyan = ((255 - pixel.getRed() - black) / (255 - black))*255; 
					magenta =  ((255 - pixel.getGreen() - black) / (255 - black))*255;
					yellow =  ((255 - pixel.getBlue() - black) / (255 - black))*255; 
				}
				/*
				int nRed =  (255-cyan) * (255-black);
				int nGreen =  (255-magenta) * (255-black);
				int nBlue =  (255-yellow) * (255-black);
*/
				System.out.println("CYMB = "+cyan+" "+yellow+" "+magenta+" "+black);
				
				//Dessin des pixels
				//cyan
				
				res.setRGB((2*x), (2*y),new Color(0, cyan, cyan).getRGB());

				//magenta
				res.setRGB((2*x)+1, (2*y),new Color(magenta, 0, magenta).getRGB());
				
				//yellow
				res.setRGB((2*x), (2*y)+1,new Color(yellow, yellow,0 ).getRGB());
				
				//Black
				res.setRGB((2*x)+1, (2*y)+1,new Color(255 - black,255 - black,255 - black).getRGB());
			}
		}
		return res;
	}

}
