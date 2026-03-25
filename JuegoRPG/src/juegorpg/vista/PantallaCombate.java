package juegorpg.vista;

import javax.swing.*;
import java.awt.*;
import juegorpg.modelo.enemigo.Enemigo;
import juegorpg.combate.SistemaCombate;

/**
 * Pantalla de combate por turnos. Se muestra cuando el nodo narrativo
 * actual tiene un enemigo asignado. Tiene dos areas de texto: una para
 * los stats del jugador y el enemigo (se actualiza cada turno), y otra
 * para el log de lo que va pasando en el combate. Los botones de accion
 * van en una fila horizontal abajo: Atacar, Habilidad, Inventario y Huir.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class PantallaCombate extends UITheme.GradientPanel 
{

    private VentanaPrincipal ventana;
    private SistemaCombate combate;

    /** Area que muestra los stats actualizados del jugador y el enemigo cada turno. */
    private JTextArea areaEstado;

    /** Log con el historial de lo que ha pasado en el combate. */
    private JTextArea areaLog;

    /**
     * Crea la pantalla de combate e inicializa el sistema de combate
     * con el personaje del jugador, el enemigo del nodo y el inventario.
     *
     * @param ventana referencia a la ventana principal para navegar y acceder al estado
     * @param enemigo enemigo contra el que se va a combatir en este nodo
     */
    public PantallaCombate(VentanaPrincipal ventana, Enemigo enemigo) 
    {
        this.ventana  = ventana;
        this.combate  = new SistemaCombate(ventana.getPersonaje(), enemigo, ventana.getInventario());
        setLayout(new BorderLayout());

        // NORTE: titulo de la pantalla
        JLabel titulo = UITheme.crearTitulo("-- COMBATE --");
        add(titulo, BorderLayout.NORTH);

        // CENTRO: stats del jugador y el enemigo
        areaEstado = new JTextArea(7, 0);
        areaEstado.setEditable(false);
        UITheme.estilizarAreaTexto(areaEstado);
        JScrollPane scrollEstado = new JScrollPane(areaEstado);
        UITheme.estilizarScrollPane(scrollEstado);
        add(scrollEstado, BorderLayout.CENTER);

        // SUR: log de combate + botones de accion
        areaLog = new JTextArea(8, 0);
        areaLog.setEditable(false);
        areaLog.setLineWrap(true);
        areaLog.setWrapStyleWord(true);
        UITheme.estilizarAreaTexto(areaLog);
        JScrollPane scrollLog = new JScrollPane(areaLog);
        UITheme.estilizarScrollPane(scrollLog);

        JPanel botones = new JPanel(new GridLayout(1, 4, 10, 0));
        botones.setOpaque(false);
        botones.setBorder(BorderFactory.createEmptyBorder(10, 18, 14, 18));

        JButton btnAtacar     = new JButton("Atacar");
        JButton btnHabilidad  = new JButton("Habilidad");
        JButton btnInventario = new JButton("Inventario");
        JButton btnHuir       = new JButton("Huir");

        UITheme.estilizarBoton(btnAtacar);
        UITheme.estilizarBoton(btnHabilidad);
        UITheme.estilizarBoton(btnInventario);
        UITheme.estilizarBotonPeligro(btnHuir);

        btnAtacar.addActionListener(e -> ejecutarTurno(1));
        btnHabilidad.addActionListener(e -> 
        {
            if (ventana.getPersonaje().getHabilidades().isEmpty())
                agregarLog("No tienes habilidades disponibles.");
            else
                ejecutarTurno(2);
        });
        btnInventario.addActionListener(e -> ventana.mostrarPantallaInventario());
        btnHuir.addActionListener(e -> 
        {
            int r = JOptionPane.showConfirmDialog(this,
                "Seguro que quieres huir? Volveras al menu principal.",
                "Huir", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) ventana.mostrarPantallaMenu();
        });

        botones.add(btnAtacar);
        botones.add(btnHabilidad);
        botones.add(btnInventario);
        botones.add(btnHuir);

        JPanel sur = new JPanel(new BorderLayout());
        sur.setOpaque(false);
        sur.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, UITheme.BORDE));
        sur.add(scrollLog, BorderLayout.CENTER);
        sur.add(botones,   BorderLayout.SOUTH);
        add(sur, BorderLayout.SOUTH);

        actualizarEstado();
        agregarLog(combate.iniciarCombate());
    }

    /**
     * Ejecuta un turno completo: primero el jugador segun la accion elegida,
     * luego comprueba si el enemigo murio, y si no, ejecuta el turno del enemigo
     * y comprueba si el jugador murio. Actualiza el estado y el log en cada paso.
     *
     * @param accion accion elegida por el jugador (1=atacar, 2=habilidad)
     */
    private void ejecutarTurno(int accion) 
    {
        agregarLog(combate.turnoJugador(accion));
        if (!combate.getEnemigo().estaVivo()) 
        {
            agregarLog("Victoria! Has derrotado al enemigo.");
            ventana.getArbolNarrativo().getNodoActual().setEnemigo(null);
            ventana.mostrarPantallaExploracion();
            return;
        }
        agregarLog(combate.turnoEnemigo());
        if (!combate.getJugador().estaVivo()) 
        {
            agregarLog("Has sido derrotado...");
            ventana.mostrarPantallaGameOver(false);
            return;
        }
        actualizarEstado();
    }

    /**
     * Refresca el area de stats con el estado actual del combate.
     * Se llama despues de cada turno para mantener los datos al dia.
     */
    private void actualizarEstado() 
    {
        areaEstado.setText(combate.obtenerEstadoCombate());
    }

    /**
     * Anade un mensaje al log de combate y hace scroll automatico al final.
     * Cada accion del jugador y del enemigo se registra aqui.
     *
     * @param msg mensaje a anadir al log
     */
    private void agregarLog(String msg) 
    {
        areaLog.append(msg + "\n");
        areaLog.setCaretPosition(areaLog.getDocument().getLength());
    }
}