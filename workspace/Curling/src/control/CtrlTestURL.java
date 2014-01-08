package control;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Vector;

import model.URLs;

/************************************************************
La classe CtrlTestURL réalise toutes les opérations de test des URLs<BR>
@author	Groupe 33
@version	1.0
*****************************/

public class CtrlTestURL {

	public static Vector<URLs> checkListURLs(Vector<URLs> pUrls)
	{
		Vector<URLs> result = new Vector<URLs>();
		
		for(int i = 0; i < pUrls.size() ; i++)
		{
			if(!checkURL(pUrls.get(i)))
			{
				result.add(pUrls.get(i));
			}
		}
		
		return result;
	}
	
	public static boolean checkURL(URLs pUrl)
	{
		
		boolean result = false;
		
		URL url;
		try {
			url = new URL(pUrl.getUrl());
			
			try {
				HttpURLConnection huc;
				huc = (HttpURLConnection) url.openConnection();
				huc.setRequestMethod("HEAD");
				int responseCode = huc.getResponseCode();

				if (responseCode != 404) {
				System.out.println("GOOD");
				result = true;
				} else {
				System.out.println("BAD");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				
				if(e instanceof UnknownHostException)
				{
					System.out.println("BAD HOST");
				}
				e.printStackTrace();
				
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		return result;
		
	}
	
}
