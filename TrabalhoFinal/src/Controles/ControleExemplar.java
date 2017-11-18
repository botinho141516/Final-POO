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
        es();
    }

    public void serializaExemplares() {
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
                Exemplares = (ArrayList) objIS.readObject();
                objIS.close();
            }
        } catch (Exception e) {

        }
    }

    public void cadastraExemplar(int id, int numero, int preco) {
        for (int i = 0; i < ctrlpub.getPublicacoes().size(); i++) {
            Publicacao pu = (Publicacao) ctrlpub.getPublicacoes().get(i);
            if (id == pu.getISBN()) {
                pu.getExemplares().add(new Exemplar(id, numero, preco, 0));
                serializaExemplares();
            }
        }
    }

}
