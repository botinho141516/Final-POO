package Controles;

import Entidades.Associado;
import Entidades.Publicacao;
import Visões.ViewPublicacao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao abrir");
        }
    }

    public boolean searchPublicacao(String titulo) {    //procura por titulo
        boolean achou = false;
        return achou;
    }

    public boolean searchPublicacao(int id) {   //procura por isbn
        boolean achou = false;
        return achou;
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
            e.printStackTrace();
            throw new Exception();
        }
    }

    public void cadastraPublicacao(int id, String text, String text0, String text1) {
        Publicacoes.add(new Publicacao(id, text, text0, text1));
        try {
            serializaPublicacao();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao serializar");
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String showPublicacao(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
