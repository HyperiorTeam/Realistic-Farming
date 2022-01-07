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
import realistic_farming.utils.actionbar.ActionBarManager;
import realistic_farming.utils.actionbar.versions.ActionBarManager_v1_8_R3;
import realistic_farming.utils.actionbar.versions.ActionBarManager_v1_9_plus;
import realistic_farming.utils.farminteraction.FarmInteractionManager;
import realistic_farming.utils.farminteraction.versions.FarmInteractionManager_v1_8_R3;
import realistic_farming.utils.farminteraction.versions.FarmInteractionManager_v1_9_plus;

public class RealisticFarming extends JavaPlugin {
	
	private static RealisticFarming instance;
	
	public static ActionBarManager actionbar;
	public static FarmInteractionManager farmInteraction;
	
	public void onEnable() {
		
		instance = this;
		
		registerCommands();
		registerEvents();
		
		loadManagers();
		
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
	
	private void registerCommands() {
		
		getCommand("realisticfarming").setExecutor(new RFCommand());
		
	}
	
	private void registerEvents() {
		
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new HoeEvent(), this);
		pm.registerEvents(new FarmInteraction(), this);
		pm.registerEvents(new SprinklerEvents(), this);
		
	}
	
	private void loadManagers() {
		
		if(Bukkit.getVersion().contains("1.8")) {
			
			actionbar = new ActionBarManager_v1_8_R3();
			farmInteraction = new FarmInteractionManager_v1_8_R3();
			
		}else {
			
			actionbar = new ActionBarManager_v1_9_plus();
			farmInteraction = new FarmInteractionManager_v1_9_plus();
			
		}
		
	}
	
}
