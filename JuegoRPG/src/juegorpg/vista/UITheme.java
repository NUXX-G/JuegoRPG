package juegorpg.vista;

import java.awt.*;
import java.io.InputStream;
import javax.swing.*;

/**
 * Tema visual centralizado del juego. Negro y verde retro, estilo terminal.
 * Aqui estan todos los colores, fuentes y metodos helper de estilo.
 * Si quieres cambiar algo visual, lo cambias aqui y afecta a todo el juego.
 * No hace falta instanciar esta clase, todo es estatico.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class UITheme 
{

    // ── COLORES ──────────────────────────────────────────────────────────────

    /** Fondo principal, negro puro. */
    public static final Color FONDO_OSCURO     = new Color(0, 0, 0);

    /** Fondo secundario, negro con un toque verdoso muy sutil. */
    public static final Color FONDO_MEDIO      = new Color(5, 12, 5);

    /** Color de acento principal, verde terminal clasico. */
    public static final Color ACENTO           = new Color(0, 200, 50);

    /** Verde mas brillante para el hover de botones. */
    public static final Color ACENTO_HOVER     = new Color(80, 255, 100);

    /** Color del texto principal, verde ligeramente mas claro que el acento. */
    public static final Color TEXTO_PRINCIPAL  = new Color(0, 210, 55);

    /** Color del texto secundario, verde oscuro para textos menos importantes. */
    public static final Color TEXTO_SECUNDARIO = new Color(0, 120, 30);

    /** Color de los bordes de paneles y botones, verde oscuro. */
    public static final Color BORDE            = new Color(0, 100, 20);

    /** Fondo de las areas de texto y logs, negro puro. */
    public static final Color LOG_FONDO        = new Color(0, 0, 0);

    /** Color de peligro, rojo para el boton de huir, salir y game over. */
    public static final Color PELIGRO          = new Color(200, 30, 30);

    /** Color de exito, verde brillante para la pantalla de victoria. */
    public static final Color EXITO            = new Color(0, 255, 80);

    // ── FUENTES ──────────────────────────────────────────────────────────────

    /**
     * Fuente pixel art cargada desde el archivo .ttf en resources/.
     * Si no se encuentra el archivo, cae a Monospaced como fallback.
     */
    private static final Font PIXEL_BASE = cargarFuentePixel();

    /** Fuente grande para titulos de pantalla. */
    public static final Font FUENTE_TITULO    = PIXEL_BASE.deriveFont(Font.PLAIN, 18f);

    /** Fuente mediana para subtitulos y taglines. */
    public static final Font FUENTE_SUBTITULO = PIXEL_BASE.deriveFont(Font.PLAIN, 10f);

    /** Fuente para el texto de los botones. */
    public static final Font FUENTE_BOTON     = PIXEL_BASE.deriveFont(Font.PLAIN, 9f);

    /** Fuente para texto general de la interfaz. */
    public static final Font FUENTE_TEXTO     = PIXEL_BASE.deriveFont(Font.PLAIN, 9f);

    /** Fuente pequeña para el log de combate y areas de texto monoespaciadas. */
    public static final Font FUENTE_MONO      = PIXEL_BASE.deriveFont(Font.PLAIN, 8f);

    // ── CARGA DE FUENTE ──────────────────────────────────────────────────────

    /**
     * Intenta cargar la fuente Press Start 2P desde resources/.
     * Si no la encuentra o hay algun error, devuelve Monospaced Bold
     * como fallback para que el juego siga funcionando igual.
     *
     * @return la fuente pixel art o Monospaced si no se pudo cargar
     */
    private static Font cargarFuentePixel() 
    {
        try 
        {
            InputStream is = UITheme.class.getResourceAsStream("/resources/PressStart2P-Regular.ttf");
            if (is != null) 
            {
                Font f = Font.createFont(Font.TRUETYPE_FONT, is);
                GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(f);
                return f;
            }
        } 
        catch (Exception e) 
        {
            System.err.println("[UITheme] No se pudo cargar PressStart2P, usando Monospaced: " + e.getMessage());
        }
        return new Font("Monospaced", Font.BOLD, 12);
    }

    // ── HELPERS ──────────────────────────────────────────────────────────────

    /**
     * Crea un JLabel con el estilo de titulo principal.
     * Verde acento, fuente grande, centrado, con padding vertical.
     *
     * @param texto texto del titulo
     * @return JLabel ya configurado con el estilo de titulo
     */
    public static JLabel crearTitulo(String texto) 
    {
        JLabel l = new JLabel(texto, SwingConstants.CENTER);
        l.setFont(FUENTE_TITULO);
        l.setForeground(ACENTO);
        l.setBorder(BorderFactory.createEmptyBorder(22, 0, 6, 0));
        return l;
    }

    /**
     * Crea un JLabel con el estilo de separador o subtitulo.
     * Verde secundario, fuente mediana, centrado.
     *
     * @param texto texto del separador
     * @return JLabel ya configurado con el estilo de separador
     */
    public static JLabel crearSeparador(String texto) 
    {
        JLabel l = new JLabel(texto, SwingConstants.CENTER);
        l.setFont(FUENTE_SUBTITULO);
        l.setForeground(TEXTO_SECUNDARIO);
        l.setBorder(BorderFactory.createEmptyBorder(4, 0, 18, 0));
        return l;
    }

    /**
     * Aplica el estilo retro verde al boton dado.
     * Fondo negro, texto verde, borde verde oscuro grueso.
     * Incluye el efecto hover: al pasar el cursor el borde y el texto
     * se vuelven mas brillantes.
     *
     * @param btn boton al que aplicar el estilo
     */
    public static void estilizarBoton(JButton btn) 
    {
        btn.setBackground(FONDO_OSCURO);
        btn.setForeground(TEXTO_PRINCIPAL);
        btn.setFont(FUENTE_BOTON);
        btn.setFocusPainted(false);
        btn.setBorderPainted(true);
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDE, 3),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            public void mouseEntered(java.awt.event.MouseEvent e) 
            {
                btn.setBackground(new Color(0, 30, 8));
                btn.setForeground(ACENTO_HOVER);
                btn.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(ACENTO, 3),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
                ));
            }
            public void mouseExited(java.awt.event.MouseEvent e) 
            {
                btn.setBackground(FONDO_OSCURO);
                btn.setForeground(TEXTO_PRINCIPAL);
                btn.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(BORDE, 3),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
                ));
            }
        });
    }

    /**
     * Aplica el estilo de peligro al boton dado.
     * Igual que estilizarBoton pero en rojo en vez de verde.
     * Se usa para los botones de Huir, Salir y Volver.
     *
     * @param btn boton al que aplicar el estilo de peligro
     */
    public static void estilizarBotonPeligro(JButton btn) 
    {
        estilizarBoton(btn);
        btn.setForeground(PELIGRO);
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 15, 15), 3),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        btn.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            public void mouseEntered(java.awt.event.MouseEvent e) 
            {
                btn.setBackground(new Color(30, 0, 0));
                btn.setForeground(new Color(255, 80, 80));
                btn.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(PELIGRO, 3),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
                ));
            }
            public void mouseExited(java.awt.event.MouseEvent e) 
            {
                btn.setBackground(FONDO_OSCURO);
                btn.setForeground(PELIGRO);
                btn.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(100, 15, 15), 3),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
                ));
            }
        });
    }

    /**
     * Aplica el estilo retro a un JTextArea.
     * Fondo negro, texto verde, fuente mono, sin borde propio.
     *
     * @param area area de texto a estilizar
     */
    public static void estilizarAreaTexto(JTextArea area) 
    {
        area.setBackground(LOG_FONDO);
        area.setForeground(TEXTO_PRINCIPAL);
        area.setFont(FUENTE_MONO);
        area.setCaretColor(ACENTO);
        area.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
    }

    /**
     * Aplica el estilo retro a un JScrollPane.
     * Borde verde oscuro grueso y fondo negro en el viewport.
     *
     * @param scroll scroll pane a estilizar
     */
    public static void estilizarScrollPane(JScrollPane scroll) 
    {
        scroll.setBorder(BorderFactory.createLineBorder(BORDE, 3));
        scroll.getViewport().setBackground(LOG_FONDO);
    }

    // ── PANEL CON EFECTO CRT ─────────────────────────────────────────────────

    /**
     * Panel con fondo negro y scanlines sutiles tipo pantalla CRT.
     * Todas las pantallas del juego extienden esta clase en vez de JPanel
     * para tener el fondo retro automaticamente.
     * Las lineas horizontales cada 3px con opacidad baja dan el efecto
     * de monitor viejo sin ser agresivo.
     */
    public static class GradientPanel extends JPanel 
    {

        /**
         * Crea el panel con fondo negro como color base.
         */
        public GradientPanel() 
        {
            setBackground(FONDO_OSCURO);
        }

        /**
         * Pinta el fondo negro y encima las scanlines CRT.
         * Se llama automaticamente por Swing al redibujar el panel.
         *
         * @param g contexto grafico de Swing
         */
        @Override
        protected void paintComponent(Graphics g) 
        {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(new Color(0, 0, 0, 18));
            for (int y = 0; y < getHeight(); y += 3)
                g2.drawLine(0, y, getWidth(), y);
            g2.dispose();
        }
    }
}