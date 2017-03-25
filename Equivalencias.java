/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaExpertoDifuso;

import java.util.ArrayList;

/**
 *
 * @author alex
 */
public class Equivalencias {
      
    private String a_reglEquivalentes;
    private double el_minimo;

    public Equivalencias(String a_reglEquivalentes_) {
        a_reglEquivalentes=a_reglEquivalentes_;
       
        
    }

    public String getA_reglEquivalentes() {
        return a_reglEquivalentes;
    }

    public void setA_reglEquivalentes(String a_reglEquivalentes) {
        this.a_reglEquivalentes = a_reglEquivalentes;
    }

    
 

    public double getEl_minimo() {
        return el_minimo;
    }

    public void setEl_minimo(double el_minimo) {
        this.el_minimo = el_minimo;
    }
    
    
    
}
