package Controles;

import Entidades.Associado;
import Visões.ViewAssociado;
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

public class ControleAssociado {

    ViewAssociado vA;
    private ArrayList<Associado> Associados = new ArrayList();

    public ControleAssociado(ViewAssociado view) {
        vA = view;
        es();
    }

    public void serializaAssociados() {
        try {
            FileOutputStream objFileOS = new FileOutputStream("Associados.dat");
            ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
            objOS.writeObject(Associados);
            objOS.flush();
            objOS.close();
        } catch (Exception e) {

        }
    }

    public void es() {

        try {
            File objFile = new File("Associados.dat");
            if (objFile.exists()) {
                FileInputStream objFileIS = new FileInputStream("Associados.dat");
                ObjectInputStream objIS = new ObjectInputStream(objFileIS);
                Associados = (ArrayList) objIS.readObject();
                //System.out.println(lista.size());

                objIS.close();
            }
             } catch (Exception e) {

        }
    }

    public void cadastraAsssociado(int id, String text, String text0, String text1, String toString) {
        Associados.add(new Associado(id, text, text0, text1, toString));
        serializaAssociados();
    }

    public void checkIdAssociado(int id) throws Exception{ //checa se o ID ja esta cadastrado
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
