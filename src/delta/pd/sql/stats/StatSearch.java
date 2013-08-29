package delta.pd.sql.stats;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import delta.pd.sql.SQL;

public class StatSearch {

    public static void getPlayerData(Player player, String target) throws SQLException, ClassNotFoundException {
		String prefix = ChatColor.GRAY + "[" + ChatColor.BLUE + "PayDay" + ChatColor.GRAY + "] ";
        String username = null;
        int heists = 0;
        double kills = 0, deaths = 0, money = 0;
        ResultSet rs = SQL.getStatement().executeQuery("SELECT COUNT(*) FROM payday WHERE username LIKE '%" + target + "';");
        rs.next();
        if (rs.getInt(1) != 0) {
            rs = SQL.getStatement().executeQuery("SELECT username FROM payday WHERE username LIKE '%" + target + "';");
            if (rs.next()) {
                username = rs.getString(1);
            }
            rs = SQL.getStatement().executeQuery("SELECT kills FROM payday WHERE username LIKE '%" + target + "';");
            if (rs.next()) {
                kills = rs.getInt(1);
            }
            rs = SQL.getStatement().executeQuery("SELECT deaths FROM payday WHERE username LIKE '%" + target + "';");
            if (rs.next()) {
                deaths = rs.getInt(1);
            }
            rs = SQL.getStatement().executeQuery("SELECT money FROM payday WHERE username LIKE '%" + target + "';");
            if (rs.next()) {
                money = rs.getInt(1);
            }
            rs = SQL.getStatement().executeQuery("SELECT losses FROM payday WHERE username LIKE '%" + target + "';");
            if (rs.next()) {
                heists = rs.getInt(1);
            }
            
            player.sendMessage(prefix + "Stats:");
            player.sendMessage(ChatColor.GRAY + "Kills:" + ChatColor.RED + (int) kills);
            player.sendMessage(ChatColor.GRAY + "Deaths:" + ChatColor.RED + (int) deaths);
            player.sendMessage(ChatColor.GRAY + "Money:" + ChatColor.RED + (int) money);
            player.sendMessage(ChatColor.GRAY + "Heists:" + ChatColor.RED + heists);
            
        } else {
        	
        	player.sendMessage(prefix + ChatColor.RED + "Player not found");
        	
        }
        
    }
    
}
