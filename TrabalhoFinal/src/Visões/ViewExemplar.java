package Visões;

import Controles.ControleExemplar;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ViewExemplar implements ActionListener{

    ControleExemplar cE;
    
    JPanel main = new JPanel(new FlowLayout(1,5000,8));
    
    JTextField tIsbn = new JTextField(20);
    JTextField tNumero = new JTextField(15);
    JTextField tPreco = new JTextField(10);
    JButton okBtn = new JButton("Cadastrar");
    
    public ViewExemplar() {
        cE = new ControleExemplar(this);
    }
    
    public JPanel ViewCadastraExemplar()
    {
        
        JLabel isbn = new JLabel("Código");
        JLabel numero = new JLabel("Numero");
        JLabel preco = new JLabel("Preço");
        JLabel blank = new JLabel(" ");
        
        main.add(isbn);
        main.add(tIsbn);
        main.add(numero);
        main.add(tNumero);
        main.add(preco);
        main.add(tPreco);
        main.add(blank);
        main.add(okBtn);
        
        okBtn.addActionListener(this);
        
        main.setSize(400,400);
        
        return main;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == okBtn)
        {
            
            if("".equals(tIsbn.getText()) || "".equals(tNumero.getText()) || "".equals(tPreco.getText()))
                JOptionPane.showMessageDialog(null,"Todos os campos devem ser preenchidos");
            
            else
            {
                try {
                    int id = Integer.parseInt(tIsbn.getText());
                    
                    try{
                        cE.checkId(id);
                    } catch(Exception ex)
                    {
                        JOptionPane.showMessageDialog(null,"ID digitado não cadastrado");
                    }
                    int numero = Integer.parseInt(tNumero.getText());
                    int preco = Integer.parseInt(tPreco.getText());
                    cE.cadastraExemplar(id,numero,preco); //A exception da aqui, inicializa a variavel ctrlpub no ControleExemplar
                
                }catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(main,"Todos os campos devem ser numeros inteiros");
                }
            }
        }
        
    }
}
