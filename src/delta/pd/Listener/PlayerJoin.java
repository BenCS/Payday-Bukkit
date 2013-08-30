package delta.pd.Listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import delta.pd.Lobby;
import delta.pd.Game.Game;

public class PlayerJoin implements Listener {

	Game g = Game.getInstance();
	
	@EventHandler
	public void onPlayerJoinPD(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		Lobby.getInstance().teleportToLobby(p);
		
		g.addToLobby(p);
		
		Bukkit.getServer().broadcastMessage(ChatColor.DARK_AQUA + p.getName() + ChatColor.YELLOW + " Joined the heist! (" + ChatColor.DARK_AQUA + (g.inLobby.size()) + ChatColor.YELLOW + "/" + ChatColor.DARK_AQUA + g.maxPlayers + ChatColor.YELLOW + ")");
		
		e.setJoinMessage(null);
		
	}
	
}
