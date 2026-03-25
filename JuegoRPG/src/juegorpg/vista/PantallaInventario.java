package juegorpg.vista;

import javax.swing.*;
import java.awt.*;
import juegorpg.modelo.item.*;

/**
 * Pantalla del inventario del jugador. Muestra todos los items que tiene,
 * los stats actuales del personaje, y permite usar o vender items.
 * Al usar un consumible se aplica el efecto y se quita del inventario.
 * Al vender cualquier item se suma el precio al oro del personaje.
 * El boton Volver regresa a la pantalla de exploracion.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class PantallaInventario extends UITheme.GradientPanel 
{

    private VentanaPrincipal ventana;

    /** Area de texto donde se muestra el inventario completo con los items numerados. */
    private JTextArea areaInventario;

    /**
     * Crea la pantalla del inventario y carga el estado actual del inventario.
     *
     * @param ventana referencia a la ventana principal para navegar y acceder al estado
     */
    public PantallaInventario(VentanaPrincipal ventana) 
    {
        this.ventana = ventana;
        setLayout(new BorderLayout());

        add(UITheme.crearTitulo("Inventario"), BorderLayout.NORTH);

        // CENTRO: lista de items con scroll
        areaInventario = new JTextArea();
        areaInventario.setEditable(false);
        UITheme.estilizarAreaTexto(areaInventario);

        JScrollPane scroll = new JScrollPane(areaInventario);
        UITheme.estilizarScrollPane(scroll);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
        add(scroll, BorderLayout.CENTER);

        // SUR: botones de accion
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 12));
        botones.setOpaque(false);
        botones.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, UITheme.BORDE));

        JButton btnUsar   = new JButton("Usar item");
        JButton btnVender = new JButton("Vender item");
        JButton btnVolver = new JButton("Volver");

        UITheme.estilizarBoton(btnUsar);
        UITheme.estilizarBoton(btnVender);
        UITheme.estilizarBotonPeligro(btnVolver);

        btnUsar.addActionListener(e -> 
        {
            String input = JOptionPane.showInputDialog(this, "Numero del item a usar:");
            if (input == null) return;
            try 
            {
                int idx = Integer.parseInt(input) - 1;
                Item item = ventana.getInventario().obtenerItem(idx);
                if (item instanceof Consumible) 
                {
                    String res = ventana.getPersonaje().usarConsumible((Consumible) item);
                    ventana.getInventario().quitarItem(idx);
                    JOptionPane.showMessageDialog(this, res);
                    actualizarInventario();
                } 
                else JOptionPane.showMessageDialog(this, "No puedes usar ese item.");
            } 
            catch (Exception ex) 
            { 
                JOptionPane.showMessageDialog(this, "Numero invalido."); 
            }
        });

        btnVender.addActionListener(e -> 
        {
            String input = JOptionPane.showInputDialog(this, "Numero del item a vender:");
            if (input == null) return;
            try 
            {
                int idx = Integer.parseInt(input) - 1;
                Item item = ventana.getInventario().obtenerItem(idx);
                if (item != null) 
                {
                    ventana.getPersonaje().setOro(ventana.getPersonaje().getOro() + item.getPrecio());
                    ventana.getInventario().quitarItem(idx);
                    JOptionPane.showMessageDialog(this, "Vendido por " + item.getPrecio() + " oro.");
                    actualizarInventario();
                } 
                else JOptionPane.showMessageDialog(this, "Item invalido.");
            } 
            catch (Exception ex) 
            { 
                JOptionPane.showMessageDialog(this, "Numero invalido."); 
            }
        });

        btnVolver.addActionListener(e -> ventana.mostrarPantallaExploracion());

        botones.add(btnUsar);
        botones.add(btnVender);
        botones.add(btnVolver);
        add(botones, BorderLayout.SOUTH);

        actualizarInventario();
    }

    /**
     * Reconstruye el texto del area de inventario con el estado actual.
     * Muestra los stats del personaje arriba y los items numerados debajo.
     * Se llama al entrar a la pantalla y despues de cada accion sobre un item.
     */
    private void actualizarInventario() 
    {
        StringBuilder sb = new StringBuilder();
        sb.append("=== INVENTARIO ===\n\n");
        sb.append(ventana.getPersonaje().toString()).append("\n\n");
        if (ventana.getInventario().estaVacio()) 
        {
            sb.append("El inventario esta vacio.\n");
        } 
        else 
        {
            for (int i = 0; i < ventana.getInventario().getCantidadActual(); i++) 
            {
                Item item = ventana.getInventario().obtenerItem(i);
                sb.append("[").append(i + 1).append("]  ").append(item.toString()).append("\n");
            }
        }
        areaInventario.setText(sb.toString());
        areaInventario.setCaretPosition(0);
    }
}