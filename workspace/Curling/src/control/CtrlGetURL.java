package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.URLs;

public class CtrlGetURL {
	
	public static Vector<URLs> getUrlsFromFile(File pFile)
	{
		Vector<URLs> result = new Vector<URLs>();
		
		try{
			InputStream ips=new FileInputStream(pFile.getAbsolutePath()); 
			InputStreamReader ipsr= new InputStreamReader(ips);
			BufferedReader br= new BufferedReader(ipsr);
			String ligne;

			while ((ligne=br.readLine())!=null){
				
				String stringUrl = getURLfromLine(ligne);
				
					if(stringUrl != null)
					{
							result.add(new URLs(stringUrl));
					}
				
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		for (int i=0 ; i< result.size(); i++) System.out.println(result.get(i).getUrl());
		
		return result;
		
	}
	
	private static String getURLfromLine(String text) {
		
		String result = null;
		 
		String regex = "\\(?\\b((http|https)://|www[.])[-A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(text);
		while(m.find()) {
		String urlStr = m.group();
		if (urlStr.startsWith("(") && urlStr.endsWith(")"))
		{
		urlStr = urlStr.substring(1, urlStr.length() - 1);
		}
		result = urlStr;
		}
		
		return result;
		}
	
}
