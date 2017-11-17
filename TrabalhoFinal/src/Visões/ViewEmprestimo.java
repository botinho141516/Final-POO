package Visões;
    
import Controles.ControleEmprestimo;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.*;

public class ViewEmprestimo implements ActionListener{
    
    ControleEmprestimo cE;
    JPanel main = new JPanel(new FlowLayout(1,5000,8));
    
    JTextField tIsbn = new JTextField(16);
    JTextField tId = new JTextField(16);
    JTextField tData = new JTextField(16);
    JButton okBtn = new JButton("Emprestar");
    
    public ViewEmprestimo(ControleEmprestimo ctrl)
    {
        cE = ctrl;
        
        JLabel isbn = new JLabel("ISBN");
        JLabel id = new JLabel("ID do cliente ");
        JLabel data = new JLabel("Data de Emprestimo");
        JLabel blank = new JLabel(" ");
        
        main.add(isbn);
        main.add(tIsbn);
        main.add(id);
        main.add(tId);
        main.add(data);
        main.add(tData);
        main.add(blank);
        main.add(okBtn);
        
        okBtn.addActionListener(this);
        
        main.setVisible(true);
        main.setSize(400,400);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == okBtn)
        {
            try {
                int id = Integer.parseInt(tIsbn.getText());
                int isbn = Integer.parseInt(tIsbn.getText());
                
                Date data = cE.checkDate(tData.getText());
                cE.cadastraExemplar(id,isbn,data);
                
            }catch(Exception ex)
            {
                JOptionPane.showMessageDialog(main,"ID e ISBN devem ser numeros inteiros");
            }
        }
        
    }

    public void showMessageError() {
        
        JOptionPane.showMessageDialog(null,"O campo data deve estar no formato dd/mm/aaaa");
    }
    

}
