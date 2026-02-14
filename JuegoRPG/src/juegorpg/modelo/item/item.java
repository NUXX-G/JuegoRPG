package juegorpg.modelo.item;
import java.io.Serializable;

public abstract class Item implements Serializable
{
    private String nombre;
    private String descripcion;
    private int precio;
    
    public Item(String nombre, String descripcion, int precio)
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }
    
    public abstract String obtenerTipo();
    
    public String getNombre() 
    {
        return nombre;
    }

    public String getDescripcion() 
    {
        return descripcion;
    }

    public int getPrecio() 
    {
        return precio;
    }

    public void setPrecio(int precio) 
    {
        this.precio = precio;
    }

    @Override
    public String toString() 
    {
        return nombre + " - " + descripcion + " (precio: " + precio + " oro)";
    }
}
