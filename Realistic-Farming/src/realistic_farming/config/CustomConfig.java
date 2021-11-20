package realistic_farming.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

abstract class CustomConfig {
	
	private static File file;
    private static FileConfiguration config;
    
    private String path;
    private String name;
    
    public CustomConfig(String path, String name) {
    	
    	this.path = path;
    	this.name = name;
    	
    	createConfig();
    	
    }
    
    public static FileConfiguration getConfig() {
    	
        return config;
        
    }
    
    private void createConfig() {
    	file = new File(path, name);
        config = new YamlConfiguration();
        
        try {
        	if (!file.exists()) {
            	
                file.createNewFile();
                
            }
        	
        	config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        
    }
    
    public static void saveConfig() {
    	
    	try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    
}
