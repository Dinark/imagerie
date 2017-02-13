package compressionLaser;

/**
 * Unite de laser dans une unité de temps de l'envoi du laser peut etre vide 
 * il contient une frequence
 * @author dinar
 *
 */
public class SecondeLaser {
	
	/**
	 * la frequence du laser si la frequence est -1 alors le laser est eteint
	 */
	int frequence;

	public SecondeLaser(int frequence)
	{
		this.frequence = frequence;
	}
	
	public int getFrequence() {
		return frequence;
	}

	public void setFrequence(int frequence) {
		this.frequence = frequence;
	}
	
	

}
