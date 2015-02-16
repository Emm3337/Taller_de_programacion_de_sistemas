package Practica_1;

import java.io.*;

/**
 *
 * @author Hector Miguel Morales Landa
 */
public class Automata {
    
    FileWriter fil = null;
    PrintWriter pw = null;
    
    public void ErrEtq(String str,int nln, String erruta){
        try {
            fil = new FileWriter(erruta,true);
            pw = new PrintWriter(fil);
            int i,t=0;
            String err="";
            char cad[]=str.toCharArray();
            
            pw.println("Linea\tError");
            if(str.length()>=8){
                pw.println(nln+"\tError excedio el tamaño maximo de caracters(8).");
                pw.println("\tUsted ingreso "+str.length());
            }
            if(!Character.isAlphabetic(cad[0])){
                pw.println(nln+"\tError Etiqueta invalida.");
                pw.println("\tUna etiqueta debe de comenzar con letra ya sea minuscula o mayuscula.");
                pw.println("\tUsted ingreso "+cad[0]+".");
                err=err+cad[0];
                t++;
            }
            for(i=1;i<cad.length;i++){
                if(!Character.isAlphabetic(cad[i]) && !Character.isDigit(cad[i]) && cad[i]!='_'){
                    err=err+","+cad[i];
                    t++;
                }
            }
            pw.println(nln+"\tError ingreso "+t+" caracter(s) invalido(s).");
            pw.println("\tCaracter(s) invalido(s) ingresado(s) {"+err+"}.");
            pw.println("\tUna etiqueta solo acepta letras(mayusculas o minusculas), numeros y guion bajo.");
            pw.println("-----------------------------------------------------------------------------");
            pw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public int Repetir(String str){
        int n=0,i;
        char cad[]=str.toCharArray();
        for(i=0;i<cad.length;i++){
            if(cad[i]=='.')
                n++;
        }
        return n;
    }
    
    public void ErrCodOps(String str,int nln, String erruta){
        try {
            fil = new FileWriter(erruta,true);
            pw = new PrintWriter(fil);
            int i,t=0;
            int n=Repetir(str);
            String err="";
            char cad[]=str.toCharArray();
            
            pw.println("Linea\tError");
            if(n>1){
                pw.println(nln+"\tError excedio el limite de puntos que es de 1.");
                pw.println("\tUsted ingreso "+n);
                err=err+".";
                for(i=2;i<n;i++){
                    err=err+",.";
                }
                t=n-1;
            }
            if(str.length()>=5){
                pw.println(nln+"\tError excedio el tamaño maximo de caracters(5).");
                pw.println("\tUsted ingreso "+str.length());
            }
            if(!Character.isAlphabetic(cad[0]) && cad[0]!='.'){
                pw.println(nln+"\tError codigo de operacion invalido.");
                pw.println("\tUna codigo de operacion debe de comenzar con letra ya sea minuscula , mayuscula o punto.");
                pw.println("\tUsted ingreso "+cad[0]+".");
                err=err+cad[0];
                t++;
            }
            for(i=1;i<cad.length;i++){
                if(!Character.isAlphabetic(cad[i]) && cad[i]!='.' ){
                    err=err+","+cad[i];
                    t++;
                }
            }
            pw.println(nln+"\tError ingreso "+t+" caracter(s) invalido(s).");
            pw.println("\tCaracter(s) invalido(s) ingresado(s) {"+err+"}.");
            pw.println("\tUna codigo de operacion solo acepta letras(mayusculas o minusculas) y un solo punto.");
            pw.println("-----------------------------------------------------------------------------");
            pw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void INC(int nln, String erruta){
        try {
            fil = new FileWriter(erruta,true);
            pw = new PrintWriter(fil);
            
            pw.println(nln+" Error Esta linea no tiene codigo de operacion");
            pw.println("-----------------------------------------------------------------------------");
            pw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void OEND(int nln, String erruta){
        try {
            fil = new FileWriter(erruta,true);
            pw = new PrintWriter(fil);
            
            pw.println(nln+" Error el cierre de archivo END no puede llevar operando.");
            pw.println("-----------------------------------------------------------------------------");
            pw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void NEND(int nln, String erruta){
        try {
            fil = new FileWriter(erruta,true);
            pw = new PrintWriter(fil);
            
            pw.println(nln+" Error el cierre de archivo END no se encontro.");
            pw.println("-----------------------------------------------------------------------------");
            pw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void MTokens(String str,int nln, String erruta){
        try {
            fil = new FileWriter(erruta,true);
            pw = new PrintWriter(fil);
            
            if(!str.contains(";")){
            pw.println(nln+"\tError ingreso una cadena que no es etiqueta, codigo de operacion, operando o comentario.");
            pw.println("\t"+str);
            pw.println("-----------------------------------------------------------------------------");
            pw.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
