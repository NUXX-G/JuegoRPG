package juegorpg.vista;
import java.awt.*;
import javax.swing.*;
import juegorpg.modelo.personaje.Personaje;
import juegorpg.narrativa.ArbolNarrativo;
import juegorpg.modelo.item.Inventario;
import juegorpg.modelo.item.Consumible;
import juegorpg.modelo.personaje.Arquero;
import juegorpg.modelo.personaje.Guerrero;
import juegorpg.modelo.personaje.Ladron;
import juegorpg.modelo.personaje.Mago;
public class PantallaSeleccionPersonaje extends JPanel
{
    private VentanaPrincipal ventana;
    private JTextArea areaDescripcion;
    
    public PantallaSeleccionPersonaje(VentanaPrincipal ventana)
    {
        this.ventana = ventana;
        
        setLayout(new BorderLayout());
        
        JLabel titulo = new JLabel("Selecciona tu personaje", SwingConstants.CENTER);
        titulo.setFont (new Font("Arial", Font.BOLD, 28));
        add(titulo, BorderLayout.NORTH);
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2, 2, 10, 10));
        
        JButton btnGuerrero = new JButton("Guerrero");
        JButton btnMago = new JButton("Mago");
        JButton btnArquero = new JButton("Arquero");
        JButton btnLadron = new JButton("Ladron");
        
        areaDescripcion = new JTextArea(5, 40);
        areaDescripcion.setEditable(false);
        areaDescripcion.setLineWrap(true);
        areaDescripcion.setWrapStyleWord(true);
        add(areaDescripcion, BorderLayout.SOUTH);
        btnGuerrero.addActionListener(e ->
        {
            Personaje guerrero = new Guerrero("Heroe");
            areaDescripcion.setText(guerrero.obtenerDescripcion() + "\n\n" + guerrero.toString());
            ventana.setPersonaje(guerrero);
            
            Inventario inv = new Inventario();
            inv.agregarItem(new Consumible("Pocion de Vida", "Restaura 50 HP", 10, 50, 0));
            inv.agregarItem(new Consumible("Pocion de Vida", "Restaura 50 HP", 10, 50, 0));
            inv.agregarItem(new Consumible("Pocion de Mana", "Restaura 30 MP", 10, 0, 30));
            ventana.setInventario(inv);
            
            ArbolNarrativo arbol = new ArbolNarrativo();
            arbol.iniciar();
            ventana.setArbolNarrativo(arbol);
            
            ventana.mostrarPantallaExploracion();
        });
        
        btnMago.addActionListener(e ->
        {
            Personaje mago = new Mago("Heroe");
            
            areaDescripcion.setText(mago.obtenerDescripcion() + "\n\n" + mago.toString());
            ventana.setPersonaje(mago);
            
            Inventario inv = new Inventario();
            inv.agregarItem(new Consumible("Pocion de Vida", "Restaura 50 HP", 10, 50, 0));
            inv.agregarItem(new Consumible("Pocion de Vida", "Restaura 50 HP", 10, 50, 0));
            inv.agregarItem(new Consumible("Pocion de Mana", "Restaura 30 MP", 10, 0, 30));
            ventana.setInventario(inv);
            
            ArbolNarrativo arbol = new ArbolNarrativo();
            arbol.iniciar();
            ventana.setArbolNarrativo(arbol);
            
            ventana.mostrarPantallaExploracion();
        });
        
        btnArquero.addActionListener(e ->
        {
            Personaje arquero = new Arquero("Heroe");
            
            areaDescripcion.setText(arquero.obtenerDescripcion() + "\n\n" + arquero.toString());
            ventana.setPersonaje(arquero);
            
            Inventario inv = new Inventario();
            inv.agregarItem(new Consumible("Pocion de Vida", "Restaura 50 HP", 10, 50, 0));
            inv.agregarItem(new Consumible("Pocion de Vida", "Restaura 50 HP", 10, 50, 0));
            inv.agregarItem(new Consumible("Pocion de Mana", "Restaura 30 MP", 10, 0, 30));
            ventana.setInventario(inv);
            
            ArbolNarrativo arbol = new ArbolNarrativo();
            arbol.iniciar();
            ventana.setArbolNarrativo(arbol);
            
            ventana.mostrarPantallaExploracion();
        });
        
        btnLadron.addActionListener(e ->
        {
            Personaje ladron = new Ladron("Heroe");
            
            areaDescripcion.setText(ladron.obtenerDescripcion() + "\n\n" + ladron.toString());
            ventana.setPersonaje(ladron);
            
            Inventario inv = new Inventario();
            inv.agregarItem(new Consumible("Pocion de Vida", "Restaura 50 HP", 10, 50, 0));
            inv.agregarItem(new Consumible("Pocion de Vida", "Restaura 50 HP", 10, 50, 0));
            inv.agregarItem(new Consumible("Pocion de Mana", "Restaura 30 MP", 10, 0, 30));
            ventana.setInventario(inv);
            
            ArbolNarrativo arbol = new ArbolNarrativo();
            arbol.iniciar();
            ventana.setArbolNarrativo(arbol);
            
            ventana.mostrarPantallaExploracion();
        });   
        
        panelBotones.add(btnGuerrero);
        panelBotones.add(btnMago);
        panelBotones.add(btnArquero);
        panelBotones.add(btnLadron);
        add(panelBotones, BorderLayout.CENTER);
    }
}