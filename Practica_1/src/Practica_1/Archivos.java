package Practica_1;

/**
 *
 * @author Hector Miguel Morales Landa
 */
import java.io.*;
import java.util.StringTokenizer;

public class Archivos{
    
    File fl = null;
    FileReader fr = null;
    BufferedReader br = null;
    FileWriter flerr = null;
    FileWriter flinst = null;
    PrintWriter pw = null;
    static String erruta;
    static String instruta;
    
    public boolean Buscar(String ruta){
        String ext=".ASM";
        if(ruta.contains(ext)){
            fl = new File(ruta);
            if(fl.exists()){
                return true;
            }
            else{
                System.err.println("ERROR. El fichero no existe.");
                return false;
            }
        }
        else{
            System.err.println("ERROR. La extencion no es correcta.");
            return false;
        }
    }
    
    public void Leer(String ruta){
        int nln=1;
        String nombre;
        String direccion;
        String ins;
        boolean band=false;
        
        nombre=Nombre(ruta);
        direccion=Direccion(ruta);
        erruta=direccion+nombre+".ERR";
        instruta=direccion+nombre+".INST";
        Automata aut = new Automata();
        try{
            fl = new File(ruta);
            fr = new FileReader(fl);
            br = new BufferedReader(fr);
            flinst = new FileWriter(instruta);
            pw = new PrintWriter(flinst);
            pw.println("LINEA\tETQ\tCODOP\tOPER");
            pw.println(".................................................................");
            pw.close();
            Partes pr = new Partes();
            String linea;
            while((linea=br.readLine())!=null){
                if(!linea.trim().equals("")){
                    ins=pr.Dividir(linea,nln,erruta);
                    if(!"Blanco".equals(ins)){
                        ins=nln+" "+ins;
                        if(ins.contains("1salidas")){
                            ins=ins.replace("1salidas","");
                            band=true;
                        }
                        Escribir(instruta,ins);
                    }
                    nln++;
                }
                else
                    nln++;
                if(band==true)
                    break;
            }
            if(band==false){
                aut.NEND(nln,erruta);
            }
            br.close();
            if(nln==0){
                flerr = new FileWriter(erruta);
                pw = new PrintWriter(flerr);
                pw.print("ERROR el archivo esta vacio");
                pw.close();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public String Nombre(String ruta){
        String nombre;
        int tam=ruta.length();
        int last;
        last=ruta.lastIndexOf('\\');
        if(last==-1){
            nombre=ruta.substring(0,tam-4);
            return nombre;
        }
        else{
            nombre=ruta.substring(last+1,tam-4);
            return nombre;
        }
    }
    
    public String Direccion(String ruta){
        String direccion;
        int last;
        last=ruta.lastIndexOf('\\');
        if(last==-1){
            direccion="";
            return direccion;
        }
        else{
            direccion=ruta.substring(0,last+1);
            return direccion;
        }
    }
    
    public void Borrar(String ruta){
        String nombre;
        String dirreccion;
        nombre=Nombre(ruta);
        dirreccion=Direccion(ruta);
        File fil = new File(nombre+dirreccion+".ERR");
        fil.delete();
        fil = new File(nombre+dirreccion+".INST");
        fil.delete();
    }
    
    public void Escribir(String instruta, String ins){
        try {
            flinst = new FileWriter(instruta,true);
            pw = new PrintWriter(flinst);
            StringTokenizer token = new StringTokenizer(ins);
            String str;
            while(token.hasMoreTokens())
            {
                str=token.nextToken();
                pw.print(str+"\t");
            }
            pw.println();
            pw.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
