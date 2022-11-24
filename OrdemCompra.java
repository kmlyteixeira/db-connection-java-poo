import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                "INSERT INTO ordem_compra (id_cliente, data) VALUES (?, ?, ?);");
        stmt.setInt(1, this.getCliente().getId());
        stmt.setString(4, this.getData());
        stmt.execute();

        stmt = DAO.createConnection().prepareStatement(
                "INSERT INTO produtos_oc (id_produto, id_ordemcompra, quantidade) VALUES (null, ?, ?, ?);");
        stmt.setInt(1, this.getProduto().getId());
        stmt.setInt(2, this.getId());
        stmt.setInt(3, this.getQuantidade());
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

    public static OrdemCompra getOrdemCompra(Connection conexao, int id) throws Exception {
        PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM ordem_compra WHERE id = ?;");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            OrdemCompra ordemCompra = new OrdemCompra(Cliente.getCliente(conexao, rs.getInt("id_cliente")),
                    Produto.getProduto(conexao, rs.getInt("id_produto")), rs.getInt("quantidade"));
            ordemCompra.setId(rs.getInt("id"));
            return ordemCompra;
        }
        return null;
    }

    public static void ImprimirOrdensCompra(Connection conexao) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("SELECT oc.id AS id, " +
                                                            "     oc.data AS data, " +
                                                            "     Group_concat(p.nome) AS produto, pc.quantidade," +
                                                            "     c.nome AS nome " +
                                                            "    FROM ordem_compra oc" +
                                                            "          INNER JOIN produtos_oc pc" +
                                                            "                    ON oc.id = pc.id_ordemcompra" +
                                                            "          INNER JOIN cliente c" +
                                                            "                    ON c.id = oc.id_cliente" +
                                                            "          INNER JOIN produto p" +
                                                            "                    ON pc.id_produto = p.id; ");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.println("ID Ordem Compra: " + rs.getInt("id") + 
                               " - data: " + rs.getDate("data") +
                               " - produto: " + rs.getString("produto") +
                               " - cliente: " + rs.getString("nome"));
        }
    }

    public void alteraData(Connection conexao, String data, int id) throws Exception {
        PreparedStatement stmt = conexao.prepareStatement("UPDATE ordem_compra SET data = ? WHERE id = ?;");
        stmt.setString(1, data);
        stmt.setInt(2, id);
        stmt.execute();
    }

    public static void excluirOrdemCompra(Connection conexao, int id) throws Exception {
        PreparedStatement stmt = conexao.prepareStatement("DELETE FROM ordem_compra WHERE id = ?;");
        stmt.setInt(1, id);
        stmt.execute();

        stmt = conexao.prepareStatement("DELETE FROM produtos_oc WHERE id_ordemcompra = ?;");
        stmt.setInt(1, id);
        stmt.execute();
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
