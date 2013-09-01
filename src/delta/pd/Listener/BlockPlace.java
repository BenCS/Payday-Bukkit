package delta.pd.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {

	@EventHandler
	public void onPlayerPlaceBlockPD(BlockPlaceEvent e) {
		
		e.setCancelled(true);
		
	}
	
}
