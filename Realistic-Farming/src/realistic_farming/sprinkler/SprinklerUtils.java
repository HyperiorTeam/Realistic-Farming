package realistic_farming.sprinkler;

import java.util.Arrays;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import net.md_5.bungee.api.ChatColor;
import realistic_farming.main.RealisticFarming;

public class SprinklerUtils {
	
	public static void giveItem(Player p) {
		
		ItemStack item = new ItemStack(Material.LEVER);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(ChatColor.GREEN + "Sprinkler");
		
		String hiddenlore = "";
		
		for (char c : ("Sprinkler ID: " + UUID.randomUUID().toString()).toCharArray()) hiddenlore += ChatColor.COLOR_CHAR+""+c;
		
		meta.setLore(Arrays.asList(new String[] {hiddenlore}));
		
		item.setItemMeta(meta);
		
		if(p.getInventory().firstEmpty() == -1) {
			
			if(RealisticFarming.getInstance().getConfig().getBoolean("commands.giveitem.drop-when-inv-full")) {
				
				p.getWorld().dropItemNaturally(p.getLocation(), item);
				
			}
			
		}else
			p.getInventory().addItem(item);
		
	}
	
	public static void SpawnSprinkler(String id, Location l) {
		
		ArmorStand sprinkler = (ArmorStand) l.getWorld().spawnEntity(l.clone().add(0.5, -0.70, 0.5), EntityType.ARMOR_STAND);
		
		sprinkler.setSmall(true);
		sprinkler.setVisible(false);
		sprinkler.setGravity(false);
		sprinkler.setCustomName("Sprinkler ID: " + id);
		
		sprinkler.setHelmet(new ItemStack(Material.IRON_BLOCK));
		
		ArmorStand rotator = (ArmorStand) l.getWorld().spawnEntity(l.clone().add(0.5, -1, 0.5), EntityType.ARMOR_STAND);
		
		rotator.setVisible(false);
		rotator.setGravity(false);
		rotator.setHelmet(new ItemStack(Material.LEVER));
		rotator.setCustomName("Sprinkler ID: " + id);
		
		rotator.setHeadPose(new EulerAngle(Math.toRadians(67.5), 0, 0));
		
		anim(sprinkler, rotator, l);
		
	}
	
	public static void DestroySprinkler(ArmorStand sprinkler, ArmorStand rotator) {
		
		Bukkit.getScheduler().cancelTask(rotator.getMetadata("TaskID").get(0).asInt());
		
		sprinkler.remove();
		rotator.remove();
		
	}
	
	public static void anim(ArmorStand sprinkler, ArmorStand rotator, Location l) {
		
		rotator.setMetadata("TaskID", new FixedMetadataValue(RealisticFarming.getInstance(), 
			new BukkitRunnable() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				
				for(float i = 0; i < 3; i+=0.25) {
					
					Location spawnLoc = new Location(l.getWorld(), rotator.getLocation().getX() + Math.sin(rotator.getHeadPose().getY()) * i * -1, rotator.getLocation().getY() + 1.5, rotator.getLocation().getZ() + Math.cos(rotator.getHeadPose().getY()) * i);
					
					l.getWorld().spigot().playEffect(spawnLoc, Effect.SPLASH, 0, 0, 0, 0, 0, 0, 3, 10);
					
				}
				
				rotator.setHeadPose(new EulerAngle(rotator.getHeadPose().getX(), rotator.getHeadPose().getY() + 0.05, rotator.getHeadPose().getZ()));
				
				int range = RealisticFarming.getInstance().getConfig().getInt("sprinkler.range");
				
				for(int x = range * -1; x <= range; x++) {
					
					for(int z = range * -1; z <= range; z++) {
						
						Block b = l.clone().add(x, -1, z).getBlock();
						
						if(b.getType().equals(Material.SOIL) && b.getData() != 7)
							b.setData((byte) 7);
						
					}
					
				}
				
			}
			
		}.runTaskTimerAsynchronously(RealisticFarming.getInstance(), 0, 1).getTaskId()));
		
	}
	
}
