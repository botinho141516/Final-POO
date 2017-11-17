package Visões;

import Controles.ControlePublicacao;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ViewPublicacao implements ActionListener{

    ControlePublicacao cP;
    
    JTextField tIsbnC = new JTextField(20);
    JTextField tTituloC = new JTextField(20);
    JTextField tIsbnS = new JTextField(20);
    JTextField tTituloS = new JTextField(20);
    JTextField tAutor = new JTextField(20);
    JTextField tEditora = new JTextField(20);
    JLabel blank = new JLabel(" ");
    JLabel blank1 = new JLabel(" ");
    JButton okBtn = new JButton("Cadastrar");
    JButton searchButton = new JButton("Buscar");
    public ViewPublicacao() {
        cP = new ControlePublicacao(this);
    }
    
    public JPanel ViewCadastraPublicacao()
    {
        JPanel main = new JPanel(new FlowLayout(1,5000,8));
      
        
        main.add(new JLabel("Código"));
        main.add(tIsbnC);
        main.add(new JLabel("Título"));
        main.add(tTituloC);
        main.add(new JLabel("Autor"));
        main.add(tAutor);
        main.add(new JLabel("Editora"));
        main.add(tEditora);
        main.add(blank1);
        main.add(okBtn);
        
        okBtn.addActionListener(this);
        
        main.setSize(400,400);
        
        return main;
    }
    
    public JPanel ViewConsultarPublicacao()
    {
        JPanel main = new JPanel(new FlowLayout(1,5000,8));
       
        main.add(new JLabel("ISBN"));
        main.add(tIsbnS);
        main.add(new JLabel("Título"));
        main.add(tTituloS);
        main.add(blank);
        main.add(searchButton);
        
        okBtn.addActionListener(this);
        
        main.setSize(400,400);
        
        return main;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == okBtn)
        {
            try {
                int id = Integer.parseInt(tIsbnC.getText());
                cP.cadastraPublicacao(id,tTituloC.getText(),tAutor.getText(),tEditora.getText());
                
            }catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"O campo ISBN deve ser um numero inteiro");
            }
        }
        else if(e.getSource() == searchButton)
        {
            if("".equals(tIsbnS.getText()))
            {
                cP.searchPublicacao(tTituloS.getText());
            }
            else if("".equals(tTituloS.getText()))
            {
                try {
                    int id = Integer.parseInt(tIsbnS.getText());
                    cP.searchPublicacao(id);

                }catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"O campo ISBN deve ser um numero inteiro");
                }
                
            }
            else if("".equals(tIsbnS.getText()) && "".equals(tTituloS.getText()))
            {
                JOptionPane.showMessageDialog(null,"Algum dos parametros de pesquisa devem ser preenchidos");
            }
        }
        
    }
}
