package realistic_farming.config;

import java.util.Set;

import org.bukkit.Location;

public class SprinklerData extends CustomConfig {

	public SprinklerData(String path, String name) {
		
		super(path, name);
		
	}
	
	public static void addSprinkler(String id, Location l) {
		
		getConfig().set(id + ".location", l);
		
		saveConfig();
		
	}
	
	public static Set<String> getSprinklers() {
		
		return getConfig().getKeys(false);
		
	}
	
}
