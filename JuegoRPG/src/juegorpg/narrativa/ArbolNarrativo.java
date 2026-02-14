package juegorpg.narrativa;
import java.util.ArrayList;
import juegorpg.modelo.enemigo.BossFinal;
import juegorpg.modelo.enemigo.Dragon;
import juegorpg.modelo.enemigo.Goblin;
import juegorpg.modelo.enemigo.Orco;
import java.io.Serializable;

public class ArbolNarrativo implements Serializable
{
    private ArrayList<Nodo> nodos;
    private Nodo nodoActual;
    
    public ArbolNarrativo()
    {
        nodos = new ArrayList<>();
        nodoActual = null;
        construirHistoria();
    }
    
   private void construirHistoria() 
   {
        Nodo inicio = new Nodo("inicio", "Despiertas en un claro del bosque. Frente a ti hay dos caminos.");
        Nodo bosque = new Nodo("bosque", "Entras al bosque oscuro. ¡Un goblin salvaje aparece!");
        Nodo bosqueProfundo = new Nodo("bosque_profundo", "Avanzas más profundo. Encuentras un campamento de goblins.");
        Nodo rio = new Nodo("rio", "Llegas a un río cristalino. Un orco guardián vigila el puente.");
        Nodo cueva = new Nodo("cueva", "Exploras una cueva húmeda. ¡Un orco te bloquea el paso!");
        Nodo cuevaProfunda = new Nodo("cueva_profunda", "Desciendes más. La cueva se vuelve un laberinto oscuro.");
        Nodo tesoro = new Nodo("tesoro", "¡Encuentras una cámara del tesoro! Pero un dragón duerme sobre él.");
        Nodo pueblo = new Nodo("pueblo", "Llegas a un pueblo abandonado. Algo se mueve entre las sombras...");
        Nodo castillo = new Nodo("castillo", "Llegas a un castillo en ruinas. ¡Un dragón desciende del cielo!");
        Nodo torreBoss = new Nodo("torre_boss", "Subes a la torre más alta. El Señor de la Oscuridad te espera.");
        Nodo finalVictoria = new Nodo("final_victoria", "¡Has derrotado al mal! Eres un héroe legendario.");
        Nodo finalHuida = new Nodo("final_huida", "Escapas con vida, pero la oscuridad sigue reinando...");
    
        inicio.agregarOpcion(new Opcion("Explorar el bosque", "bosque"));
        inicio.agregarOpcion(new Opcion("Entrar a la cueva", "cueva"));
    
        bosque.agregarOpcion(new Opcion("Adentrarte más en el bosque", "bosque_profundo"));
        bosque.agregarOpcion(new Opcion("Seguir el camino hacia el río", "rio"));
    
        bosqueProfundo.agregarOpcion(new Opcion("Cruzar el río", "rio"));
        bosqueProfundo.agregarOpcion(new Opcion("Regresar al bosque", "bosque"));
    
        rio.agregarOpcion(new Opcion("Dirigirte al pueblo", "pueblo"));
        rio.agregarOpcion(new Opcion("Volver al bosque", "bosque"));
    
        cueva.agregarOpcion(new Opcion("Explorar más profundo", "cueva_profunda"));
        cueva.agregarOpcion(new Opcion("Salir de la cueva", "inicio"));
    
        cuevaProfunda.agregarOpcion(new Opcion("Entrar a la cámara del tesoro", "tesoro"));
        cuevaProfunda.agregarOpcion(new Opcion("Salir antes de que sea tarde", "cueva"));
    
        tesoro.agregarOpcion(new Opcion("Huir con el tesoro", "final_huida"));
        tesoro.agregarOpcion(new Opcion("Ir al castillo", "castillo"));
    
        pueblo.agregarOpcion(new Opcion("Investigar el castillo cercano", "castillo"));
        pueblo.agregarOpcion(new Opcion("Huir del pueblo", "final_huida"));
    
        castillo.agregarOpcion(new Opcion("Subir a la torre del boss", "torre_boss"));
        castillo.agregarOpcion(new Opcion("Retirarte mientras puedas", "final_huida"));
    
        torreBoss.agregarOpcion(new Opcion("Reclamar tu victoria", "final_victoria"));
    
        bosque.setEnemigo(new Goblin(2));
        bosqueProfundo.setEnemigo(new Goblin(3));
        rio.setEnemigo(new Orco(3));
        cueva.setEnemigo(new Orco(2));
        cuevaProfunda.setEnemigo(new Orco(4));
        tesoro.setEnemigo(new Dragon(4));
        pueblo.setEnemigo(new Goblin(3));
        castillo.setEnemigo(new Dragon(5));
        torreBoss.setEnemigo(new BossFinal(6));
    
        finalVictoria.setEsNodoFinal(true);
        finalHuida.setEsNodoFinal(true);
    
        agregarNodo(inicio);
        agregarNodo(bosque);
        agregarNodo(bosqueProfundo);
        agregarNodo(rio);
        agregarNodo(cueva);
        agregarNodo(cuevaProfunda);
        agregarNodo(tesoro);
        agregarNodo(pueblo);
        agregarNodo(castillo);
        agregarNodo(torreBoss);
        agregarNodo(finalVictoria);
        agregarNodo(finalHuida);
    }
    
    public void agregarNodo(Nodo nodo)
    {
        nodos.add(nodo);
    }
    
    public Nodo obtenerNodo(String id)
    {
        for (Nodo nodo : nodos) 
        {
            if (nodo.getId().equals(id)) 
            {
                return nodo;
            }
        }
        
        return null;
    }
    
    public void iniciar()
    {
        nodoActual = obtenerNodo("inicio");
    }
    
    public void avanzar(int indiceOpcion)
    {
        Opcion opcionElegida = nodoActual.getOpciones().get(indiceOpcion);
        String idDestino = opcionElegida.getNodoDestino();
        nodoActual = obtenerNodo(idDestino);
    }

    public ArrayList<Nodo> getNodos() 
    {
        return nodos;
    }

    public Nodo getNodoActual() 
    {
        return nodoActual;
    }
    
    
}
