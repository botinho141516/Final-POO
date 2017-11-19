package Controles;

import Entidades.Associado;
import Visões.ViewAssociado;
import java.io.*;
import java.util.ArrayList;

public class ControleAssociado implements Serializable {

    ViewAssociado vA;
    private ArrayList<Associado> Associados = new ArrayList();

    public ControleAssociado(ViewAssociado view) {
        vA = view;
        try {
            deserializaAssociados();
        } catch (Exception e) {
            vA.showMessageError(0);
        }

    }

    public void cadastraAsssociado(int id, String nome, String endereco, String email, String status) {
        Associados.add(new Associado(id, nome, endereco, email, status));
        try {
            serializaAssociados();
            deserializaAssociados();
            //Print de teste
            for (int i = 0; i < Associados.size(); i++) {
                Associado a = (Associado) Associados.get(i);
                String s = "Nome " + a.getName() + " Email " + a.getEmail() + " Endereco " + a.getEndereco() + " Status " + a.getStatus() + " Codigo " + a.getCodigo();
                System.out.println(s);
            }

        } catch (Exception ex) {
            vA.showMessageError(1);
        }
    }

    public void checkIdAssociado(int id) throws Exception { //checa se o ID ja esta cadastrado
        for (int i = 0; i < Associados.size(); i++) {
            Associado a = (Associado) Associados.get(i);
            if (a.getCodigo() == id) {
                throw new Exception();
            }
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

    public void deserializaAssociados() throws Exception {

        try {
            File objFile = new File("Associados.dat");
            if (objFile.exists()) {
                FileInputStream objFileIS = new FileInputStream("Associados.dat");
                ObjectInputStream objIS = new ObjectInputStream(objFileIS);
                Associados = (ArrayList) objIS.readObject();

                objIS.close();
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public ArrayList<Associado> getAssociados() {
        return Associados;
    }

    public void setAssociados(ArrayList<Associado> Associados) {
        this.Associados = Associados;
    }
}
