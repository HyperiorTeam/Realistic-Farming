package realistic_farming.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import realistic_farming.sprinkler.SprinklerUtils;

public class SprinklerEvents implements Listener {
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		
		Player p = e.getPlayer();
		
		if(p.getItemInHand().getItemMeta().getLore().get(0).replaceAll("§", "").startsWith("Sprinkler ID: ")) {
			
			if(e.isCancelled() == false) {
				
				Block b = e.getBlock();
				
				SprinklerUtils.SpawnSprinkler(b);
				
			}
			
		}
		
	}
	
}
