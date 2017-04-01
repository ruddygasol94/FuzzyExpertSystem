/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaExpertoDifuso;

import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author ruddygasol94
 */
public class Principal extends JFrame implements ActionListener{
    ArrayList<JTextField> campos;
    ArrayList <Variables> a_variables;
    ArrayList <Variables> a_salidas;
    private String[] a_reglas;
    
    Operaciones a_op;

    public Principal(Operaciones op, String[] reglas, ArrayList<Variables> p_salidas){
        super("Principal - SED");
        a_op = op;
        a_reglas = reglas;
        a_salidas = p_salidas;
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().add(new JLabel("Sistema Experto Difuso - Entrada de Variables"), BorderLayout.NORTH);
    }
    
    public void m_setArraCampos(ArrayList<JTextField> p_campos){
        campos = p_campos;
    }
    
    public void m_setArraVariables(ArrayList<Variables> p_variables){
        a_variables = p_variables;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        
       if(campos != null){
           for(int i = 0; i < a_variables.size(); i++){
               JTextField txt = campos.get(i);
               double temp=Double.parseDouble(txt.getText());
               if(temp>=a_variables.get(i).getLi()&&temp<=a_variables.get(i).getLs())
               {
                   a_op.darGrados(Double.parseDouble(txt.getText()), a_variables.get(i));
               }else
                    System.out.println("La entrada : "+temp + " esta fuera de los limites de la variable: "+ 
                           a_variables.get(i).getNombre());
              
           }
       }
       for(int j = 0; j < a_variables.size(); j++){
           Variables v = a_variables.get(j);
           System.out.println("Variable " + v.getNombre());
           for(int k = 0; k < v.darEtiquetas().size(); k++){
               Etiquetas eti = v.darEtiquetas().get(k);
               System.out.println("\t" + eti.darNombre() + ": " + eti.getSalida_difusa());
           }
       }
       new ReglasInterfaz(a_reglas, a_variables.size(), a_variables, a_salidas);
    }
    
}
