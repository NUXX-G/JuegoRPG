package juegorpg.modelo.enemigo;

/**
 * El Dragon. Enemigo de dificultad 3 con mecanica de furia por turnos.
 * Cada 3 turnos desata un ataque que hace el triple de danio.
 * Hay que contarlos para saber cuando viene el golpe gordo y tener
 * pociones listas. Dificultad 3.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class Dragon extends Enemigo 
{

    /**
     * Contador de turnos que lleva el dragon en combate.
     * Cada 3 turnos se resetea y desata su ataque de furia.
     */
    private int turnosEnfurecido;

    /**
     * Crea un Dragon escalado al nivel indicado.
     * Stats altos y mecanica de burst cada 3 turnos.
     *
     * @param nivel nivel del dragon
     */
    public Dragon(int nivel) 
    {
        super("Dragon", nivel * 30, nivel * 12, nivel * 6, nivel, nivel * 200, nivel * 40, 3);
    }

    /**
     * Descripcion del Dragon.
     *
     * @return descripcion del enemigo
     */
    @Override
    public String obtenerDescripcion() 
    {
        return "Dragon: bestia ancient. Cada tres turnos desata su furia.";
    }

    /**
     * Ataca incrementando el contador de turnos.
     * Al llegar a 3, hace el triple de danio y resetea el contador a 0.
     * Los dos turnos intermedios son danio normal.
     *
     * @return danio del ataque, normal o x3 segun el contador
     */
    @Override
    public int calcularDanioAtaque() 
    {
        turnosEnfurecido++;
        int ataque = getAtaque();
        if (turnosEnfurecido >= 3) 
        {
            ataque *= 3;
            turnosEnfurecido = 0;
        }
        return ataque;
    }

    /** @return turnos acumulados hacia la furia */
    public int getTurnosEnfurecido() 
    { 
        return turnosEnfurecido; 
    }

    /** @param turnosEnfurecido nuevo contador de turnos */
    public void setTurnosEnfurecido(int turnosEnfurecido) 
    { 
        this.turnosEnfurecido = turnosEnfurecido; 
    }
}