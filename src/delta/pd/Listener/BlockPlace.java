package delta.pd.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {

	@EventHandler
	public void onPlayerPlaceBlockPD(BlockPlaceEvent e) {
		
		Player p = e.getPlayer();
		
		e.setCancelled(true);
		
	}
	
}
