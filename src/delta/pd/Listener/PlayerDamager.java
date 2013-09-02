package delta.pd.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamager implements Listener {
	
	@EventHandler
	public void onPlayerDamagedPD(EntityDamageByEntityEvent e) {
		
		if(e.getEntity() instanceof Player) {
			
			Player p = (Player) e.getEntity();
			
			if(e.getDamager() instanceof Player) {
				
				e.setCancelled(true);
				
			}
			
		}
		
	}

}
