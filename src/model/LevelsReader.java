package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class LevelsReader {
	
	public LevelsReader() {
		
	}
	public HashMap<String,Character[][]> readLevels() throws IOException{
		HashMap<String, Character[][]> levels =  new HashMap<>();
		System.out.println(getClass().getResource(""));
		File levelsFolder =  new File("resources/levels");
		System.out.println("Reading Levels....");
		for (File newLevel : levelsFolder.listFiles()) {
		            System.out.println("Reading : "+newLevel.getName().substring(0, newLevel.getName().length()-4) + "....");
		            Character[][] map =  new Character[30][30];
		            BufferedReader reader = new BufferedReader(new FileReader(newLevel));
		            String line;
		            int cnt = 0;
		            while ((line = reader.readLine()) != null) {
		               // System.out.println(line);
		                line = line.replace("\t", "");
		                line = line.replace(" ", "");
		                
		                for(char c : line.toCharArray()) {
		                	if(c !=' ') {
		                		if(cnt<900) {
		                			if(c == ' ')continue;
		                			map[cnt/30][cnt%30] = c;
		                		//System.out.println("added : " + c);
		                		cnt++;
		                		}
		                	}
		                }
		            }
		            levels.put(newLevel.getName().substring(0, newLevel.getName().length()-4), map);
		            reader.close();
		}		
		return levels;
	}
}
