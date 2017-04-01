/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaExpertoDifuso;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ruddygasol94
 */
public class Operaciones{
    Registro reg=new Registro();
    ArrayList<Funcion> a_funciones;
    ArrayList <Variables> las_variables;
    double si,ent;
    Scanner in = new Scanner(System.in);
    int i = 0;
    
    public double darY(double x, double x1, int y1, double x2, int y2){
        return (((double)(x-x1)/(double)(x2-x1))*(y2-y1) + y1);
    }
    
    public ArrayList<Variables> entradas(String[] p_reglas, ArrayList<Variables> p_salidas)
    {
        ArrayList <Variables> temp=reg.leerVariables(new File("var.dat"));
        Principal pr = new Principal(this, p_reglas, p_salidas);
        pr.m_setArraVariables(temp);
        ArrayList<JTextField> v_campos = new ArrayList<JTextField>();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(temp.size(), 2));
        for (int i = 0; i < temp.size(); i++) {
            Variables nom_var=temp.get(i);//Para traer todo el paquete de una variable
            //Y preguntar entradas por variable
            panel.add(new JLabel(nom_var.getNombre()));
            JTextField txt = new JTextField();
            v_campos.add(txt);
            panel.add(txt);
           
        //System.out.println("Captura entrada para variable "+nom_var.getNombre());
        //ent=in.nextDouble();
             
          //  darGrados(ent,temp.get(i));//Envio como parametro el bonche de 
            //dtos de datos de cada variable, su nombre, sus puntos criticos, y la salida_difusa
            //se incluye en temp.
            
        }   
        pr.m_setArraCampos(v_campos);
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(pr);
        pr.getContentPane().add(panel, BorderLayout.CENTER);
        pr.getContentPane().add(btnAceptar, BorderLayout.SOUTH);
        pr.invalidate();
        pr.setVisible(true);
        return temp;        
    }

   public void darGrados(double ent,Variables paaquete){
        double resultado = -1;
       
        for(int p=0; p < paaquete.etiquetas.size(); p++){//Tomo la variable enviada como parametro, para
            //recorrer el numero de etiquetas que esten dentro de la variable
           
            Funcion f = a_funciones.get(i);//a_funciones, es el paquete con todos los datos sin distincion de 
            //etiqueta o variable, con la variable global i, se va a hacer el recorrido de este array,
            //de manera que cuando se acaben las etiquetas, saber en cual a_funcion se quedo, y cuando se
            //hagan los siguientes envios de variables, continua la toma de valores apartir de esta variable.
            i++;
          /*El resto de los calculos quedo igual, se calcula y se envia al campo
            salida difusa*/
                
            
            if (f.getPuntos().size() == 1){
               
             
                if(ent>=(int)f.getInicio() && (int)ent<=f.getFinal()){
                   //  System.out.println("Tri");
                    
                if (ent < f.getPuntos().get(0))
                    resultado = darY(ent, (double)f.getInicio(), 0, (double)f.getPuntos().get(0), 1);
                else {
                    resultado = darY(ent, (double)f.getPuntos().get(0), 1, (double)f.getFinal(), 0);
                    }
                paaquete.etiquetas.get(p).setSalida_difusa(resultado);
                //Envio la salida difusa calculada a cada una de las etiquetas
                //en su campo salida_difusa.
                
               //  System.out.println("Grado de membresía: " + resultado);
                 
            }
                
                
            } else {
              
                 if(ent>=f.getPuntos().get(0) && ent<=f.getPuntos().get(1)){
                  //   System.out.println("Tra");
                    paaquete.etiquetas.get(p).setSalida_difusa(resultado);
                     
                   //  System.out.println("Grado de membresía: " + resultado);
                     
                      
                }else
                {
               
                 if(ent>=(int)f.getInicio() && (int)ent<=f.getFinal()){
                     //  System.out.println("Tra");
                      
                       
                 
                if(ent < f.getPuntos().get(0)){
                    resultado = darY(ent, (double)f.getInicio(), 0, (double)f.getPuntos().get(0), 1);
                } else {
                    resultado = darY(ent, (double)f.getPuntos().get(1), 1, (double)f.getFinal(), 0);
                    
                }
                paaquete.etiquetas.get(p).setSalida_difusa(resultado);
                // System.out.println("Grado de membresía: " + resultado);
                   
                  }
            }
            
            }
            
           
        } 
          
        
        
    
    }
    public void recorre(File p_archivo){
        a_funciones = new ArrayList<>();
        int lleva_cuenta=0;
        boolean bandera=true,bandera2=true,bandera3=true;
        
        las_variables=reg.leerVariables(new File("var.dat"));
        Registro reg = new Registro();
        ArrayList<Etiquetas> etiq = reg.leerSecuencial(p_archivo);//Obtiene todas las etiquetas y sus punto critico de 
        
        //para primer función
        Etiquetas e1 = etiq.get(0);//La primera etiqueta se procesa
        Etiquetas e1_prue;
        int origen = 0;
        double origen_=0.0;
        double distancia = 0;
         int ultiPunto=0;
      /*  
        if (e1.darPuntos().size() == 1)//Determina cuantos puntos criticos se capturaron
            distancia = e1.darPuntos().get(0) - origen;
        else if (e1.darPuntos().size() == 2){
            double mitad = (double)((double)(e1.darPuntos().get(1) - e1.darPuntos().get(0)) / 2);
            //distancia = mitad + origen;
            distancia = e1.darPuntos().get(0) - origen;
        }
        double origen_=0.0;
        origen_=e1.darPuntos().get(0);
        int cony=1;
          
        int ultiPunto = e1.darPuntos().get(e1.darPuntos().size() - 1);
        a_funciones.add(new Funcion(origen, ultiPunto + distancia, e1.darPuntos()));//Se almacena 
        //la primera funcion
        */
       
        
        for (int v_co = 0; v_co < las_variables.size(); v_co++){
            //variables
            double segmento=0;
           int yum=las_variables.get(v_co).darEtiquetas().size();
          for (int v_c=0; v_c < yum; v_c++){ 
           
           e1 = las_variables.get(v_co).darEtiquetas().get(v_c);
               
               if(segmento!=0){//Si es la etiqueta 0, 
              e1_prue = las_variables.get(v_co).darEtiquetas().get(v_c-1);
               segmento=e1_prue.darPuntos().get(e1_prue.darPuntos().size()-1);
                }else{
                
               e1_prue = las_variables.get(v_co).darEtiquetas().get(v_c);
               }
             
          
          double dis = segmento * 0.4; //Obtener origen con base en el 40% del punto final de la función anterior.
           // e1 = etiq.get(v_contador);
           //double origen_ = etiq.get(v_contador).darPuntos().get(0) - dis;
          //origen_=origen_+dis;
          origen_=segmento+dis;
          double distance = 0;
            if (e1.darPuntos().size() == 1)
                distancia =(double) (e1.darPuntos().get(0)) - origen_;
            else if (e1.darPuntos().size() == 2){
                double mitad = (double)((e1.darPuntos().get(1) - e1.darPuntos().get(0)) / 2);
               // distancia = mitad + origen_;
                distancia=(double)e1.darPuntos().get(0)-origen_;
            }

            ultiPunto = e1.darPuntos().get(e1.darPuntos().size() - 1);
            a_funciones.add(new Funcion(origen_, ultiPunto + distancia, e1.darPuntos()));
            
            if(e1.darPuntos().size()==1){
           origen_ = las_variables.get(v_co).darEtiquetas().get(v_c).darPuntos().get(0);
            }else
            {
                 origen_ = las_variables.get(v_co).darEtiquetas().get(v_c).darPuntos().get(1);
          
            }
            segmento=-1;
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

