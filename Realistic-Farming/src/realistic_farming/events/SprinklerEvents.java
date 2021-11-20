package realistic_farming.events;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import realistic_farming.sprinkler.SprinklerManager;

public class SprinklerEvents implements Listener {
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		
		Player p = e.getPlayer();
		
		if(p.getItemInHand().getItemMeta().getLore().get(0).replaceAll("§", "").startsWith("Sprinkler ID: ")) {
			
			if(e.isCancelled() == false) {
				
				Location l = e.getBlock().getLocation();
				String id = p.getItemInHand().getItemMeta().getLore().get(0).replaceAll("§", "").replace("Sprinkler ID: ", "");
				
				SprinklerManager.createSprinkler(id, l);
				
			}
			
		}
		
	}
	
	@EventHandler
	public void onEntityInteract(PlayerInteractEntityEvent e) {
		
		Entity entity = e.getRightClicked();
		
		if(entity.getMetadata("Sprinkler ID").size() != 0) {
			
			
			
			e.setCancelled(true);
			
		}
		
	}
	
}
