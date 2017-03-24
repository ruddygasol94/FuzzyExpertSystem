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
public class Funcion {
    private ArrayList<Integer> a_puntCriticos;
    private double a_puntInicio;
    private double a_puntFinal;
    private String tipo;
    
    public Funcion(double inicio, double fin, ArrayList<Integer> puntos){
        a_puntInicio = inicio;
        a_puntFinal = fin;
        a_puntCriticos = puntos;
    }

    public double getInicio(){
        return a_puntInicio;
    }
    
    public double getFinal(){
        return a_puntFinal;
    }
    
    public ArrayList<Integer> getPuntos(){
        return a_puntCriticos;
    }
}
