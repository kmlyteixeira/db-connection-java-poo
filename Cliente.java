import java.sql.PreparedStatement;

public class Cliente {
    private int id;
    private String nome;
    private String cpf;

    public Cliente(String nome, String cpf) throws Exception {
        this.nome = nome;
        this.cpf = cpf;

        PreparedStatement stmt = DAO.createConnection().prepareStatement(
                "INSERT INTO cliente (nome, cpf) VALUES (?, ?);");
        stmt.setString(2, this.getNome());
        stmt.setString(3, this.getCpf());
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

    @Override
    public String toString() {
        return "Cliente: " + this.getNome() + " - CPF: " + this.getCpf();
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
