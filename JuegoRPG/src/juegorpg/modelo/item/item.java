package juegorpg.modelo.item;

import java.io.Serializable;

/**
 * Clase abstracta base para todos los items del juego.
 * Todo lo que puede ir en el inventario hereda de aqui:
 * Arma, Armadura y Consumible. Tiene el nombre, la descripcion
 * y el precio para comprar o vender.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public abstract class Item implements Serializable 
{

    private String nombre;
    private String descripcion;
    private int precio;

    /**
     * Constructor base del item.
     *
     * @param nombre      nombre del item que aparece en el inventario
     * @param descripcion descripcion corta de lo que hace o es
     * @param precio      precio en oro para comprar o vender
     */
    public Item(String nombre, String descripcion, int precio) 
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    /**
     * Devuelve el tipo del item en texto.
     * Cada subclase devuelve "Arma", "Armadura" o "Consumible".
     *
     * @return tipo del item como string
     */
    public abstract String obtenerTipo();

    /**
     * Muestra el item en formato legible para el inventario.
     *
     * @return string con nombre, descripcion y precio
     */
    @Override
    public String toString() 
    {
        return nombre + " - " + descripcion + " (precio: " + precio + " oro)";
    }

    /** @return nombre del item */
    public String getNombre() 
    { 
        return nombre; 
    }

    /** @return descripcion del item */
    public String getDescripcion() 
    { 
        return descripcion; 
    }

    /** @return precio en oro */
    public int getPrecio() 
    { 
        return precio; 
    }

    /** @param precio nuevo precio */
    public void setPrecio(int precio) 
    { 
        this.precio = precio; 
    }
}