package Practica_1;

/**
 *
 * @author Hector Miguel Morales Landa
 */

import java.util.Scanner;

public class Main {
    

    public static void main(String[] args) {
        
        Archivos infl = new Archivos();
        Scanner sc = new Scanner(System.in);
        
        String ruta;
        
        System.out.println("Ruta:");
        ruta=sc.nextLine();
        
        if(infl.Buscar(ruta)){
            infl.Borrar(ruta);
            infl.Leer(ruta);
        }
        
    }
}
