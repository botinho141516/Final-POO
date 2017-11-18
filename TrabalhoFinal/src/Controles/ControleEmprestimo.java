package Controles;

import Entidades.Exemplar;
import Entidades.Publicacao;
import Visões.ViewEmprestimo;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class ControleEmprestimo {

    ControlePublicacao ctrlpub;
    ViewEmprestimo vE;

    public ControleEmprestimo(ViewEmprestimo view) {
        vE = view;
    }

    public Date checkDate(String pData) {

        String test = pData;
        String format = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);

        try {
            Date date = sdf.parse(test);
            if (!sdf.format(date).equals(test)) {
                throw new Exception();
            } else {
                return date;
            }
        } catch (Exception ex) {

            return null;
        }
    }

    public void cadastraExemplar(int id, int isbn, Date data) {
        Exemplar e;
        for (int i = 0; i < ctrlpub.getPublicacoes().size(); i++) {
            Publicacao pu = (Publicacao) ctrlpub.getPublicacoes().get(i);
            if (isbn == pu.getISBN()) {
                for (int k = 0; k < pu.getExemplares().size(); k++) {
                    e = (Exemplar) pu.getExemplares().get(k);
                    if (id == e.getISBN() && e.getFlag() == 0) {
                        e.setFlag(1);
                    }
                }
            }
        }
    }

    public void checkIsbn(String text) throws Exception {

        for (int i = 0; i < ctrlpub.getPublicacoes().size(); i++) {
            Publicacao pu = (Publicacao) ctrlpub.getPublicacoes().get(i);
            Exemplar e;
            if (Integer.parseInt(text) == pu.getISBN()) {
                for (int j = 0; j < pu.getExemplares().size(); j++) {
                    e = (Exemplar) pu.getExemplares().get(j);
                    if (e.getFlag() == 1) {
                        System.out.println("imprima os exemplares emprestados aqui");
                    } else {
                        System.out.println("imprima os exemplares não emprestados aqui");
                    }
                }
            }
        }
    }

    public void checkID(String text) throws Exception {
        for (int i = 0; i < ctrlpub.getPublicacoes().size(); i++) {
            Publicacao pu = (Publicacao) ctrlpub.getPublicacoes().get(i);
            Exemplar e;
            if ((text) == pu.getTitulo()) {
                for (int j = 0; j < pu.getExemplares().size(); j++) {
                    e = (Exemplar) pu.getExemplares().get(j);
                    if (e.getFlag() == 1) {
                        System.out.println("imprima os exemplares emprestados aqui");
                    } else {
                        System.out.println("imprima os exemplares não emprestados aqui");
                    }
                }
            }
        }
    }

    public void devolveExemplar(String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String checkAtrasos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
