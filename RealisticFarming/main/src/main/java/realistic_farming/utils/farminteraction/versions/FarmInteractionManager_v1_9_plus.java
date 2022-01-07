package realistic_farming.utils.farminteraction.versions;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.player.PlayerInteractEvent;

import realistic_farming.main.RealisticFarming;
import realistic_farming.utils.farminteraction.FarmInteractionManager;

public class FarmInteractionManager_v1_9_plus implements FarmInteractionManager {
	
	public FarmInteractionManager_v1_9_plus() {
		
		
		
	}

	@Override
	public void boneMeal(PlayerInteractEvent e) {
		
		Block b = e.getClickedBlock();
		
		if(b.getType().equals(Material.CROPS) ||
				b.getType().equals(Material.PUMPKIN_STEM) ||
				b.getType().equals(Material.MELON_STEM) ||
				b.getType().equals(Material.CARROT) ||
				b.getType().equals(Material.POTATO) ||
				b.getType().equals(Material.BEETROOT_BLOCK))
		{
			
			e.setCancelled(!RealisticFarming.getInstance().getConfig().getBoolean("bone-meal.enabled") || 
					!RealisticFarming.getInstance().getConfig().getBoolean("bone-meal." + b.getType().toString().toLowerCase() + "-enabled"));
			
		}
		
	}

	@Override
	public void setToDirt(Block b) {
		
		if(b.getType().equals(Material.CROPS) ||
				b.getType().equals(Material.PUMPKIN_STEM) ||
				b.getType().equals(Material.MELON_STEM) ||
				b.getType().equals(Material.CARROT) ||
				b.getType().equals(Material.POTATO) ||
				b.getType().equals(Material.BEETROOT_BLOCK))
		{
			
			b.getRelative(BlockFace.DOWN).setType(Material.DIRT);
			
		}
		
	}
	
}
