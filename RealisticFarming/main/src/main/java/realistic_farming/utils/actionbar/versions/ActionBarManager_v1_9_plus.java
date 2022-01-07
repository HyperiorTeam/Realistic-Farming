package realistic_farming.utils.actionbar.versions;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import realistic_farming.utils.actionbar.ActionBarManager;

public class ActionBarManager_v1_9_plus implements ActionBarManager {
	
	public ActionBarManager_v1_9_plus() {
		
		
		
	}
	
	@Override
	public void sendMessage(Player p, String message) {
		
		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', message)));
		
	}

}
