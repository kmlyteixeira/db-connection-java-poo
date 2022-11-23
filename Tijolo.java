import java.sql.Connection;
import java.sql.PreparedStatement;

public class Tijolo extends Produto {
    private String cor;
    private double peso;

    public Tijolo(String nome, double preco, String cor, double peso) throws Exception {
        super(nome, preco);
        this.cor = cor;
        this.peso = peso;

        PreparedStatement stmt = DAO.createConnection().prepareStatement(
                "INSERT INTO produto (nome, preco, cor, peso, id_tipo) VALUES (?, ?, ?, ?, ?);");
        stmt.setString(1, this.getNome());
        stmt.setDouble(2, this.getPreco());
        stmt.setString(3, this.getCor());
        stmt.setDouble(4, this.getPeso());
        stmt.setInt(5, 3);
        stmt.execute();
        DAO.closeConnection();
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public static void alteraCor(Connection conexao, String cor, int id) throws Exception {
        PreparedStatement stmt = conexao.prepareStatement("UPDATE produto SET cor = ? WHERE id = ?;");
        stmt.setString(1, cor);
        stmt.setInt(2, id);
        stmt.execute();
    }

    public static void alteraPeso(Connection conexao, double peso, int id) throws Exception {
        PreparedStatement stmt = conexao.prepareStatement("UPDATE produto SET peso = ? WHERE id = ?;");
        stmt.setDouble(1, peso);
        stmt.setInt(2, id);
        stmt.execute();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof Tijolo)) {
            return false;
        }
        final Tijolo other = (Tijolo) object;

        return this.getId() == other.getId();
    }
}
