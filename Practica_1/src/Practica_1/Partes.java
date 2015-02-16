/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica_1;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Hector
 */
public class Partes {
    
    ArrayList<String> pila = new ArrayList();
    static boolean conm=false;
    
    public String Dividir(String linea, int nln, String erruta){
        StringTokenizer token = new StringTokenizer(linea);
        Pattern patEtq = Pattern.compile("^[a-zA-Z][\\w\\d]{0,7}");
        Pattern patCodOps = Pattern.compile("^[a-zA-Z][\\w]{0,4}");
        Automata aut = new Automata();
        
        String str=token.nextToken();
        String aux;
        String ins;
        Matcher matEtq = patEtq.matcher(str);
        char band = linea.charAt(0);
        int inda;
        conm=false;
        
        if(str.indexOf(";")==0){
            pila.add(0,null);
            pila.add(1,null);
            pila.add(2,null);
            conm=true;
        }
        
        if(band!=' ' && band!='\t' && conm == false){
            pila.clear();
            if(matEtq.matches()){
                pila.add(0,str);
            }
            else{
                if(str.contains(";")){
                    inda=str.indexOf(";");
                    aux=str.substring(0,inda);
                    patEtq.matcher(aux);
                    conm = true;
                    if(matEtq.matches()){
                        pila.add(0,aux);
                        pila.add(1,null);
                        pila.add(2,null);
                    }
                    else{
                        pila.add(0,null);
                        aut.ErrEtq(aux,nln,erruta);
                    }
                }
                else{
                    pila.add(0,null);
                    aut.ErrEtq(str,nln,erruta);
                }
            }
        }
        else if(conm==false){
            pila.clear();
            int n=aut.Repetir(str);
            if(n<=1){
                aux=str.replaceAll("\\.","");
                 Matcher matCodOps = patCodOps.matcher(aux);
                if(matCodOps.matches()){
                    pila.add(0,null);
                    pila.add(1,str);
                }
                else{
                    if(aux.contains(";")){
                        inda=aux.indexOf(";");
                        aux=aux.substring(0,inda);
                        patCodOps.matcher(aux);
                        conm = true;
                        if(matCodOps.matches()){
                            pila.add(0,null);
                            pila.add(1,aux);
                            pila.add(2,null);
                        }
                        else{
                            pila.add(0,null);
                            pila.add(1,null);
                            aut.ErrCodOps(aux,nln,erruta);
                        }
                    }
                    else{
                        pila.add(0,null);
                        pila.add(1,null);
                        aut.ErrCodOps(str,nln,erruta);
                    }
                }
            }
            else{
                pila.add(0,null);
                pila.add(1,null);
                aut.ErrCodOps(str,nln,erruta);
            }
        }
        if(token.hasMoreTokens() && conm == false && pila.size()==1){
            str=token.nextToken();
            int n=aut.Repetir(str);
            if(n<=1){
                aux=str.replaceAll("\\.","");
                Matcher matCodOps = patCodOps.matcher(aux);
                if(matCodOps.matches()){
                    pila.add(1,str);
                }
                else{
                    if(aux.contains(";")){
                        inda=aux.indexOf(";");
                        aux=aux.substring(0,inda);
                        patCodOps.matcher(aux);
                        conm = true;
                        if(matCodOps.matches()){
                            pila.add(1,aux);
                            pila.add(2,null);
                        }
                        else{
                            pila.add(1,null);
                            aut.ErrCodOps(aux,nln,erruta);
                        }
                    }
                    else{
                        pila.add(1,null);
                        aut.ErrCodOps(str,nln,erruta);
                    }
                }
            }
        }
        else{
            if(pila.size()==1){
                pila.add(1,null);
                pila.add(2,null);
            }
        }
        if(token.hasMoreTokens() && conm == false && pila.size()==2){
            str=token.nextToken();
            if(str.contains(";")){
                inda=str.indexOf(";");
                str=str.substring(0,inda);
            }
            pila.add(2,str);
        }
        else{
            if(pila.size()==2){
                pila.add(2,null);
            }
        }
        while(token.hasMoreTokens() && conm == false){
            str=token.nextToken();
            aut.MTokens(str,nln,erruta);
        }
        if(conm==true){
            ins="Blanco";
        }
        else{
            String c;
            String d="end";
            String b="";
            c=pila.get(0);
            ins=c+" ";
            c=pila.get(1);
            if(c==null){
                aut.OEND(nln, erruta);
            }
            else if(c.equalsIgnoreCase(d)){
                b="1salidas";
            }
            ins=ins+c+" ";
            c=pila.get(2);
            if("1salidas".equals(b) && c != null){
                aut.OEND(nln, erruta);
            }
            ins=ins+c+" "+b;
        }
        
        return ins;
    }
    
}
