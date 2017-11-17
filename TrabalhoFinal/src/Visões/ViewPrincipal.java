package Visões;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class ViewPrincipal extends JFrame implements ActionListener{
    String[] opt = {
        "Associado",
        "Publicação",
        "Exemplar",
        "Emprestimo"
    };
    
    JComboBox cb = new JComboBox(opt);
    
    public ViewPrincipal()
    {
        super("Biblioteca SC");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
