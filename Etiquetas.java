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
public class Etiquetas {
    private String nombre;
    private ArrayList<Integer> puntos_criticos;
    private double salida_difusa;
    
    public Etiquetas(String p_nombre, ArrayList<Integer> p_ptosCriticos){
        nombre = p_nombre;
        puntos_criticos = p_ptosCriticos;
    }
    
    public double getSalida_difusa() {
        return salida_difusa;
    }

    public void setSalida_difusa(double salida_difusa) {
        this.salida_difusa = salida_difusa;
    }
    
    public String darNombre(){
        return nombre;
    }
    
    public ArrayList<Integer> darPuntos(){
        return puntos_criticos;
    }
    
}
