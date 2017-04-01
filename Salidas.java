/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaExpertoDifuso;

import java.util.ArrayList;

/**
 *
 * @author ruddygasol94
 */
public class Salidas {
    private ArrayList<Variables> a_variables, a_salidas;
    private ArrayList<Equivalencias> a_equivalencias;
    private ArrayList<ArraySalidas> salidas;
    
    
    
    public Salidas(ArrayList<Variables> p_variables, ArrayList<Variables> p_salidas, ArrayList<Equivalencias> p_equi){
        a_variables = p_variables;
        a_salidas = p_salidas;
        a_equivalencias = p_equi;
        salidas = new ArrayList<>();
        
                
        m_agreSalidas();
        m_max();
    }
    
    public void m_agreSalidas(){
        //recorre salidas
        for(int i = 0; i < a_salidas.size(); i++){
            Variables temp = a_salidas.get(i);
            for(int j = 0; j < temp.darEtiquetas().size(); j++){
                for(int k = 0; k < a_equivalencias.size(); k++){
                    Equivalencias equi = a_equivalencias.get(k);
                    Etiquetas eti = temp.darEtiquetas().get(j);
                    salidas.add(new ArraySalidas(equi.getEl_minimo(), eti.darNombre()));
                    //System.out.println(equi.getEl_minimo() + "-" + eti.darNombre());
                    
                }
            }
        }
        //System.out.println("aqui estoy");
        
    }
    
    public void m_max(){
        for(int i = 0; i < a_salidas.size(); i++){
            Variables vtem = a_salidas.get(i);
            for(int k = 0; k < vtem.darEtiquetas().size(); k++){
                Etiquetas e = vtem.darEtiquetas().get(k);
               ArrayList<Double> max = new ArrayList<>();
            
                for(int j = 0; j < salidas.size(); j++){
                    ArraySalidas a = salidas.get(j);
                        if (a.getA_resultados().equals(e.darNombre())){
                            max.add(a.getA_resultados1());
                            
                        }
                    
                }
            double tem = max.get(0);
                for(int v = 1; v < max.size(); v++){
                    if(tem < max.get(v)){
                        tem = max.get(v);
                    }
                    
                }
                System.out.println("salida " + vtem.getNombre() + ": " + tem);
                
                
        }
        
        }
    }   
}