import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

public class OrdemCompra {
    private int id;
    private Cliente cliente;
    private Produto produto;
    private int quantidade;
    private String data;

    public OrdemCompra(Cliente cliente, Produto produto, int quantidade) throws Exception {
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.data = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));

        PreparedStatement stmt = DAO.createConnection().prepareStatement(
                "INSERT INTO ordem_compra (cliente_id, produto_id, quantidade, data) VALUES (?, ?, ?, ?);");
        stmt.setInt(1, this.getCliente().getId());
        stmt.setInt(2, this.getProduto().getId());
        stmt.setInt(3, this.getQuantidade());
        stmt.setString(4, this.getData());
        stmt.execute();
        DAO.closeConnection();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Ordem de Compra: " + this.getId() + " - Cliente: " + this.getCliente().getNome() + " - Produto: " + this.getProduto().getNome() + " - Quantidade: " + this.getQuantidade() + " - Data: " + this.getData();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof OrdemCompra)) {
            return false;
        }
        final OrdemCompra other = (OrdemCompra) object;

        return this.getId() == other.getId();
    }
}
