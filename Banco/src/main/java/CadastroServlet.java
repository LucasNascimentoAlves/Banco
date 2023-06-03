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

@WebServlet("/CadastroServlet")
public class CadastroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String confirmarEmail = request.getParameter("confirmarEmail");
        String senha = request.getParameter("senha");
        String confirmarSenha = request.getParameter("confirmarSenha");

        try {
            if (verificarLoginExistente(login)) {
                response.getWriter().println("O login já está em uso. Escolha outro login.");
            } else if (senha.equals(login)) {
                response.getWriter().println("A senha deve ser diferente do login.");
            } else if (!email.equals(confirmarEmail)) {
                response.getWriter().println("Os campos de email não coincidem.");
            } else if (!senha.equals(confirmarSenha)) {
                response.getWriter().println("Os campos de senha não coincidem.");
            } else {
                // Realizar o cadastro no banco de dados
                try (Connection conexao = ConexaoUtil.obterConexao()) {
                    String sql = "INSERT INTO USUARIO (login, senha, nome, email) VALUES (?, ?, ?, ?)";
                    PreparedStatement stmt = conexao.prepareStatement(sql);
                    stmt.setString(1, login);
                    stmt.setString(2, senha);
                    stmt.setString(3, nome);
                    stmt.setString(4, email);
                    stmt.executeUpdate();

                    response.getWriter().println("Usuário cadastrado com sucesso.");
                } catch (SQLException e) {
                    response.getWriter().println("Erro ao cadastrar usuário: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            response.getWriter().println("Erro ao verificar login existente: " + e.getMessage());
        }
    }

    private boolean verificarLoginExistente(String login) throws SQLException {
        try (Connection conexao = ConexaoUtil.obterConexao()) {
            String sql = "SELECT * FROM USUARIO WHERE login = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Retorna true se encontrar algum usuário com o mesmo login
        }
    }
}
