/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaExpertoDifuso;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author ruddygasol94
 */
public class Difuso1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Registro reg = new Registro();
        File variables = new File("var.dat");
        File salidas = new File("salidas.dat");
        
        System.out.println("Ingrese las variables de entrada.");
        //reg.m_escrArchMaestro(variables);
        System.out.println("Ingrese las variables de salida.");
        //reg.m_escrArchMaestro(salidas);
        
        /*Operaciones op = new Operaciones();
        op.recorre(variables);
        op.muestFunciones();
        
        System.out.println("");
        op.entradas();
        //op.ordenar();*/
        ArrayList<Variables> var = reg.leerVariables(variables);
        
        ArrayList<Variables> sal = reg.leerVariables(salidas);
        int v_numeVariables = var.size();
        System.out.println("variables: " + v_numeVariables);
        int total_combinaciones = 1;
        for(int i = 0; i < var.size(); i++){
            Variables temp = var.get(i);
            total_combinaciones *= temp.darEtiquetas().size();
        }
        
        System.out.println("combinacion = " + total_combinaciones);
        
        String reglas[] = new String[total_combinaciones];
        
        //crear reglas vacias
        for(int v_reg = 0; v_reg < total_combinaciones; v_reg++){
            reglas[v_reg] = "";
        }
        
        
        int acarreo = 1;
        //ciclo para recorrer variables
        for(int v_contVariables = var.size()-1; v_contVariables >= 0; v_contVariables--){
            Variables v_variTemporal = var.get(v_contVariables);
            int v_veceImprEtiqueta = total_combinaciones/v_variTemporal.darEtiquetas().size();
            int v_indiReglas = 0;
            //prueba acarreo
            for(int v_contAcarreo = 0; v_contAcarreo < acarreo; v_contAcarreo++){
                //ciclo para etiquetas
                for(int v_contEtiquetas = 0; v_contEtiquetas < v_variTemporal.darEtiquetas().size(); v_contEtiquetas++){                
                    for(int v_contVeces = 0; v_contVeces < v_veceImprEtiqueta/acarreo; v_contVeces++){
                        reglas[v_indiReglas] = reglas[v_indiReglas] + v_variTemporal.darEtiquetas().get(v_contEtiquetas).darNombre().replace(" ", "") + "/";
                        v_indiReglas++;
                    }                
                }
            }
            acarreo *= v_variTemporal.darEtiquetas().size();
        }               
        
        for(int cArre = 0; cArre < total_combinaciones; cArre++){
            System.out.println((cArre+1) + ": " +reglas[cArre]);
        }
        new ReglasInterfaz(reglas, var.size());
    }    
}
