package testDeCamion;

import java.awt.Color;
import java.awt.image.BufferedImage;


/**
 * Un camion est blanc et surtout rectangle le ciel nest pas vraiment rectangle,
 * Ainsi la recherche dune forme rectangle blanc permet de deceler camion ou au moins dessayer
 * @author dinar
 *
 */
public class RechercheCamionSimple implements IRechercheCamion{

	public static double COEFFICIENT = 1.5;
	
	
	
	/**
	 * Cette partie  analyse les couleurs de dessinDeConstructions 
	 * Le but de cette methode est de a partir d'une image simplifie de trouver les formes rectangulaire
	 * Si une forme rectangulaire est decele il est fort possible qu un camion soit trouve 
	 * 
	 */
	@Override
	public boolean rechercheCamion(BufferedImage img) {



		BufferedImage imgAanalyser = dessinDeConstruction(img);




		/*
		 * cest vide ici
		 */





		return false;
	}
	
	
	/**
	 * methode qui renvoie une image de meme taille il teste chacun des pixels et verifie les voisins
	 * directe (gauche droite et bas), si il voit une difference nette de luminence il colore l'image 
	 * avec une couleur (R pour gauche , G pour droite et B pour bas ) il contient ainsi 8 couleur au maximum
	 * le pixels est colore uniquement si il est plus clair que son voisin
	 */
	@Override
	public BufferedImage dessinDeConstruction(BufferedImage img) {

		BufferedImage res  = new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_INT_RGB);

		
		for(int y=0 ;y < img.getHeight(); y++)
		{
			for(int x=0 ; x < img.getWidth();x++)
			{
				boolean r =  IsDifferentGauche(x, y, img);
				boolean g = IsDifferentDroite(x, y, img);
				boolean b = IsDifferentBas(x, y, img);
				
				int cr = 0;
				int cg = 0;
				int cb = 0;
				
				if(r)
				{
					cr = 255;
				}
				if(g)
				{
					cg = 255;
				}
				if(b)
				{
					cb = 255;
				}
				
				
				res.setRGB(x, y, 
						new Color(
								cr,
								cg,
								cb
								).getRGB());

			}
		}


		return null;
	}

	/**
	 * permet de savoir si il ya une nette difference entre la gauche du pixel et ce dernier
	 * on regarde si le pixel est plus clair que le pixel de gauche
	 */
	private Boolean IsDifferentGauche(int x, int y, BufferedImage img )
	{
		boolean res= false;


		if( !(x-1 < 0 && y-1 < 0 && y+1>img.getHeight() && x+1>img.getWidth()) )
		{
			Color pixel = new Color(img.getRGB(x, y));
			Color pixelnext = new Color(img.getRGB(x-1, y));

			
			
			//des chiffres magiques
			int luminanceP = (int) (0.3* pixel.getRed() + 0.59* pixel.getGreen() + 0.11 *pixel.getBlue());
			int luminancePn = (int) (0.3* pixelnext.getRed() 
					+ 0.59* pixelnext.getGreen() 
					+ 0.11 *pixelnext.getBlue());
			
			
			//encore des chiffres magiques

			res =( luminanceP < luminancePn*COEFFICIENT);

		}

		return res;
	}
	
	
	/**
	 * permet de savoir si il ya une nette difference entre la gauche du pixel et ce dernier
	 * on regarde si le pixel est plus clair que le pixel de gauche
	 */
	private boolean IsDifferentDroite(int x, int y, BufferedImage img )
	{
		boolean res= false;


		if( !(x-1 < 0 && y-1 < 0 && y+1>img.getHeight() && x+1>img.getWidth()) )
		{
			Color pixel = new Color(img.getRGB(x, y));
			Color pixelnext = new Color(img.getRGB(x+1, y));

			
			
			//des chiffres magiques
			int luminanceP = (int) (0.3* pixel.getRed() + 0.59* pixel.getGreen() + 0.11 *pixel.getBlue());
			int luminancePn = (int) (0.3* pixelnext.getRed() 
					+ 0.59* pixelnext.getGreen() 
					+ 0.11 *pixelnext.getBlue());
			
			
			//encore des chiffres magiques

			res =( luminanceP < luminancePn*COEFFICIENT);

		}

		return res;
	}
	
	private boolean IsDifferentBas(int x, int y, BufferedImage img )
	{
		boolean res= false;


		if( !(x-1 < 0 && y-1 < 0 && y+1>img.getHeight() && x+1>img.getWidth()) )
		{
			Color pixel = new Color(img.getRGB(x, y));
			Color pixelnext = new Color(img.getRGB(x, y+1));

			
			
			//des chiffres magiques
			int luminanceP = (int) (0.3* pixel.getRed() + 0.59* pixel.getGreen() + 0.11 *pixel.getBlue());
			int luminancePn = (int) (0.3* pixelnext.getRed() 
					+ 0.59* pixelnext.getGreen() 
					+ 0.11 *pixelnext.getBlue());
			
			
			//encore des chiffres magiques

			res =( luminanceP < luminancePn*COEFFICIENT);

		}

		return res;
	}


}
