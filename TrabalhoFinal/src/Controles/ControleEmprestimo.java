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
                        return;
                    } else {
                        throw new Exception();
                    }
                }
            }
        }
    }

    public void checkID(int id) throws Exception {
        Associado a;
        for (int i = 0; i < Emprestimos.size(); i++) {
            Emprestimo em = (Emprestimo) Emprestimos.get(i);
            if (em.getCodigoAssociado() == id) {
                for (int j = 0; j < ctrlasso.getAssociados().size(); j++) {
                    a = (Associado) ctrlasso.getAssociados().get(j);
                    if (a.getCodigo() == id) {
                        return;
                    } else {
                        throw new Exception();
                    }
                }
            }
        }
    }
    
    public boolean checkIfAtraso(String cargo, Date dia)
    {
        if(diferencaData(cargo,dia) > 0)
            return true;
        
        return false;
    }

    public int diferencaData(String cargo, Date dEmprestimo) {
        int diasAtraso = 0;
        int diasDisponiveis = 0;
        
        switch(cargo)
        {
            case " Aluno Graduação ":
                diasDisponiveis = 7;
                break;
            case " Aluno Pós-Graduação ":
                diasDisponiveis = 10;
                
                break;
                
            case " Professor ":
                diasDisponiveis = 14;
                break;
                
            default:;
                System.out.println("ta errado");
                break;
        }
        
        Date now = new Date();
        
        //in milliseconds
        long dif = now.getTime() - dEmprestimo.getTime();
        
        dif = dif/(1000*60*60*24); //transforma milisegundos em dias
        
        if(dif > diasDisponiveis)
            diasAtraso = (int)dif - diasDisponiveis;

        return diasAtraso;
    }

    
    public void devolveExemplar(int isbn, int id) throws Exception {
        Date d = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        Exemplar e;
        Associado a;

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

    public String showAtrasados() throws Exception {
        String lista = "Publicações atrasadas:\n\n";
        
        Associado a = checkAtraso();
        
        if(a != null)
        {
            lista+=a.getCodigo() + " - " + a.getName() + ": ";
            for(int i=0;i<Emprestimos.size();i++)
            {
                if(a.getCodigo() == Emprestimos.get(i).getCodigoAssociado())
                {
                    if(checkIfAtraso(a.getStatus(), Emprestimos.get(i).getDataEmprestimo()))
                        lista+= "\t\t" + Emprestimos.get(i).getISBN() + " - " + 
                                diferencaData(a.getStatus(),Emprestimos.get(i).getDataEmprestimo()) + "\n";
                }
            }
        }
        
        System.out.println(lista);
        return lista;
    }
    
    public Associado checkAtraso()
    {
        for(int i=0;i<Emprestimos.size();i++)
        {
            Emprestimo e = Emprestimos.get(i);
            for(int j=0;j<ctrlasso.getAssociados().size();j++)
            {
                Associado a = ctrlasso.getAssociados().get(j);

                if(a.getCodigo() == e.getCodigoAssociado())
                {
                    if(checkIfAtraso(a.getStatus(), e.getDataEmprestimo()))
                        return a;
                }
            }
        }
        return null;
    }
}
