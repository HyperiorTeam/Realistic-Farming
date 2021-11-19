package realistic_farming.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import realistic_farming.events.GrowEvents;
import realistic_farming.events.HoeEvent;

public class RealisticFarming extends JavaPlugin {
	
	private static RealisticFarming instance;
	
	public void onEnable() {
		
		instance = this;
		
		registerEvents();
		
		if(!getDataFolder().exists()) {
			
			getDataFolder().mkdirs();
			
		}
		
		saveDefaultConfig();
		
	}
	
	public static RealisticFarming getInstance() {
		
		return instance;
		
	}
	
	public void registerEvents() {
		
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new HoeEvent(), this);
		pm.registerEvents(new GrowEvents(), this);
		
	}
	
}
