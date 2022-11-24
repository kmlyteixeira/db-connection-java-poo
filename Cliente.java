import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Cliente {
    private int id;
    private String nome;
    private String cpf;

    public Cliente(String nome, String cpf) throws Exception {
        this.nome = nome;
        this.cpf = cpf;

        PreparedStatement stmt = DAO.createConnection().prepareStatement(
                "INSERT INTO cliente (nome, cpf) VALUES (?, ?);");
        stmt.setString(1, this.getNome());
        stmt.setString(2, this.getCpf());
        stmt.execute();
        DAO.closeConnection();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public static Cliente getCliente(Connection conexao, int id) throws Exception {
        PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM cliente WHERE id = ?;");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Cliente cliente = new Cliente(rs.getString("nome"), rs.getString("cpf"));
            cliente.setId(rs.getInt("id"));
            return cliente;
        }
        return null;
    }

    public static void imprimirClientes(Connection conexao) throws Exception {
        PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM cliente;");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.println("Cliente: " + rs.getString("nome") + " - CPF: " + rs.getString("cpf") + "\n\n");
        }
    }

    public static void alterarCpf(Connection conexao, String cpf, int id) throws Exception {
        PreparedStatement stmt = conexao.prepareStatement("UPDATE cliente SET cpf = ? WHERE id = ?;");
        stmt.setString(1, cpf);
        stmt.setInt(2, id);
        stmt.execute();
    }

    public static void alterarNome(Connection conexao, String nome, int id) throws Exception {
        PreparedStatement stmt = conexao.prepareStatement("UPDATE cliente SET nome = ? WHERE id = ?;");
        stmt.setString(1, nome);
        stmt.setInt(2, id);
        stmt.execute();
    }

    public static void excluirCliente(Connection conexao, int id) throws Exception {
        PreparedStatement stmt = conexao.prepareStatement("DELETE FROM cliente WHERE id = ?;");
        stmt.setInt(1, id);
        stmt.execute();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof Cliente)) {
            return false;
        }
        final Cliente other = (Cliente) object;

        return this.id == other.id;
    }
}
