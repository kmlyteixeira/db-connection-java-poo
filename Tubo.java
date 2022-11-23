import java.sql.Connection;
import java.sql.PreparedStatement;

public class Tubo extends Produto {
    private String cor;
    private double tamanho;

    public Tubo(String nome, double preco, String cor, double tamanho) throws Exception {
        super(nome, preco);
        this.cor = cor;
        this.tamanho = tamanho;

        PreparedStatement stmt = DAO.createConnection().prepareStatement(
                "INSERT INTO produto (nome, preco, cor, tamanho, id_tipo) VALUES (?, ?, ?, ?, ?);");
        stmt.setString(1, this.getNome());
        stmt.setDouble(2, this.getPreco());
        stmt.setString(3, this.getCor());
        stmt.setDouble(4, this.getTamanho());
        stmt.setInt(5, 1);
        stmt.execute();
        DAO.closeConnection();
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    public static void alteraCor(Connection conexao, String cor, int id) throws Exception {
        PreparedStatement stmt = conexao.prepareStatement("UPDATE produto SET cor = ? WHERE id = ?;");
        stmt.setString(1, cor);
        stmt.setInt(2, id);
        stmt.execute();
    }

    public static void alteraTamanho(Connection conexao, double tamanho, int id) throws Exception {
        PreparedStatement stmt = conexao.prepareStatement("UPDATE produto SET tamanho = ? WHERE id = ?;");
        stmt.setDouble(1, tamanho);
        stmt.setInt(2, id);
        stmt.execute();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof Tubo)) {
            return false;
        }
        final Tubo other = (Tubo) object;

        return this.getId() == other.getId();
    }
}
