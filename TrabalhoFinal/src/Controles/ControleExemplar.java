package Controles;

import Entidades.Exemplar;
import Entidades.Publicacao;
import Visões.ViewExemplar;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;



public class ControleExemplar implements Serializable{

    ControlePublicacao ctrlpub;
    Publicacao p;
    ViewExemplar vE;
    private ArrayList<Exemplar> Exemplares = new ArrayList();

    public ControleExemplar(ViewExemplar view) {
        vE = view;
        try {
            deserializaExemplares();
            
        }catch(Exception ex)
        {
            vE.showMessageError(0);
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
                    vE.showMessageError(1);
                }
            }
        }
    }

    public void checkId(int isbn) throws Exception {    //procura se o ID que foi digitado realmente existe
        for(int i = 0; i < Exemplares.size(); i++)
        {
            Exemplar a = (Exemplar) Exemplares.get(i);
            if(a.getISBN() == isbn)
                throw new Exception();
        }
    }

    public void checkNumero(int numero) throws Exception{
        
        if(numero <=0)
            throw new Exception();
        
        for(int i=0;i<Exemplares.size();i++)
        {
            Exemplar e = Exemplares.get(i);
            
            if(e.getNumero() == numero)
                throw new Exception();
            
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

    public void deserializaExemplares() throws Exception{

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
