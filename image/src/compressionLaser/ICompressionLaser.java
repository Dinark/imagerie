package compressionLaser;

import java.util.ArrayList;
import java.util.List;

public interface ICompressionLaser {
	
	
	Laser coder(List<Byte>  message);

	
	
	ArrayList<Byte> decoder(Laser laser);
	

}
