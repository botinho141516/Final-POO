package Controles;

import Visões.ViewEmprestimo;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class ControleEmprestimo {
    
    ViewEmprestimo vE;
    
    public ControleEmprestimo()
    {
        //vE = new ViewEmprestimo(this);
    }
    
    public Date checkDate(String pData)
    {
        
        String test = pData;
        String format = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        
        try
        {
            Date date = sdf.parse(test);
            if (!sdf.format(date).equals(test))
            {
                throw new Exception();
            }
            else
            {
                return date;
            }
        } catch (Exception ex)
        {
            
            return null;
        }
    }

    public void cadastraExemplar(int id, int isbn, Date data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
