package delta.pd.Listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import delta.pd.Game.Game;

public class PlayerLeave implements Listener {

	Game g = Game.getInstance();
	
	@EventHandler
	public void onPlayerLeavePD(PlayerQuitEvent e) {
		
		Player p = e.getPlayer();
		
		if(g.isPlayerInGame(p)) {
			
			g.removeFromGame(p);
			
		}
		
		if(g.isPlayerInLobby(p)) {
			
			g.removeFromLobby(p);
			
		}
		
		e.setQuitMessage(null);
		
		Bukkit.getServer().broadcastMessage(ChatColor.DARK_AQUA + p.getName() + ChatColor.YELLOW + " Left the heist! (" + ChatColor.DARK_AQUA + (g.inLobby.size()) + ChatColor.YELLOW + "/" + ChatColor.DARK_AQUA + g.maxPlayers + ChatColor.YELLOW + ")");
		
	}
	
}
