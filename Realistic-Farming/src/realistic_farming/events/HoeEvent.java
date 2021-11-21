package realistic_farming.events;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import realistic_farming.main.RealisticFarming;

public class HoeEvent implements Listener {
	
	private static HashMap<Player, Integer> ids = new HashMap<Player, Integer>();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		
		if(RealisticFarming.getInstance().getConfig().getBoolean("hoeing.enabled") && !e.isCancelled()) {
			
			Block b = e.getClickedBlock();
			ItemStack item = e.getItem();
			
			if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && item != null) {
				
				Player p = e.getPlayer();
				
				if(b.getType().equals(Material.GRASS) || 
						(b.getType().equals(Material.DIRT) && 
								(b.getData() == 0 || b.getData() == 1))) 
				{
					
					if(item.getType().equals(Material.WOOD_HOE) || item.getType().equals(Material.STONE_HOE) || item.getType().equals(Material.IRON_HOE) || item.getType().equals(Material.GOLD_HOE) || item.getType().equals(Material.DIAMOND_HOE)) {
						
						if(RealisticFarming.getInstance().getConfig().getInt("hoeing.time") != 0) {
							
							e.setCancelled(true);
							
							if(!ids.containsKey(p)) {
								
								if(RealisticFarming.getInstance().getConfig().getBoolean("hoeing.messages.start.enabled"))
									RealisticFarming.actionbar.sendMessage(p, RealisticFarming.getInstance().getConfig().getString("hoeing.messages.start.message"));
								
								ids.put(p, new BukkitRunnable() {
									
									@Override
									public void run() {
										
										b.setType(Material.SOIL);
										
										ids.remove(p);
										
									}
									
								}.runTaskLater(RealisticFarming.getInstance(), RealisticFarming.getInstance().getConfig().getInt("hoeing.time") * 20).getTaskId());
								
							}
							
						}
						
					}
					
				}
				 
			}
			
		}
		
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		
		if(RealisticFarming.getInstance().getConfig().getBoolean("hoeing.cancel-on-move") && RealisticFarming.getInstance().getConfig().getBoolean("hoeing.enabled")) {
			
			Player p = e.getPlayer();
			
			if(ids.containsKey(p)) {
				if(RealisticFarming.getInstance().getConfig().getBoolean("hoeing.messages.cancelled.enabled"))
					RealisticFarming.actionbar.sendMessage(p, RealisticFarming.getInstance().getConfig().getString("hoeing.messages.cancelled.message"));
				
				Bukkit.getScheduler().cancelTask(ids.get(p));
				
				ids.remove(e.getPlayer());
				
			}
			
		}
		
	}
	
}
