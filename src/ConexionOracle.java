import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionOracle {
    private static final String url = "jdbc:oracle:thin:@172.16.11.16:1521:desa";
    private static final String USUARIO = "epd";
    private static final String CLAVE = "_migracion2025";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(url, USUARIO, CLAVE);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
