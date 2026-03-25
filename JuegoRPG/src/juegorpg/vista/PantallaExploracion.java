package juegorpg.vista;

import javax.swing.*;
import java.awt.*;
import juegorpg.narrativa.*;
import juegorpg.guardado.*;

/**
 * Pantalla de exploracion narrativa. Es donde el jugador pasa la mayor
 * parte del tiempo tomando decisiones. Muestra el texto del nodo actual
 * en el centro y los botones de decision abajo. Si el nodo tiene enemigo,
 * navega automaticamente al combate. Si es un nodo final, navega al game over.
 * Arriba a la derecha tiene los botones de inventario y guardar partida.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class PantallaExploracion extends UITheme.GradientPanel {

    private VentanaPrincipal ventana;

    /** Area donde se muestra el texto narrativo del nodo actual. */
    private JTextArea areaNarrativa;

    /** Panel donde se generan dinamicamente los botones de decision. */
    private JPanel panelOpciones;

    /**
     * Crea la pantalla de exploracion y carga el nodo actual del arbol narrativo.
     *
     * @param ventana referencia a la ventana principal para navegar y acceder al estado
     */
    public PantallaExploracion(VentanaPrincipal ventana) 
    {
        this.ventana = ventana;
        setLayout(new BorderLayout());

        // NORTE: barra con botones de inventario y guardar
        JPanel norte = new JPanel(new FlowLayout(FlowLayout.RIGHT, 14, 10));
        norte.setOpaque(false);
        norte.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, UITheme.BORDE));

        JButton btnInventario = new JButton("Inventario");
        JButton btnGuardar    = new JButton("Guardar");
        UITheme.estilizarBoton(btnInventario);
        UITheme.estilizarBoton(btnGuardar);

        btnInventario.addActionListener(e -> ventana.mostrarPantallaInventario());
        btnGuardar.addActionListener(e -> 
        {
            PartidaGuardada p = new PartidaGuardada(
                "Jugador", ventana.getPersonaje(),
                ventana.getArbolNarrativo(), ventana.getInventario()
            );
            GestorGuardado.guardarPartida(p, "partida_guardada");
            JOptionPane.showMessageDialog(this, "Partida guardada.");
        });

        norte.add(btnInventario);
        norte.add(btnGuardar);
        add(norte, BorderLayout.NORTH);

        // CENTRO: texto narrativo del nodo
        areaNarrativa = new JTextArea();
        areaNarrativa.setEditable(false);
        areaNarrativa.setLineWrap(true);
        areaNarrativa.setWrapStyleWord(true);
        UITheme.estilizarAreaTexto(areaNarrativa);

        JScrollPane scroll = new JScrollPane(areaNarrativa);
        UITheme.estilizarScrollPane(scroll);
        scroll.setBorder(BorderFactory.createEmptyBorder(12, 18, 12, 18));
        add(scroll, BorderLayout.CENTER);

        // SUR: botones de decision generados dinamicamente
        panelOpciones = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 14));
        panelOpciones.setOpaque(false);
        panelOpciones.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, UITheme.BORDE));
        add(panelOpciones, BorderLayout.SOUTH);

        actualizarPantalla();
    }

    /**
     * Actualiza la pantalla con el nodo actual del arbol narrativo.
     * Si el nodo tiene enemigo, navega al combate automaticamente.
     * Si es un nodo final, navega al game over. Si no, muestra el texto
     * y genera los botones de decision del nodo.
     */
    private void actualizarPantalla() 
    {
        Nodo nodo = ventana.getArbolNarrativo().getNodoActual();
        areaNarrativa.setText(nodo.getDescripcion());

        if (nodo.tieneCombate()) 
        { 
            ventana.mostrarPantallaCombate(nodo.getEnemigo()); return; 
        }
        if (nodo.isEsNodoFinal()) 
        { 
            ventana.mostrarPantallaGameOver(true); return; 
        }

        // Generar botones de decision para las opciones del nodo
        panelOpciones.removeAll();
        for (int i = 0; i < nodo.getOpciones().size(); i++) 
        {
            final int idx = i;
            JButton btn = new JButton(nodo.getOpciones().get(i).getTexto());
            UITheme.estilizarBoton(btn);
            btn.addActionListener(e -> 
            {
                ventana.getArbolNarrativo().avanzar(idx);
                actualizarPantalla();
            });
            panelOpciones.add(btn);
        }
        panelOpciones.revalidate();
        panelOpciones.repaint();
    }
}