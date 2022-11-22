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

    @Override
    public String toString() {
        return "Tijolo: " + this.getNome() + " - R$ " + this.getPreco() + " - Cor: " + this.getCor() + " - Peso: " + this.getPeso();
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
