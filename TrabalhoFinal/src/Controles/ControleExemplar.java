package Controles;

import Entidades.Exemplar;
import Entidades.Publicacao;
import Visões.ViewExemplar;
import Visões.ViewPublicacao;
import java.io.Serializable;
import java.util.ArrayList;

public class ControleExemplar implements Serializable {

    ViewPublicacao v = new ViewPublicacao();
    ControlePublicacao ctrlpub = new ControlePublicacao(v);
    Publicacao p;
    ViewExemplar vE;

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
        ctrlpub.deserializaPublicacao();
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
        ArrayList<Exemplar> e = null;
        ctrlpub.deserializaPublicacao();
        for (int i = 0; i < ctrlpub.getPublicacoes().size(); i++) {
            Publicacao a = ctrlpub.getPublicacoes().get(i);
            if(a.getISBN() == isbn)
                return;
        }
        throw new Exception();
    }

    
}
