import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class Produto {
    private int id;
    private String nome;
    private double preco;

    protected Produto(String nome, double preco) throws Exception {
        this.nome = nome;
        this.preco = preco;
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public static void ImprimirProdutos(Connection conexao) throws Exception {
        PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM produto;");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.println("Produto: " + rs.getString("nome") + " - Preço: " + rs.getString("preco"));
        }
    }

    public static void alterarPreco(Connection conexao, double preco, int id) throws Exception {
        PreparedStatement stmt = conexao.prepareStatement("UPDATE produto SET preco = ? WHERE id = ?;");
        stmt.setDouble(1, preco);
        stmt.setInt(2, id);
        stmt.execute();
    }

    public static void alterarNome(Connection conexao, String nome, int id) throws Exception {
        PreparedStatement stmt = conexao.prepareStatement("UPDATE produto SET nome = ? WHERE id = ?;");
        stmt.setString(1, nome);
        stmt.setInt(2, id);
        stmt.execute();
    }

    public static void excluirProduto(Connection conexao, int id) throws Exception {
        PreparedStatement stmt = conexao.prepareStatement("DELETE FROM produto WHERE id = ?;");
        stmt.setInt(1, id);
        stmt.execute();
    }

    public static Produto getProduto(Connection conexao, int id) throws Exception {
        PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM produto WHERE id = ?;");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.getInt("id_tipo") == 1) {
            Produto tubo = new Tubo(rs.getString("nome"), rs.getDouble("preco"), rs.getString("cor"), rs.getDouble("tamanho"));
            return tubo;
        } else if (rs.getInt("id_tipo") == 2) {
            Produto cabo = new Cabo(rs.getString("nome"), rs.getDouble("preco"), rs.getDouble("peso"), rs.getDouble("tamanho"));
            return cabo;
        } else if (rs.getInt("id_tipo") == 3) {
            Produto tijolo = new Tijolo(rs.getString("nome"), rs.getDouble("preco"), rs.getString("cor"), rs.getDouble("peso"));
            return tijolo;
        }

        return null;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof Produto)) {
            return false;
        }
        final Produto other = (Produto) object;

        return this.id == other.id;
    }
}
