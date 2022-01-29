package net.hyperior.realisticfarming.v1_8.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import realistic_farming.main.RealisticFarming;

public class FarmInteraction implements Listener {
	
	private Plugin instance;
	
	public FarmInteraction(Plugin instance) {
		
		this.instance = instance;
		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		
		Block b = e.getClickedBlock();
		ItemStack item = e.getItem();
		
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			
			if(item != null) {
				
				if(item.getType().equals(Material.INK_SAC) && item.getData().getData() == 15) {
					
					//RealisticFarming.farmInteraction.boneMeal(e);
					
				}
				
			}
			
		}else if(e.getAction().equals(Action.LEFT_CLICK_BLOCK) && RealisticFarming.getInstance().getConfig().getBoolean("hoeing.set-to-dirt-after-seed-break")) {
			
			if(b.getType().equals(Material.CROPS) || b.getType().equals(Material.PUMPKIN_STEM) || b.getType().equals(Material.MELON_STEM)) {
				
				//RealisticFarming.farmInteraction.setToDirt(b);
				
			}
			
		}
		
	}
	
}
