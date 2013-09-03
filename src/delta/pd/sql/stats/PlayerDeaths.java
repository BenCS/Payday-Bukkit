package delta.pd.sql.stats;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import delta.pd.sql.SQL;

public class PlayerDeaths {

    public static void setPlayerDeathsCMD(String player, int value) throws SQLException, ClassNotFoundException {

        int deaths = 0;

        ResultSet rs = SQL.getStatement().executeQuery("SELECT deaths FROM payday WHERE username ='" + player + "';");

        if (rs.next()) {

            deaths = rs.getInt(1) + value;
            SQL.getStatement().execute("UPDATE deaths SET kills=" + deaths + " WHERE username='" + player + "'");

        }

        SQL.getConnection().close();
    }
    
	public static int deaths = 0;
	
    public static int getPlayerDeaths(Player player) throws SQLException, ClassNotFoundException {

        ResultSet rs = SQL.getStatement().executeQuery("SELECT deaths FROM payday WHERE username ='" + player.getName() + "';");

        rs = SQL.getStatement().executeQuery("SELECT deaths FROM payday WHERE username LIKE '%" + player.getName() + "';");
        if (rs.next()) {
            deaths = rs.getInt(1);
        }

        SQL.getConnection().close();
        
        return deaths;
    }
	
}
