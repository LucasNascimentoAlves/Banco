import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AtualizarUsuariosServlet")
public class AtualizarUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");

        try (Connection conexao = ConexaoUtil.obterConexao()) {
            String sql = "UPDATE USUARIO SET nome = ?, email = ? WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setInt(3, id);
            stmt.executeUpdate();

            response.sendRedirect("listarUsuarios.jsp");
        } catch (SQLException e) {
            response.getWriter().println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }
}
