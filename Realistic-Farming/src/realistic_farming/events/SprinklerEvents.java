package realistic_farming.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

import realistic_farming.sprinkler.SprinklerManager;
import realistic_farming.sprinkler.SprinklerUtils;

public class SprinklerEvents implements Listener {
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		
		Player p = e.getPlayer();
		ItemStack item = e.getItemInHand();
		
		try {
			
			if(item.getItemMeta().getLore().get(0).replaceAll("§", "").startsWith("Sprinkler ID: ")) {
				
				if(e.isCancelled() == false) {
					
					Location l = e.getBlock().getLocation();
					String id = item.getItemMeta().getLore().get(0).replaceAll("§", "").replace("Sprinkler ID: ", "");
					
					SprinklerManager.createSprinkler(id, l);
					
					p.getInventory().remove(e.getItemInHand());
					
				}
				
			}
			
		}catch(NullPointerException exc) {}
		
	}
	
	@EventHandler
	public void onEntityInteract(PlayerInteractAtEntityEvent e) {
		
		String name = e.getRightClicked().getCustomName();
		
		if(name != null && name.startsWith("Sprinkler ID:")) {
			
			SprinklerManager.destroySprinkler(name.replaceAll("Sprinkler ID: ", ""));
			SprinklerUtils.giveItem(e.getPlayer());
			
			e.setCancelled(true);
			
		}
		
	}
	
	/*/@EventHandler
	public void onGrow(BlockGrowEvent e) {
		
		growFunction(e);
		
		
	}
	
	@SuppressWarnings("deprecation")
	public static void growFunction(BlockGrowEvent e) {
		
		Block b = e.getBlock();
		
		int range = RealisticFarming.getInstance().getConfig().getInt("sprinkler.range");
		
		for(int x = range * -1; x <= range; x++) {
			
			for(int z = range * -1; z <= range; z++) {
				
				Location l = b.getLocation().clone().add(x, -1, z);
				
				for(Entity entity : l.getWorld().getNearbyEntities(l, 1, 1, 1)) {
					
					if(entity.getCustomName() != null && entity.getCustomName().startsWith("Sprinkler ID: ")) {
						
						Bukkit.getConsoleSender().sendMessage(b.getData() + "");
						
						if(e.getBlock().getState().getData() instanceof Crops) {
							
							Crops crops = (Crops) e.getBlock().getState().getData();
							
							crops.setState(CropState.GERMINATED);
							
						}
						
						return;
						
					}
					
				}
				
			}
			
		}
		
	}/*/
	
}
