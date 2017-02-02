import halfToning.IHalfingProcessus;
import halfToning.halfToningBasique;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class main {


	public static void main (String[] args){
		System.out.println("Hello World");
		
		String path;
		//path = args[0];
		path = "ressources/invasionzombie.png";

		//URL url = ClassLoader.getSystemResource(path);
		BufferedImage img= null;
		
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(img!= null)
		{
		
		ImageIcon imgIcon = new ImageIcon();
		imgIcon.setImage(img);
		
		JFrame jf = new JFrame("testImage");
		
		JFrame jf2 = new JFrame("ImageTransforme");
		
		jf.add(new JLabel(imgIcon));
		jf.pack();
		jf.setVisible(true);
		
		
		
		/*
		IRechercheCamion methodeCalcul = new RechercheCamionSimple();
		boolean theanswer = methodeCalcul.rechercheCamion(img);
		System.out.println("la reponse a ta question :"+ theanswer);		
		*/
		
		IHalfingProcessus methode = new halfToningBasique();
		
		
		jf2.add(new JLabel(new ImageIcon(methode.convertirImage(img))));
		jf2.pack();
		jf2.setVisible(true);
		}
		
		
		
	}

}
