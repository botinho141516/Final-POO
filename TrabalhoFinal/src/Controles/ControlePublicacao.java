package Controles;

import Entidades.Publicacao;
import Visões.ViewPublicacao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Vector;



public class ControlePublicacao {
    
    ViewPublicacao vP;
    private ArrayList<Publicacao> Publicacoes = new ArrayList();

    public ArrayList<Publicacao> getPublicacoes() {
        return Publicacoes;
    }
         
    public ControlePublicacao(ViewPublicacao view)
    {
        vP = view;
        es();
    }

     public void serializaPublicacoes() {
        try {
            FileOutputStream objFileOS = new FileOutputStream("Publicacoes.dat");
            ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
            objOS.writeObject(Publicacoes);
            objOS.flush();
            objOS.close();
        } catch (Exception e) {

        }
    }

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
    }

}
