package Controles;

import Entidades.Exemplar;
import Entidades.Publicacao;
import Visões.ViewPublicacao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class ControlePublicacao implements Serializable {

    ViewPublicacao vP;

    private ArrayList<Publicacao> Publicacoes = new ArrayList();

    public ArrayList<Publicacao> getPublicacoes() {
        return Publicacoes;
    }

    public ControlePublicacao(ViewPublicacao view) {
        vP = view;
        try {
            deserializaPublicacao();
            System.out.println(Publicacoes.size() + "Publicacoes");
        } catch (Exception e) {
            vP.showErrorMessage(0);
        }
    }

    public boolean searchPublicacao(String titulo) {    //procura por titulo
        try {
            deserializaPublicacao();
        } catch (Exception e) {
        }
        for (int i = 0; i < Publicacoes.size(); i++) {
            Publicacao p = (Publicacao) Publicacoes.get(i);
            if (p.getTitulo().equals(titulo)) {
                return true;
            }
        }
        return false;
    }

    public boolean searchPublicacao(int id) {
        try {
            deserializaPublicacao();
        } catch (Exception e) {
        }//procura por isbn
        for (int i = 0; i < Publicacoes.size(); i++) {
            Publicacao p = Publicacoes.get(i);
            if (p.getISBN() == id) {
                return true;
            }
        }
        return false;
    }

    public void cadastraPublicacao(int id, String titulo, String autor, String editora) throws Exception {
        try {
            Publicacoes.add(new Publicacao(id, titulo, autor, editora));
            serializaPublicacao();
            //deserializaPublicacao();
            //Print de teste
            for (int i = 0; i < Publicacoes.size(); i++) {
                Publicacao a = Publicacoes.get(i);
                String s = "Titulo " + a.getTitulo() + " Autor: " + a.getAutor() + " Editora: " + a.getEditora() + " ISBN: " + a.getISBN() + " Exemplares: " + a.getExemplares();
                System.out.println(s);
            }

        } catch (Exception ex) {
            throw new Exception();
        }
    }

    public void checkId(int id) throws Exception { //checa se publicação com id ja existe
        for (int i = 0; i < Publicacoes.size(); i++) {
            
            Publicacao a = Publicacoes.get(i);
            if (a.getISBN() == id) {
                throw new Exception();
            }
        }
    }

    public String showPublicacao(String text) throws Exception{
        String tudo = "";
        for (int i = 0; i < Publicacoes.size(); i++) {
            
            String emprestados = "", naoemprestados = "";
            Exemplar e;
            Publicacao p = Publicacoes.get(i);
            if (p.getTitulo().equals(text)) {
                for (int j = 0; j < p.getExemplares().size(); j++) {
                    e = p.getExemplares().get(j);
                    if (e.getFlag() == 1) {
                        emprestados += "Emprestado: \nISBN: " + e.getISBN() + " Número: " + e.getNumero() + "\n\n";
                    }
                    if (e.getFlag() == 0) {
                        naoemprestados += "Não emprestado: \nISBN: " + e.getISBN() + " Número: " + e.getNumero()+ "\n\n";
                    }
                }
            }
            tudo += emprestados;
            tudo += naoemprestados;
        }
        if("".equals(tudo))
            throw new Exception();
        else
            return tudo;
        
    }

    public String showPublicacao(int id) throws Exception{
        try {
            deserializaPublicacao();}
        catch(Exception e){
            vP.showErrorMessage(1);
        }
        String tudo = "";
        for (int i = 0; i < Publicacoes.size(); i++) {
            String emprestados = "", naoemprestados = "";
            Exemplar e;
            Publicacao p = (Publicacao) Publicacoes.get(i);
            
            if (p.getISBN() == id) {
                for (int j = 0; j < p.getExemplares().size(); j++) {
                    
                    e = (Exemplar) p.getExemplares().get(j);
                    
                    if (e.getFlag() == 1) {
                        emprestados += "Emprestado: \nISBN: " + e.getISBN() + " Número: " + e.getNumero() + "\n\n";
                    }
                    if (e.getFlag() == 0) {
                        naoemprestados += "Não emprestado: \nISBN: " + e.getISBN() + " Número: " + e.getNumero() + "\n\n";
                    }
                }
            }
            tudo += emprestados;
            tudo += naoemprestados;
        }
        if("".equals(tudo))
            throw new Exception();
        
        else
            return tudo;
        
    }

    public void deserializaPublicacao() throws Exception {

        try {
            File objFile = new File("Publicacoes.dat");
            if (objFile.exists()) {
                FileInputStream objFileIS = new FileInputStream("Publicacoes.dat");
                ObjectInputStream objIS = new ObjectInputStream(objFileIS);
                Publicacoes = (ArrayList) objIS.readObject();

                objIS.close();
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public void serializaPublicacao() throws Exception {
        try {
            FileOutputStream objFileOS = new FileOutputStream("Publicacoes.dat");
            ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
            objOS.writeObject(Publicacoes);
            objOS.flush();
            objOS.close();
        } catch (Exception e) {
            throw new Exception();
        }
    }

}
