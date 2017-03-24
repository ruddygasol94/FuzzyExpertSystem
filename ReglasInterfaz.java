/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaExpertoDifuso;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ruddygasol94
 */
public class ReglasInterfaz extends JFrame{
    private String[] a_reglas;
    private ArrayList<String> a_reglSeleccionadas;    
    private int a_numeVariables;
    
    public ReglasInterfaz(String[] reglas, int p_numeVariables){
        super("ventanita");
        a_reglas = reglas;
        a_numeVariables = p_numeVariables;
        a_reglSeleccionadas = new ArrayList<>();
        this.setSize(600, 600);
        this.setLocation(0, 0);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panel(), BorderLayout.CENTER);
        JButton btnF = new JButton("Seleccionar");
        btnF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < a_reglSeleccionadas.size(); i++){
                    System.out.println(a_reglSeleccionadas.get(i));
                }
            }
        });
        this.getContentPane().add(btnF, BorderLayout.SOUTH);
        this.invalidate();
        this.repaint();
        this.setVisible(true);
    }
    
    public JPanel panel(){
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout((a_reglas.length/a_numeVariables), a_numeVariables));
        for(int v_contReglas = 0; v_contReglas < a_reglas.length; v_contReglas++){
            JButton btn = new JButton(a_reglas[v_contReglas]);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btn.setEnabled(false);
                    a_reglSeleccionadas.add(btn.getText());
                }
            });
            jp.add(btn);
        }
        jp.repaint();
        return jp;
    }
}
