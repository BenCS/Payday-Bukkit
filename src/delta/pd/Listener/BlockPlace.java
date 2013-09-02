package delta.pd.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import delta.pd.Main;
import delta.pd.Util.WorldEditUtility;

public class BlockPlace implements Listener {

	@EventHandler
	public void onPlayerPlaceBlockPD(BlockPlaceEvent e) {
	
		if(Main.getInstance().Arena.contains("Arena")) {
			
			if(WorldEditUtility.getInstance().isBlockInArenaPlace(e.getBlock().getLocation())) {
		
				e.setCancelled(true);
		
			}
		
		}
		
	}
	
}
