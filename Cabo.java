import java.sql.PreparedStatement;

public class Cabo extends Produto {

    private double peso;
    private double tamanho;

    public Cabo (String nome, double preco, double peso, double tamanho) throws Exception {
        super(nome, preco);
        this.peso = peso;
        this.tamanho = tamanho;

        PreparedStatement stmt = DAO.createConnection().prepareStatement(
                "INSERT INTO produto (nome, preco, peso, tamanho, id_tipo) VALUES (?, ?, ?, ?, ?);");
        stmt.setString(1, this.getNome());
        stmt.setDouble(2, this.getPreco());
        stmt.setDouble(3, this.getPeso());
        stmt.setDouble(4, this.getTamanho());
        stmt.setInt(5, 2);
        stmt.execute();
        DAO.closeConnection();
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return "Cabo: " + this.getNome() + " - R$ " + this.getPreco() + " - Peso: " + this.getPeso() + " - Tamanho: " + this.getTamanho();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof Cabo)) {
            return false;
        }
        final Cabo other = (Cabo) object;

        return this.getId() == other.getId();
    }
    
}
