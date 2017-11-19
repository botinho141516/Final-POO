package Controles;

import Entidades.Associado;
import Entidades.Emprestimo;
import Entidades.Exemplar;
import Entidades.Publicacao;
import Visões.ViewEmprestimo;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ControleEmprestimo implements Serializable {

    ControleAssociado ctrlasso;
    ControlePublicacao ctrlpub;
    ViewEmprestimo vE;
    ArrayList<Emprestimo> Emprestimos = new ArrayList<>();

    public ControleEmprestimo(ViewEmprestimo view) {
        vE = view;
    }

    public Date checkDate(String pData) throws Exception {

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

            throw new Exception();
        }
    }

    public void cadastraEmprestimo(int id, int isbn, Date data) throws Exception {
        Exemplar e;
        for (int i = 0; i < ctrlpub.getPublicacoes().size(); i++) {
            Publicacao pu = (Publicacao) ctrlpub.getPublicacoes().get(i);
            if (isbn == pu.getISBN()) {
                for (int k = 0; k < pu.getExemplares().size(); k++) {
                    e = (Exemplar) pu.getExemplares().get(k);
                    if (id == e.getISBN() && e.getFlag() == 0) {
                        e.setFlag(1);
                        Emprestimos.add(new Emprestimo(isbn, data, id));
                    }
                }
            }
        }
    }

    public void checkIsbn(int isbn) throws Exception {

        for (int i = 0; i < ctrlpub.getPublicacoes().size(); i++) {

            Publicacao pu = (Publicacao) ctrlpub.getPublicacoes().get(i);
            if (isbn == pu.getISBN()) {
                for (int j = 0; j < pu.getExemplares().size(); j++) {

                    if (pu.getExemplares().get(j).getFlag() == 1) {
                        System.out.println("imprima os exemplares emprestados aqui");
                    } else {
                        System.out.println("imprima os exemplares não emprestados aqui");
                    }
                }
            }
        }
    }

    public void checkID(int id) throws Exception {
        for (int i = 0; i < ctrlpub.getPublicacoes().size(); i++) {
            Publicacao pu = (Publicacao) ctrlpub.getPublicacoes().get(i);
            Exemplar e;
            //if (id == pu.get()) {//DEVE CHECAR NO ARRAYLIST  DE ASSOCIADOS SE O ID EXISTE
            for (int j = 0; j < pu.getExemplares().size(); j++) {
                e = (Exemplar) pu.getExemplares().get(j);
                if (e.getFlag() == 1) {
                    System.out.println("imprima os exemplares emprestados aqui");
                } else {
                    System.out.println("imprima os exemplares não emprestados aqui");
                }
            }
            //}
        }
    }

    public int diferencaData(String cargo, Date hoje) {
        int k = 0;
        return k;
    }

    public void devolveExemplar(int isbn, int id) throws Exception {
        Date d = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        Exemplar e;
        Associado a;
        String s;
        for (int i = 0; i < Emprestimos.size(); i++) {
            Emprestimo em = (Emprestimo) Emprestimos.get(i);
            if (em.getCodigoAssociado() == id) {
                for (int j = 0; j < ctrlasso.getAssociados().size(); j++) {
                    a = (Associado) ctrlasso.getAssociados().get(j);
                    if (a.getCodigo() == id) {
                        diferencaData(a.getStatus(), d);
                    }
                }
            }
        }
        for (int i = 0; i < ctrlpub.getPublicacoes().size(); i++) {
            Publicacao pu = (Publicacao) ctrlpub.getPublicacoes().get(i);
            if (isbn == pu.getISBN()) {
                for (int k = 0; k < pu.getExemplares().size(); k++) {
                    e = (Exemplar) pu.getExemplares().get(k);
                    if (id == e.getISBN() && e.getFlag() == 1) {
                        e.setFlag(0);
                    }
                }
            }
        }
    }

    public String checkAtrasos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
