package juegorpg.narrativa;

import java.io.Serializable;

/**
 * Una opcion de decision en el arbol narrativo.
 * Cada nodo tiene una lista de opciones. Cada opcion tiene el texto
 * que se muestra en el boton y el id del nodo al que lleva.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class Opcion implements Serializable 
{

    /** Texto que aparece en el boton de decision. */
    private String texto;

    /** Id del nodo al que lleva esta opcion al elegirla. */
    private String nodoDestino;

    /**
     * Crea una opcion con su texto y su destino.
     *
     * @param texto         texto del boton que ve el jugador
     * @param nodoDestino   id del nodo al que navega al elegir esta opcion
     */
    public Opcion(String texto, String nodoDestino) 
    {
        this.texto = texto;
        this.nodoDestino = nodoDestino;
    }

    /**
     * Muestra la opcion como texto simple con un guion al principio.
     *
     * @return texto de la opcion
     */
    @Override
    public String toString() 
    {
        return "- " + texto;
    }

    /** @return texto del boton de la opcion */
    public String getTexto() 
    { 
        return texto; 
    }

    /** @return id del nodo destino al elegir esta opcion */
    public String getNodoDestino() 
    { 
        return nodoDestino; 
    }
}