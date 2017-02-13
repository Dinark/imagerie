package compressionLaser;

import java.util.ArrayList;
import java.util.List;
/**
 * Compression Basique du signal Laser sinpirant du system JPEG
 * -Il convertit un message en frequence a partir dune frequence de base 
 * Si plusieurs valeurs consecutifs sont egales alors les valeurs suivantes sont 
 * remplacees par une absence de flash et un chiffres representant le nombre de valeurs
 * consecutives-1, cet algorithme est adapté pour les messages contenant des chaines
 * repetitifs 
 * Le message contient des caracteres de fin et de debut, mais pas de element de verification
 * 
 * @author dinar
 *
 */
public class CompressionLaserBasique implements ICompressionLaser{
	
	
	
	private static int BASEFREQUENCE = 750;

	private static int BEGINFREQUENCE = 0;

	private static int ENDFREQUENCE = 1;

	@Override
	public Laser coder(List<Byte> message) {

		Laser res = new Laser();
		
		//Frequence envoie pour le commencement du message
		res.add(new SecondeLaser(BEGINFREQUENCE));
		
	
		int numberOfOldByte =0;
		boolean isBegun = false;
		Byte OldByte = 0;
		
		
		
		for(Byte b : message)
		{
			if(!isBegun)
			{
				isBegun = true;
				res.add(new SecondeLaser(BASEFREQUENCE+b));
				OldByte = b;
			}
			else
			{
				if( OldByte == b)
				{
					numberOfOldByte++;
				}
				else
				{
					if(numberOfOldByte != 0)
					{
						res.add(new SecondeLaser(-1));
						res.add(new SecondeLaser(BASEFREQUENCE+numberOfOldByte));

					}
					OldByte = b;
					numberOfOldByte = 0;
					res.add(new SecondeLaser(BASEFREQUENCE+b));

				}
			}
			
			
		}
		
		res.add(new SecondeLaser(ENDFREQUENCE)); //frequence de fin du message

		
		
		
		return res;
	}

	@Override
	public ArrayList<Byte> decoder(Laser laser) {

		
		ArrayList<Byte> res = new ArrayList<Byte>();
		
		Boolean HasBegun = false;
		Boolean HasFinished = false;
		Byte lastByteGet =0;
		Boolean NextIsNumber = false;
		
		for (SecondeLaser l : laser)
		{
			if( !HasBegun)
			{
				HasBegun = (l.getFrequence() == BEGINFREQUENCE);
			}
			else if (!HasFinished)
			{
				if(NextIsNumber)
				{
					int times = l.getFrequence() - BASEFREQUENCE;
					
					for(int i =0; i < times ;i++)
					{
						res.add(lastByteGet);
					}
					NextIsNumber = false;
				}
				else
				{
					if(l.getFrequence() == -1)
					{
						NextIsNumber = true;
					}
					else if(l.getFrequence() == ENDFREQUENCE)
					{
						
						HasFinished  = true;
						
					}
					else
					{
						res.add((byte) (l.getFrequence() - BASEFREQUENCE));
					}
				}
			}
			
		}
		
		if(!HasFinished)
		{
			System.out.println("[Erreur] Le message ne contient pas de fin");
		}
		
		return res;
	}

}
