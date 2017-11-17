package Visões;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class ViewPrincipal extends JFrame implements ActionListener{ 
   
    ViewEmprestimo vEm = new ViewEmprestimo();
    ViewExemplar vEx = new ViewExemplar();
    ViewAssociado vAs = new ViewAssociado();
    ViewPublicacao vPu = new ViewPublicacao();
    
    JMenuBar mb = new JMenuBar();
    JMenu sm = new JMenu("Paginas",true);
    JMenuItem mAs = new JMenuItem("Associados");
    JMenu mPu = new JMenu("Publicação");
    JMenuItem mPu1 = new JMenuItem("Cadastrar");
    JMenuItem mPu2 = new JMenuItem("Consultar");
    JMenuItem mEx = new JMenuItem("Exemplar");
    JMenu mEm = new JMenu("Emprestimo");
    JMenuItem mEm1 = new JMenuItem("Emprestar");
    JMenuItem mEm2 = new JMenuItem("Devolução");
    JSeparator sep = new JSeparator();
    JMenuItem mAt = new JMenuItem("Mostrar Atrasos");
    JPanel main = new JPanel(new CardLayout());
    
    public ViewPrincipal()
    {
        super("Biblioteca SC");
        add(main);
        
        main.add(vAs.ViewCadastraAssociado());
        main.add(vPu.ViewCadastraPublicacao());
        main.add(vPu.ViewConsultarPublicacao());
        main.add(vEx.ViewCadastraExemplar());
        main.add(vEm.ViewEmprestimo());
        main.add(vEm.ViewDevolucao());
        
        mb.add(sm);
            sm.add(mAs);
            sm.add(mPu);
                mPu.add(mPu1);
                mPu.add(mPu2);
            sm.add(mEx);
            sm.add(mEm);
                mEm.add(mEm1);
                mEm.add(mEm2);
            sm.add(sep);
            sm.add(mAt);
        
        
        setJMenuBar(mb);
        setVisible(true);
        setSize(500,500);
        setDefaultCloseOperation(3);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        CardLayout layout = (CardLayout) main.getLayout();
        
        
        if(e.getSource() == mAs) {
            layout.next(main);
        }
        else if(e.getSource() == mPu1) {
            layout.next(main);
            layout.next(main);
            
        } 
        else if(e.getSource() == mPu2) {
            layout.next(main);
            layout.next(main);
            layout.next(main);
        
        }
        else if(e.getSource() == mEx) {
            layout.next(main);
            layout.next(main);
            layout.next(main);
            layout.next(main);
            
        }
        else if(e.getSource() == mEm1) {
            layout.last(main);
            layout.previous(main);
            layout.previous(main);
        }
        else if(e.getSource() == mEm2) {
            layout.last(main);
            layout.previous(main);
            
        }
        else if(e.getSource() == mAt) {
            layout.last(main);
            
        }
                            
    }
    
}
