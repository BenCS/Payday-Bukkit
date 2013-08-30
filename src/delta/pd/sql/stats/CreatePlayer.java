package delta.pd.sql.stats;

import java.sql.ResultSet;
import java.sql.SQLException;

import delta.pd.sql.SQL;

public class CreatePlayer {

    public static void enterNewUser(String player) throws SQLException, ClassNotFoundException {

        ResultSet rs = SQL.getStatement().executeQuery("SELECT COUNT(*) FROM payday WHERE username='" + player + "';");

        rs.next();

        if (rs.getInt(1) == 0) {

            SQL.getStatement().execute("INSERT INTO payday (username, kills, deaths, heists, money) VALUES('" + player + "', 0, 0, 0, 0);");
            SQL.getConnection().close();
            
        }
    }
}
