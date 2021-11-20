package realistic_farming.main;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import realistic_farming.commands.RFCommand;
import realistic_farming.config.SprinklerData;
import realistic_farming.events.FarmInteraction;
import realistic_farming.events.HoeEvent;
import realistic_farming.events.SprinklerEvents;
import realistic_farming.sprinkler.SprinklerManager;

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
		
		if(!new File(getDataFolder() + "\\data").exists()) {
			
			new File(getDataFolder() + "\\data").mkdirs();
			
		}
		
		new SprinklerData(getDataFolder() + "\\data", "sprinklers.yml");
		
		SprinklerManager.loadSprinklers();
		
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
