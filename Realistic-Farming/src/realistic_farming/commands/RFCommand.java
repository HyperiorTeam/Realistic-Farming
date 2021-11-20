package realistic_farming.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import realistic_farming.sprinkler.SprinklerUtils;

public class RFCommand implements TabExecutor {
	
	private static final String[] commands = { "giveitem" };
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		final List<String> completions = new ArrayList<>();
		
		StringUtil.copyPartialMatches(args[0], Arrays.asList(commands), completions);
		
		Collections.sort(completions);
		
		return completions;
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length >= 1) {
			
			switch(args[0].toLowerCase()) {
			
			case "giveitem":
				
				if(args.length == 2) {
					
					Player p = Bukkit.getPlayer(args[1]);
					
					if(p != null) {
						
						SprinklerUtils.giveItem(p);
						
					}
					
				}
				
				break;
			
			default:
				
				sender.sendMessage("Uknown Command");
				
				break;
			
			}
			
		}
		
		return false;
		
	}
	
	
	
}
