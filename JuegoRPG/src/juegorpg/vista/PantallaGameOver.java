package juegorpg.vista;

import javax.swing.*;
import java.awt.*;

/**
 * Pantalla de fin de partida. Se muestra tanto si el jugador gana como si pierde.
 * Si es victoria, muestra "VICTORIA" en verde y un mensaje segun el oro acumulado.
 * Si es derrota, muestra "DERROTA" en rojo. En ambos casos hay un boton
 * para volver al menu principal y empezar de nuevo.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class PantallaGameOver extends UITheme.GradientPanel 
{

    private VentanaPrincipal ventana;

    /**
     * Crea la pantalla de fin de partida segun el resultado.
     * Si es victoria y hay personaje disponible, calcula el mensaje
     * de oro segun cuanto acumulo durante la partida.
     *
     * @param ventana  referencia a la ventana principal para navegar al menu
     * @param victoria true si el jugador gano, false si fue derrotado
     */
    public PantallaGameOver(VentanaPrincipal ventana, boolean victoria) 
    {
        this.ventana = ventana;
        setLayout(new BorderLayout());

        // CENTRO: titulo grande y mensaje de oro si es victoria
        JPanel centro = new JPanel();
        centro.setOpaque(false);
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel(victoria ? "VICTORIA" : "DERROTA", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 56));
        lblTitulo.setForeground(victoria ? UITheme.EXITO : UITheme.PELIGRO);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(60, 0, 20, 0));
        centro.add(lblTitulo);

        // Mensaje de oro solo en victoria
        if (victoria && ventana.getPersonaje() != null) 
        {
            int oro = ventana.getPersonaje().getOro();
            String msgOro;
            if      (oro < 50)  msgOro = "Apenas juntaste " + oro + " monedas...";
            else if (oro < 100) msgOro = "Conseguiste " + oro + " monedas. No esta mal.";
            else if (oro < 200) msgOro = oro + " monedas. Buen trabajo.";
            else if (oro < 500) msgOro = "Increible! " + oro + " monedas.";
            else                msgOro = "Eres rico! " + oro + " monedas de oro!";

            JLabel lblOro = new JLabel(msgOro, SwingConstants.CENTER);
            lblOro.setFont(UITheme.FUENTE_SUBTITULO);
            lblOro.setForeground(UITheme.TEXTO_SECUNDARIO);
            lblOro.setAlignmentX(Component.CENTER_ALIGNMENT);
            lblOro.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            centro.add(lblOro);
        }

        add(centro, BorderLayout.CENTER);

        // SUR: boton para volver al menu
        JPanel sur = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 16));
        sur.setOpaque(false);
        JButton btnMenu = new JButton("Volver al menu");
        UITheme.estilizarBoton(btnMenu);
        btnMenu.addActionListener(e -> ventana.mostrarPantallaMenu());
        sur.add(btnMenu);
        add(sur, BorderLayout.SOUTH);
    }
}