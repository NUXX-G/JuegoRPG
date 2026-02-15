package juegorpg.vista;

import javax.swing.*;
import juegorpg.combate.SistemaCombate;
import juegorpg.modelo.enemigo.Enemigo;
import juegorpg.modelo.item.Inventario;
import juegorpg.modelo.personaje.Personaje;
import juegorpg.narrativa.ArbolNarrativo;

public class VentanaPrincipal extends JFrame
{
    private JPanel pantallaActual;
    private Personaje personaje;
    private ArbolNarrativo arbolNarrativo;
    private Inventario inventario;
    private SistemaCombate combateActual;
    
    public VentanaPrincipal()
    {
        setTitle("RPG - Aventura Épica");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        mostrarPantallaMenu();
        
        setVisible(true);
    }
    
    public void mostrarPantallaMenu()
    {
        getContentPane().removeAll();
        
        pantallaActual = new PantallaMenu(this);
        
        getContentPane().add(pantallaActual);
        
        revalidate();
        repaint();
    }
    
    public void mostrarPantallaSeleccionPersonaje()
    {
        getContentPane().removeAll();
        
        pantallaActual = new PantallaSeleccionPersonaje(this);
        
        getContentPane().add(pantallaActual);
        
        revalidate();
        repaint();
    }
    
    public void mostrarPantallaExploracion()
    {
        getContentPane().removeAll();
        
        pantallaActual = new PantallaExploracion(this);
        
        getContentPane().add(pantallaActual);
        
        revalidate();
        repaint();
    }
    
    public void mostrarPantallaCombate(Enemigo enemigo)
    {
        getContentPane().removeAll();
        
        pantallaActual = new PantallaCombate(this, enemigo);
        
        getContentPane().add(pantallaActual);
        
        revalidate();
        repaint();
    }
    
    public void mostrarPantallaInventario()
    {
        getContentPane().removeAll();
        
        pantallaActual = new PantallaInventario(this);
        
        getContentPane().add(pantallaActual);
        
        revalidate();
        repaint();
    }
    
    public void mostrarPantallaGameOver(boolean victoria)
    {
        getContentPane().removeAll();
    
        pantallaActual = new PantallaGameOver(this, victoria);
    
        getContentPane().add(pantallaActual);
    
        revalidate();
        repaint();
    }

    public JPanel getPantallaActual() 
    {
        return pantallaActual;
    }

    public void setPantallaActual(JPanel pantallaActual) 
    {
        this.pantallaActual = pantallaActual;
    }

    public Personaje getPersonaje() 
    {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) 
    {
        this.personaje = personaje;
    }

    public ArbolNarrativo getArbolNarrativo() 
    {
        return arbolNarrativo;
    }

    public void setArbolNarrativo(ArbolNarrativo arbolNarrativo) 
    {
        this.arbolNarrativo = arbolNarrativo;
    }

    public Inventario getInventario() 
    {
        return inventario;
    }

    public void setInventario(Inventario inventario) 
    {
        this.inventario = inventario;
    }

    public SistemaCombate getCombateActual() 
    {
        return combateActual;
    }

    public void setCombateActual(SistemaCombate combateActual) 
    {
        this.combateActual = combateActual;
    }
    
    
}
