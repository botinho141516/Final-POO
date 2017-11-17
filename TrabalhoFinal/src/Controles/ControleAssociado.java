package Controles;

import Entidades.Associado;
import Visões.ViewAssociado;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class ControleAssociado {

    ViewAssociado vA;
    private Vector<Associado> Associados = new Vector();

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
                Associados = (Vector) objIS.readObject();
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

}
