package realistic_farming.utils.farminteraction;

import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;

public interface FarmInteractionManager {
	
	void boneMeal(PlayerInteractEvent e);
	
	void setToDirt(Block b);
	
}
