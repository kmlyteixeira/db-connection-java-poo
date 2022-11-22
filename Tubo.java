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

    @Override
    public String toString() {
        return "Tubo: " + this.getNome() + " - R$ " + this.getPreco() + " - Cor: " + this.getCor() + " - Tamanho: " + this.getTamanho();
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
