import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ListarUsuariosServlet")
public class ListarUsuariosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conexao = ConexaoUtil.obterConexao()) {
            String sql = "SELECT * FROM USUARIO";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            request.setAttribute("usuarios", rs);
            request.getRequestDispatcher("listarUsuarios.jsp").forward(request, response);
        } catch (SQLException e) {
            response.getWriter().println("Erro ao listar usuários: " + e.getMessage());
        }
    }
}
