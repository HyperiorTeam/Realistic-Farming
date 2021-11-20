package realistic_farming.utilities;

import java.util.Arrays;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class SprinklerUtilities {
	
	public static void giveItem(Player p) {
		
		ItemStack item = new ItemStack(Material.LEVER);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(ChatColor.GREEN + "Sprinkler");
		
		
		String hiddenlore = "";
		
		for (char c : ("Sprinkler ID: " + UUID.randomUUID().toString()).toCharArray()) hiddenlore += ChatColor.COLOR_CHAR+""+c;
		
		meta.setLore(Arrays.asList(new String[] {hiddenlore}));
		
		item.setItemMeta(meta);
		
		p.getInventory().addItem(item);
		
	}
	
}
