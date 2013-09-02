package delta.pd;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Lobby {

    static Lobby instance = new Lobby();

    public static Lobby getInstance() {
        return instance;
    }
	
    public void setLobby(Player p) {

        Main.getInstance().Spawns.set("MainLobby.X", p.getLocation().getBlockX());
        Main.getInstance().Spawns.set("MainLobby.Y", p.getLocation().getBlockY());
        Main.getInstance().Spawns.set("MainLobby.Z", p.getLocation().getBlockZ());
        Main.getInstance().Spawns.set("MainLobby.YAW", p.getLocation().getPitch());
        Main.getInstance().Spawns.set("MainLobby.PITCH", p.getLocation().getYaw());
        Main.getInstance().Spawns.set("MainLobby.WORLD", p.getLocation().getWorld().getName());
        ConfigManager.getInstance().saveYamls();
    }
	
    public boolean doesLobbyExist() {

        if (Main.getInstance().Spawns.contains("MainLobby")) {
            return true;
        }

        return false;
    }
    
    public boolean teleportToLobby(Player p) {

        if (!Main.getInstance().Spawns.contains("MainLobby")) {
            return false;
        }

        World world = Bukkit.getServer().getWorld(Main.getInstance().Spawns.getString("MainLobby.WORLD"));
        double x = Main.getInstance().Spawns.getDouble("MainLobby.X");
        double y = Main.getInstance().Spawns.getDouble("MainLobby.Y");
        double z = Main.getInstance().Spawns.getDouble("MainLobby.Z");
        long yaw = Main.getInstance().Spawns.getLong("MainLobby.YAW");
        long pitch = Main.getInstance().Spawns.getLong("MainLobby.PITCH");
        
        Location location = new Location(world, x, y, z, yaw, pitch);

        p.teleport(location);
		
        return true;
        
    }
    
    public void setGameSpawn(Player p) {
    	
        Main.getInstance().Spawns.set("GameSpawn.X", p.getLocation().getBlockX());
        Main.getInstance().Spawns.set("GameSpawn.Y", p.getLocation().getBlockY());
        Main.getInstance().Spawns.set("GameSpawn.Z", p.getLocation().getBlockZ());
        Main.getInstance().Spawns.set("GameSpawn.YAW", p.getLocation().getPitch());
        Main.getInstance().Spawns.set("GameSpawn.PITCH", p.getLocation().getYaw());
        Main.getInstance().Spawns.set("GameSpawn.WORLD", p.getLocation().getWorld().getName());
        ConfigManager.getInstance().saveYamls();
    	
    }
    
    public boolean teleportToGame(Player p) {

        if (!Main.getInstance().Spawns.contains("GameSpawn")) {
            return false;
        }

        World world = Bukkit.getServer().getWorld(Main.getInstance().Spawns.getString("GameSpawn.WORLD"));
        double x = Main.getInstance().Spawns.getDouble("GameSpawn.X");
        double y = Main.getInstance().Spawns.getDouble("GameSpawn.Y");
        double z = Main.getInstance().Spawns.getDouble("GameSpawn.Z");
        long yaw = Main.getInstance().Spawns.getLong("GameSpawn.YAW");
        long pitch = Main.getInstance().Spawns.getLong("GameSpawn.PITCH");
        
        Location location = new Location(world, x, y, z, yaw, pitch);

        p.teleport(location);
		
        return true;
        
    }
    
}
