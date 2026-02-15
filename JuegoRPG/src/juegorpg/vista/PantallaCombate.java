package juegorpg.vista;

import javax.swing.*;
import java.awt.*;
import juegorpg.modelo.enemigo.Enemigo;
import juegorpg.combate.SistemaCombate;

public class PantallaCombate extends JPanel 
{
    private VentanaPrincipal ventana;
    private SistemaCombate combate;
    private JTextArea areaEstado;
    private JTextArea areaLog;
    
    public PantallaCombate(VentanaPrincipal ventana, Enemigo enemigo) 
    {
        this.ventana = ventana;
        this.combate = new SistemaCombate(ventana.getPersonaje(), enemigo, ventana.getInventario());
        
        setLayout(new BorderLayout());
        
        JLabel titulo = new JLabel("¡COMBATE!", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 32));
        add(titulo, BorderLayout.NORTH);
        
        areaEstado = new JTextArea(8, 50);
        areaEstado.setEditable(false);
        areaEstado.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollEstado = new JScrollPane(areaEstado);
        add(scrollEstado, BorderLayout.CENTER);
        
        areaLog = new JTextArea(10, 50);
        areaLog.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(areaLog);
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2, 2, 10, 10));
        
        JButton btnAtacar = new JButton("Atacar");
        JButton btnHabilidad = new JButton("Usar Habilidad");
        JButton btnInventario = new JButton("Inventario");
        JButton btnHuir = new JButton("Huir");
        
        btnAtacar.addActionListener(e -> 
        {
            ejecutarTurno(1);
        });
        
        btnHabilidad.addActionListener(e -> 
        {
            if (ventana.getPersonaje().getHabilidades().isEmpty()) {
                agregarLog("No tienes habilidades disponibles");
            } 
            else 
            {
                ejecutarTurno(2);
            }
        });
        
        btnInventario.addActionListener(e -> 
        {
            ventana.mostrarPantallaInventario();
        });
        
        btnHuir.addActionListener(e -> 
        {
            int confirmacion = JOptionPane.showConfirmDialog
            (
                this, 
                "¿Seguro que quieres huir? Volverás al menú principal", 
                "Huir", 
                JOptionPane.YES_NO_OPTION
            );
    
            if (confirmacion == JOptionPane.YES_OPTION) 
            {
                ventana.mostrarPantallaMenu();
            }
        });
        
        panelBotones.add(btnAtacar);
        panelBotones.add(btnHabilidad);
        panelBotones.add(btnInventario);
        panelBotones.add(btnHuir);
        
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(scrollLog, BorderLayout.CENTER);
        panelInferior.add(panelBotones, BorderLayout.SOUTH);
        
        add(panelInferior, BorderLayout.SOUTH);
        
        actualizarEstado();
        agregarLog(combate.iniciarCombate());
    }
    
    private void ejecutarTurno(int accion) 
    {
        String resultadoJugador = combate.turnoJugador(accion);
        agregarLog(resultadoJugador);
        
        if (!combate.getEnemigo().estaVivo()) 
        {
            agregarLog("¡Victoria! Has derrotado al enemigo");
            ventana.getArbolNarrativo().getNodoActual().setEnemigo(null);
            ventana.mostrarPantallaExploracion();
            return;
        }
        
        // Turno del enemigo
        String resultadoEnemigo = combate.turnoEnemigo();
        agregarLog(resultadoEnemigo);
        
        if (!combate.getJugador().estaVivo()) 
        {
            agregarLog("Has sido derrotado...");
            ventana.mostrarPantallaGameOver(false);
            return;
        }
        
        actualizarEstado();
    }
    
    private void actualizarEstado() 
    {
        areaEstado.setText(combate.obtenerEstadoCombate());
    }
    
    private void agregarLog(String mensaje) 
    {
        areaLog.append(mensaje + "\n");
        areaLog.setCaretPosition(areaLog.getDocument().getLength());
    }
}