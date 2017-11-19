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
            serializaPublicacao();
        } catch (Exception e) {
            vP.showErrorMessage(0);
        }
    }

    public boolean searchPublicacao(String titulo) {    //procura por titulo
        for (int i = 0; i < Publicacoes.size(); i++) {
            Publicacao p = (Publicacao) Publicacoes.get(i);
            if (p.getTitulo() == titulo) {
                return true;
            }
        }
        return false;
    }

    public boolean searchPublicacao(int id) {   //procura por isbn
        for (int i = 0; i < Publicacoes.size(); i++) {
            Publicacao p = (Publicacao) Publicacoes.get(i);
            if (p.getISBN() == id) {
                return true;
            }
        }
        return false;
    }

    public void deserializaPublicacao() throws Exception {
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

    public void serializaPublicacao() throws Exception {

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

    public void cadastraPublicacao(int id, String titulo, String autor, String editora) {
        Publicacoes.add(new Publicacao(id, titulo, autor, editora));
        try {
            serializaPublicacao();

        } catch (Exception ex) {
            vP.showErrorMessage(1);
        }
    }

    public void checkId(int id) throws Exception { //checa se publicação com id ja existe
        for (int i = 0; i < Publicacoes.size(); i++) {
            Publicacao a = (Publicacao) Publicacoes.get(i);
            if (a.getISBN() == id) {
                throw new Exception();
            }
        }
    }

    public String showPublicacao(String text) {
        String tudo = "";
        for (int i = 0; i < Publicacoes.size(); i++) {
            String emprestados = "", naoemprestados = "";
            Exemplar e;
            Publicacao p = (Publicacao) Publicacoes.get(i);
            if (p.getTitulo() == text) {
                for (int j = 0; j < p.getExemplares().size(); j++) {
                    e = (Exemplar) p.getExemplares().get(j);
                    if (e.getFlag() == 1) {
                        emprestados += "Emprestados: \nISBN: " + e.getISBN() + " Número: " + e.getNumero() + "\n\n";
                    }
                    if (e.getFlag() == 0) {
                        naoemprestados += "Não emprestados: \nISBN: " + e.getISBN() + " Número: " + e.getNumero();
                    }
                }
            }
            tudo += emprestados;
            tudo += naoemprestados;
        }
        return tudo;
    }

    public String showPublicacao(int id) {
        String tudo = "";
        for (int i = 0; i < Publicacoes.size(); i++) {
            String emprestados = "", naoemprestados = "";
            Exemplar e;
            Publicacao p = (Publicacao) Publicacoes.get(i);
            if (p.getISBN() == id) {
                for (int j = 0; j < p.getExemplares().size(); j++) {
                    e = (Exemplar) p.getExemplares().get(j);
                    if (e.getFlag() == 1) {
                        emprestados += "Emprestados: \nISBN: " + e.getISBN() + " Número: " + e.getNumero() + "\n\n";
                    }
                    if (e.getFlag() == 0) {
                        naoemprestados += "Não emprestados: \nISBN: " + e.getISBN() + " Número: " + e.getNumero();
                    }
                }
            }
            tudo += emprestados;
            tudo += naoemprestados;
        }
        return tudo;
    }

}
