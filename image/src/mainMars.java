import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import compressionLaser.CompressionLaserBasique;
import compressionLaser.ICompressionLaser;
import compressionLaser.Laser;

/**
 * Main pour envoyer des images sur Mars grace a la frequence et à la intermitence
 * @author dinar
 *
 */
public class mainMars {

	public static void main (String[] args){

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

			JFrame jf2 = new JFrame("ImageTraduite");

			jf.add(new JLabel(imgIcon));
			jf.pack();
			jf.setVisible(true);




		}
		
		
		byte[] data = new byte[1];
		try {
			Path paths = Paths.get(".",path);
			data = Files.readAllBytes(paths);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		List<Byte> message = new ArrayList<Byte>();
		
		for( byte b : data)
		{
			message.add(new Byte(b));
			System.out.print(b+" ");

		}
		System.out.println();
		
		ICompressionLaser laserTraduction = new CompressionLaserBasique();
		
		Laser laserAenvoyer =  laserTraduction.coder(message);
		
		List<Byte> messageRecu = laserTraduction.decoder(laserAenvoyer);
		
		
		int erreur = 0;
		
		for(int i = 0 ; i < messageRecu.size() ;i++)
		{
			if( !message.get(i).equals(messageRecu.get(i)))
			{
				erreur++;
			}
			System.out.print(messageRecu.get(i)+" ");
			
			
		}
		System.out.println();
		
		System.out.println("Nombre erreur :"+ erreur);
		
		
	
	}			

}