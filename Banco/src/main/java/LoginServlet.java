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
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        // Realizar a autenticação do usuário
        if (autenticarUsuario(login, senha)) {
            HttpSession session = request.getSession();
            session.setAttribute("login", login);
            response.sendRedirect("paginaInicial.jsp");
        } else {
            response.getWriter().println("Login ou senha inválidos.");
        }
    }

    private boolean autenticarUsuario(String login, String senha) {
        try (Connection conexao = ConexaoUtil.obterConexao()) {
            String sql = "SELECT * FROM Usuario WHERE login = ? AND senha = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            // Verificar se encontrou algum usuário com o login e senha fornecidos
            if (rs.next()) {
                return true; // Autenticação bem-sucedida
            } else {
                return false; // Autenticação falhou
            }
        } catch (SQLException e) {
            System.out.println("Erro ao autenticar usuário: " + e.getMessage());
            return false;
        }
    }

}
