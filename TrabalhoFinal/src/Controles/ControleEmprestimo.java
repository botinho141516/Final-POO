package Controles;

import Entidades.Associado;
import Entidades.Emprestimo;
import Entidades.Exemplar;
import Entidades.Publicacao;
import Visões.ViewAssociado;
import Visões.ViewEmprestimo;
import Visões.ViewPublicacao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ControleEmprestimo implements Serializable {

    ViewAssociado a = new ViewAssociado();
    ViewPublicacao pa = new ViewPublicacao();
    ControleAssociado ctrlasso = new ControleAssociado(a);
    ControlePublicacao ctrlpub = new ControlePublicacao(pa);
    ViewEmprestimo vE;
    ArrayList<Emprestimo> Emprestimos = new ArrayList<>();

    public ControleEmprestimo(ViewEmprestimo view) {
        try {
            deserializaEmprestimos();
        } catch (Exception ex) {
            vE.showMessageError(1);
        }
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

    public void cadastraEmprestimo(int idassociado, int numero, int isbn, Date data) throws Exception {
        deserializaEmprestimos();
        ctrlasso.deserializaAssociados();
        ctrlpub.deserializaPublicacao();
        Exemplar e;
        
        for (int i = 0; i < ctrlpub.getPublicacoes().size(); i++) {
            
            Publicacao pu = ctrlpub.getPublicacoes().get(i);
            
            if (isbn == pu.getISBN()) {
                for (int k = 0; k < pu.getExemplares().size(); k++) {
                    e = (Exemplar) pu.getExemplares().get(k);
                    if (numero == e.getNumero() && e.getFlag() == 0) {
                        
                        Emprestimos.add(new Emprestimo(isbn, data, idassociado));
                        e.setFlag(1);
                        
                        try {
                            serializaEmprestimos();
                            ctrlpub.serializaPublicacao();
                        } catch (Exception ex) {
                            vE.showMessageError(1);
                        }
                        return;
                    }
                }
                throw new Exception();
            }
        }
    }

    public void checkIsbn(int isbn) throws Exception {
        ctrlpub.deserializaPublicacao();   
        Publicacao pu;
        
        for (int i = 0; i < ctrlpub.getPublicacoes().size(); i++) {
            
            pu = ctrlpub.getPublicacoes().get(i);
            
        if (pu.getISBN() == isbn) {
                return;
            }
        }
        throw new Exception();
    }
    
    public void checkDisponivel(int isbn,int numero) throws Exception {
        ctrlpub.deserializaPublicacao();
        Publicacao pu;
        Exemplar e;

        for (int i = 0; i < ctrlpub.getPublicacoes().size(); i++) {
            
            pu = ctrlpub.getPublicacoes().get(i);
            
            if (pu.getISBN() == isbn) {
                for (int j = 0; j < pu.getExemplares().size(); j++) {
                    e = pu.getExemplares().get(j);

                    if (e.getFlag() == 1 && e.getNumero() == numero) {
                        throw new Exception();
                    }
                }
            }
        }
        
    }
    
    public void checkNumero(int isbn,int numero) throws Exception {
        ctrlpub.deserializaPublicacao();
       Publicacao pu;
        Exemplar e;

        for (int i = 0; i < ctrlpub.getPublicacoes().size(); i++) {
            
            pu = ctrlpub.getPublicacoes().get(i);
            
            if (pu.getISBN() == isbn) {
                for (int j = 0; j < pu.getExemplares().size(); j++) {
                    e = pu.getExemplares().get(j);

                    if (e.getNumero() == numero) {
                        return;
                    }
                }
                throw new Exception();
            }
        }
        
    }

    public void checkIDassociado(int id) throws Exception {
        ctrlasso.deserializaAssociados();
        Associado a;
        
        for (int j = 0; j < ctrlasso.getAssociados().size(); j++) {
            a = (Associado) ctrlasso.getAssociados().get(j);
            if (a.getCodigo() == id) {
                return;
            }

        }
        
        throw new Exception();
    }

    public boolean checkIfAtraso(String cargo, Date dia) {
        if (diferencaData(cargo, dia) > 0) {
            return true;
        }

        return false;
    }

    public int diferencaData(String cargo, Date dEmprestimo) {
        int diasAtraso = 0;
        int diasDisponiveis = 0;

        switch (cargo) {
            case "Aluno Graduação":
                diasDisponiveis = 7;
                break;
            case "Aluno Pos-Graduação":
                diasDisponiveis = 10;

                break;

            case " Professor ":
                diasDisponiveis = 14;
                break;

            default:
                System.out.println("."+cargo+".");
                break;
        }

        Date now = new Date();

        //in milliseconds
        long dif = now.getTime() - dEmprestimo.getTime();

        dif = dif / (1000 * 60 * 60 * 24); //transforma milisegundos em dias
        System.out.println(now.getTime()+"\n"+dif+"\n"+dEmprestimo.getTime()+"\n\n");
        if (dif > diasDisponiveis) {
            diasAtraso = (int) dif - diasDisponiveis;
        }
        
        System.out.println("Dif: "+ dif + "Dias Disponiveis: "+ diasDisponiveis + "Dia atraso:" + diasAtraso);
        return diasAtraso;
    }

    public void devolveExemplar(int isbn, int id) throws Exception {
        
        try {
            deserializaEmprestimos();
            ctrlpub.deserializaPublicacao();
            ctrlasso.deserializaAssociados();
        } catch (Exception ex) {
        }
        
        Date d = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        Exemplar e;
        Associado a = null;
        int tempoRestante = 0;

        for (int i = 0; i < Emprestimos.size(); i++) {
            Emprestimo em = (Emprestimo) Emprestimos.get(i);
            if (em.getCodigoAssociado() == id) {
                for (int j = 0; j < ctrlasso.getAssociados().size(); j++) {
                    a = (Associado) ctrlasso.getAssociados().get(j);
                    
                    if (a.getCodigo() == id) {
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < ctrlpub.getPublicacoes().size(); i++) {
            Publicacao pu = (Publicacao) ctrlpub.getPublicacoes().get(i);
            if (isbn == pu.getISBN()) {
                for (int k = 0; k < pu.getExemplares().size(); k++) {
                    e = (Exemplar) pu.getExemplares().get(k);
                    if (id == a.getCodigo() && e.getFlag() == 1) {
                        e.setFlag(0);
                        for(int j=0;j<Emprestimos.size();j++)
                        {
                            if(id == Emprestimos.get(j).getCodigoAssociado() && isbn == Emprestimos.get(j).getISBN())
                                tempoRestante = diferencaData(a.getStatus(), Emprestimos.get(j).getDataEmprestimo());
                                Emprestimos.remove(j);
                        }

                        if(tempoRestante > 0)
                            vE.showAtrasoMessage(tempoRestante);
                        
                            ctrlpub.serializaPublicacao();
                        
                        return;
                    }
                }
            }
        }
        throw new Exception();
    }

    public String showAtrasados() throws Exception {
        try {
            serializaEmprestimos();
            ctrlpub.serializaPublicacao();
            ctrlasso.serializaAssociados();
        } catch (Exception ex) {
        }
        String lista = "Publicações atrasadas:\n\n";

        Associado a = checkAtraso();

        if (a != null) {
            lista += a.getCodigo() + " - " + a.getName() + ": \n";
            for (int i = 0; i < Emprestimos.size(); i++) {
                if (a.getCodigo() == Emprestimos.get(i).getCodigoAssociado()) {
                    if (checkIfAtraso(a.getStatus(), Emprestimos.get(i).getDataEmprestimo())) {
                        lista += "        " + Emprestimos.get(i).getISBN() + " - "
                                + diferencaData(a.getStatus(), Emprestimos.get(i).getDataEmprestimo()) + "\n";
                    }
                }
            }
        }
        
        return lista;
    }

    public Associado checkAtraso() {
        for (int i = 0; i < Emprestimos.size(); i++) {
            Emprestimo e = Emprestimos.get(i);
            for (int j = 0; j < ctrlasso.getAssociados().size(); j++) {
                Associado a = ctrlasso.getAssociados().get(j);

                if (a.getCodigo() == e.getCodigoAssociado()) {
                    if (checkIfAtraso(a.getStatus(), e.getDataEmprestimo())) {
                        return a;
                    }
                }
            }
        }
        return null;
    }

    public void serializaEmprestimos() throws Exception {
        try {
            FileOutputStream objFileOS = new FileOutputStream("Emprestimos.dat");
            ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
            objOS.writeObject(Emprestimos);
            objOS.flush();
            objOS.close();
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public void deserializaEmprestimos() throws Exception {

        try {
            File objFile = new File("Emprestimos.dat");
            if (objFile.exists()) {
                FileInputStream objFileIS = new FileInputStream("Emprestimos.dat");
                ObjectInputStream objIS = new ObjectInputStream(objFileIS);
                Emprestimos = (ArrayList) objIS.readObject();

                objIS.close();
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
