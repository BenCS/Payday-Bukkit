package delta.pd;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import delta.pd.command.PD;

public class Main extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(this, this);
		
		getCommand("pd").setExecutor(new PD());
		
	}
	
}
