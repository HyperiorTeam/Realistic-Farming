package realistic_farming.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import realistic_farming.commands.RFCommand;
import realistic_farming.events.FarmInteraction;
import realistic_farming.events.HoeEvent;
import realistic_farming.events.SprinklerEvents;

public class RealisticFarming extends JavaPlugin {
	
	private static RealisticFarming instance;
	
	public void onEnable() {
		
		instance = this;
		
		registerCommands();
		registerEvents();
		
		if(!getDataFolder().exists()) {
			
			getDataFolder().mkdirs();
			
		}
		
		saveDefaultConfig();
		
	}
	
	public static RealisticFarming getInstance() {
		
		return instance;
		
	}
	
	public void registerCommands() {
		
		getCommand("realisticfarming").setExecutor(new RFCommand());
		
	}
	
	public void registerEvents() {
		
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new HoeEvent(), this);
		pm.registerEvents(new FarmInteraction(), this);
		pm.registerEvents(new SprinklerEvents(), this);
		
	}
	
}
