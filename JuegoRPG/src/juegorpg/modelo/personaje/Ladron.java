package juegorpg.modelo.personaje;

import juegorpg.modelo.habilidad.HabilidadFisica;

/**
 * El Ladron. La clase mas esquiva del juego.
 * Su mecanica es la esquiva: cuando recibe un ataque, hay una probabilidad
 * (esquiva%) de que lo esquive por completo y no reciba nada de danio.
 * Empieza con 30% y sube con el nivel. Aprende Apunalar y Golpe Silencioso.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class Ladron extends Personaje 
{

    /** Porcentaje de probabilidad de esquivar ataques. Maximo 60%. */
    private int esquiva;

    /**
     * Crea un Ladron agil pero fragil: poca vida (80), ataque bajo (10),
     * defensa baja (4) y esquiva inicial del 30%.
     *
     * @param nombre nombre del heroe
     */
    public Ladron(String nombre) 
    {
        super(nombre, 80, 10, 4, 1);
        esquiva = 30;
        aprenderHabilidad(new HabilidadFisica("Apunalar", "Ataque rapido por la espalda", 15, 10, 2.5));
        aprenderHabilidad(new HabilidadFisica("Golpe Silencioso", "Ataque sigiloso", 10, 8, 2.0));
    }

    /**
     * Sobreescribe recibirDanio para aplicar la mecanica de esquiva.
     * Genera un numero aleatorio: si cae dentro del porcentaje de esquiva,
     * el golpe no llega. Si no, se aplica el danio normal.
     * Al esquivar imprime un mensaje por consola (se podria pasar a la vista).
     *
     * @param danio danio bruto que intenta recibir
     */
    @Override
    public void recibirDanio(int danio) 
    {
        int numeroRandom = (int)(Math.random() * 100) + 1;
        if (numeroRandom <= esquiva) 
        {
            System.out.println(getNombre() + " esquivo el ataque!!");
        } 
        else 
        {
            super.recibirDanio(danio);
        }
    }

    /**
     * Al subir de nivel sube un poco de todo y la esquiva sube 3 puntos.
     * El tope de esquiva es 60% para que no sea demasiado roto.
     */
    @Override
    protected void alSubirNivel() 
    {
        setVidaMaxima(getVidaMaxima() + 8);
        setVidaActual(getVidaMaxima());
        setAtaque(getAtaque() + 2);
        setDefensa(getDefensa() + 1);
        esquiva += 3;
        if (esquiva > 60) esquiva = 60;
    }

    /**
     * El Ladron ataca con el stat base sin mecanicas especiales en el ataque.
     * Su mecanica esta en la defensa (esquiva), no en el ataque.
     *
     * @return danio del ataque base
     */
    @Override
    public int calcularDanioAtaque() 
    {
        return getAtaque();
    }

    /**
     * Descripcion corta del Ladron para la pantalla de seleccion.
     *
     * @return descripcion de la clase
     */
    @Override
    public String obtenerDescripcion() 
    {
        return "Ladron: maestro de la esquiva. Agil y escurridizo.";
    }

    /**
     * Extiende el toString con el porcentaje de esquiva actual.
     *
     * @return stats completos incluyendo esquiva
     */
    @Override
    public String toString() 
    {
        return super.toString() + " | Esquiva: " + esquiva + "%";
    }

    /** @return porcentaje de esquiva actual */
    public int getEsquiva() 
    { 
        return esquiva; 
    }

    /** @param esquiva nuevo porcentaje de esquiva */
    public void setEsquiva(int esquiva) 
    { 
        this.esquiva = esquiva; 
    }
}