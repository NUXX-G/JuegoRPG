package juegorpg.narrativa;

import java.util.ArrayList;
import juegorpg.modelo.enemigo.*;
import java.io.Serializable;

/**
 * El arbol de decision que estructura la historia del juego.
 * Contiene todos los nodos narrativos y gestiona la navegacion entre ellos.
 * Al crearse construye automaticamente toda la historia con sus conexiones,
 * enemigos y finales. Se serializa para guardar el progreso.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class ArbolNarrativo implements Serializable 
{

    private ArrayList<Nodo> nodos;

    /** Nodo en el que esta el jugador actualmente. */
    private Nodo nodoActual;

    /**
     * Crea el arbol narrativo y construye toda la historia automaticamente.
     * El nodo actual empieza en null hasta que se llame a iniciar().
     */
    public ArbolNarrativo() 
    {
        nodos = new ArrayList<>();
        nodoActual = null;
        construirHistoria();
    }

    /**
     * Construye todos los nodos, sus conexiones, enemigos y finales.
     * La historia tiene 12 nodos: inicio, 9 ubicaciones y 2 finales.
     * Los enemigos escalan en nivel segun la ubicacion en el mapa narrativo.
     */
    private void construirHistoria() 
    {
        Nodo inicio        = new Nodo("inicio",         "Despiertas en un claro del bosque. Frente a ti hay dos caminos.");
        Nodo bosque        = new Nodo("bosque",         "Entras al bosque oscuro. Un goblin salvaje aparece!");
        Nodo bosqueProfundo= new Nodo("bosque_profundo","Avanzas mas profundo. Encuentras un campamento de goblins.");
        Nodo rio           = new Nodo("rio",            "Llegas a un rio cristalino. Un orco guardian vigila el puente.");
        Nodo cueva         = new Nodo("cueva",          "Exploras una cueva humeda. Un orco te bloquea el paso!");
        Nodo cuevaProfunda = new Nodo("cueva_profunda", "Desciendes mas. La cueva se vuelve un laberinto oscuro.");
        Nodo tesoro        = new Nodo("tesoro",         "Encuentras una camara del tesoro! Pero un dragon duerme sobre el.");
        Nodo pueblo        = new Nodo("pueblo",         "Llegas a un pueblo abandonado. Algo se mueve entre las sombras...");
        Nodo castillo      = new Nodo("castillo",       "Llegas a un castillo en ruinas. Un dragon desciende del cielo!");
        Nodo torreBoss     = new Nodo("torre_boss",     "Subes a la torre mas alta. El Senor de la Oscuridad te espera.");
        Nodo finalVictoria = new Nodo("final_victoria", "Has derrotado al mal! Eres un heroe legendario.");
        Nodo finalHuida    = new Nodo("final_huida",    "Escapas con vida, pero la oscuridad sigue reinando...");

        // Conexiones entre nodos
        inicio.agregarOpcion(new Opcion("Explorar el bosque", "bosque"));
        inicio.agregarOpcion(new Opcion("Entrar a la cueva", "cueva"));
        bosque.agregarOpcion(new Opcion("Adentrarte mas en el bosque", "bosque_profundo"));
        bosque.agregarOpcion(new Opcion("Seguir el camino hacia el rio", "rio"));
        bosqueProfundo.agregarOpcion(new Opcion("Cruzar el rio", "rio"));
        bosqueProfundo.agregarOpcion(new Opcion("Regresar al bosque", "bosque"));
        rio.agregarOpcion(new Opcion("Dirigirte al pueblo", "pueblo"));
        rio.agregarOpcion(new Opcion("Volver al bosque", "bosque"));
        cueva.agregarOpcion(new Opcion("Explorar mas profundo", "cueva_profunda"));
        cueva.agregarOpcion(new Opcion("Salir de la cueva", "inicio"));
        cuevaProfunda.agregarOpcion(new Opcion("Entrar a la camara del tesoro", "tesoro"));
        cuevaProfunda.agregarOpcion(new Opcion("Salir antes de que sea tarde", "cueva"));
        tesoro.agregarOpcion(new Opcion("Huir con el tesoro", "final_huida"));
        tesoro.agregarOpcion(new Opcion("Ir al castillo", "castillo"));
        pueblo.agregarOpcion(new Opcion("Investigar el castillo cercano", "castillo"));
        pueblo.agregarOpcion(new Opcion("Huir del pueblo", "final_huida"));
        castillo.agregarOpcion(new Opcion("Subir a la torre del boss", "torre_boss"));
        castillo.agregarOpcion(new Opcion("Retirarte mientras puedas", "final_huida"));
        torreBoss.agregarOpcion(new Opcion("Reclamar tu victoria", "final_victoria"));

        // Enemigos por nodo (escalan en nivel)
        bosque.setEnemigo(new Goblin(2));
        bosqueProfundo.setEnemigo(new Goblin(3));
        rio.setEnemigo(new Orco(3));
        cueva.setEnemigo(new Orco(2));
        cuevaProfunda.setEnemigo(new Orco(4));
        tesoro.setEnemigo(new Dragon(4));
        pueblo.setEnemigo(new Goblin(3));
        castillo.setEnemigo(new Dragon(5));
        torreBoss.setEnemigo(new BossFinal(6));

        // Finales
        finalVictoria.setEsNodoFinal(true);
        finalHuida.setEsNodoFinal(true);

        // Agregar todos los nodos
        agregarNodo(inicio); agregarNodo(bosque); agregarNodo(bosqueProfundo);
        agregarNodo(rio); agregarNodo(cueva); agregarNodo(cuevaProfunda);
        agregarNodo(tesoro); agregarNodo(pueblo); agregarNodo(castillo);
        agregarNodo(torreBoss); agregarNodo(finalVictoria); agregarNodo(finalHuida);
    }

    /**
     * Anade un nodo a la lista de nodos del arbol.
     *
     * @param nodo nodo a agregar
     */
    public void agregarNodo(Nodo nodo) 
    {
        nodos.add(nodo);
    }

    /**
     * Busca y devuelve un nodo por su id unico.
     * Devuelve null si no existe ningun nodo con ese id.
     *
     * @param id identificador del nodo a buscar
     * @return el nodo encontrado o null si no existe
     */
    public Nodo obtenerNodo(String id) 
    {
        for (Nodo nodo : nodos) 
        {
            if (nodo.getId().equals(id)) return nodo;
        }
        return null;
    }

    /**
     * Inicializa el arbol poniendo el nodo actual en el nodo de inicio.
     * Hay que llamar a esto antes de empezar a jugar.
     */
    public void iniciar() 
    {
        nodoActual = obtenerNodo("inicio");
    }

    /**
     * Avanza al siguiente nodo segun la opcion elegida por el jugador.
     * Coge el destino de la opcion en esa posicion y busca el nodo correspondiente.
     *
     * @param indiceOpcion indice de la opcion elegida (empieza en 0)
     */
    public void avanzar(int indiceOpcion) 
    {
        String idDestino = nodoActual.getOpciones().get(indiceOpcion).getNodoDestino();
        nodoActual = obtenerNodo(idDestino);
    }

    /** @return lista de todos los nodos del arbol */
    public ArrayList<Nodo> getNodos() 
    { 
        return nodos; 
    }

    /** @return nodo en el que esta el jugador actualmente */
    public Nodo getNodoActual() 
    { 
        return nodoActual; 
    }
}