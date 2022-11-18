
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

    @Override
    public String toString() {
        return "Produto: " + this.getNome() + " - R$ " + this.getPreco();
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
