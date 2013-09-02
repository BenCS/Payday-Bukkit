package delta.pd.sql.stats;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import delta.pd.sql.SQL;

public class Mask {

	public static String getMask(Player player) throws ClassNotFoundException, SQLException {
		
	   ResultSet rs = SQL.getStatement().executeQuery("SELECT mask FROM payday WHERE username ='" + player.getName() + "';");

	   String mask = null;
	   
	   rs = SQL.getStatement().executeQuery("SELECT mask FROM payday WHERE username LIKE '%" + player.getName() + "';");
	     if (rs.next()) {
	    	 mask = rs.getString(1);
	     }

	   SQL.getConnection().close();
	   
	return mask;
		
	}
	
}
