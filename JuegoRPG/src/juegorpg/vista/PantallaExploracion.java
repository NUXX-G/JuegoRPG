package juegorpg.vista;

import javax.swing.*;
import java.awt.*;
import juegorpg.narrativa.*;
import juegorpg.guardado.*;

public class PantallaExploracion extends JPanel 
{
    private VentanaPrincipal ventana;
    private JTextArea areaNarrativa;
    private JPanel panelOpciones;
    
    public PantallaExploracion(VentanaPrincipal ventana) 
    {
        this.ventana = ventana;
        
        setLayout(new BorderLayout());
        
        JPanel panelSuperior = new JPanel();
        JButton btnInventario = new JButton("Inventario");
        JButton btnGuardar = new JButton("Guardar Partida");
        
        btnInventario.addActionListener(e -> 
        {
            ventana.mostrarPantallaInventario();
        });
        
        btnGuardar.addActionListener(e -> 
        {
            PartidaGuardada partida = new PartidaGuardada(
                "Jugador",
                ventana.getPersonaje(),
                ventana.getArbolNarrativo(),
                ventana.getInventario()
            );
            GestorGuardado.guardarPartida(partida, "partida_guardada");
            JOptionPane.showMessageDialog(this, "Partida guardada correctamente");
        });
        
        panelSuperior.add(btnInventario);
        panelSuperior.add(btnGuardar);
        add(panelSuperior, BorderLayout.NORTH);
        
        areaNarrativa = new JTextArea(15, 50);
        areaNarrativa.setEditable(false);
        areaNarrativa.setLineWrap(true);
        areaNarrativa.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(areaNarrativa);
        add(scroll, BorderLayout.CENTER);
        
        panelOpciones = new JPanel();
        add(panelOpciones, BorderLayout.SOUTH);
        
        actualizarPantalla();
    }
    
    private void actualizarPantalla() 
    {
        Nodo nodoActual = ventana.getArbolNarrativo().getNodoActual();
        
        areaNarrativa.setText(nodoActual.getDescripcion());
        
        if (nodoActual.tieneCombate()) 
        {
            ventana.mostrarPantallaCombate(nodoActual.getEnemigo());
            return;
        }
        
        if (nodoActual.isEsNodoFinal()) 
        {
            ventana.mostrarPantallaGameOver(true);
            return;
        }
        
        panelOpciones.removeAll();
        
        for (int i = 0; i < nodoActual.getOpciones().size(); i++) 
        {
            final int indice = i;
            
            Opcion opcion = nodoActual.getOpciones().get(i);
            
            JButton btnOpcion = new JButton(opcion.getTexto());
            
            btnOpcion.addActionListener(e -> 
            {
                ventana.getArbolNarrativo().avanzar(indice);
                
                actualizarPantalla();
            });
            
            panelOpciones.add(btnOpcion);
        }
        
        panelOpciones.revalidate();
        panelOpciones.repaint();
    }
}