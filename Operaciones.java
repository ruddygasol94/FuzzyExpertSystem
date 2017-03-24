/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package difuso1;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ruddygasol94
 */
public class Operaciones {
    Registro reg=new Registro();
    ArrayList<Funcion> a_funciones;
    double si,ent;
    Scanner in = new Scanner(System.in);
    
    public double darY(double x, double x1, int y1, double x2, int y2){
        return (((double)(x-x1)/(double)(x2-x1))*(y2-y1) + y1);
    }
    
    public void entradas()
    {
        ArrayList <Variables> temp=reg.leerVariables(new File("var.dat"));
 
        for (int i = 0; i < temp.size(); i++) {
            Variables nom_var=temp.get(i);//Para traer todo el paquete de una variable
            //Y preguntar entradas por variable
           
        System.out.println("Captura entrada para variable "+nom_var.getNombre());
        ent=in.nextDouble();
             
            darGrados(ent,temp.get(i));//Envio como parametro el bonche de 
            //dtos de datos de cada variable, su nombre, sus puntos criticos, y la salida_difusa
            //se incluye en temp.
            
        }   
        
        
        }

    public void darGrados(double ent,Variables paaquete){
        double resultado = -1;
       
        for(int i = 0; i < paaquete.etiquetas.size(); i++){
            Funcion f = a_funciones.get(i);
          
                
            
            if (f.getPuntos().size() == 1){
               
             
                if(ent>=(int)f.getInicio() && (int)ent<=f.getFinal()){
                     System.out.println("Tri");
                    
                if (ent < f.getPuntos().get(0))
                    resultado = darY(ent, (int)f.getInicio(), 0, f.getPuntos().get(0), 1);
                else {
                    resultado = darY(ent, f.getPuntos().get(0), 1, (int)f.getFinal(), 0);
                    }
                paaquete.etiquetas.get(i).setSalida_difusa(resultado);
                //Envio la salida difusa calculada a cada una de las etiquetas
                //en su campo salida_difusa.
                
                 System.out.println("Grado de membresía: " + resultado);
                 
                 
                 
            }
                
                
            } else {
              
                 if(ent>=f.getPuntos().get(0) && ent<=f.getPuntos().get(1)){
                     System.out.println("Tra");
                    paaquete.etiquetas.get(i).setSalida_difusa(resultado);
                     
                     System.out.println("Grado de membresía: " + resultado);
                     
                      
                }else
                {
               
                 if(ent>=(int)f.getInicio() && (int)ent<=f.getFinal()){
                       System.out.println("Tra");
                      
                       
                 
                if(ent < f.getPuntos().get(0)){
                    resultado = darY(ent, (double)f.getInicio(), 0, f.getPuntos().get(0), 1);
                } else {
                    resultado = darY(ent, f.getPuntos().get(1), 1, (double)f.getFinal(), 0);
                    
                }
                paaquete.etiquetas.get(i).setSalida_difusa(resultado);
                 System.out.println("Grado de membresía: " + resultado);
                   
                  }
            }
            
            }
           
        } 
          
        
        
    
    }
    
    public void recorre(File p_archivo){
        a_funciones = new ArrayList<>();
        Registro reg = new Registro();
        ArrayList<Etiquetas> etiq = reg.leerSecuencial(p_archivo);//Obtiene todas las etiquetas y sus punto critico de 
        
        //para primer función
        Etiquetas e1 = etiq.get(0);//La primera etiqueta se procesa
        int origen = 0;
        double distancia = 0;
        if (e1.darPuntos().size() == 1)//Determina cuantos puntos criticos se capturaron
            distancia = e1.darPuntos().get(0) - origen;// Calculo de la 
        else if (e1.darPuntos().size() == 2){
            double mitad = (double)((e1.darPuntos().get(1) - e1.darPuntos().get(0)) / 2);
            distancia = mitad + origen;
        }
        double origen_=0.0;
        origen_=e1.darPuntos().get(0);
        
          
        int ultiPunto = e1.darPuntos().get(e1.darPuntos().size() - 1);
        a_funciones.add(new Funcion(origen, ultiPunto + distancia, e1.darPuntos()));
        for (int v_contador = 1; v_contador < etiq.size(); v_contador++){
            //Obtener origen con base en el 40% del punto final de la función anterior.
            double segmento = a_funciones.get(0).getFinal() - a_funciones.get(0).getPuntos().get(a_funciones.get(0).getPuntos().size() - 1);
            double dis = segmento * 0.4;
            e1 = etiq.get(v_contador);
          //  double origen_ = etiq.get(v_contador).darPuntos().get(0) - dis;
          origen_=origen_+dis;
          double distance = 0;
            if (e1.darPuntos().size() == 1)
                distancia =(double) (e1.darPuntos().get(0)) - origen_;
            else if (e1.darPuntos().size() == 2){
                double mitad = (double)((e1.darPuntos().get(1) - e1.darPuntos().get(0)) / 2);
                //distancia = mitad + origen_;
                distancia=e1.darPuntos().get(0)-origen_;
            }

            ultiPunto = e1.darPuntos().get(e1.darPuntos().size() - 1);
            a_funciones.add(new Funcion(origen_, ultiPunto + distancia, e1.darPuntos()));
            if(e1.darPuntos().size()==1){
           origen_ = etiq.get(v_contador).darPuntos().get(0);
            }else
            {
                 origen_ = etiq.get(v_contador).darPuntos().get(1);
            }
        }
    }
    
    public void muestFunciones(){
        for (int i = 0; i < a_funciones.size(); i++){
            Funcion temp = a_funciones.get(i);
            System.out.println("Inicia " + temp.getInicio());
            System.out.print("pasa por : ");
            for (int j = 0; j < temp.getPuntos().size(); j++){
                System.out.print(temp.getPuntos().get(j)+" ");
            }
            System.out.println("");
            System.out.println("Final: " + temp.getFinal());
        }
    }
}
