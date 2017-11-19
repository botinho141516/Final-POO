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
    JMenuItem mAs = new JMenuItem("Cadastrar Associado");
    JMenu mPu = new JMenu("Publicação");
    JMenuItem mPu1 = new JMenuItem("Cadastrar");
    JMenuItem mPu2 = new JMenuItem("Consultar");
    JMenuItem mEx = new JMenuItem("Cadastrar Exemplar");
    JMenu mEm = new JMenu("Emprestimo");
    JMenuItem mEm1 = new JMenuItem("Emprestar");
    JMenuItem mEm2 = new JMenuItem("Devolução");
    JSeparator sep = new JSeparator();
    JMenuItem mAt = new JMenuItem("Mostrar Atrasos");
    JPanel main = new JPanel(new CardLayout());
    
    JPanel atrasos = new JPanel();
    
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
        main.add(atrasos);
        
        atrasos.add(vEm.ViewAtrasos());
        
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
        
        mAs.addActionListener(this);
        mPu1.addActionListener(this);
        mPu2.addActionListener(this);
        mEx.addActionListener(this);
        mEm1.addActionListener(this);
        mEm2.addActionListener(this);
        mAt.addActionListener(this);
        
        setJMenuBar(mb);
        setVisible(true);
        setSize(500,500);
        setDefaultCloseOperation(3);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        CardLayout layout = (CardLayout) main.getLayout();
        
        if(e.getSource() == mAs) {
            layout.first(main); 
        }
        else if(e.getSource() == mPu1) {
            layout.first(main);
            layout.next(main);
            
        } 
        else if(e.getSource() == mPu2) {
            layout.first(main);
            layout.next(main);
            layout.next(main);
        
        }
        else if(e.getSource() == mEx) {
            layout.last(main);
            layout.previous(main);
            layout.previous(main);
            layout.previous(main);
            
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
