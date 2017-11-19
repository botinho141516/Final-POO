package Entidades;

import java.util.ArrayList;
import java.io.Serializable;

public class Publicacao implements Serializable{

    private int ISBN;
    private String titulo;
    private String autor;
    private String editora;
    private ArrayList<Exemplar> exemplares = new ArrayList();

    public Publicacao(int ISBN, String titulo, String autor, String editora){//, ArrayList<Exemplar> exemplares) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public ArrayList<Exemplar> getExemplares() {
        return exemplares;
    }

    public void setExemplares(ArrayList<Exemplar> exemplares) {
        this.exemplares = exemplares;
    }

}
