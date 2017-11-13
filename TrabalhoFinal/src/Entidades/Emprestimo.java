package Entidades;

import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Emprestimo {

    private int ISBN;
    private Date dataEmprestimo;
    private int codigoAssociado;

    public Emprestimo(int ISBN, Date dataEmprestimo, int codigoAssociado) {
        this.ISBN = ISBN;
        this.dataEmprestimo = dataEmprestimo;
        this.codigoAssociado = codigoAssociado;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public int getCodigoAssociado() {
        return codigoAssociado;
    }

    public void setCodigoAssociado(int codigoAssociado) {
        this.codigoAssociado = codigoAssociado;
    }

}
