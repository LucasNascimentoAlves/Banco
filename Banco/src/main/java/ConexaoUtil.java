import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;


@WebServlet("/ConexaoUtil")
public class ConexaoUtil {
    private static final String URL = "jdbc:postgresql://localhost:5432/Banco";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";

    public static Connection obterConexao() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do PostgreSQL não encontrado.", e);
        }
    }
}
