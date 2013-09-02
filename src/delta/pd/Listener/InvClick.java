package delta.pd.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import delta.pd.Game.Game;

public class InvClick implements Listener {
	
	@EventHandler
	public void onInventoryClickInGame(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		
		if(Game.getInstance().isPlayerInGame(p)) {
			
			e.setCancelled(true);
			
		}
		
	}

}
