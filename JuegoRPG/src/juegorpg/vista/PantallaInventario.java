package juegorpg.vista;

import javax.swing.*;
import java.awt.*;
import juegorpg.modelo.item.*;

public class PantallaInventario extends JPanel 
{
    private VentanaPrincipal ventana;
    private JTextArea areaInventario;
    
    public PantallaInventario(VentanaPrincipal ventana) 
    {
        this.ventana = ventana;
        
        setLayout(new BorderLayout());
        
        JLabel titulo = new JLabel("Inventario", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        add(titulo, BorderLayout.NORTH);
        
        areaInventario = new JTextArea(20, 50);
        areaInventario.setEditable(false);
        areaInventario.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(areaInventario);
        add(scroll, BorderLayout.CENTER);
        
        JPanel panelBotones = new JPanel();
        JButton btnUsar = new JButton("Usar Item");
        JButton btnVender = new JButton("Vender Item");
        JButton btnVolver = new JButton("Volver");
        
        btnUsar.addActionListener(e -> 
        {
            String input = JOptionPane.showInputDialog(this, "Ingresa el número del item a usar:");
            if (input != null) 
            {
                try 
                {
                    int indice = Integer.parseInt(input) - 1;
                    Item item = ventana.getInventario().obtenerItem(indice);
                    
                    if (item != null && item instanceof Consumible) 
                    {
                        Consumible consumible = (Consumible) item;
                        String resultado = ventana.getPersonaje().usarConsumible(consumible);
                        ventana.getInventario().quitarItem(indice);
                        JOptionPane.showMessageDialog(this, resultado);
                        actualizarInventario();
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(this, "No puedes usar ese item");
                    }
                } 
                catch (Exception ex) 
                {
                    JOptionPane.showMessageDialog(this, "Número inválido");
                }
            }
        });
        
        btnVender.addActionListener(e -> 
        {
            String input = JOptionPane.showInputDialog(this, "Ingresa el número del item a vender:");
            if (input != null) 
            {
                try 
                {
                    int indice = Integer.parseInt(input) - 1;
                    Item item = ventana.getInventario().obtenerItem(indice);
            
                    if (item != null) 
                    {
                        int precio = item.getPrecio();
                        ventana.getPersonaje().setOro(ventana.getPersonaje().getOro() + precio);
                        ventana.getInventario().quitarItem(indice);
                        JOptionPane.showMessageDialog(this, "Vendido por " + precio + " oro");
                        actualizarInventario();
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(this, "Item inválido");
                    }
                } 
                catch (Exception ex) 
                {
                    JOptionPane.showMessageDialog(this, "Número inválido");
                }
            }
        });
        
        btnVolver.addActionListener(e -> 
        {
            ventana.mostrarPantallaExploracion();
        });
        
        panelBotones.add(btnUsar);
        panelBotones.add(btnVender);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);
        
        actualizarInventario();
    }
    
    private void actualizarInventario() 
    {
        StringBuilder sb = new StringBuilder();
        sb.append("=== INVENTARIO ===\n\n");
        sb.append(ventana.getPersonaje().toString()).append("\n\n");
        
        if (ventana.getInventario().estaVacio()) 
        {
            sb.append("El inventario está vacío\n");
        } 
        else 
        {
            for (int i = 0; i < ventana.getInventario().getCantidadActual(); i++) 
            {
                Item item = ventana.getInventario().obtenerItem(i);
                sb.append("[").append(i + 1).append("] ").append(item.toString()).append("\n");
            }
        }
        
        areaInventario.setText(sb.toString());
    }
}