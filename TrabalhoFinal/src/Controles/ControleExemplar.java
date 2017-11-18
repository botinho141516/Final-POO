package Controles;

import Entidades.Exemplar;
import Entidades.Publicacao;
import Visões.ViewExemplar;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class ControleExemplar {

    ControlePublicacao ctrlpub;
    Publicacao p;
    ViewExemplar vE;
    private ArrayList<Exemplar> Exemplares = new ArrayList();

    public ControleExemplar(ViewExemplar view) {
        vE = view;
        try {
            es();
            
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Erro na deserialização");
        }
    }

    public void serializaExemplares() throws Exception{
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

    public void es() throws Exception{

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

    public void cadastraExemplar(int id, int numero, int preco) {
        for (int i = 0; i < ctrlpub.getPublicacoes().size(); i++) {
            Publicacao pu = (Publicacao) ctrlpub.getPublicacoes().get(i);
            if (id == pu.getISBN()) {
                pu.getExemplares().add(new Exemplar(id, numero, preco, 0));
                try {
                    serializaExemplares();
                }catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Erro na serialização");
                }
            }
        }
    }

    public void checkId(int id) throws Exception {//procura se o ID que foi digitado realmente existe
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
