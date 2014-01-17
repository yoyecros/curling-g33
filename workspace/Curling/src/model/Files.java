package model;

import java.util.Date;

/************************************************************
La classe Files est la classe des fichiers.<BR>
Elle a trois atributs : chemin, dateCheck et extension.<BR>
@author	Groupe 33
@version	1.0
*****************************/

public class Files {

	private String chemin;
	private Date dateCheck;
	private String extension;
	
	public Files(String pAbsolutePath)
	{
		this(pAbsolutePath, new Date());
	}
	
	public Files(String pChemin, Date pCheck)
	{
		setChemin(pChemin);
		setDate(pCheck);
		setExtension();
	}
	
	/**
	 * Permet d'avoir le chemin du fichier<BR>
	 * <BR>
	 * @return		Le chemin du fichier.
	 */

	public String getChemin() {
		return chemin;
	}
	
	/**
	 * D�fini le chemin du fichier<BR>
	 * <BR>
	 */
	
	public void setChemin(String pChemin) {
		this.chemin = pChemin;
	}
	
	/**
	 * Permet d'avoir la date � laquelle le fichier a �t� test�.<BR>
	 * <BR>
	 * @return		date.
	 */

	public Date getDate() {
		return dateCheck;
	}
	
	/**
	 * D�fini la date du fichier.<BR>
	 * <BR>
	 */

	public void setDate(Date pCheck) {
		this.dateCheck = pCheck;
	}
	
	/**
	 * Permet d'avoir l'extension du fichier.<BR>
	 * <BR>
	 * @return		L'extension du fichier.
	 */

	public String getExtension() {
		return extension;
	}
	
	/**
	 * D�fini l'extension automatiquement.<BR>
	 * <BR>
	 */
	
	public void setExtension() {
		
		String extension = "";

		int i = this.getChemin().lastIndexOf('.');
		int p = Math.max(this.getChemin().lastIndexOf('/'), this.getChemin().lastIndexOf('\\'));

		if (i > p) {
		    extension = this.getChemin().substring(i+1);
		}
		
		this.extension = extension;
	}

	
}
