package juegorpg.vista;

import javax.swing.*;
import juegorpg.combate.SistemaCombate;
import juegorpg.modelo.enemigo.Enemigo;
import juegorpg.modelo.item.Inventario;
import juegorpg.modelo.personaje.Personaje;
import juegorpg.narrativa.ArbolNarrativo;

/**
 * La ventana principal del juego. Es el JFrame que contiene todo.
 * Actua como controlador central: guarda el estado global (personaje,
 * inventario, arbol narrativo) y gestiona que pantalla se muestra en
 * cada momento. Todas las pantallas tienen referencia a esta clase
 * para poder navegar entre ellas y acceder al estado del juego.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class VentanaPrincipal extends JFrame 
{

    private JPanel pantallaActual;
    private Personaje personaje;
    private ArbolNarrativo arbolNarrativo;
    private Inventario inventario;
    private SistemaCombate combateActual;

    /**
     * Crea la ventana principal, configura su tamano y la hace visible.
     * La primera pantalla que se muestra es siempre el menu principal.
     */
    public VentanaPrincipal() 
    {
        setTitle("RPG - Aventura Epica");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getRootPane().setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 180, 40), 4));

        mostrarPantallaMenu();

        setVisible(true);
    }

    /**
     * Navega al menu principal.
     * Elimina la pantalla actual y carga PantallaMenu.
     */
    public void mostrarPantallaMenu() 
    {
        getContentPane().removeAll();
        pantallaActual = new PantallaMenu(this);
        getContentPane().add(pantallaActual);
        revalidate();
        repaint();
    }

    /**
     * Navega a la pantalla de seleccion de personaje.
     * Se llama al pulsar "Nueva Partida" en el menu.
     */
    public void mostrarPantallaSeleccionPersonaje() 
    {
        getContentPane().removeAll();
        pantallaActual = new PantallaSeleccionPersonaje(this);
        getContentPane().add(pantallaActual);
        revalidate();
        repaint();
    }

    /**
     * Navega a la pantalla de exploracion narrativa.
     * Se llama al seleccionar personaje o al volver del inventario o combate.
     */
    public void mostrarPantallaExploracion() 
    {
        getContentPane().removeAll();
        pantallaActual = new PantallaExploracion(this);
        getContentPane().add(pantallaActual);
        revalidate();
        repaint();
    }

    /**
     * Navega a la pantalla de combate contra el enemigo indicado.
     * Se llama desde PantallaExploracion cuando el nodo actual tiene enemigo.
     *
     * @param enemigo el enemigo contra el que se va a combatir
     */
    public void mostrarPantallaCombate(Enemigo enemigo) 
    {
        getContentPane().removeAll();
        pantallaActual = new PantallaCombate(this, enemigo);
        getContentPane().add(pantallaActual);
        revalidate();
        repaint();
    }

    /**
     * Navega a la pantalla de inventario.
     * Se puede llamar tanto desde la exploracion como desde el combate.
     */
    public void mostrarPantallaInventario() 
    {
        getContentPane().removeAll();
        pantallaActual = new PantallaInventario(this);
        getContentPane().add(pantallaActual);
        revalidate();
        repaint();
    }

    /**
     * Navega a la pantalla de game over o victoria.
     * Se llama cuando el jugador muere o completa la historia.
     *
     * @param victoria true si el jugador gano, false si fue derrotado
     */
    public void mostrarPantallaGameOver(boolean victoria) 
    {
        getContentPane().removeAll();
        pantallaActual = new PantallaGameOver(this, victoria);
        getContentPane().add(pantallaActual);
        revalidate();
        repaint();
    }

    // ── GETTERS Y SETTERS ────────────────────────────────────────────────────

    /** @return pantalla que se esta mostrando actualmente */
    public JPanel getPantallaActual() 
    { 
        return pantallaActual; 
    }

    /** @param pantallaActual nueva pantalla activa */
    public void setPantallaActual(JPanel pantallaActual) 
    { 
        this.pantallaActual = pantallaActual; 
    }

    /** @return personaje del jugador */
    public Personaje getPersonaje() 
    { 
        return personaje; 
    }

    /** @param personaje personaje seleccionado por el jugador */
    public void setPersonaje(Personaje personaje) 
    { 
        this.personaje = personaje; 
    }

    /** @return arbol narrativo con el estado actual de la historia */
    public ArbolNarrativo getArbolNarrativo() 
    { 
        return arbolNarrativo; 
    }

    /** @param arbolNarrativo nuevo arbol narrativo */
    public void setArbolNarrativo(ArbolNarrativo arbolNarrativo) 
    { 
        this.arbolNarrativo = arbolNarrativo; 
    }

    /** @return inventario del jugador */
    public Inventario getInventario()
    { 
        return inventario; 
    }

    /** @param inventario nuevo inventario */
    public void setInventario(Inventario inventario) 
    { 
        this.inventario = inventario; 
    }

    /** @return sistema de combate activo, o null si no hay combate */
    public SistemaCombate getCombateActual() 
    { 
        return combateActual; 
    }

    /** @param combateActual nuevo sistema de combate */
    public void setCombateActual(SistemaCombate combateActual) 
    { 
        this.combateActual = combateActual; 
    }
}