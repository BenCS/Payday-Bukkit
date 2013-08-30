package delta.pd.Game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Game {
	
    static Game instance = new Game();

    public static Game getInstance() {
        return instance;
    }

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
	
}
