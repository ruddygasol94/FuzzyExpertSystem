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
import java.io.File;
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
    private ArrayList<Equivalencias> a_reglEquivalentes=new ArrayList<>(); 
    private int a_numeVariables;
    
    public ReglasInterfaz(String[] reglas, int p_numeVariables,ArrayList<Variables> etiquetas_, ArrayList<Variables> p_salidas){
        super("ventanita");
        a_reglas = reglas;
        ArrayList<Variables> etiquetas=etiquetas_;
        ArrayList<Variables> salidas = p_salidas;
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
                String acumulado = "";/*Acumula una cadena con las equivalencias de cada etiqueta*/
                double minimo = 0;
                 for(int i = 0; i < a_reglSeleccionadas.size(); i++){/*Recorre el
                     arreglo de reglas seleccionadas*/
                         
                         String cadena_regla_seleccionadas[];
                         cadena_regla_seleccionadas=a_reglSeleccionadas.get(i).split("/");/*Separa en un arreglo  
                         cada regla selccionada que estaba dividida por un -> / <- , para comparar por separado*/
                 for (int l = 0; l < cadena_regla_seleccionadas.length; l++) {/*recorre el arreglo de las reglas
                     que se separaron para comparar las etiquetas que conforman cada regla*/
                   for (int j = 0; j < etiquetas.size(); j++) {/*Recorre las vaariables*/
                       for (int k = 0; k < etiquetas.get(j).etiquetas.size(); k++) {/*recorre las etiquetas que estan
                           dentro de cada variable*/
                          // System.out.println("Etiqueta: "+etiquetas.get(j).etiquetas.get(k).darNombre()+" Salida difusa: "+etiquetas.get(j).etiquetas.get(k).getSalida_difusa());
                    
                             String nombre=etiquetas.get(j).etiquetas.get(k).darNombre().replace(" ","");/*Por alguna razon
                             al separar coloca espacios, se los quite*/
                              if(nombre.equalsIgnoreCase(cadena_regla_seleccionadas[l])){/*Compara las etiquetas que conforman
                                  a cada regla con las etiquetas almacenadas, para sustituir el nombre por su salida difusa*/
                                  
                               
                                 acumulado=acumulado+etiquetas.get(j).etiquetas.get(k).getSalida_difusa()+"/";/*Acumula las 
                                 salidas difusas para remplazar a los nombres de cada etiqueta*/
                           }
                              
                         }                        
                       }     
                    }
                         System.out.println(acumulado);
                         a_reglEquivalentes.add(new Equivalencias(acumulado));/*Las almaceno cada una de las reglas
                         pero sin nombre, mas bien con el valor de salida difusa que le corresponde*/
                         acumulado="";
                   // System.out.println(a_reglSeleccionadas.get(i));
                }
                  // System.out.println("cisco");
                for (int i = 0; i < a_reglEquivalentes.size(); i++) {/*Recorre la lista con las reglas
                    intercambiadas por su valor equivalente*/
                     String cadena_valores[];
                         cadena_valores=a_reglEquivalentes.get(i).getA_reglEquivalentes().split("/");/*Como se almacenaron
                         con el mismo formato las vuelvo a separar para compararlas*/
                         minimo=Double.parseDouble(cadena_valores[0]);/*Establezco un minimo para compararlo*/
                         for (int j = 1; j < cadena_valores.length; j++) {/*Recorre el arreglo de reglas con 
                             sus valores equivalentes*/
                             if(Double.parseDouble(cadena_valores[j])<=minimo)/*Comparacion para determinar cual es
                                 el minimo valor*/
                             {
                                 minimo=Double.parseDouble(cadena_valores[j]);
                             }
                            a_reglEquivalentes.get(i).setEl_minimo(minimo);/*Cuado determina el minimo, se lo agrega 
                            al list, para asociar un minimo a cada regla almacenada*/
                        
                    }
                             
                  
                    
                }
                File equivalentes = new File("equivalentes.dat");
                Registro obj=new Registro();
                obj.escrReglasEquivalentes(equivalentes,a_reglEquivalentes);
                
                new Salidas(etiquetas, p_salidas, a_reglEquivalentes);
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

