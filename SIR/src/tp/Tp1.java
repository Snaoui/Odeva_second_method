package tp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * Cree trois fichier a partir d'un fichier :
 * </br>
 * Un contenant le noms des utilisateurs
 * </br>
 * Un contenant le noms des themes
 * </br>
 * Un contenant le nombre d'occurence du couple utilisateur/theme
 * 
 * @author Remy
 *
 */

public class Tp1 {
	private Map<String, Map<String, Integer>> map;
	private List<String> themes;
	
	/**
	 * Constructeur de la classe
	 */
	public Tp1(){
		map = new HashMap<String, Map<String,Integer>>();
		themes = new ArrayList<String>();
	}
	
	/**
	 * Parse le fichier et alimente la structure de donnees
	 * @param name Nom du fichier a parser
	 * @throws IOException 
	 */
	public void parser(String name) throws IOException{
		BufferedReader buffer = new BufferedReader(new FileReader(new File(name)));
		String line;
		
		while((line = buffer.readLine()) != null){
			String[] lineCut = line.split(";");
			
			if(lineCut.length == 3){
				if(!map.containsKey(lineCut[1]))
					map.put(lineCut[1], new HashMap<String, Integer>());
				
				if(!map.get(lineCut[1]).containsKey(lineCut[2]))
					map.get(lineCut[1]).put(lineCut[2], 0);
				
				map.get(lineCut[1]).put(lineCut[2], map.get(lineCut[1]).get(lineCut[2]) + 1);
				themes.add(lineCut[2]);
			}
		}
		
		buffer.close();
	}
	
	/**
	 * Eneleve les doublons de la liste
	 */
	public void clean(){
        Set<String> set = new HashSet<String>() ;
        set.addAll(themes) ;
        themes = new ArrayList<String>(set) ;

	}
	
	/**
	 * Cree les trois fichier
	 */
	public void createFile() throws IOException{
		BufferedWriter bufferName = new BufferedWriter(new FileWriter(new File("name.txt")));
		BufferedWriter bufferTheme = new BufferedWriter(new FileWriter(new File("theme.txt")));
		BufferedWriter bufferOccurence = new BufferedWriter(new FileWriter(new File("occurence.txt")));
		
		for(String key : map.keySet())
			bufferName.write(key + "\n");
		
		bufferName.close();
		
		for(String theme : themes)
			bufferTheme.write(theme + "\n");
		
		bufferTheme.close();
		
		for(String key : map.keySet()){
			for(String keyT : map.get(key).keySet()){
				bufferOccurence.write(key + ";" + keyT + ";" + map.get(key).get(keyT) + "\n");
			}
		}
		
		bufferOccurence.close();
	}
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		Tp1 tp1 = new Tp1();
		try {
			tp1.parser("Log-clients-themes.txt");
			tp1.clean();
			tp1.createFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
