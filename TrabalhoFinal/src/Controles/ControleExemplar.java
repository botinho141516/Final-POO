package Controles;

import Entidades.Exemplar;
import Visões.ViewExemplar;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class ControleExemplar {

    ViewExemplar vE;
    private Vector<Exemplar> Exemplares = new Vector();
    
    public ControleExemplar(ViewExemplar view) {
        vE = view;
        es();
    }

    public void serializaPublicacoes() {
        try {
            FileOutputStream objFileOS = new FileOutputStream("Exemplares.dat");
            ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
            objOS.writeObject(Exemplares);
            objOS.flush();
            objOS.close();
        } catch (Exception e) {

        }
    }

    public void es() {

        try {
            File objFile = new File("Exemplares.dat");
            if (objFile.exists()) {
                FileInputStream objFileIS = new FileInputStream("Exemplares.dat");
                ObjectInputStream objIS = new ObjectInputStream(objFileIS);
                Exemplares = (Vector) objIS.readObject();
                objIS.close();
            }
        } catch (Exception e) {

        }
    }

    public void cadastraExemplar(int id, int numero, int preco) {
        Exemplares.add(new Exemplar(id, numero, preco));
    }

}
