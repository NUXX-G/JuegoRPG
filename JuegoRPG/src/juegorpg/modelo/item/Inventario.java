package juegorpg.modelo.item;

import java.util.ArrayList;

public class Inventario 
{
    private static final int CAPACIDAD_MAXIMA = 20;
    private ArrayList<Item> items;
    
    public Inventario()
    {
        this.items = new ArrayList<>();
    }
    
    public boolean agregarItem(Item item)
    {
        boolean agregar = false;
        
        if (items.size() < CAPACIDAD_MAXIMA) 
        {
            items.add(item);
            agregar = true;
        }
        
        return agregar;
    }
    
    public boolean quitarItem(int indice)
    {
        boolean quitar = false;
        
        if (indice < items.size() && indice >= 0) 
        {
            items.remove(indice);
            quitar = true;
        }
        
        return quitar;
    }
    
    public Item obtenerItem(int indice)
    {
        if (indice < 0 || indice >= items.size()) 
        {
            return null;
        }
        
        return items.get(indice);
    }
    
    public int getCantidadActual() 
    {
        return items.size();
    }
    
    public boolean estaLleno()
    {
        boolean lleno = false;
        
        if (items.size() >= CAPACIDAD_MAXIMA) 
        {
            lleno = true;
        }
        
        return lleno;
    }
    
    public boolean estaVacio()
    {
        boolean vacio = false;
        
        if (items.size() == 0) 
        {
            vacio = true;
        }
        
        return vacio;
    }
    
    public void mostrarInventario()
    {
        String inventarioMostrar = "El inventario esta vacio.";
        if (items.size() > 0) 
        {
            inventarioMostrar = "═══ INVENTARIO (" + items.size() + "/" + CAPACIDAD_MAXIMA + ") ═══" + "\n";
            for (int i = 0; i < items.size(); i++) 
            {
                inventarioMostrar += "[" + (i + 1) + "] " + items.get(i).toString() + "\n";
            }
            
            inventarioMostrar += "══════════════════════════" + "\n";
        }
        
        System.out.println(inventarioMostrar);
    }
}