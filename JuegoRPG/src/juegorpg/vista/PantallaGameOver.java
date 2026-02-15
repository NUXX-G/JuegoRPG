package juegorpg.vista;

import javax.swing.*;
import java.awt.*;

public class PantallaGameOver extends JPanel 
{
    private VentanaPrincipal ventana;
    
    public PantallaGameOver(VentanaPrincipal ventana, boolean victoria) 
    {
        this.ventana = ventana;
        
        setLayout(new BorderLayout());
        
        String mensaje = victoria ? "¡VICTORIA!" : "DERROTA";
        JLabel lblMensaje = new JLabel(mensaje, SwingConstants.CENTER);
        lblMensaje.setFont(new Font("Arial", Font.BOLD, 48));
        
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        lblMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentro.add(lblMensaje);
        
        if (victoria && ventana.getPersonaje() != null) 
        {
            int oro = ventana.getPersonaje().getOro();
            String mensajeOro = "";
            
            if (oro < 50) 
            {
                mensajeOro = "Apenas juntaste " + oro + " monedas...";
            } 
            else if (oro < 100) 
            {
                mensajeOro = "Conseguiste " + oro + " monedas. No está mal.";
            } 
            else if (oro < 200) 
            {
                mensajeOro = "¡" + oro + " monedas! Buen trabajo.";
            } 
            else if (oro < 500) 
            {
                mensajeOro = "¡Increíble! Tienes " + oro + " monedas.";
            } 
            else 
            {
                mensajeOro = "¡ERES RICO! " + oro + " monedas de oro!";
            }
            
            JLabel lblOro = new JLabel(mensajeOro, SwingConstants.CENTER);
            lblOro.setFont(new Font("Arial", Font.PLAIN, 20));
            lblOro.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelCentro.add(Box.createVerticalStrut(20));
            panelCentro.add(lblOro);
        }
        
        add(panelCentro, BorderLayout.CENTER);
        
        JButton btnMenu = new JButton("Volver al Menú");
        btnMenu.addActionListener(e -> 
        {
            ventana.mostrarPantallaMenu();
        });
        
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnMenu);
        add(panelBoton, BorderLayout.SOUTH);
    }
}