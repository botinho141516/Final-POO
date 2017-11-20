package Entidades;
import java.io.Serializable;


public class Exemplar implements Serializable{

    private int numero;
    private int ISBN;
    private double preco;
    private int flag = 0;

    public Exemplar(int ISBN, int numero, double preco, int flag) {
        this.numero = numero;
        this.ISBN = ISBN;
        this.preco = preco;
        this.flag = flag;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

}
