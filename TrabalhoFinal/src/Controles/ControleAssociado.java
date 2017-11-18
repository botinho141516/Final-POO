package Controles;

import Entidades.Associado;
import Visões.ViewAssociado;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ControleAssociado implements Serializable{

    ViewAssociado vA;
    private ArrayList<Associado> Associados = new ArrayList();

    public ControleAssociado(ViewAssociado view) {
        vA = view;
        try {
            serializaAssociados();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao abrir");
        }
        
    }

    public void deserializaAssociados() throws Exception {
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

    public void serializaAssociados() throws Exception {

        try {
            File objFile = new File("Associados.dat");
            if (objFile.exists()) {
                FileInputStream objFileIS = new FileInputStream("Associados.dat");
                ObjectInputStream objIS = new ObjectInputStream(objFileIS);
                Associados = (ArrayList) objIS.readObject();

                objIS.close();
            }
             } catch (Exception e) {
                 e.printStackTrace();
                 throw new Exception();
        }
    }

    public void cadastraAsssociado(int id, String nome, String endereco, String email, String status) {
       Associados.add(new Associado(id, nome, endereco, email, status));
       try{
            deserializaAssociados();
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
