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
public class Variables {
    private String nombre;
    ArrayList<Etiquetas> etiquetas;
    private double li;
    private double ls;
   
    
    
    
    public Variables(String p_nombre,double p_li,double p_ls){
        nombre = p_nombre;
        li=p_li;
        ls=p_ls;
        
    }

    public double getLi() {
        return li;
    }

    public void setLi(double li) {
        this.li = li;
    }

    public double getLs() {
        return ls;
    }

    public void setLs(double ls) {
        this.ls = ls;
    }
    
    
    
    public void setEtiquetas(ArrayList<Etiquetas> p_eti){
        etiquetas = p_eti;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public ArrayList<Etiquetas> darEtiquetas(){
        return etiquetas;
    }
}
