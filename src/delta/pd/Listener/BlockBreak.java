package delta.pd.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import delta.pd.Util.WorldEditUtility;

public class BlockBreak implements Listener {

	@EventHandler
	public void onPlayerBreakBlockPD(BlockBreakEvent e) {
		
		if(WorldEditUtility.getInstance().isBlockInArenaPlace(e.getBlock().getLocation())) {
			
			e.setCancelled(true);
		
		}
		
		if(WorldEditUtility.getInstance().isBlockInLootPlace(e.getBlock().getLocation())) {
			
			e.setCancelled(true);
			
		}
		
	}
	
}
