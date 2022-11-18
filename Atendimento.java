import java.sql.Connection;

public class Atendimento {
    public static void main(String[] args) {
        try {
            Connection conexao = DAO.createConnection();
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
