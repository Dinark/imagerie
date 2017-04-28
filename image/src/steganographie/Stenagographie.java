package steganographie;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Stenagographie implements IStenagographie{
	

	// On cache dans Chaques couleurs de pixel (rgb) un octet de donnée. (2 + 3 + 3)
	private static int RBIT = 2;
	private static int GBIT = 3;
	private static int BBIT = 3;

	private static int BITWISEMOVE = 3;

	@Override
	public BufferedImage Steganofer(BufferedImage img, byte[] data)
	{
		int width = img.getWidth();
		int height = img.getHeight();
		int mult = 1;
		while(width * height < data.length + 10) // we need an image capable of holding the data
		{
			mult *= 2;
			width = mult * img.getWidth();
			height = mult * img.getHeight();
			
			if(width * height > 8192*8192)
				throw new ExceptionInInitializerError("file too large");
		}
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		byte[] dataLength = new byte[4]; // 4 bytes in an int
        int dataLengthInt = data.length;
        for (int i = 3; i >= 0; i--)
        {
        	dataLength[i] = (byte)dataLengthInt; // store 1 byte in the byte array
        	dataLengthInt = dataLengthInt >> 8;  // and then right shift by 8 bits to get the next byte in position
        }
		
		boolean lengthInput = true;
		int k = 0;
		
		for(int i = 0 ; i < width; i++)
		{
			for(int j = 0 ; j< height;j++)
			{
				Color inter = new Color(img.getRGB(i/mult, j/mult));//the new image is bigger by mult times.
				int red = (inter.getRed() >> RBIT) << RBIT;
				int green = (inter.getGreen() >> GBIT) << GBIT;
				int blue = (inter.getBlue() >> BBIT) << BBIT;
				int toRed = 0, toGreen = 0, toBlue = 0;
				
				if(lengthInput)
				{
					toRed = (dataLength[k]) >> (8 - RBIT);
					toGreen = (((dataLength[k]&(1<<(GBIT + BBIT))-1))) >> BBIT;
					toBlue = (((dataLength[k]&(1<<BBIT)-1)));
					++k;
					if(k >= 4)
					{
						k = 0;
						lengthInput = false;
					}
				}
				else
				{
					if(k < data.length)
					{
						toRed = (data[k]) >> (8 - RBIT);
						toGreen = (((data[k]&(1<<(GBIT + BBIT))-1))) >> BBIT;
						toBlue = (((data[k]&(1<<BBIT)-1)));
						++k;
					}
				}
				if(toRed < 0)
					toRed = (int)Math.pow(2,  (RBIT-1)) + (int)(Math.pow(2,  (RBIT-1)) + toRed); 
				if(toBlue < 0)
					toBlue = (int)Math.pow(2,  (RBIT-1)) + (int)(Math.pow(2,  (RBIT-1)) + toBlue); 
				if(toGreen < 0)
					toGreen = (int)Math.pow(2,  (RBIT-1)) + (int)(Math.pow(2,  (RBIT-1)) + toGreen); 
				red += toRed;
				green += toGreen;
				blue += toBlue;
				result.setRGB(i, j, new Color(red,green,blue).getRGB());
			}			
		}
		return result;
	}

	@Override
	public byte[] DeSteganofer(BufferedImage img)
	{		
		byte[] data = new byte[0];
		int dataLength = 0;
		boolean lengthOutput = true;
		int k = 4;

		for(int i = 0 ; i < img.getWidth();i++)
		{
			for(int j = 0 ; j < img.getHeight() ; j++)
			{
				Color inter = new Color(img.getRGB(i, j));
				int redBits = (((inter.getRed()&(1<<RBIT)-1)));
				int greenBits = (((inter.getGreen()&(1<<GBIT)-1)));
				int blueBits = (((inter.getBlue()&(1<<BBIT)-1)));
				byte dataByte = (byte)((redBits << (GBIT + BBIT)) + (greenBits << BBIT) + blueBits);
				
				if(lengthOutput)
				{
					dataLength <<= 8;
					dataLength += 0xFF & dataByte;
					--k;
					if(k <= 0)
					{
						data = new byte[dataLength];
						lengthOutput = false;
					}					
				}
				else
				{
					if(k < dataLength)
					{
						data[k] = dataByte;
						++k;
					}
				}
			}
		}
		return data;
	}
}