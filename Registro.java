/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaExpertoDifuso;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ruddygasol94
 */
public class Registro {
    private String nombre, unidades;
    private int li, ls;
    private String etiqueta;
    private ArrayList<Integer> ptos;
    private ArrayList<Etiquetas> etiq;
    
    public void m_escrArchMaestro(File p_archivo){
        //kj
        try{
            int n;
            StringBuffer buffer = null;
            RandomAccessFile raf = new RandomAccessFile(p_archivo, "rw");
            Scanner in = new Scanner(System.in);
            do{
                if (raf.length() > 0)
                    raf.seek(raf.length());
                System.out.println("Nombre de Variable");
                nombre = in.next();
                buffer = new StringBuffer(nombre);
                buffer.setLength(20);
                raf.writeChars(buffer.toString());
                
                System.out.println("Ingrese las unidades");
                unidades = in.next();
                buffer = new StringBuffer(unidades);
                buffer.setLength(20);
                raf.writeChars(buffer.toString());
                
                System.out.println("Universo de discurso");
                System.out.println("Ingrese el límite inferior");
                li = in.nextInt();
                raf.writeInt(li);
                
                System.out.println("Ingrese el límite superior");
                ls = in.nextInt();
                raf.writeInt(ls);
                
                int si = 0;
                etiq = new ArrayList<>();
                //ciclo para pedir etiquetas
                do{
                    System.out.println("Ingrese la etiqueta");
                    etiqueta = in.next();
                    ptos = new ArrayList<>();
                    System.out.println("Ingrese el punto crítico");
                    int a = in.nextInt();
                    ptos.add(a);
                    System.out.println("Existe otro punto crítico? Si = 1, No = 0");
                    int opcion = in.nextInt();
                    if (opcion == 1){
                        System.out.println("Ingrese el punto crítico");
                        a = in.nextInt();
                        ptos.add(a);
                    } 
                    System.out.println("Desea insertar otra etiqueta? Si = 1, No = 0");
                    si = in.nextInt();
                    
                    etiq.add(new Etiquetas(etiqueta, ptos));
                } while(si == 1);
                
                escrEtiquetas(raf);
                
                
                System.out.println("Desea ingresar otra variable?: Si = 1, No = 0");
                n = in.nextInt();
            } while (n == 1);
            raf.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    public void escrEtiquetas(RandomAccessFile raf){
        try{
            //escribe entero para definir el numero de etiquetas
            raf.writeInt(etiq.size());
            for (int i = 0; i < etiq.size(); i++){
                StringBuffer buffer;
                Etiquetas et = etiq.get(i);
                buffer = new StringBuffer(et.darNombre());
                buffer.setLength(20);
                raf.writeChars(buffer.toString());
                
                //variable para numero de puntos criticos
                int v_ptosCriticos = et.darPuntos().size();
                raf.writeInt(v_ptosCriticos);
                for (int j = 0; j < et.darPuntos().size(); j++){
                    raf.writeInt(et.darPuntos().get(j));
                }
            }
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    public ArrayList<Etiquetas> leerSecuencial(File p_archivo){
        ArrayList<Etiquetas> v_etiquetas = new ArrayList<>();
        try{
            long v_apunActual, v_apunFinal;
            
            RandomAccessFile raf = new RandomAccessFile(p_archivo, "r");
            while( (v_apunActual = raf.getFilePointer()) != (v_apunFinal = raf.length())){
                //Leer nombre
                char nombre[] = new char[20], temp;
                for (int c = 0; c < nombre.length; c++){
                    temp = raf.readChar();
                    nombre[c] = temp;
                }
                System.out.println("Nombre: " + new String(nombre).replace('\0', ' '));
                
                //Leer unidades
                char a_unidades[] = new char[20];
                for (int i = 0; i < a_unidades.length; i++){
                    temp = raf.readChar();
                    a_unidades[i] = temp;
                }
                System.out.println("Unidades: " + new String(a_unidades).replace('\0', ' '));
                
                //Leer inferior y superior
                System.out.println("Lim Inferior: " + raf.readInt());
                System.out.println("Lim Superior: " + raf.readInt());
                
                //Leer numero de etiquetas
                int num_etiquetas = raf.readInt();
                
                for(int  v_contEtiquetas = 0; v_contEtiquetas < num_etiquetas; v_contEtiquetas++){
                    String v_nombEtiqueta = "";
                    char a_etiqueta[] = new char[20];
                    for (int  a = 0; a < 20; a++){
                        temp = raf.readChar();
                        a_etiqueta[a] = temp;
                    }
                    v_nombEtiqueta = new String(a_etiqueta).replace('\0', ' ');
                    System.out.println(v_nombEtiqueta);
                    ArrayList<Integer> v_puntos = new ArrayList<>();
                    //numero de puntos criticos
                    int nume_ptosCriticos = raf.readInt();
                    for (int v_contPuntCriticos = 0; v_contPuntCriticos < nume_ptosCriticos; v_contPuntCriticos++){
                        int v_pc = raf.readInt();
                        v_puntos.add(v_pc);
                        System.out.println(v_pc);
                    }
                    System.out.println("----------------------");
                    v_etiquetas.add(new Etiquetas(v_nombEtiqueta, v_puntos));
                }
                System.out.println("========================");
            }
            raf.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        return v_etiquetas;
    }
    
    public ArrayList<Variables> leerVariables(File p_archivo){
        ArrayList<Variables> v_variables = new ArrayList<>();
        
        try{
            long v_apunActual, v_apunFinal;
            
            RandomAccessFile raf = new RandomAccessFile(p_archivo, "r");
            while( (v_apunActual = raf.getFilePointer()) != (v_apunFinal = raf.length())){
                //Leer nombre
                char nombre[] = new char[20], temp;
                for (int c = 0; c < nombre.length; c++){
                    temp = raf.readChar();
                    nombre[c] = temp;
                }
                ArrayList<Etiquetas> v_etiquetas = new ArrayList<>();
               // Variables v1 = new Variables(new String(nombre).replace('\0', ' '),li,ls);
                //Leer unidades
                char a_unidades[] = new char[20];
                for (int i = 0; i < a_unidades.length; i++){
                    temp = raf.readChar();
                    a_unidades[i] = temp;
                }
                System.out.println("Unidades: " + new String(a_unidades).replace('\0', ' '));
                
                //Leer inferior y superior
               // System.out.println("Lim Inferior: " + raf.readInt());
                //System.out.println("Lim Superior: " + raf.readInt());
                 Variables v1 = new Variables(new String(nombre).replace('\0', ' '),raf.readInt(),raf.readInt());
                
                //Leer numero de etiquetas
                int num_etiquetas = raf.readInt();
                
                for(int  v_contEtiquetas = 0; v_contEtiquetas < num_etiquetas; v_contEtiquetas++){
                    String v_nombEtiqueta = "";
                    char a_etiqueta[] = new char[20];
                    for (int  a = 0; a < 20; a++){
                        temp = raf.readChar();
                        a_etiqueta[a] = temp;
                    }
                    v_nombEtiqueta = new String(a_etiqueta).replace('\0', ' ');
                    System.out.println(v_nombEtiqueta);
                    ArrayList<Integer> v_puntos = new ArrayList<>();
                    //numero de puntos criticos
                    int nume_ptosCriticos = raf.readInt();
                    for (int v_contPuntCriticos = 0; v_contPuntCriticos < nume_ptosCriticos; v_contPuntCriticos++){
                        int v_pc = raf.readInt();
                        v_puntos.add(v_pc);
                        System.out.println(v_pc);
                    }
                    System.out.println("----------------------");
                    v_etiquetas.add(new Etiquetas(v_nombEtiqueta, v_puntos));
                }
                v1.setEtiquetas(v_etiquetas);
                v_variables.add(v1);
                System.out.println("========================");
            }
            raf.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        return v_variables;
    }
    
     public void escrReglasEquivalentes(File p_archivo,ArrayList<Equivalencias> reglas){
        try{
             RandomAccessFile raf = new RandomAccessFile(p_archivo, "rw");
            //escribe entero para definir el numero de etiquetas
            raf.writeInt(reglas.size());
            for (int i = 0; i < reglas.size(); i++){
                StringBuffer buffer;
                Equivalencias et = reglas.get(i);
                buffer = new StringBuffer(et.getA_reglEquivalentes());
                buffer.setLength(20);
                raf.writeChars(buffer.toString());
                
              
            }
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    public void imprime(){
        for (int i = 0; i < etiq.size(); i++){
            Etiquetas et = etiq.get(i);
            System.out.println(et.darNombre());
            for(int j = 0; j < et.darPuntos().size(); j++){
                System.out.println(et.darPuntos().get(j));
            }
            System.out.println("----------------------");
        }
    }
    /*
    public void m_leerSecuMaestro(File p_archivo){
        try{
            long v_apunActual, v_apunFinal;
            
            RandomAccessFile raf = new RandomAccessFile(p_archivo, "r");
            while ( (v_apunActual = raf.getFilePointer()) != (v_apunFinal = raf.length())){
                a_llave = raf.readInt();
                System.out.println(a_llave);
                char nombre[] = new char[15], temp;
                for (int c = 0; c < nombre.length; c++){
                    temp = raf.readChar();
                    nombre[c] = temp;
                }
                a_titulo = new String(nombre).replace('\0', ' ');
                System.out.println(a_titulo);
                a_clasificacion = raf.readDouble();
                System.out.println(a_clasificacion);
            }
            raf.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    public boolean m_leerAleaMaestro(File p_archivo, int p_llave){
        boolean bandera = false;
        try{
            int n, dl;
            long lreg, desplaza;
            
            RandomAccessFile raf = new RandomAccessFile(p_archivo, "r");
            Scanner in = new Scanner(System.in);
            raf.readInt();
            for(int c = 0; c < 15; c++)
                raf.readChar();
            raf.readDouble();
            lreg = raf.getFilePointer();
            
            dl = p_llave;
            desplaza = (dl - 1) * lreg;
            if (desplaza < raf.length()){
                raf.seek(desplaza);
                a_llave = raf.readInt();
                System.out.println("Datos:");
                System.out.println(a_llave);
                char nombre[] = new char[15], temp;
                for (int c1 = 0; c1 < nombre.length; c1++){
                    temp = raf.readChar();
                    nombre[c1] = temp;
                }
                a_titulo = new String(nombre).replace('\0', ' ');
                System.out.println(a_titulo);
                a_clasificacion = raf.readDouble();
                System.out.println(a_clasificacion);
                bandera = true;
            } 
            raf.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        return bandera;
    }
    
    public boolean m_borrRegistro(File p_archivo, int p_llave){
        boolean coincidencia = false;
        try{
            long v_apunActual, v_apunFinal;
            
            RandomAccessFile raf = new RandomAccessFile(p_archivo,"r");
            File v_archTemporal = new File("auxiliar");
            RandomAccessFile rafT = new RandomAccessFile(v_archTemporal, "rw");
            
            while( (v_apunActual = raf.getFilePointer()) != (v_apunFinal = raf.length())){
                int v_llave = raf.readInt();
                if (v_llave != p_llave){
                    rafT.writeInt(v_llave);
                    char nombre[] = new char[15], temp;
                    for (int i = 0; i < nombre.length; i++){
                        temp = raf.readChar();
                        nombre[i] = temp;
                    }
                    String v_titulo = new String(nombre).replace('\0', ' ');
                    StringBuffer buff = new StringBuffer(v_titulo);
                    buff.setLength(15);
                    rafT.writeChars(buff.toString());
                    double v_class = raf.readDouble();
                    rafT.writeDouble(v_class);
                } else {
                    for(int o = 0; o < 15; o++)
                        raf.readChar();
                    raf.readDouble();
                    coincidencia = true;
                }
            }
            raf.close();
            rafT.close();
            String name = p_archivo.getName();
            p_archivo.delete();
            File f2 = new File(name);
            v_archTemporal.renameTo(f2);
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        return coincidencia;
    }*/
}

