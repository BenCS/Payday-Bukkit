package delta.pd.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

	@EventHandler
	public void onPlayerBreakBlockPD(BlockBreakEvent e) {
		
		e.setCancelled(true);
		
	}
	
}
