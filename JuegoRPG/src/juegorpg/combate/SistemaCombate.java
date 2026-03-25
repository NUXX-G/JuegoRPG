package juegorpg.combate;

import juegorpg.modelo.item.Arma;
import juegorpg.modelo.item.Armadura;
import juegorpg.modelo.item.Inventario;
import juegorpg.rng.GeneradorRNG;
import juegorpg.modelo.enemigo.Enemigo;
import juegorpg.modelo.personaje.Personaje;

/**
 * Gestiona toda la logica del combate por turnos.
 * Controla los turnos del jugador y del enemigo, aplica el danio,
 * gestiona las recompensas al ganar y genera los drops aleatorios.
 * La vista (PantallaCombate) llama a los metodos de aqui y muestra
 * los resultados en el log de combate.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class SistemaCombate 
{

    private Personaje jugador;
    private Enemigo enemigo;

    /** Contador de turnos, empieza en 1 y sube con cada accion. */
    private int turnoActual;

    private Inventario inventario;

    /**
     * Crea un nuevo sistema de combate entre el jugador y un enemigo.
     * El inventario se pasa para poder meter los drops ahi directamente.
     *
     * @param jugador    personaje del jugador
     * @param enemigo    enemigo contra el que se pelea
     * @param inventario inventario del jugador para los drops
     */
    public SistemaCombate(Personaje jugador, Enemigo enemigo, Inventario inventario) 
    {
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.inventario = inventario;
        turnoActual = 1;
    }

    /**
     * Mensaje inicial que se muestra al empezar el combate.
     * Lo llama PantallaCombate al crearse.
     *
     * @return mensaje de inicio con el nombre del enemigo
     */
    public String iniciarCombate() 
    {
        return "Combate iniciado contra " + enemigo.getNombre() + "!";
    }

    /**
     * Ejecuta el turno del jugador segun la accion elegida.
     * <ul>
     *   <li>Accion 1: ataque normal</li>
     *   <li>Accion 2: primera habilidad especial</li>
     *   <li>Accion 3: huir (raramente usado desde aqui, lo gestiona la vista)</li>
     * </ul>
     * Si el enemigo muere con el ataque, aplica las recompensas (exp, oro, drop)
     * y devuelve el mensaje de victoria.
     *
     * @param accion numero de accion elegida por el jugador (1, 2 o 3)
     * @return mensaje con el resultado del turno para el log de combate
     */
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
                turnoJugador = "Accion invalida.";
                break;
        }

        // Si el enemigo murio, aplica recompensas y posible drop
        if (!enemigo.estaVivo()) 
        {
            jugador.ganarExperiencia(enemigo.getExperienciaAlMorir());
            jugador.setOro(jugador.getOro() + enemigo.getOroAlMorir());

            String mensajeVictoria = jugador.getNombre() + " ha matado al " + enemigo.getNombre()
                    + ", ha conseguido " + enemigo.getExperienciaAlMorir()
                    + " experiencia y " + enemigo.getOroAlMorir() + " de oro.";

            // Drop aleatorio: 30% arma, 30% armadura, 40% nada
            int drop = GeneradorRNG.entero(1, 10);
            if (drop <= 3) 
            {
                Arma arma = generarArmaAleatoria(enemigo.getNivel());
                mensajeVictoria += "\nHas obtenido: " + arma.getNombre() + "!";
            } 
            else if (drop <= 6) 
            {
                Armadura armadura = generarArmaduraAleatoria(enemigo.getNivel());
                mensajeVictoria += "\nHas obtenido: " + armadura.getNombre() + "!";
            }

            turnoJugador = mensajeVictoria;
        }

        turnoActual++;
        return turnoJugador;
    }

    /**
     * Ejecuta el turno del enemigo. El enemigo siempre ataca al jugador
     * con su propio calcularDanioAtaque(), que varia segun el tipo de enemigo.
     *
     * @return mensaje con el resultado del ataque del enemigo
     */
    public String turnoEnemigo() 
    {
        jugador.recibirDanio(enemigo.calcularDanioAtaque());
        turnoActual++;
        return "El " + enemigo.getNombre() + " ha atacado.";
    }

    /**
     * Comprueba si el combate ya ha terminado.
     * Termina cuando cualquiera de los dos se queda sin vida.
     *
     * @return true si el combate ha terminado
     */
    public boolean combateTerminado() 
    {
        return !jugador.estaVivo() || !enemigo.estaVivo();
    }

    /**
     * Devuelve el estado actual del combate formateado para el area de stats.
     * Muestra el turno, los stats del jugador y los del enemigo.
     *
     * @return string con el estado completo del combate
     */
    public String obtenerEstadoCombate() 
    {
        return "=== TURNO " + turnoActual + " ===\n"
                + jugador.toString() + "\n"
                + "VS\n"
                + enemigo.toString() + "\n"
                + "=========================";
    }

    /**
     * Genera un arma aleatoria al derrotar un enemigo.
     * El nombre y el bonus escalan con el nivel del enemigo.
     * El arma se mete directamente en el inventario del jugador.
     *
     * @param nivel nivel del enemigo derrotado
     * @return el arma generada y ya anadida al inventario
     */
    private Arma generarArmaAleatoria(int nivel) 
    {
        String[] nombres = {"Espada", "Hacha", "Lanza", "Daga", "Martillo"};
        String nombre = nombres[GeneradorRNG.entero(0, nombres.length - 1)];
        int bonus = nivel * GeneradorRNG.entero(2, 5);
        Arma arma = new Arma(nombre + " +" + bonus, "Arma encontrada", nivel * 10, bonus);
        inventario.agregarItem(arma);
        return arma;
    }

    /**
     * Genera una armadura aleatoria al derrotar un enemigo.
     * El nombre y el bonus escalan con el nivel del enemigo.
     * La armadura se mete directamente en el inventario del jugador.
     *
     * @param nivel nivel del enemigo derrotado
     * @return la armadura generada y ya anadida al inventario
     */
    private Armadura generarArmaduraAleatoria(int nivel) 
    {
        String[] nombres = {"Casco", "Peto", "Botas", "Guantes", "Escudo"};
        String nombre = nombres[GeneradorRNG.entero(0, nombres.length - 1)];
        int bonus = nivel * GeneradorRNG.entero(1, 3);
        Armadura armadura = new Armadura(nombre + " +" + bonus, "Armadura encontrada", nivel * 10, bonus);
        inventario.agregarItem(armadura);
        return armadura;
    }

    /** @return personaje del jugador */
    public Personaje getJugador() 
    {
        return jugador; 
    }

    /** @return enemigo actual */
    public Enemigo getEnemigo() 
    { 
        return enemigo; 
    }

    /** @return turno actual del combate */
    public int getTurnoActual() 
    { 
        return turnoActual; 
    }
}