import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RemoverUsuarioServlet")
public class RemoverUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection conexao = ConexaoUtil.obterConexao()) {
            String sql = "DELETE FROM USUARIO WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            response.sendRedirect("listarUsuarios.jsp");
        } catch (SQLException e) {
            response.getWriter().println("Erro ao remover usuário: " + e.getMessage());
        }
    }
}
