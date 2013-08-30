package delta.pd.Listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import delta.pd.Lobby;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onPlayerJoinPD(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		Lobby.getInstance().teleportToLobby(p);
		
		Bukkit.getServer().broadcastMessage(arg0)
		
	}
	
}
