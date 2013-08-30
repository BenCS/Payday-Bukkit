package delta.pd.Game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Game {
	
    static Game instance = new Game();

    public static Game getInstance() {
        return instance;
    }

	public List<String> players = new ArrayList<String>();
	public List<String> inGame = new ArrayList<String>();
	
	public int onlinePlayers = Bukkit.getOnlinePlayers().length;
	public int maxPlayers = Bukkit.getMaxPlayers();
	
	public ScoreboardManager sm = Bukkit.getScoreboardManager();
	public Scoreboard lobby = sm.getNewScoreboard();
	
}
