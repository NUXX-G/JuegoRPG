package juegorpg;

import juegorpg.modelo.personaje.Arquero;
import juegorpg.modelo.personaje.Guerrero;
import juegorpg.modelo.personaje.Ladron;
import juegorpg.modelo.personaje.Mago;

public class JuegoRPG 
{

    public static void main(String[] args) 
    {
        Guerrero guerrero1 = new Guerrero("Guerrero1");
        Mago mago1 = new Mago("Mago1");
        Arquero arquero1 = new Arquero("Arquero1");
        Ladron ladron1 = new Ladron("Ladron1");
        
        System.out.println("--- GUERRERO ---");
        System.out.println(guerrero1.obtenerDescripcion());
        System.out.println(guerrero1.toString());
        System.out.println(guerrero1.getNombre() + " recibe 30 de danio");
        guerrero1.recibirDanio(30);
        System.out.println(guerrero1.toString());
        System.out.println(guerrero1.getNombre() + " recibe 100 de experiencia");
        guerrero1.ganarExperiencia(100);
        System.out.println(guerrero1.toString());
        System.out.println("");
        
        System.out.println("--- MAGO ---");
        System.out.println(mago1.obtenerDescripcion());
        System.out.println(mago1.toString());
        System.out.println(mago1.getNombre() + " recibe 30 de danio");
        mago1.recibirDanio(30);
        System.out.println(mago1.toString());
        System.out.println(mago1.getNombre() + " recibe 100 de experiencia");
        mago1.ganarExperiencia(100);
        System.out.println(mago1.toString());
        System.out.println("");
        
        System.out.println("--- ARQUERO ---");
        System.out.println(arquero1.obtenerDescripcion());
        System.out.println(arquero1.toString());
        System.out.println(arquero1.getNombre() + " recibe 30 de danio");
        arquero1.recibirDanio(30);
        System.out.println(arquero1.toString());
        System.out.println(arquero1.getNombre() + " recibe 100 de experiencia");
        arquero1.ganarExperiencia(100);
        System.out.println(arquero1.toString());
        System.out.println("");
        
        System.out.println("--- LADRON ---");
        System.out.println(ladron1.obtenerDescripcion());
        System.out.println(ladron1.toString());
        System.out.println(ladron1.getNombre() + " recibe 30 de danio");
        ladron1.recibirDanio(30);
        System.out.println(ladron1.toString());
        System.out.println(ladron1.getNombre() + " recibe 100 de experiencia");
        ladron1.ganarExperiencia(100);
        System.out.println(ladron1.toString());
        System.out.println("");
    }
    
}
