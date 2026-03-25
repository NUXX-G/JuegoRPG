package juegorpg.modelo.enemigo;

/**
 * El Senor de la Oscuridad. El boss final del juego.
 * Tiene dos fases: en la fase 1 ataca normal, pero cuando baja al 50%
 * de vida entra en fase 2, duplica su ataque y ademas anade danio
 * aleatorio extra en cada golpe. El jugador tiene que intentar
 * matarlo rapido antes de que active la fase 2.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class BossFinal extends Enemigo 
{

    /**
     * Fase actual del boss.
     * 1 = fase normal, 2 = fase de furia (activada al 50% de vida).
     */
    private int fase;

    /**
     * Crea el boss final con los stats mas altos del juego.
     * Mucha vida, ataque y defensa, escalados al nivel indicado.
     *
     * @param nivel nivel del boss (el mas alto de todos los enemigos)
     */
    public BossFinal(int nivel) 
    {
        super("Senor de la Oscuridad", nivel * 80, nivel * 15, nivel * 10,
              nivel, nivel * 500, nivel * 100, 3);
        this.fase = 1;
    }

    /**
     * Sobreescribe recibirDanio para activar la fase 2 al llegar al 50% de vida.
     * Cuando la vida baja del umbral y sigue en fase 1, duplica el ataque
     * y cambia a fase 2. Solo ocurre una vez por combate.
     *
     * @param danio danio bruto recibido
     */
    @Override
    public void recibirDanio(int danio) 
    {
        super.recibirDanio(danio);
        if (fase == 1 && getVidaActual() <= getVidaMaxima() / 2) 
        {
            fase = 2;
            setAtaque(getAtaque() * 2);
            System.out.println("El Senor de la Oscuridad entra en FASE 2!");
        }
    }

    /**
     * Descripcion del boss final.
     *
     * @return descripcion del enemigo
     */
    @Override
    public String obtenerDescripcion() 
    {
        return "Senor de la Oscuridad: el mal absoluto. Cambia de fase al 50% de vida.";
    }

    /**
     * En fase 1 ataca con el stat base. En fase 2 anade entre 0 y 20
     * de danio extra aleatorio en cada golpe. En fase 2 es muy dificil
     * predecir cuanto va a doler el siguiente ataque.
     *
     * @return danio del ataque segun la fase actual
     */
    @Override
    public int calcularDanioAtaque() 
    {
        int ataque = getAtaque();
        if (fase == 2) ataque += (int)(Math.random() * 21);
        return ataque;
    }

    /**
     * Extiende el toString con la fase actual del boss.
     *
     * @return stats completos incluyendo fase
     */
    @Override
    public String toString() 
    {
        return super.toString() + " | Fase: " + fase;
    }

    /** @return fase actual (1 o 2) */
    public int getFase() 
    { 
        return fase; 
    }
}