package realistic_farming.events;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;
import realistic_farming.main.RealisticFarming;

public class HoeEvent implements Listener {
	
	private static HashMap<Player, Integer> ids = new HashMap<Player, Integer>();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		
		Player p = e.getPlayer();
		Block b = e.getClickedBlock();
		
		if(b != null) {
			
			if(b.getType().equals(Material.GRASS) || 
					(b.getType().equals(Material.DIRT) && 
							(b.getData() == 0 || b.getData() == 1))) 
			{
				
				e.setCancelled(true);
				
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cHoeing started, do not move!"));
				
				ids.put(p, new BukkitRunnable() {
					
					@Override
					public void run() {
						
						b.setType(Material.SOIL);
						
						ids.remove(p);
						
					}
					
				}.runTaskLater(RealisticFarming.getInstance(), 2 * 20).getTaskId());
				
			}
			 
		}
		
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		
		Player p = e.getPlayer();
		
		if(ids.containsKey(p)) {
			
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You moved, hoeing cancelled!"));
			
			Bukkit.getScheduler().cancelTask(ids.get(p));
			
			ids.remove(e.getPlayer());
			
		}
		
	}
	
}
