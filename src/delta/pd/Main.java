package delta.pd;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import delta.pd.Listener.BlockBreak;
import delta.pd.Listener.BlockLoot;
import delta.pd.Listener.BlockPlace;
import delta.pd.Listener.InvClick;
import delta.pd.Listener.ItemClick;
import delta.pd.Listener.LootDrop;
import delta.pd.Listener.PlayerDamager;
import delta.pd.Listener.PlayerDeath;
import delta.pd.Listener.PlayerJoin;
import delta.pd.Listener.PlayerLeave;
import delta.pd.Listener.PlayerMove;
import delta.pd.Listener.RankChat;
import delta.pd.command.PD;
import delta.pd.sql.SQL;

public class Main extends JavaPlugin implements Listener {

	static Main instance;

	public static Main getInstance() {

		return instance;

	}
	
	/**
	 * 
	 * Payday plugin for MinecraftUniverse.
	 * 
	 * @author BenCS_
	 * 
	 */

	public File configFile;
	public FileConfiguration Config;

	public File arenaFile;
	public FileConfiguration Arena;

	public File invFile;
	public FileConfiguration Inv;

	public File chestFile;
	public FileConfiguration Chest;

	public File spawnFile;
	public FileConfiguration Spawns;

	Logger log = Bukkit.getLogger();

	@Override
	public void onEnable() {

		instance = this;

		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

		configFile = new File(getDataFolder(), "config.yml");
		arenaFile = new File(getDataFolder(), "arena.yml");
		invFile = new File(getDataFolder(), "inventorys.yml");
		chestFile = new File(getDataFolder(), "chests.yml");
		spawnFile = new File(getDataFolder(), "spawns.yml");

		try {

			ConfigManager.getInstance().firstRun();

		} catch (Exception e) {

			e.printStackTrace();
		}

		this.Config = new YamlConfiguration();
		this.Arena = new YamlConfiguration();
		this.Inv = new YamlConfiguration();
		this.Chest = new YamlConfiguration();
		this.Spawns = new YamlConfiguration();
		ConfigManager.getInstance().loadYamls();
		ConfigManager.getInstance().saveYamls();

		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(this, this);
		pm.registerEvents(new RankChat(), this);
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new PlayerLeave(), this);
		pm.registerEvents(new BlockBreak(), this);
		pm.registerEvents(new BlockPlace(), this);
		pm.registerEvents(new ItemClick(), this);
		pm.registerEvents(new LootDrop(), this);
		pm.registerEvents(new PlayerMove(), this);
		pm.registerEvents(new PlayerDeath(), this);
		pm.registerEvents(new InvClick(), this);
		pm.registerEvents(new BlockLoot(), this);
		pm.registerEvents(new PlayerDamager(), this);

		getCommand("pd").setExecutor(new PD());

		try {
			this.checkDatabase();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			log.severe("Connection failed. Defaulting to SQLite.");
			this.Config.set("MySQL.Enable", false);
		}

		Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
				"gamerule naturalRegeneration false");

	}

	public void checkDatabase() throws SQLException, ClassNotFoundException {

		Connection con = null;

		con = SQL.getConnection();

		// for server status, 0 = false 1 = true

		/*
		 * con.createStatement().execute(
		 * "CREATE TABLE IF NOT EXISTS payday(username VARCHAR(255), kills INTEGER, deaths INTEGER, heists INTEGER, money INTEGER)"
		 * ); con.createStatement().execute(
		 * "CREATE TABLE IF NOT EXISTS serverinfo(servername VARCHAR(255), enabled INTEGER, inlobby INTEGER, ingame INTEGER, startings INTEGER)"
		 * );
		 */
	}

	public boolean doesPlayerExist(String target) throws SQLException,
			ClassNotFoundException {
		ResultSet rs = SQL.getStatement().executeQuery(
				"SELECT COUNT(*) FROM payday WHERE username LIKE '%" + target
						+ "';");
		rs.next();
		if (rs.getInt(1) != 0) {
			return true;
		}
		return false;
	}

}
