package Visões;

import Controles.ControlePublicacao;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ViewPublicacao implements ActionListener{

    ControlePublicacao cP;
    
    JPanel main = new JPanel(new FlowLayout(1,5000,8));
    
    JTextField tIsbn = new JTextField(20);
    JTextField tTitulo = new JTextField(20);
    JTextField tAutor = new JTextField(20);
    JTextField tEditora = new JTextField(20);
    JButton okBtn = new JButton("Cadastrar");
    
    public JPanel ViewCadastraPublicacao()
    {
        JLabel isbn = new JLabel("Código");
        JLabel titulo = new JLabel("Título");
        JLabel autor = new JLabel("Autor");
        JLabel editora = new JLabel("Editora");
        JLabel blank = new JLabel(" ");
        
        main.add(isbn);
        main.add(tIsbn);
        main.add(titulo);
        main.add(tTitulo);
        main.add(autor);
        main.add(tAutor);
        main.add(editora);
        main.add(tEditora);
        main.add(blank);
        main.add(okBtn);
        
        okBtn.addActionListener(this);
        
        
        main.setSize(400,400);
        main.setVisible(true);
        
        return main;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == okBtn)
        {
            try {
                int id = Integer.parseInt(tIsbn.getText());
                cP.cadastraPublicacao(id,tTitulo.getText(),tTitulo.getText(),tAutor.getText(),tEditora.getText());
                
            }catch(Exception ex)
            {
                JOptionPane.showMessageDialog(main,"O campo ISBN deve ser um numero inteiro");
            }
        }
        
    }
}
