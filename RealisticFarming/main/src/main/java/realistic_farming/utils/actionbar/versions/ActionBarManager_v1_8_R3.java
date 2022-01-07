package realistic_farming.utils.actionbar.versions;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import realistic_farming.utils.actionbar.ActionBarManager;

public class ActionBarManager_v1_8_R3 implements ActionBarManager {
	
	public ActionBarManager_v1_8_R3() {
		
		
		
	}

	@Override
	public void sendMessage(Player p, String message) {
		
		PacketPlayOutChat packet = new PacketPlayOutChat(new ChatComponentText(ChatColor.translateAlternateColorCodes('&', message)), (byte)2);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
		
	}
	
}
