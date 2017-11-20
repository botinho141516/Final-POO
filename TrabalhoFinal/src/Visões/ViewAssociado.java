package Visões;

import Controles.ControleAssociado;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ViewAssociado implements ActionListener {
    
    ControleAssociado cA;
    
    String[] optStatus = {
        
        "Aluno Graduação",
        "Aluno Pos-Graduação",
        "Professor"
    };
    JPanel main = new JPanel(new FlowLayout(1,5000,8));
    
    JTextField tId = new JTextField(20);
    JTextField tName = new JTextField(20);
    JTextField tEnd = new JTextField(20);
    JTextField tEmail = new JTextField(20);
    JComboBox tStatus = new JComboBox(optStatus);
    JButton okBtn = new JButton("Cadastrar");
    
    public ViewAssociado()
    {
        cA = new ControleAssociado(this);
        
    }
    
    public JPanel ViewCadastraAssociado()
    {
        
        JLabel id = new JLabel("Código");
        JLabel name = new JLabel("Nome");
        JLabel end = new JLabel("Endereço");
        JLabel email = new JLabel("Email");
        JLabel status = new JLabel("Status");
        JLabel blank = new JLabel(" ");
        
        main.add(id);
        main.add(tId);
        main.add(name);
        main.add(tName);
        main.add(end);
        main.add(tEnd);
        main.add(email);
        main.add(tEmail);
        main.add(status);
        main.add(tStatus);
        main.add(blank);
        main.add(okBtn);
        
        tStatus.setEditable(true);
        tStatus.setSelectedItem("Status");
        tStatus.setEditable(false);
        
        tStatus.addActionListener(this);
        okBtn.addActionListener(this);
        
        
        
        
        return main;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == okBtn)
        {
            
            if("".equals(tId.getText()) || "".equals(tName.getText()) || "".equals(tEnd.getText()) || "".equals(tEmail.getText()) || "Status".equals(tStatus.getSelectedItem().toString()))
                JOptionPane.showMessageDialog(null,"Todos os campos devem ser preenchidos");
            else
            {
                try {
                    int id = Integer.parseInt(tId.getText());
                    try{
                        cA.checkIdAssociado(id);
                        cA.cadastraAsssociado(id,tName.getText(),tEnd.getText(),tEmail.getText(),tStatus.getSelectedItem().toString());
                        JOptionPane.showMessageDialog(null,"Associado cadastrado com sucesso");
                        
                    }catch(Exception ex)
                    {
                        JOptionPane.showMessageDialog(null,"Id já cadastrado");
                    }
                }catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(main,"O campo ID deve ser um numero inteiro");
                }
            }
        }
    }

    public void showMessageError(int i) {
        switch(i)
        {
            case 0:
                JOptionPane.showMessageDialog(null, "Erro ao serializar");
                break;
                
            case 1:
                JOptionPane.showMessageDialog(null,"Erro ao deserializar");
                break;
                
        }
    }
}
