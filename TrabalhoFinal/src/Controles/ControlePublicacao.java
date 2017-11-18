package Controles;

import Visões.ViewPublicacao;
<<<<<<< HEAD
=======
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Vector;
>>>>>>> daff38ee6c6464d26fe0a8dd275f6c025ab76e1d



public class ControlePublicacao {
    
    ViewPublicacao vP;
<<<<<<< HEAD
    
=======
    private ArrayList<Publicacao> Publicacoes = new ArrayList();

    public ArrayList<Publicacao> getPublicacoes() {
        return Publicacoes;
    }
         
>>>>>>> daff38ee6c6464d26fe0a8dd275f6c025ab76e1d
    public ControlePublicacao(ViewPublicacao view)
    {
        vP = view;
    }

    public void cadastraPublicacao(int id, String text0, String text1, String text2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
<<<<<<< HEAD
    public void searchPublicacao(String titulo) {
        
    }
    public void searchPublicacao(int id) {
        
=======

    public void es() {

        try {
            File objFile = new File("Publicacoes.dat");
            if (objFile.exists()) {
                FileInputStream objFileIS = new FileInputStream("Publicacoes.dat");
                ObjectInputStream objIS = new ObjectInputStream(objFileIS);
                Publicacoes = (ArrayList) objIS.readObject();
                objIS.close();
            }
             } catch (Exception e) {

        }
    }
    
    public void cadastraPublicacao(int id, String text, String text0, String text1, String text2) {
        Publicacoes.add(new Publicacao(id, text, text0, text1));
        serializaPublicacoes();
>>>>>>> daff38ee6c6464d26fe0a8dd275f6c025ab76e1d
    }

}
