package juegorpg.combate;

import juegorpg.modelo.enemigo.Enemigo;
import juegorpg.modelo.personaje.Personaje;

public class SistemaCombate 
{
    private Personaje jugador;
    private Enemigo enemigo;
    private int turnoActual;
    
    public SistemaCombate(Personaje jugador, Enemigo enemigo)
    {
        this.jugador = jugador;
        this.enemigo = enemigo;
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
            jugador.setExperiencia(jugador.getExperiencia() + enemigo.getExperienciaAlMorir());
            jugador.setOro(jugador.getOro() + enemigo.getOroAlMorir());
            turnoJugador = jugador.getNombre() + " ha matado al " + enemigo.getNombre() + " ha conseguido " + enemigo.getExperienciaAlMorir() + " experiencia y " + enemigo.getOroAlMorir() + " de oro";
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
