package delta.pd.Listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import delta.pd.Main;
import delta.pd.Game.Game;
import delta.pd.Util.FireworkEffectPlayer;
import delta.pd.Util.WorldEditUtility;

public class LootDrop implements Listener {
	
	int lnm = Main.getInstance().getConfig().getInt("Loot-Needed");
	int ln = 0;
	
	FireworkEffectPlayer fplayer = new FireworkEffectPlayer();
	
	@EventHandler
	public void lootDrop(PlayerDropItemEvent e) {
	
	if(Game.getInstance().canLootBeSecured) {	
		
		if(e.getItemDrop().getItemStack().getType().equals(Material.GOLD_BLOCK)) {
		
			final Entity en = e.getItemDrop();
		
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {

				@Override
				public void run() throws IllegalArgumentException {

					if(WorldEditUtility.getInstance().isBlockInLootPlace(en.getLocation())) {
					
						en.remove();
					
						try {
							fplayer.playFirework(en.getWorld(), en.getLocation(), FireworkEffect.builder().with(Type.BALL).withColor(Color.RED).withFlicker().build());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						if(ln < lnm) {
					
							ln++;
							
							Game.getInstance().broadcastGame(ChatColor.DARK_AQUA + "Loot secured!");
						
						}
						
						if(ln == lnm) {
							
							Game.getInstance().canLootBeSecured = false;
							
							Game.getInstance().broadcastGame(ChatColor.DARK_AQUA + "That's all we need! Lets get out of here!");
							
							Game.getInstance().escape = true;
							
						}
						
					}
				
				}
				
			}, 20);
		}
	}

	}
}
