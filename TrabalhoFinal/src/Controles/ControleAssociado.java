package Controles;

import Entidades.Associado;
import Visões.ViewAssociado;
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;

public class ControleAssociado {

    ViewAssociado vA;
    private ArrayList<Associado> Associados = new ArrayList();

    public ControleAssociado(ViewAssociado view) {
        vA = view;
        try {
            es();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao abrir");
        }
        
    }

    public void serializaAssociados() throws Exception {
        try {
            FileOutputStream objFileOS = new FileOutputStream("Associados.dat");
            ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
            objOS.writeObject(Associados);
            objOS.flush();
            objOS.close();
        } catch (Exception e) {
                throw new Exception();
        }
    }

    public void es() throws Exception {

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
                 throw new Exception();
        }
    }

    public void cadastraAsssociado(int id, String text, String text0, String text1, String toString) {
        Associados.add(new Associado(id, text, text0, text1, toString));
       try{
            serializaAssociados();
        } catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Erro ao serializar");
        }
    }

    public void checkIdAssociado(int id) throws Exception{ //checa se o ID ja esta cadastrado
        for(int i = 0; i < Associados.size(); i++)
        {
            Associado a = (Associado) Associados.get(i);
            if(a.getCodigo() == id)
                throw new Exception();
        }
    }
}
