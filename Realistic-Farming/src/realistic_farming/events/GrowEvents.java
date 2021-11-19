package realistic_farming.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import realistic_farming.main.RealisticFarming;

public class GrowEvents implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onGrow(PlayerInteractEvent e) {
		
		Block b = e.getClickedBlock();
		ItemStack item = e.getItem();
		
		if(b != null && item != null) {
			
			if(item.getType().equals(Material.INK_SACK) && item.getData().getData() == 15) {
				
				if(b.getType().equals(Material.CROPS) || b.getType().equals(Material.PUMPKIN_STEM) || b.getType().equals(Material.MELON_STEM)) {
					
					e.setCancelled(!RealisticFarming.getInstance().getConfig().getBoolean("bone-meal.enabled") || 
							!RealisticFarming.getInstance().getConfig().getBoolean("bone-meal." + b.getType().toString().toLowerCase() + "-enabled"));
					
				}
				
			}
			
		}
		
	}
	
}
