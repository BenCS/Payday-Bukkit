package delta.pd.Listener;

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

	Game g = Game.getInstance();
	
	int cd = 61;
	int countdown;
	
	@EventHandler
	public void onPlayerJoinPD(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		Lobby.getInstance().teleportToLobby(p);
		
		g.addToLobby(p);
		
		Bukkit.getServer().broadcastMessage(ChatColor.DARK_AQUA + p.getName() + ChatColor.YELLOW + " Joined the heist! (" + ChatColor.DARK_AQUA + (g.inLobby.size()) + ChatColor.YELLOW + "/" + ChatColor.DARK_AQUA + g.maxPlayers + ChatColor.YELLOW + ")");
		
		e.setJoinMessage(null);
		
		if(g.inLobby.size() == 4) {
			
			countdown = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

				@Override
				public void run() {

					if(cd != 0) {
						cd--;
					}
					
					if(cd == 60) {
						Bukkit.broadcastMessage(ChatColor.YELLOW + "" + cd + ChatColor.DARK_AQUA + " seconds left unil heist!");
					}
					
					if(cd == 30) {
						Bukkit.broadcastMessage(ChatColor.YELLOW + "" + cd + ChatColor.DARK_AQUA + " seconds left unil heist!");
					}
					
					if(cd < 11) {
						Bukkit.broadcastMessage(ChatColor.YELLOW + "" + cd + ChatColor.DARK_AQUA + " seconds left unil heist!");
					}
					
					if(cd == 0) {
						Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "Heist started on map " + ChatColor.YELLOW + Main.getInstance().getConfig().getString("Map-Name"));
						cd = 60;
						Bukkit.getScheduler().cancelTask(countdown);
					}
					
				}
				
			}, 0, 20L);
			
		}
		
	}
	
}
