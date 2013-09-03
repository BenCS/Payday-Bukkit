package delta.pd.Listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import delta.pd.Game.Game;

public class PlayerDeath implements Listener {

	@EventHandler
	public void onPlayerDeathPD(PlayerDeathEvent e) {
		
		Player p = e.getEntity();
		
		if(Game.getInstance().isPlayerInGame(p)) {
			
			Game.getInstance().broadcastGame(ChatColor.YELLOW + p.getName() + ChatColor.DARK_AQUA + " Is in custody!");
			
			Game.getInstance().removeFromGame(p);
			
			e.getDrops().clear();
			
			if(Game.getInstance().inGame.size() == 0) {
				
				Game.getInstance().endFail();
				
			}
			
		}
		
	}
	
}
