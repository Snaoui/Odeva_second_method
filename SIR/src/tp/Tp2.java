package tp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Permet d'analyser les resultats du TP1
 * 
 * @author hanquez
 *
 */

public class Tp2 {
	
	/**
	 * Compte le nombre de ligne d'un fichier
	 * @param name Nom du fichier
	 * @return Le nmobre de ligne
	 * @throws IOException
	 */
	public int count(String name) throws IOException {
		BufferedReader buffer = new BufferedReader(new FileReader(new File(name)));
		int nbClient = 0;
		
		while(buffer.readLine() != null)
			nbClient++;		
		buffer.close();
		
		return nbClient;
	}
	
	/**
	 * Permet de recuperer les 3 themes les plus populaires
	 * @param name Nom du fichier
	 * @return Un tableau contenant les themes
	 * @throws IOException
	 */
	public String[] populateTheme(String name) throws IOException{
		BufferedReader buffer = new BufferedReader(new FileReader(new File(name)));
		String line;
		Map<String, Integer> themeOccur = new HashMap<String, Integer>();
		String[] morePopulate = new String[3];
		
		while((line = buffer.readLine()) != null){
			String[] cutLine = line.split(";");
			
			if(themeOccur.containsKey(cutLine[1]))
				themeOccur.put(cutLine[1], themeOccur.get(cutLine[1])+Integer.parseInt(cutLine[2]));
			else
				themeOccur.put(cutLine[1], Integer.parseInt(cutLine[2]));
		}
		
		for(int i = 0; i < 3; i++){
			int max = 0;
			
			for(String key : themeOccur.keySet()){
				if(themeOccur.get(key) >= max){
					max = themeOccur.get(key);
					morePopulate[i] = key;
				}
			}
			
			themeOccur.remove(morePopulate[i]);
		}
		
		buffer.close();
		
		return morePopulate;
	}
	
	/**
	 * Permet de recuperer les 5 clients qui ont acheté le plus sur les trois plus themes
	 * @param name Nom du fichier
	 * @param theme Les themes
	 * @return Un tableau contenant les clients
	 * @throws IOException
	 */
	public String[] populateCustomer(String name, String[] theme) throws IOException{
		BufferedReader buffer = new BufferedReader(new FileReader(new File(name)));
		String line;
		Map<String, Integer> themeOccur = new HashMap<String, Integer>();
		String[] morePopulate = new String[5];
		
		while((line = buffer.readLine()) != null){
			String[] cutLine = line.split(";");
			
			if(theme[0].equals(cutLine[1]) || theme[1].equals(cutLine[1]) || theme[2].equals(cutLine[1])){
				if(themeOccur.containsKey(cutLine[0]))
					themeOccur.put(cutLine[0], themeOccur.get(cutLine[0])+Integer.parseInt(cutLine[2]));
				else
					themeOccur.put(cutLine[0], Integer.parseInt(cutLine[2]));
			}
		}
		
		for(int i = 0; i < 5; i++){
			int max = 0;
			
			for(String key : themeOccur.keySet()){
				if(themeOccur.get(key) >= max){
					max = themeOccur.get(key);
					morePopulate[i] = key;
				}
			}
			
			themeOccur.remove(morePopulate[i]);
		}
		
		buffer.close();
		
		return morePopulate;
	}
	
	public static void main(String[] args) {
		Tp2 tp2 = new Tp2();
		try {
			System.out.println("Nombre de client : " + tp2.count("name.txt"));
			System.out.println("Nombre de theme : " + tp2.count("theme.txt"));
			
			String[] populate = tp2.populateTheme("occurence.txt");
			System.out.println("More populate theme :");
			System.out.println("   1 : " + populate[0]);
			System.out.println("   2 : " + populate[1]);
			System.out.println("   3 : " + populate[2]);
			
			String[] populateC = tp2.populateCustomer("occurence.txt", populate);
			System.out.println("More populate client :");
			System.out.println("   1 : " + populateC[0]);
			System.out.println("   2 : " + populateC[1]);
			System.out.println("   3 : " + populateC[2]);
			System.out.println("   4 : " + populateC[3]);
			System.out.println("   5 : " + populateC[4]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
