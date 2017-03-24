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
    
    public Variables(String p_nombre){
        nombre = p_nombre;
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
