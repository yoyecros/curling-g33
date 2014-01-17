package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Vector;

import model.Files;

/************************************************************
La classe CtrlHistory réalise toutes les opérations concernant l'historique.<BR>
@author	Groupe 13
@version	1.0
*****************************/

public class CtrlHistory {

	public static void createHistory()
	{
		File file = new File("historique");
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Vector<Files> getFilesFromHistory()
	{
		Vector<Files> result = new Vector<Files>();
		SimpleDateFormat formatter = new SimpleDateFormat("'Le' dd/MM/yyyy 'à' hh:mm:ss");
		
		try{
			InputStream ips=new FileInputStream("historique"); 
			InputStreamReader ipsr= new InputStreamReader(ips);
			BufferedReader br= new BufferedReader(ipsr);
			String ligne;
			String elem[];
			elem = new String[2];
			while ((ligne=br.readLine())!=null){
				
					elem = ligne.split(";");
					result.add(new Files(elem[0], formatter.parse(elem[1])));
				
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	public static void updateHistory(Files pFile) {
		
		boolean bNewLine = true;
		
		Vector<String> filesHistory = new Vector<String>();
		Vector<String> filesStringHistory = new Vector<String>();
		
		SimpleDateFormat formatter = new SimpleDateFormat("'Le' dd/MM/yyyy 'à' hh:mm:ss");
		
        try {
            File inFile = new File("historique");

            if (!inFile.isFile()) {
                createHistory();
                inFile = new File("historique");
            }

            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader("historique"));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line = null;
            String elem[];
            elem = new String[2];

            //Lit le fichier original et écrit dans le nouveau
            while ((line = br.readLine()) != null) {
            	
            	filesHistory.add(line);
            	
            	elem = line.split(";");
            	
            	filesStringHistory.add(elem[0]);
            }
            
            br.close();
            
            for(int i = 0; i < filesHistory.size(); i++)
            {
            	if(filesStringHistory.get(i).equals(pFile.getChemin()))
            	{
            		
            		pw.println(pFile.getChemin() + ";" + formatter.format(pFile.getDate()));
            		System.out.println( formatter.format(pFile.getDate()));
            		
            		bNewLine = false;
            	}
            	else
            	{
            		pw.println(filesHistory.get(i));
            	}
            }
            
            if(bNewLine)
            {
            	pw.println(pFile.getChemin() + ";" + formatter.format(pFile.getDate()));
            }
            	
            pw.flush();
            pw.close();
            

            //Supprime le fichier original
            if (!inFile.delete()) {
                System.out.println("Erreur lors de la suppression de l'ancien fichier.");
                return;
            }

            //Renomme le nouveau fichier comme le fichier original
            if (!tempFile.renameTo(inFile)) System.out.println("Erreur lors du renommage du fichier.");

        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
	
}