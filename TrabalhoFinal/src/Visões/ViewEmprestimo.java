package Visões;

import Controles.ControleEmprestimo;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.*;

public class ViewEmprestimo implements ActionListener {

    ControleEmprestimo cE;

    JTextField tNumeroEx = new JTextField(16);
    JTextField tIsbn = new JTextField(16);
    JTextField tIdAssociado = new JTextField(16);
    JTextField tData = new JTextField(16);
    JButton empBtn = new JButton("Emprestar");
    JButton devBtn = new JButton("Devolver");

    public ViewEmprestimo() {
        cE = new ControleEmprestimo(this);

    }

    public JPanel ViewEmprestimo() {

        JPanel main = new JPanel(new FlowLayout(1, 5000, 8));

        JLabel numero = new JLabel("Número do Exemplar");
        JLabel isbn = new JLabel("ISBN");
        JLabel id = new JLabel("ID do cliente ");
        JLabel data = new JLabel("Data do Emprestimo");
        JLabel blank = new JLabel(" ");

        
        main.add(isbn);
        main.add(tIsbn);
        main.add(id);
        main.add(tIdAssociado);
        main.add(numero);
        main.add(tNumeroEx);
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
        try
        {
            return new JLabel(cE.showAtrasados());

        } catch (Exception ex)
        {
            return new JLabel("Erro ao checar atrasados");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == empBtn)
        {
            if ("".equals(tIsbn.getText()) || "".equals(tIdAssociado.getText()) || "".equals(tData.getText()))
            {
                JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");
            } else
            {
                try
                {
                    int idAssociado = Integer.parseInt(tIsbn.getText());
                    int isbn = Integer.parseInt(tIsbn.getText());

                    try
                    {
                        Date data = cE.checkDate(tData.getText());

                        try
                        {
                            cE.checkIDassociado(idAssociado);
                            try
                            {
                                cE.checkIsbn(isbn,idAssociado);
                                try
                                {
                                    cE.cadastraEmprestimo(idAssociado, isbn, data);
                                } catch (Exception ex)
                                {
                                    JOptionPane.showMessageDialog(null,"Erro ao cadastrar");
                                }
                            }catch(Exception ex)
                            {
                                JOptionPane.showMessageDialog(null,"ISBN não encontrado");
                            }
                            try
                            {
                                cE.checkIsbn(isbn, idAssociado);
                            }
                            catch (Exception ex)
                            {
                                JOptionPane.showMessageDialog(null, "ISBN não cadastrado");
                            }

                        } catch (Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, "ID não cadastrado");
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
                    int idassociado = Integer.parseInt(tIdAssociado.getText());//aqui caralho
                    int isbn = Integer.parseInt(tIsbnD.getText());
                    int id = Integer.parseInt(tIdD.getText());
                    
                    try
                    {
                        cE.checkIDassociado(idassociado);
                        try
                        {
                            cE.checkIsbn(isbn, id);
                            
                            try
                            {
                                
                                cE.devolveExemplar(isbn, id);
                                JOptionPane.showMessageDialog(null, "Devolução Registrada");
        
                            } catch (Exception ex)
                            {
                                JOptionPane.showMessageDialog(null, "problema no back trocar para (Devolução falhou) linha 157");
                            }
                        } catch(Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, "ID do exemplar não cadastrado");
                        }
                    } catch (Exception x)
                    {
                        JOptionPane.showMessageDialog(null, "Cliente não cadastrado");
                    }
                } catch (Exception ex)
                {
                            JOptionPane.showMessageDialog(null, "Os campos devem ser numeros inteiros");

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
                JOptionPane.showMessageDialog(null, "Erro ao deserializar");
                break;
        }
    }

}
