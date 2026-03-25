package juegorpg.vista;

import java.awt.*;
import javax.swing.*;
import juegorpg.modelo.personaje.*;
import juegorpg.narrativa.ArbolNarrativo;
import juegorpg.modelo.item.*;

/**
 * Pantalla de seleccion de personaje.
 * Muestra los 4 botones de clase en un grid 2x2. Al pasar el cursor
 * por encima de cada boton, el area de descripcion de abajo muestra
 * los stats y la descripcion de esa clase sin necesidad de hacer click.
 * Al hacer click, selecciona la clase, inicializa el inventario y el arbol
 * narrativo, y navega a la exploracion.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class PantallaSeleccionPersonaje extends UITheme.GradientPanel 
{

    private VentanaPrincipal ventana;

    /** Area de texto que muestra la descripcion de la clase al hacer hover. */
    private JTextArea areaDescripcion;

    /**
     * Crea la pantalla de seleccion con el grid de clases y el area de descripcion.
     *
     * @param ventana referencia a la ventana principal para navegar y guardar estado
     */
    public PantallaSeleccionPersonaje(VentanaPrincipal ventana) 
    {
        this.ventana = ventana;
        setLayout(new BorderLayout());

        // NORTE: titulo
        JLabel titulo = UITheme.crearTitulo("Selecciona tu personaje");
        add(titulo, BorderLayout.NORTH);

        // CENTRO: grid 2x2 con los 4 botones de clase
        JPanel grid = new JPanel(new GridLayout(2, 2, 12, 12));
        grid.setOpaque(false);
        grid.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        JButton btnGuerrero = new JButton("Guerrero");
        JButton btnMago     = new JButton("Mago");
        JButton btnArquero  = new JButton("Arquero");
        JButton btnLadron   = new JButton("Ladron");

        for (JButton b : new JButton[]{btnGuerrero, btnMago, btnArquero, btnLadron})
            UITheme.estilizarBoton(b);

        // Hover: muestra la descripcion sin navegar todavia
        btnGuerrero.addMouseListener(hover(new Guerrero("Heroe")));
        btnMago.addMouseListener(hover(new Mago("Heroe")));
        btnArquero.addMouseListener(hover(new Arquero("Heroe")));
        btnLadron.addMouseListener(hover(new Ladron("Heroe")));

        // Click: selecciona la clase y navega a la exploracion
        btnGuerrero.addActionListener(e -> seleccionar(new Guerrero("Heroe")));
        btnMago.addActionListener(e     -> seleccionar(new Mago("Heroe")));
        btnArquero.addActionListener(e  -> seleccionar(new Arquero("Heroe")));
        btnLadron.addActionListener(e   -> seleccionar(new Ladron("Heroe")));

        grid.add(btnGuerrero);
        grid.add(btnMago);
        grid.add(btnArquero);
        grid.add(btnLadron);
        add(grid, BorderLayout.CENTER);

        // SUR: area de descripcion con scroll
        areaDescripcion = new JTextArea(5, 0);
        areaDescripcion.setEditable(false);
        areaDescripcion.setLineWrap(true);
        areaDescripcion.setWrapStyleWord(true);
        areaDescripcion.setText("Pasa el cursor sobre un personaje para ver su descripcion.");
        UITheme.estilizarAreaTexto(areaDescripcion);

        JScrollPane scroll = new JScrollPane(areaDescripcion);
        UITheme.estilizarScrollPane(scroll);
        scroll.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, UITheme.BORDE));
        add(scroll, BorderLayout.SOUTH);
    }

    /**
     * Crea un MouseAdapter que muestra la descripcion del personaje dado
     * en el area de descripcion al pasar el cursor por encima del boton.
     *
     * @param p personaje cuya descripcion se mostrara en el hover
     * @return MouseAdapter configurado para el hover
     */
    private java.awt.event.MouseAdapter hover(Personaje p) 
    {
        return new java.awt.event.MouseAdapter() 
        {
            public void mouseEntered(java.awt.event.MouseEvent e) 
            {
                areaDescripcion.setText(p.obtenerDescripcion() + "\n\n" + p.toString());
            }
        };
    }

    /**
     * Selecciona un personaje, inicializa su inventario de inicio con las
     * pociones base, crea el arbol narrativo y navega a la exploracion.
     *
     * @param p personaje seleccionado por el jugador
     */
    private void seleccionar(Personaje p) 
    {
        ventana.setPersonaje(p);
        Inventario inv = new Inventario();
        inv.agregarItem(new Consumible("Pocion de Vida",  "Restaura 50 HP", 10, 50, 0));
        inv.agregarItem(new Consumible("Pocion de Vida",  "Restaura 50 HP", 10, 50, 0));
        inv.agregarItem(new Consumible("Pocion de Mana",  "Restaura 30 MP", 10, 0, 30));
        ventana.setInventario(inv);
        ArbolNarrativo arbol = new ArbolNarrativo();
        arbol.iniciar();
        ventana.setArbolNarrativo(arbol);
        ventana.mostrarPantallaExploracion();
    }
}