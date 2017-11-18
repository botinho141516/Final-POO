package Controles;

import Entidades.Publicacao;
import Visões.ViewPublicacao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;



public class ControlePublicacao {
    
    ViewPublicacao vP;
    
    private ArrayList<Publicacao> Publicacoes = new ArrayList();

    public ArrayList<Publicacao> getPublicacoes() {
        return Publicacoes;
    }
         
    public ControlePublicacao(ViewPublicacao view)
    {
        vP = view;
    }

    public void cadastraPublicacao(int id, String text0, String text1, String text2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public boolean searchPublicacao(String titulo) {    //procura por titulo
        boolean achou = false;
        return achou;
    }
    public boolean searchPublicacao(int id) {   //procura por isbn
        boolean achou = false;
        return achou;
    }
    
    
    public void serializaPublicacoes() throws Exception {

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
    
    public void cadastraPublicacao(int id, String text, String text0, String text1, String text2) {
        Publicacoes.add(new Publicacao(id, text, text0, text1));
        try {
            serializaPublicacoes();
            
        } catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Erro ao serializar");
        }
    }

    public void checkId(int id) { //checa se publicação com id ja existe
        
    }

    public String showPublicacao(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String showPublicacao(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
