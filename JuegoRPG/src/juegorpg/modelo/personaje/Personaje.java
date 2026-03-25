package juegorpg.modelo.personaje;

import java.util.ArrayList;
import juegorpg.modelo.Entidad;
import juegorpg.modelo.habilidad.Habilidad;
import juegorpg.modelo.item.Consumible;

/**
 * Clase abstracta que representa al personaje que controla el jugador.
 * Extiende Entidad y le mete lo especifico de un heroe: experiencia,
 * oro, habilidades y la logica de subir de nivel.
 * Las 4 clases jugables heredan de aqui.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public abstract class Personaje extends Entidad 
{

    private int experiencia;
    private int experienciaSiguienteNivel;
    private int oro;
    private ArrayList<Habilidad> habilidades;

    /**
     * Constructor base del personaje.
     * Empieza con 0 experiencia, 10 de oro y sin habilidades.
     * Las habilidades se aprenden desde el constructor de cada subclase.
     *
     * @param nombre     nombre del heroe
     * @param vidaMaxima vida maxima inicial
     * @param ataque     ataque inicial
     * @param defensa    defensa inicial
     * @param nivel      nivel inicial (siempre 1 al crear)
     */
    public Personaje(String nombre, int vidaMaxima, int ataque, int defensa, int nivel) 
    {
        super(nombre, vidaMaxima, ataque, defensa, nivel);
        experiencia = 0;
        experienciaSiguienteNivel = 100;
        oro = 10;
        habilidades = new ArrayList<>();
    }

    /**
     * Le da experiencia al personaje y comprueba si sube de nivel.
     * Si la experiencia acumulada llega o supera el umbral, sube nivel.
     *
     * @param cantidad experiencia a ganar (viene del enemigo derrotado)
     */
    public void ganarExperiencia(int cantidad) 
    {
        experiencia += cantidad;
        if (experiencia >= experienciaSiguienteNivel) subirNivel();
    }

    /**
     * Sube el nivel del personaje.
     * Resetea la experiencia a 0, aumenta el umbral del siguiente nivel
     * un 20% mas dificil, y llama a alSubirNivel() para que cada clase
     * aplique sus propias mejoras de stats.
     */
    public void subirNivel() 
    {
        setNivel(getNivel() + 1);
        experiencia = 0;
        experienciaSiguienteNivel = (int)(experienciaSiguienteNivel * 1.2);
        alSubirNivel();
    }

    /**
     * Anade una habilidad al listado del personaje.
     * Se llama desde el constructor de cada subclase para equipar
     * las habilidades propias de esa clase.
     *
     * @param habilidad habilidad a aprender
     */
    public void aprenderHabilidad(Habilidad habilidad) 
    {
        habilidades.add(habilidad);
    }

    /**
     * Devuelve una habilidad por su posicion en la lista.
     * Devuelve null si el indice esta fuera de rango.
     *
     * @param indice posicion en el listado (empieza en 0)
     * @return la habilidad o null si no existe
     */
    public Habilidad obtenerHabilidad(int indice) 
    {
        if (indice < 0 || indice >= habilidades.size()) return null;
        return habilidades.get(indice);
    }

    /**
     * Muestra las habilidades disponibles por consola.
     * Solo se usa para depuracion, la vista tiene su propio sistema.
     */
    public void mostrarHabilidades() 
    {
        String mostrarHabilidades = "== HABILIDADES ===\n";
        if (habilidades.isEmpty()) 
        {
            mostrarHabilidades = "No tiene habilidades aprendidas.";
        } 
        else 
        {
            for (int i = 0; i < habilidades.size(); i++) 
            {
                mostrarHabilidades += "[" + (i + 1) + "] " + habilidades.get(i).getNombre()
                        + " (Costo: " + habilidades.get(i).getCostoMana() + " MP)\n";
            }
            mostrarHabilidades += "==============";
        }
        System.out.println(mostrarHabilidades);
    }

    /**
     * Usa un consumible del inventario sobre este personaje.
     * Si el consumible cura vida, se aplica curar(). Si restaura mana
     * y el personaje es un Mago, se sube el mana (con tope en el maximo).
     *
     * @param consumible el item consumible a usar
     * @return mensaje con el resultado para mostrar en pantalla
     */
    public String usarConsumible(Consumible consumible) 
    {
        if (consumible.getEfectoVida() > 0) curar(consumible.getEfectoVida());
        if (consumible.getEfectoMana() > 0 && this instanceof Mago) 
        {
            Mago mago = (Mago) this;
            mago.setManaActual(mago.getManaActual() + consumible.getEfectoMana());
            if (mago.getManaActual() > mago.getManaMaximo()) mago.setManaActual(mago.getManaMaximo());
        }
        return getNombre() + " uso " + consumible.getNombre() + "! +"
                + consumible.getEfectoVida() + " HP +" + consumible.getEfectoMana() + " MP";
    }

    /**
     * Hook que llama subirNivel() para que cada subclase aplique
     * sus propias mejoras de stats al subir de nivel.
     * El Guerrero sube mas vida, el Mago mas mana, etc.
     */
    protected abstract void alSubirNivel();

    /**
     * Extiende el toString de Entidad con experiencia y oro.
     *
     * @return string con todos los stats del personaje
     */
    @Override
    public String toString() 
    {
        return super.toString() + " | EXP: " + experiencia + "/" + experienciaSiguienteNivel + " | ORO: " + oro;
    }

    // ── GETTERS Y SETTERS ────────────────────────────────────────────────────

    /** @return lista de habilidades del personaje */
    public ArrayList<Habilidad> getHabilidades() 
    { 
        return habilidades; 
    }

    /** @return experiencia actual */
    public int getExperiencia() 
    { 
        return experiencia; 
    }

    /** @param experiencia nueva experiencia */
    public void setExperiencia(int experiencia) 
    { 
        this.experiencia = experiencia; 
    }

    /** @return experiencia necesaria para el siguiente nivel */
    public int getExperienciaSiguienteNivel() 
    { 
        return experienciaSiguienteNivel; 
    }

    /** @param experienciaSiguienteNivel nuevo umbral de experiencia */
    public void setExperienciaSiguienteNivel(int experienciaSiguienteNivel) 
    {
        this.experienciaSiguienteNivel = experienciaSiguienteNivel;
    }

    /** @return oro actual del personaje */
    public int getOro() 
    { 
        return oro; 
    }

    /** @param oro nueva cantidad de oro */
    public void setOro(int oro) 
    { 
        this.oro = oro; 
    }
}