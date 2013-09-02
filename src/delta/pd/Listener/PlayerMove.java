package delta.pd.Listener;

import java.util.List;
import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import delta.pd.Game.Game;
import delta.pd.Util.WorldEditUtility;

public class PlayerMove implements Listener {

	public List<String> inEscape = new ArrayList<String>();
	
	@EventHandler
	public void onPlayerMovePD(PlayerMoveEvent e) {
		
		Player p = e.getPlayer();
		
		if(Game.getInstance().escape == true) {
			
			if(Game.getInstance().isPlayerInGame(p)) {
				
				if(WorldEditUtility.getInstance().isPlayerInEscape(p.getLocation())) {
					
					if(!inEscape.contains(p.getName())) {
						
						inEscape.add(p.getName());
						
					}
					
					else if(inEscape.size() == 4) {
						
						Game.getInstance().endWin();
						
					}
					
				}
				
			}
			
		}
		
	}
	
}
