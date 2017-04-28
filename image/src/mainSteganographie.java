import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import steganographie.IStenagographie;
import steganographie.Stenagographie;


public class mainSteganographie {


	public static void main (String[] args){
		System.out.println("Hello World");
		
		String path;
		String path2;
		//path = args[0];
		path = "ressources/test.pdf";
		path2 = "ressources/camion.jpeg";

		
		//URL url = ClassLoader.getSystemResource(path);
		BufferedImage img= null;
		//BufferedImage img2= null;

		byte[] data= null;
		
		try {
			img = ImageIO.read(new File(path2));
			//img2 = ImageIO.read(new File(path));
			data = Files.readAllBytes(new File(path).toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(img!= null)
		{
		
		ImageIcon imgIcon = new ImageIcon();
		imgIcon.setImage(img);
		
		JFrame jf = new JFrame("ImageMasqueDeBase");
		JFrame jfmodele = new JFrame("ImageAcacher");
		JFrame jf2 = new JFrame("ImageAvecSteganographie");
		
		JFrame jf3 = new JFrame("ImageApresDeSteganographie");

		
		jf.add(new JLabel(imgIcon));
		jf.pack();
		jf.setVisible(true);
		
		ImageIcon imgIcon2 = new ImageIcon();
		//imgIcon2.setImage(img2);
		

		jfmodele.add(new JLabel(imgIcon2));
		jfmodele.pack();
		jfmodele.setVisible(true);
		
		/*
		IRechercheCamion methodeCalcul = new RechercheCamionSimple();
		boolean theanswer = methodeCalcul.rechercheCamion(img);
		System.out.println("la reponse a ta question :"+ theanswer);		
		*/
		
		IStenagographie methode = new Stenagographie();
		

		//BufferedImage stegonagraphe = methode.Steganofer(img, img2);
		BufferedImage stegonagraphe = methode.Steganofer(img, data);
		//BufferedImage destanographe = methode.DeSteganofer(stegonagraphe);
		byte[] destanographeData = methode.DeSteganofer(stegonagraphe);
		jf2.add(new JLabel(new ImageIcon(stegonagraphe)));
		jf2.pack();
		jf2.setVisible(true);
		

		String pathresult = "ressources/toto";
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("ressources/toto");
			fos.write(destanographeData);
			fos.close();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//jf3.add(new JLabel(new ImageIcon(destanographe)));
		//jf3.pack();
		//jf3.setVisible(true);
		}
		
		
		
	}

}
