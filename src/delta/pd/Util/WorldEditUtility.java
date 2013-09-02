package delta.pd.Util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import com.sk89q.worldedit.regions.Region;
import delta.pd.ConfigManager;
import delta.pd.Main;

public class WorldEditUtility {
	
    static WorldEditUtility instance = new WorldEditUtility();

    public static WorldEditUtility getInstance() {
        return instance;
    }
	
    WorldEditPlugin we = getWorldEdit();

    public WorldEditPlugin getWorldEdit() {

        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");

        if ((plugin == null) || (!(plugin instanceof WorldEditPlugin))) {
            return null;
        }

        return (WorldEditPlugin) plugin;

    }
    
    public boolean doesSelectionExist(Player p) {


        Selection sel = we.getSelection(p);

        if (sel == null) {
            return false;
        }

        return true;
    }
    
    public void setArena(Player p) {
    	
        WorldEdit instance = WorldEdit.getInstance();
        LocalSession session = instance.getSession(p.getName());
        Region region = null;

        try {
            region = session.getSelection(session.getSelectionWorld());
        } catch (IncompleteRegionException e1) {
            e1.printStackTrace();
        }

        Vector min = region.getMinimumPoint();
        Vector max = region.getMaximumPoint();

        Main.getInstance().Arena.set("Arena.MaxX", max.getBlockX());
        Main.getInstance().Arena.set("Arena.MaxY", max.getBlockY());
        Main.getInstance().Arena.set("Arena.MaxZ", max.getBlockZ());
        Main.getInstance().Arena.set("Arena.MinX", min.getBlockX());
        Main.getInstance().Arena.set("Arena.MinY", min.getBlockY());
        Main.getInstance().Arena.set("Arena.MinZ", min.getBlockZ());
        Main.getInstance().Arena.set("Arena.World", p.getWorld().getName());

        ConfigManager.getInstance().saveYamls();
    	
    }
    
    public boolean isBlockInArenaPlace(Location l) {
    	
    	int minx = Main.getInstance().Arena.getInt("Arena.MinX");
    	int miny = Main.getInstance().Arena.getInt("Arena.MinY");
    	int minz = Main.getInstance().Arena.getInt("Arena.MinZ");
    	
    	int maxx = Main.getInstance().Arena.getInt("Arena.MaxX");
    	int maxy = Main.getInstance().Arena.getInt("Arena.MaxY");
    	int maxz = Main.getInstance().Arena.getInt("Arena.MaxZ");
    	
    	World w = Bukkit.getWorld(Main.getInstance().Arena.getString("Arena.World"));
    	
    	Location min = new Location(w, minx, miny, minz);
    	
    	Location max = new Location(w, maxx, maxy, maxz);
    	
        if (l.getWorld() != min.getWorld())
            return false;

        double x = l.getX();
        double z = l.getZ();
        double y = l.getY();

        return (x >= min.getBlockX()) && (x < max.getBlockX() + 1) && (y >= min.getBlockY()) && (y < max.getBlockY() + 1) && (z >= min.getBlockZ()) && (z < max.getBlockZ() + 1);
    }
    
    public void setLootDropZone(Player p) {
    	
        WorldEdit instance = WorldEdit.getInstance();
        LocalSession session = instance.getSession(p.getName());
        Region region = null;

        try {
            region = session.getSelection(session.getSelectionWorld());
        } catch (IncompleteRegionException e1) {
            e1.printStackTrace();
        }

        Vector min = region.getMinimumPoint();
        Vector max = region.getMaximumPoint();

        Main.getInstance().Arena.set("Loot.MaxX", max.getBlockX());
        Main.getInstance().Arena.set("Loot.MaxY", max.getBlockY());
        Main.getInstance().Arena.set("Loot.MaxZ", max.getBlockZ());
        Main.getInstance().Arena.set("Loot.MinX", min.getBlockX());
        Main.getInstance().Arena.set("Loot.MinY", min.getBlockY());
        Main.getInstance().Arena.set("Loot.MinZ", min.getBlockZ());
        Main.getInstance().Arena.set("Loot.World", p.getWorld().getName());

        ConfigManager.getInstance().saveYamls();
    	
    }
    
    public boolean isBlockInLootPlace(Location l) {
    	
    	int minx = Main.getInstance().Arena.getInt("Loot.MinX");
    	int miny = Main.getInstance().Arena.getInt("Loot.MinY");
    	int minz = Main.getInstance().Arena.getInt("Loot.MinZ");
    	
    	int maxx = Main.getInstance().Arena.getInt("Loot.MaxX");
    	int maxy = Main.getInstance().Arena.getInt("Loot.MaxY");
    	int maxz = Main.getInstance().Arena.getInt("Loot.MaxZ");
    	
    	World w = Bukkit.getWorld(Main.getInstance().Arena.getString("Loot.World"));
    	
    	Location min = new Location(w, minx, miny, minz);
    	
    	Location max = new Location(w, maxx, maxy, maxz);
    	
        if (l.getWorld() != min.getWorld())
            return false;

        double x = l.getX();
        double z = l.getZ();
        double y = l.getY();

        return (x >= min.getBlockX()) && (x < max.getBlockX() + 1) && (y >= min.getBlockY()) && (y < max.getBlockY() + 1) && (z >= min.getBlockZ()) && (z < max.getBlockZ() + 1);
    }
    
    public void setEscapeZone(Player p) {
    	
        WorldEdit instance = WorldEdit.getInstance();
        LocalSession session = instance.getSession(p.getName());
        Region region = null;

        try {
            region = session.getSelection(session.getSelectionWorld());
        } catch (IncompleteRegionException e1) {
            e1.printStackTrace();
        }

        Vector min = region.getMinimumPoint();
        Vector max = region.getMaximumPoint();

        Main.getInstance().Arena.set("Escape.MaxX", max.getBlockX());
        Main.getInstance().Arena.set("Escape.MaxY", max.getBlockY());
        Main.getInstance().Arena.set("Escape.MaxZ", max.getBlockZ());
        Main.getInstance().Arena.set("Escape.MinX", min.getBlockX());
        Main.getInstance().Arena.set("Escape.MinY", min.getBlockY());
        Main.getInstance().Arena.set("Escape.MinZ", min.getBlockZ());
        Main.getInstance().Arena.set("Escape.World", p.getWorld().getName());

        ConfigManager.getInstance().saveYamls();
    	
    }
    
    public boolean isPlayerInEscape(Location l) {
    	
    	int minx = Main.getInstance().Arena.getInt("Escape.MinX");
    	int miny = Main.getInstance().Arena.getInt("Escape.MinY");
    	int minz = Main.getInstance().Arena.getInt("Escape.MinZ");
    	
    	int maxx = Main.getInstance().Arena.getInt("Escape.MaxX");
    	int maxy = Main.getInstance().Arena.getInt("Escape.MaxY");
    	int maxz = Main.getInstance().Arena.getInt("Escape.MaxZ");
    	
    	World w = Bukkit.getWorld(Main.getInstance().Arena.getString("Escape.World"));
    	
    	Location min = new Location(w, minx, miny, minz);
    	
    	Location max = new Location(w, maxx, maxy, maxz);
    	
        if (l.getWorld() != min.getWorld())
            return false;

        double x = l.getX();
        double z = l.getZ();
        double y = l.getY();

        return (x >= min.getBlockX()) && (x < max.getBlockX() + 1) && (y >= min.getBlockY()) && (y < max.getBlockY() + 1) && (z >= min.getBlockZ()) && (z < max.getBlockZ() + 1);
    }
    
}
