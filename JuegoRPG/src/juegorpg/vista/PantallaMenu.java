package juegorpg.vista;

import java.awt.*;
import javax.swing.*;
import juegorpg.guardado.GestorGuardado;
import juegorpg.guardado.PartidaGuardada;

/**
 * Pantalla del menu principal del juego.
 * Es la primera pantalla que ve el jugador al arrancar.
 * Tiene tres opciones: nueva partida, cargar partida y salir.
 * Extiende GradientPanel para tener el fondo negro con scanlines CRT.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class PantallaMenu extends UITheme.GradientPanel 
{

    private VentanaPrincipal ventana;

    /**
     * Crea el menu principal con su titulo, subtitulo, botones y footer.
     * Los botones estan centrados en la pantalla con GridBagLayout.
     *
     * @param ventana referencia a la ventana principal para navegar entre pantallas
     */
    public PantallaMenu(VentanaPrincipal ventana) 
    {
        this.ventana = ventana;
        setLayout(new BorderLayout());

        // NORTE: titulo + tagline
        JLabel titulo = UITheme.crearTitulo("RPG  --  Aventura Epica");
        JLabel tagline = UITheme.crearSeparador("Un heroe olvidado. Un destino inevitable.");
        JPanel norte = new JPanel(new BorderLayout());
        norte.setOpaque(false);
        norte.add(titulo, BorderLayout.NORTH);
        norte.add(tagline, BorderLayout.SOUTH);
        add(norte, BorderLayout.NORTH);

        // CENTRO: botones centrados con GridBagLayout
        JPanel centro = new JPanel(new GridBagLayout());
        centro.setOpaque(false);

        JPanel columna = new JPanel(new GridLayout(3, 1, 0, 14));
        columna.setOpaque(false);
        columna.setPreferredSize(new Dimension(260, 155));

        JButton btnNueva  = new JButton("Nueva Partida");
        JButton btnCargar = new JButton("Cargar Partida");
        JButton btnSalir  = new JButton("Salir");

        UITheme.estilizarBoton(btnNueva);
        UITheme.estilizarBoton(btnCargar);
        UITheme.estilizarBotonPeligro(btnSalir);

        btnNueva.addActionListener(e -> ventana.mostrarPantallaSeleccionPersonaje());

        btnCargar.addActionListener(e -> 
        {
            if (GestorGuardado.existePartida("partida_guardada")) 
            {
                PartidaGuardada p = GestorGuardado.cargarPartida("partida_guardada");
                if (p != null) 
                {
                    ventana.setPersonaje(p.getPersonaje());
                    ventana.setArbolNarrativo(p.getArbolNarrativo());
                    ventana.setInventario(p.getInventario());
                    ventana.mostrarPantallaExploracion();
                } 
                else JOptionPane.showMessageDialog(this, "Error al cargar la partida.");
            } 
            else JOptionPane.showMessageDialog(this, "No hay partidas guardadas.");
        });

        btnSalir.addActionListener(e -> System.exit(0));

        columna.add(btnNueva);
        columna.add(btnCargar);
        columna.add(btnSalir);
        centro.add(columna);
        add(centro, BorderLayout.CENTER);

        // SUR: footer con el nombre del autor
        JLabel footer = new JLabel("Nelson Filipe Fardilha Karlsson  --  DAM 2025", SwingConstants.CENTER);
        footer.setFont(new Font("SansSerif", Font.PLAIN, 11));
        footer.setForeground(new Color(70, 60, 100));
        footer.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        add(footer, BorderLayout.SOUTH);
    }
}