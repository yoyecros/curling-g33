package model;

public class URLs {

	private String url;
	
	public URLs()
	{
		this("URL indéfinie");
	}
	
	public URLs(String pUrl)
	{
		setUrl(pUrl);
	}


	/**
	 * Retourne l'url d'une URLs<BR>
	 * <BR>
	 * @return		l'url de l' URLs.
	 */
	
	public String getUrl() {
		return url;
	}

	/**
	 * Défini l'url d'une URLs<BR>
	 * <BR>
	 */
	
	public void setUrl(String pUrl) {
		this.url = pUrl;
	}
	
}
