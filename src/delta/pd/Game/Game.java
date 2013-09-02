package delta.pd.Game;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import delta.pd.Lobby;
import delta.pd.Main;
import delta.pd.sql.stats.StatSearch;

public class Game {
	
    static Game instance = new Game();

    public static Game getInstance() {
        return instance;
    }

	int cd = 61;
	int countdown;
    
	public List<String> inLobby = new ArrayList<String>();
	public List<String> inGame = new ArrayList<String>();
	
	public int onlinePlayers = Bukkit.getOnlinePlayers().length;
	public int maxPlayers = Bukkit.getMaxPlayers();
	
	public ScoreboardManager sm = Bukkit.getScoreboardManager();
	public Scoreboard lobby = sm.getNewScoreboard();
	
	/*
	 * 
	 * End Header
	 * 
	 */
	
	public boolean isPlayerInGame(Player p) {
		
		if(inGame.contains(p.getName())) {
			
			return true;
			
		}
		
		return false;
		
	}
	
	public boolean isPlayerInLobby(Player p) {
		
		if(inLobby.contains(p.getName())) {
			
			return true;
			
		}
		
		return false;
		
	}
	
	public void removeFromGame(Player p) {
		
		inGame.remove(p.getName());
		
	}
	
	public void removeFromLobby(Player p) {
		
		inLobby.remove(p.getName());
		
	}
	
	public void addToLobby(Player p) {
		
		inLobby.add(p.getName());
		
	}
	
	public void addToGame(Player p) {
		
		inGame.add(p.getName());
		
	}
	
	public void fillLobbyInv(Player p) throws ClassNotFoundException, SQLException {
		
		StatSearch.setBookStats(p, p.getName());
		
		ItemStack leave = new ItemStack(Material.COMPASS);
		ItemMeta lm = leave.getItemMeta();
		lm.setDisplayName(ChatColor.RED + "Leave Game");
		leave.setItemMeta(lm);
		
		ItemStack shop = new ItemStack(Material.DIAMOND);
		ItemMeta sm = shop.getItemMeta();
		
	}
	
	public void startGame() {
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			
			if(isPlayerInLobby(p)) {
				
				Lobby.getInstance().teleportToGame(p);
				
			}
			
		}
		
		Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "Heist started on map " + ChatColor.YELLOW + Main.getInstance().getConfig().getString("Map-Name"));
		Bukkit.broadcastMessage(ChatColor.DARK_AQUA + Main.getInstance().getConfig().getString("Mission-Objective"));
		
	}
	
	public void countDown() {
		
		countdown = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

			@Override
			public void run() {

				if(cd != 0) {
					cd--;
				}
				
				if(cd == 60) {
					Bukkit.broadcastMessage(ChatColor.YELLOW + "" + cd + ChatColor.DARK_AQUA + " seconds left until heist!");
				}
				
				if(cd == 30) {
					Bukkit.broadcastMessage(ChatColor.YELLOW + "" + cd + ChatColor.DARK_AQUA + " seconds left until heist!");
				}
				
				if(cd < 11) {
					Bukkit.broadcastMessage(ChatColor.YELLOW + "" + cd + ChatColor.DARK_AQUA + " seconds left until heist!");
				}
				
				if(cd == 0) {
					cd = 61;
					Bukkit.getScheduler().cancelTask(countdown);
					
					startGame();
					
				}
				
			}
			
		}, 0, 20L);
		
	}
	
}
