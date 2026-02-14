package juegorpg;

import juegorpg.modelo.enemigo.BossFinal;
import juegorpg.modelo.enemigo.Dragon;
import juegorpg.modelo.enemigo.Goblin;
import juegorpg.modelo.enemigo.Orco;
import juegorpg.modelo.item.Arma;
import juegorpg.modelo.item.Armadura;
import juegorpg.modelo.item.Consumible;
import juegorpg.modelo.item.Inventario;
import juegorpg.modelo.item.Item;
import juegorpg.modelo.personaje.Arquero;
import juegorpg.modelo.personaje.Guerrero;
import juegorpg.modelo.personaje.Ladron;
import juegorpg.modelo.personaje.Mago;
import juegorpg.combate.SistemaCombate;
import juegorpg.modelo.enemigo.Enemigo;
import juegorpg.modelo.personaje.Personaje;

public class JuegoRPG 
{

    public static void main(String[] args) 
    {
        //PERSONAJE
        Guerrero guerrero1 = new Guerrero("Guerrero1");
        Mago mago1 = new Mago("Mago1");
        Arquero arquero1 = new Arquero("Arquero1");
        Ladron ladron1 = new Ladron("Ladron1");
        
        System.out.println("--- GUERRERO ---");
        System.out.println(guerrero1.obtenerDescripcion());
        System.out.println(guerrero1.toString());
        System.out.println(guerrero1.getNombre() + " recibe 30 de danio");
        guerrero1.recibirDanio(30);
        System.out.println(guerrero1.toString());
        System.out.println(guerrero1.getNombre() + " recibe 100 de experiencia");
        guerrero1.ganarExperiencia(100);
        System.out.println(guerrero1.toString());
        System.out.println("");
        
        System.out.println("--- MAGO ---");
        System.out.println(mago1.obtenerDescripcion());
        System.out.println(mago1.toString());
        System.out.println(mago1.getNombre() + " recibe 30 de danio");
        mago1.recibirDanio(30);
        System.out.println(mago1.toString());
        System.out.println(mago1.getNombre() + " recibe 100 de experiencia");
        mago1.ganarExperiencia(100);
        System.out.println(mago1.toString());
        System.out.println("");
        
        System.out.println("--- ARQUERO ---");
        System.out.println(arquero1.obtenerDescripcion());
        System.out.println(arquero1.toString());
        System.out.println(arquero1.getNombre() + " recibe 30 de danio");
        arquero1.recibirDanio(30);
        System.out.println(arquero1.toString());
        System.out.println(arquero1.getNombre() + " recibe 100 de experiencia");
        arquero1.ganarExperiencia(100);
        System.out.println(arquero1.toString());
        System.out.println("");
        
        System.out.println("--- LADRON ---");
        System.out.println(ladron1.obtenerDescripcion());
        System.out.println(ladron1.toString());
        System.out.println(ladron1.getNombre() + " recibe 30 de danio");
        ladron1.recibirDanio(30);
        System.out.println(ladron1.toString());
        System.out.println(ladron1.getNombre() + " recibe 100 de experiencia");
        ladron1.ganarExperiencia(100);
        System.out.println(ladron1.toString());
        System.out.println("");
        
        
        //ENEMIGO
        Goblin goblin1 = new Goblin(1);
        Orco orco1 = new Orco(1);
        Dragon dragon1 = new Dragon(1);
        BossFinal bossFinal1 = new BossFinal(1);
        
        System.out.println("--- GOBLIN ---");
        System.out.println(goblin1.obtenerDescripcion());
        System.out.println(goblin1.toString());
        System.out.println(goblin1.getNombre() + " recibe 20 de danio");
        goblin1.recibirDanio(20);
        goblin1.recibirDanio(20);
        goblin1.recibirDanio(20);
        System.out.println(goblin1.toString());
        System.out.println("Daño: " + goblin1.calcularDanioAtaque());
        System.out.println("Daño: " + goblin1.calcularDanioAtaque());
        System.out.println("Daño: " + goblin1.calcularDanioAtaque());
        System.out.println("Daño: " + goblin1.calcularDanioAtaque());
        System.out.println("");
        
        System.out.println("--- ORCO ---");
        System.out.println(orco1.obtenerDescripcion());
        System.out.println(orco1.toString());
        System.out.println(orco1.getNombre() + " recibe 20 de danio");
        orco1.recibirDanio(20);
        orco1.recibirDanio(20);
        orco1.recibirDanio(20);
        System.out.println(orco1.toString());
        System.out.println("Daño: " + orco1.calcularDanioAtaque());
        System.out.println("Daño: " + orco1.calcularDanioAtaque());
        System.out.println("Daño: " + orco1.calcularDanioAtaque());
        System.out.println("Daño: " + orco1.calcularDanioAtaque());
        System.out.println("");
        
        System.out.println("--- DRAGON ---");
        System.out.println(dragon1.obtenerDescripcion());
        System.out.println(dragon1.toString());
        System.out.println(dragon1.getNombre() + " recibe 20 de danio");
        dragon1.recibirDanio(20);
        dragon1.recibirDanio(20);
        dragon1.recibirDanio(20);
        System.out.println(dragon1.toString());
        System.out.println("Daño: " + dragon1.calcularDanioAtaque());
        System.out.println("Daño: " + dragon1.calcularDanioAtaque());
        System.out.println("Daño: " + dragon1.calcularDanioAtaque());
        System.out.println("Daño: " + dragon1.calcularDanioAtaque());
        System.out.println("");
        
        System.out.println("--- BOSS FINAL ---");
        System.out.println(bossFinal1.obtenerDescripcion());
        System.out.println(bossFinal1.toString());
        System.out.println(bossFinal1.getNombre() + " recibe 50 de danio");
        bossFinal1.recibirDanio(50);
        System.out.println(bossFinal1.toString());
        System.out.println("Danio: " + bossFinal1.calcularDanioAtaque());
        System.out.println("Danio: " + bossFinal1.calcularDanioAtaque());
        bossFinal1.recibirDanio(50);
        System.out.println("Danio: " + bossFinal1.calcularDanioAtaque());
        System.out.println("Danio: " + bossFinal1.calcularDanioAtaque());
        System.out.println(bossFinal1.toString());
        System.out.println("");
        
                
        //Item
        Inventario inventario1 = new Inventario();
        Arma arma1 = new Arma("Daga", "Arma corta", 5, 2);
        Arma arma2 = new Arma("Espada", "Locura es una espada", 15, 10);
        Arma arma3 = new Arma("Arco", "Arma a distancia", 10, 4);
        Armadura armadura1 = new Armadura("Botas", "Sirven para proteger los pies", 8, 4);
        Armadura armadura2 = new Armadura("Peto", "Sirve para proteger el pecho", 15, 10);
        Consumible consumible1 = new Consumible("Pocion de Mana", "Sirve para recuperar mana", 5, 0, 10);
        Consumible consumible2 = new Consumible("Pocion de vida", "Sirve para recuperar vida", 5, 10, 0);
        Consumible consumible3 = new Consumible("Pocion divina", "Sirve para recuperar tanto mana como vida", 15, 10, 10);
        inventario1.agregarItem(arma1);
        inventario1.agregarItem(arma2);
        inventario1.agregarItem(arma3);
        inventario1.agregarItem(armadura1);
        inventario1.agregarItem(armadura2);
        inventario1.agregarItem(consumible1);
        inventario1.agregarItem(consumible2);
        inventario1.agregarItem(consumible3);
        System.out.println("--- MOSTRAR INVENTARIO ---");
        inventario1.mostrarInventario();
        System.out.println("");
        
        System.out.println("--- MOSTRAR INVENTARIO DESPUES DE ELIMINAR 2 ITEMS ---");
        inventario1.quitarItem(0);
        inventario1.quitarItem(4);
        inventario1.mostrarInventario();
        System.out.println("");
        
        System.out.println("--- INVENTARIO LLENO ---");
        Arma prueba1 = new Arma("Prueba", "Descripcion Prueba", 0, 0);
        inventario1.agregarItem(prueba1);
        inventario1.agregarItem(prueba1);
        inventario1.agregarItem(prueba1);
        inventario1.agregarItem(prueba1);
        inventario1.agregarItem(prueba1);
        inventario1.agregarItem(prueba1);
        inventario1.agregarItem(prueba1);
        inventario1.agregarItem(prueba1);
        inventario1.agregarItem(prueba1);
        inventario1.agregarItem(prueba1);
        inventario1.agregarItem(prueba1);
        inventario1.agregarItem(prueba1);
        inventario1.agregarItem(prueba1);
        inventario1.agregarItem(prueba1);
        inventario1.agregarItem(prueba1);
        inventario1.agregarItem(prueba1);
        inventario1.mostrarInventario();
        
        //Habilidades
        Guerrero conan = new Guerrero("Conan");
        Mago merlin = new Mago("Merlin");
        Arquero robin = new Arquero("Robin");
        Ladron percy = new Ladron("Percy");
        
        System.out.println("--- MOSTRAR HABILIDADES ---");
        conan.mostrarHabilidades();
        merlin.mostrarHabilidades();
        robin.mostrarHabilidades();
        percy.mostrarHabilidades();
        System.out.println("");
        
        System.out.println("--- OBTENER HABILIDAD ---");
        System.out.println(conan.obtenerHabilidad(0));
        System.out.println(merlin.obtenerHabilidad(0));
        System.out.println(robin.obtenerHabilidad(0));
        System.out.println(percy.obtenerHabilidad(0));
        System.out.println("");
        
        System.out.println("--- CALCULAR DANIO ---");
        System.out.println("Daño de " + conan.getNombre() + " " + conan.obtenerHabilidad(0).calcularDanio(conan.getAtaque()));
        System.out.println("Daño de " + merlin.getNombre() + " " + merlin.obtenerHabilidad(0).calcularDanio(merlin.getAtaque()));
        System.out.println("Daño de " + robin.getNombre() + " " + robin.obtenerHabilidad(0).calcularDanio(robin.getAtaque()));
        System.out.println("Daño de " + percy.getNombre() + " " + percy.obtenerHabilidad(0).calcularDanio(percy.getAtaque()));
        System.out.println("");
        
        
        //GeneradorRNG SistemaCombate
        Personaje heroe = new Guerrero("Heroe");
        Enemigo goblinHeroe = new Goblin(3);
        SistemaCombate combate = new SistemaCombate(heroe, goblinHeroe);
        
        System.out.println("--- INICIAR COMBATE ---");
        System.out.println(combate.iniciarCombate());
        System.out.println("--- ESTADO COMBATE ---");
        System.out.println( combate.obtenerEstadoCombate());
        System.out.println("--- TURNOS ---");
        System.out.println(combate.turnoJugador(1));
        System.out.println(combate.turnoEnemigo());
        System.out.println(combate.turnoJugador(2));
        System.out.println(combate.turnoEnemigo());
        System.out.println(combate.turnoJugador(1));
        System.out.println(combate.turnoEnemigo());
        System.out.println("--- ESTADO FINAL DEL COMBATE ---");
        System.out.println(combate.obtenerEstadoCombate());

    }
    
}
