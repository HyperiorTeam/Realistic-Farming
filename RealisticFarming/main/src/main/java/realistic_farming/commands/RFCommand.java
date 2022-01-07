package realistic_farming.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import realistic_farming.main.RealisticFarming;
import realistic_farming.sprinkler.SprinklerUtils;

public class RFCommand implements TabExecutor {
	
private static final String[] commands = { "givesprinkler", "reload", "help" };
	
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		
		final List<String> completions = new ArrayList<>();
		
		StringUtil.copyPartialMatches(args[0], Arrays.asList(commands), completions);
		
		Collections.sort(completions);
		
		return completions;
		
	}
	
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(args.length >= 1) {
			
			switch(args[0].toLowerCase()) {
			
			case "givesprinkler":
				
				if(args.length == 2) {
					
					Player p = Bukkit.getPlayer(args[1]);
					
					if(p != null) {
						
						SprinklerUtils.giveItem(p);
						
					}
					
				}
				
				break;
			
			case "reload":
				
				RealisticFarming.getInstance().reloadConfig();
				
				sender.sendMessage(ChatColor.GREEN + "Successfully reloaded the config!");
				
				break;
			
			case "help":
				
				sender.sendMessage(ChatColor.YELLOW + "---- Realistic Farming ----\n" +
						"/rf givesprinkler [player] - " + ChatColor.GREEN + "Gives a sprinkler to the given player\n" +
						ChatColor.YELLOW + "/rf reload -" + ChatColor.GREEN + "Reloades the config");
				
				break;
			
			default:
				
				sender.sendMessage(ChatColor.DARK_RED + "Uknown Command!");
				
				break;
			
			}
			
		}else {
			
			sender.sendMessage(ChatColor.YELLOW + "---- Realistic Farming ----\n" +
					"/rf givesprinkler [player] - " + ChatColor.GREEN + "Gives a sprinkler to the given player\n" +
					ChatColor.YELLOW + "/rf reload -" + ChatColor.GREEN + "Reloades the config");
			
		}
				
		return false;
	
	}
	
}
