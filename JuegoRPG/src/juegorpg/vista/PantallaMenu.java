package juegorpg.vista;

import java.awt.*;
import javax.swing.*;
import juegorpg.modelo.personaje.Personaje;
import juegorpg.modelo.enemigo.Enemigo;
import juegorpg.narrativa.ArbolNarrativo;
import juegorpg.modelo.item.Inventario;
import juegorpg.combate.SistemaCombate;
import juegorpg.guardado.GestorGuardado;
import juegorpg.guardado.PartidaGuardada;

public class PantallaMenu extends JPanel
{
    private VentanaPrincipal ventana;
    
public PantallaMenu(VentanaPrincipal ventana) 
{
    this.ventana = ventana;
    
    setLayout(new BorderLayout());
    
    JLabel titulo = new JLabel("RPG - Aventura Épica", SwingConstants.CENTER);
    titulo.setFont(new Font("Arial", Font.BOLD, 36));
    add(titulo, BorderLayout.NORTH);
    

    JPanel panelBotones = new JPanel();
    panelBotones.setLayout(new GridLayout(3, 1, 10, 10));
    
    JButton btnNueva = new JButton("Nueva Partida");
    JButton btnCargar = new JButton("Cargar Partida");
    JButton btnSalir = new JButton("Salir");
    
    btnNueva.addActionListener(e -> 
    {
        ventana.mostrarPantallaSeleccionPersonaje();
    });
    
    btnCargar.addActionListener(e -> 
    {
        if (GestorGuardado.existePartida("partida_guardada")) 
        {
            PartidaGuardada partida = GestorGuardado.cargarPartida("partida_guardada");
            
            if (partida != null) 
            {
                ventana.setPersonaje(partida.getPersonaje());
                ventana.setArbolNarrativo(partida.getArbolNarrativo());
                ventana.setInventario(partida.getInventario());
                
                ventana.mostrarPantallaExploracion();
            } else 
            {
                JOptionPane.showMessageDialog(this, "Error al cargar la partida");
            }
        } 
        else 
        {
            JOptionPane.showMessageDialog(this, "No hay partidas guardadas");
        }
    });
    
    btnSalir.addActionListener(e -> 
    {
        System.exit(0);
    });
    
    panelBotones.add(btnNueva);
    panelBotones.add(btnCargar);
    panelBotones.add(btnSalir);
    
    add(panelBotones, BorderLayout.CENTER);
}
}
