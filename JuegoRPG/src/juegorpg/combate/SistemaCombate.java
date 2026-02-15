package juegorpg.combate;

import juegorpg.modelo.item.Arma;
import juegorpg.modelo.item.Armadura;
import juegorpg.modelo.item.Inventario;
import juegorpg.rng.GeneradorRNG;
import juegorpg.modelo.enemigo.Enemigo;
import juegorpg.modelo.personaje.Personaje;

public class SistemaCombate 
{
    private Personaje jugador;
    private Enemigo enemigo;
    private int turnoActual;
    private Inventario inventario;
    
    public SistemaCombate(Personaje jugador, Enemigo enemigo, Inventario inventario)
    {
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.inventario = inventario;
        turnoActual = 1;
    }
    
    public String iniciarCombate()
    {
        String mensajeCombate ="¡Combate iniciado contra " + enemigo.getNombre() + "!";
        
        return mensajeCombate;
    }
    
    public String turnoJugador(int accion)
    {
        String turnoJugador = "";
        
        switch (accion)
        {
            case 1:
              enemigo.recibirDanio(jugador.calcularDanioAtaque());
              turnoJugador = jugador.getNombre() + " ha hecho un ataque normal.";
              break;
            case 2:
               enemigo.recibirDanio(jugador.obtenerHabilidad(0).calcularDanio(jugador.getAtaque()));
               turnoJugador = jugador.getNombre() + " ha usado la habilidad " + jugador.obtenerHabilidad(0).getNombre();
               break;
            case 3:
               turnoJugador = jugador.getNombre() + " ha huido.";
               break;
            default:
                turnoJugador = "Accion invalida";
                break;
        }
        
        if (!enemigo.estaVivo()) 
        {
            jugador.ganarExperiencia(enemigo.getExperienciaAlMorir());
            jugador.setOro(jugador.getOro() + enemigo.getOroAlMorir());
    
            String mensajeVictoria = jugador.getNombre() + " ha matado al " + enemigo.getNombre() + 
                            " ha conseguido " + enemigo.getExperienciaAlMorir() + 
                            " experiencia y " + enemigo.getOroAlMorir() + " de oro";
    
            int drop = GeneradorRNG.entero(1, 10);
    
            if (drop <= 3) 
            {
                Arma arma = generarArmaAleatoria(enemigo.getNivel());
                mensajeVictoria += "\n¡Has obtenido: " + arma.getNombre() + "!";
            } 
            else if (drop <= 6) 
            {
                Armadura armadura = generarArmaduraAleatoria(enemigo.getNivel());
                mensajeVictoria += "\n¡Has obtenido: " + armadura.getNombre() + "!";
            }
    
            turnoJugador = mensajeVictoria;
        }
        
        turnoActual++;
        
        return turnoJugador;
    }
    
    public String turnoEnemigo()
    {
        String turnoEnemigo = "El " + enemigo.getNombre() + " ha atacado";
        jugador.recibirDanio(enemigo.calcularDanioAtaque());
        turnoActual++;
        
        return turnoEnemigo;
    }
    
    public boolean combateTerminado()
    {
        boolean combateTerminado = false;
        
        if (!jugador.estaVivo() || !enemigo.estaVivo()) 
        {
            combateTerminado = true;
        }
        
        return combateTerminado;
    }
    
    public String obtenerEstadoCombate()
    {
        String estadoCombate = "=== TURNO " + turnoActual + " ===" + "\n" +
                              jugador.toString() + "\n" +
                              "VS" + "\n" +
                              enemigo.toString() + "\n"+
                              "=========================";
        return estadoCombate;
    }
    
    private Arma generarArmaAleatoria(int nivel) 
    {
        String[] nombres = {"Espada", "Hacha", "Lanza", "Daga", "Martillo"};
        String nombre = nombres[GeneradorRNG.entero(0, nombres.length - 1)];
        int bonus = nivel * GeneradorRNG.entero(2, 5);
    
        Arma arma = new Arma(nombre + " +" + bonus, "Arma encontrada", nivel * 10, bonus);
        inventario.agregarItem(arma);
        return arma;
    }

    private Armadura generarArmaduraAleatoria(int nivel) 
    {
        String[] nombres = {"Casco", "Peto", "Botas", "Guantes", "Escudo"};
        String nombre = nombres[GeneradorRNG.entero(0, nombres.length - 1)];
        int bonus = nivel * GeneradorRNG.entero(1, 3);
    
        Armadura armadura = new Armadura(nombre + " +" + bonus, "Armadura encontrada", nivel * 10, bonus);
        inventario.agregarItem(armadura);
        return armadura;
    }

    public Personaje getJugador() 
    {
        return jugador;
    }

    public Enemigo getEnemigo() 
    {
        return enemigo;
    }

    public int getTurnoActual() 
    {
        return turnoActual;
    }
}
