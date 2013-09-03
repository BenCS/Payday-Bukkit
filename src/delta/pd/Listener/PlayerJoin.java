package delta.pd.Listener;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import delta.pd.Lobby;
import delta.pd.Main;
import delta.pd.Game.Game;

public class PlayerJoin implements Listener {
		
	@EventHandler
	public void onPlayerJoinPD(PlayerJoinEvent e) throws IllegalStateException, ClassNotFoundException, SQLException {
		
		Player p = e.getPlayer();
		
		Lobby.getInstance().teleportToLobby(p);
		
		Game.getInstance().addToLobby(p);
		
		Bukkit.getServer().broadcastMessage(ChatColor.DARK_AQUA + p.getName() + ChatColor.YELLOW + " Joined the heist! (" + ChatColor.DARK_AQUA + (Game.getInstance().inLobby.size()) + ChatColor.YELLOW + "/" + ChatColor.DARK_AQUA + Game.getInstance().maxPlayers + ChatColor.YELLOW + ")");
		
		e.setJoinMessage(null);
		
		try {
			Game.getInstance().fillLobbyInv(p);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Game.getInstance().setLobbyScoreboard(p);
		
		p.setScoreboard(Game.getInstance().lobbysb);
		
		if(Game.getInstance().inLobby.size() == Main.getInstance().getConfig().getInt("Players-Needed")) {
			
			Game.getInstance().countDown();
			
		}
		
	}
	
}
