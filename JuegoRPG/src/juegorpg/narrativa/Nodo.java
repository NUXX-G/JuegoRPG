package juegorpg.narrativa;
import java.util.ArrayList;
import juegorpg.modelo.enemigo.Enemigo;
import java.io.Serializable;

public class Nodo implements Serializable
{
    private String id;
    private String descripcion;
    private ArrayList<Opcion> opciones;
    private Enemigo enemigo;
    private boolean esNodoFinal;
    
    public Nodo(String id, String descripcion)
    {
        opciones = new ArrayList<>();
        enemigo = null;
        esNodoFinal = false;
        this.id = id;
        this.descripcion = descripcion;
    }
    
    public void agregarOpcion(Opcion opcion)
    {
        opciones.add(opcion);
    }

    public void setEnemigo(Enemigo enemigo) 
    {
        this.enemigo = enemigo;
    }

    public void setEsNodoFinal(boolean esNodoFinal) 
    {
        this.esNodoFinal = esNodoFinal;
    }

    public String getId() 
    {
        return id;
    }

    public String getDescripcion() 
    {
        return descripcion;
    }

    public ArrayList<Opcion> getOpciones() 
    {
        return opciones;
    }

    public Enemigo getEnemigo() 
    {
        return enemigo;
    }

    public boolean isEsNodoFinal() 
    {
        return esNodoFinal;
    }
    
    public boolean tieneCombate()
    {
        return enemigo != null;
    }
}
