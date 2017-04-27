package soundCompresion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface ISoundSteganographe {
	
	
	public File encode(String masquePath,String targetPath,String resultPath) throws IOException ;
	
	
	public File decode(String masquePath);
	

}
