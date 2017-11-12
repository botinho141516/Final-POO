package Entidades;

public class Associado {

    private int codigo;
    private String name;
    private String endereco;
    private String email;
    private String status;

    private Associado(int codigo, String name, String endereco, String email, String status) {
        this.codigo = codigo;
        this.name = name;
        this.endereco = endereco;
        this.email = email;
        this.status = status;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
