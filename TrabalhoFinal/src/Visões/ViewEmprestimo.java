package Visões;

import Controles.ControleEmprestimo;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ViewEmprestimo implements ActionListener {

    ControleEmprestimo cE;

    JTextField tIsbn = new JTextField(16);
    JTextField tId = new JTextField(16);
    JTextField tData = new JTextField(16);
    JButton empBtn = new JButton("Emprestar");
    JButton devBtn = new JButton("Devolver");

    public ViewEmprestimo() {
        cE = new ControleEmprestimo(this);

    }

    public JPanel ViewEmprestimo() {

        JPanel main = new JPanel(new FlowLayout(1, 5000, 8));

        JLabel isbn = new JLabel("ISBN");
        JLabel id = new JLabel("ID do cliente ");
        JLabel data = new JLabel("Data do Emprestimo");
        JLabel blank = new JLabel(" ");

        main.add(isbn);
        main.add(tIsbn);
        main.add(id);
        main.add(tId);
        main.add(data);
        main.add(tData);
        main.add(blank);
        main.add(empBtn);

        empBtn.addActionListener(this);
        main.setSize(400, 400);

        return main;

    }

    JTextField tIdD = new JTextField(16);
    JTextField tIsbnD = new JTextField(16);

    public JPanel ViewDevolucao() {

        JPanel main = new JPanel(new FlowLayout(1, 5000, 8));

        JLabel isbn = new JLabel("ISBN do exemplar emprestado");
        JLabel id = new JLabel("ID do cliente ");
        JLabel blank = new JLabel(" ");

        main.add(id);
        main.add(tIdD);
        main.add(isbn);
        main.add(tIsbnD);
        main.add(blank);
        main.add(devBtn);

        devBtn.addActionListener(this);

        main.setSize(400, 400);

        return main;

    }

    public JLabel ViewAtrasos() {
        try {
            return new JLabel(cE.checkAtrasos());
            
        } catch(Exception ex)
        {
        }
        return null;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == empBtn)
        {
            if ("".equals(tIsbn.getText()) || "".equals(tId.getText()) || "".equals(tData.getText()))
            {
                JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");
            } else
            {
                try
                {
                    int id = Integer.parseInt(tIsbn.getText());
                    int isbn = Integer.parseInt(tIsbn.getText());

                    try
                    {
                        Date data = cE.checkDate(tData.getText());

                        try
                        {
                            cE.checkID(tId.getText());
                            try
                            {
                                cE.checkIsbn(tIsbn.getText());
                                try
                                {
                                    cE.cadastraExemplar(id, isbn, data);
                                } catch (Exception ex)
                                {
                                    JOptionPane.showMessageDialog(null, "back ta errado(deleta isso depois de dar tudo certo, linha 112)");
                                }

                            } catch (Exception ex)
                            {
                                JOptionPane.showMessageDialog(null, "ID não cadastrado");
                            }

                        } catch (Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, "ISBN não cadastrado");
                        }

                    } catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog(null, "Data deve estar no formato dd/mm/aaaa");
                    }

                } catch (Exception x)
                {
                    JOptionPane.showMessageDialog(null, "ID e ISBN devem ser numeros inteiros");
                }
            }

        } else if (e.getSource() == devBtn)
        {
            if ("".equals(tIsbnD.getText()) || "".equals(tIdD.getText()))
            {
                JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");
            } else
            {
                try
                {
                    cE.checkIsbn(tIsbnD.getText());

                    try
                    {
                        cE.checkID(tIdD.getText());

                        try
                        {
                            cE.devolveExemplar(tIsbnD.getText(), tIdD.getText());
                            JOptionPane.showMessageDialog(null, "Devolução Registrada");
                        } catch (Exception x)
                        {
                            JOptionPane.showMessageDialog(null, "problema no back trocar para (Devolução falhou) linha 157");
                        }


                    } catch (Exception x)
                    {
                        JOptionPane.showMessageDialog(null, "Cliente não cadastrado");
                    }

                } catch (Exception x)
                {
                    JOptionPane.showMessageDialog(null, "ID do exemplar não cadastrado");
                }

            }
        }

    }

    public void showMessageError() {

        JOptionPane.showMessageDialog(null, "O campo data deve estar no formato dd/mm/aaaa");
    }

}
