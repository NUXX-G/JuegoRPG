package juegorpg;

import juegorpg.vista.VentanaPrincipal;

/**
 * Clase principal del juego. El punto de entrada, nada mas.
 * Aqui arranca todo, simplemente crea la ventana y ya.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class JuegoRPG {

    /**
     * Main del juego. Crea la ventana principal y el resto se gestiona solo
     * desde ahi. No hace falta mas logica aqui.
     *
     * @param args argumentos de la linea de comandos (no se usan)
     */
    public static void main(String[] args) 
    {
        new VentanaPrincipal();
    }
}