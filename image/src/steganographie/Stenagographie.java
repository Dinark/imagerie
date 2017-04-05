package steganographie;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Stenagographie implements IStenagographie{
	
	
	//Doit etre entre 0 et 8
	//0 ou 8 extreme rersultat non probant
	// pour 1 Fort steganographie faible qualite de limage cache
	// pour 7 Faible steganographie (image tres visible) forte qualite de limage cache

	private static int BITWISEMOVE = 3;

	@Override
	public BufferedImage Steganofer(BufferedImage aMasque, BufferedImage cible)  {

		if(aMasque.getWidth() < cible.getWidth() || aMasque.getHeight() < cible.getHeight())
		{
			throw new ExceptionInInitializerError("l image a mettre");
		}	
		Color  ciblebyte[][] = new Color[cible.getWidth()][cible.getHeight()];
		//Color  aMasquebyte[][] = new Color[aMasque.getWidth()][aMasque.getHeight()];



		//
		for(int i = 0 ; i < cible.getWidth(); i++)
		{
			for(int j = 0 ; j< cible.getHeight();j++)
			{
				Color inter = new Color(cible.getRGB(i, j));
				int red = (inter.getRed() >> (8-BITWISEMOVE)) ;
				int blu = (inter.getBlue() >> (8-BITWISEMOVE));
				int green = (inter.getGreen() >> (8-BITWISEMOVE));;
				ciblebyte[i][j] = new Color(red,green,blu);
				
			}

			}
		BufferedImage res = new BufferedImage(aMasque.getWidth(), aMasque.getHeight(),BufferedImage.TYPE_INT_ARGB);
		
		
		for(int i = 0 ; i < aMasque.getWidth(); i++)
		{
			for(int j = 0 ; j< aMasque.getHeight();j++)
			{
				Color inter = new Color(aMasque.getRGB(i, j));
				int red = (inter.getRed() >> BITWISEMOVE) << BITWISEMOVE;
				int blu = (inter.getBlue() >> BITWISEMOVE) << BITWISEMOVE;
				int green = (inter.getGreen() >> BITWISEMOVE) << BITWISEMOVE;;
				
				if(i < cible.getWidth() && j < cible.getHeight())
				{
					red += ciblebyte[i][j].getRed();
					green += ciblebyte[i][j].getGreen();
					blu += ciblebyte[i][j].getBlue();
				}
				
				
				 res.setRGB(i, j, new Color(red,green,blu).getRGB() );
				

			}
		}		
		return res;
	}

	@Override
	public BufferedImage DeSteganofer(BufferedImage cible) {
		
		BufferedImage res = new BufferedImage(cible.getWidth(), cible.getHeight()
				,BufferedImage.TYPE_INT_ARGB);

		for(int i = 0 ; i<cible.getWidth();i++)
		{
			for(int j = 0 ; j < cible.getHeight() ; j++)
			{
				Color inter = new Color(cible.getRGB(i, j));
				int red = (inter.getRed() << (8-BITWISEMOVE)) % 256 ;
				int blu = (inter.getBlue() << (8-BITWISEMOVE)) % 256 ;
				int green = (inter.getGreen() << (8-BITWISEMOVE)) % 256 ;;
				
				
				 res.setRGB(i, j, new Color(red,green,blu).getRGB() );

			}
		}
		
		
		return res;


	}
}