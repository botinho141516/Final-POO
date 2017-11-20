package Controles;

import Entidades.Exemplar;
import Entidades.Publicacao;
import Visões.ViewExemplar;
import Visões.ViewPublicacao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ControleExemplar implements Serializable {

    ViewPublicacao v = new ViewPublicacao();
    ControlePublicacao ctrlpub = new ControlePublicacao(v);
    Publicacao p;
    ViewExemplar vE;
    private ArrayList<Exemplar> Exemplares = new ArrayList();

    public ControleExemplar(ViewExemplar view) {
        vE = view;
        try {
            ctrlpub.deserializaPublicacao();

        } catch (Exception ex) {
            vE.showMessageError(0);
        }
    }

    public void cadastraExemplar(int id, int numero, int preco) {
        try {
            ctrlpub.deserializaPublicacao();
        } catch(Exception e)  {
        }
        for (int i = 0; i < ctrlpub.getPublicacoes().size(); i++) {
            Publicacao pu = ctrlpub.getPublicacoes().get(i);
            if (id == pu.getISBN()) {
                pu.getExemplares().add(new Exemplar(id, numero, preco, 0));
                try {
                    ctrlpub.serializaPublicacao();
                } catch (Exception ex) {
                    vE.showMessageError(1);
                }
            }
        }
    }
    
    public void checkNumero(int id,int numero) throws Exception {
        ArrayList<Exemplar> e = null;
        
        for (int i = 0; i < ctrlpub.getPublicacoes().size(); i++) {
            Publicacao a = ctrlpub.getPublicacoes().get(i);
            if(id == a.getISBN())
                e = a.getExemplares();
        }
        for(int i=0;i<e.size();i++)
        {
            if (e.get(i).getNumero() == numero) {
                throw new Exception();
            }
        }
        
        
    }

    public void checkId(int isbn) throws Exception {    //procura se o ID que foi digitado realmente existe
        for (int i = 0; i < Exemplares.size(); i++) {
            Exemplar a = (Exemplar) Exemplares.get(i);
            if (a.getISBN() == isbn) {
                throw new Exception();
            }
        }
    }

    public void serializaExemplares() throws Exception {
        try {
            FileOutputStream objFileOS = new FileOutputStream("Exemplares.dat");
            ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
            objOS.writeObject(Exemplares);
            objOS.flush();
            objOS.close();
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public void deserializaExemplares() throws Exception {

        try {
            File objFile = new File("Exemplares.dat");
            if (objFile.exists()) {
                FileInputStream objFileIS = new FileInputStream("Exemplares.dat");
                ObjectInputStream objIS = new ObjectInputStream(objFileIS);
                Exemplares = (ArrayList) objIS.readObject();
                objIS.close();
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
