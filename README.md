# JuegoRPG — Java Turn-Based RPG

> Proyecto personal desarrollado en Java puro como parte de mi aprendizaje en 1º CFGS DAM.  
> Un RPG por turnos con narrativa tipo novela gráfica, combate estilo Pokémon/Final Fantasy e interfaz gráfica con Swing.

---

##  Descripción

JuegoRPG es un juego de rol por turnos desarrollado íntegramente en Java sin librerías externas.  
El jugador avanza por una historia ramificada tomando decisiones que afectan qué combates enfrenta y con qué dificultad.  
Cada decisión importa: el camino que elijas determina los enemigos, el botín y el final que obtienes.

---

## Características

-  **4 clases de personaje** — Guerrero, Mago, Arquero y Ladrón, cada uno con stats, mecánicas y habilidades únicas
-  **Combate por turnos** — sistema de combate estilo Pokémon/Final Fantasy con ataques, habilidades, objetos y huida
-  **Narrativa ramificada** — historia con múltiples caminos y decisiones que afectan al desarrollo de la partida
-  **Sistema RNG** — críticos, esquivas, drops de objetos y eventos aleatorios
-  **Inventario y equipamiento** — armas, armaduras y consumibles con estadísticas propias
-  **Sistema de guardado** — guarda y carga partidas mediante serialización Java
-  **Interfaz gráfica** — desarrollada con `javax.swing`, sin instalación adicional necesaria

---

##  Arquitectura del proyecto

El proyecto aplica principios de **Programación Orientada a Objetos**:

- **Herencia** — jerarquía `Entidad → Personaje → Guerrero/Mago/Arquero/Ladrón`
- **Clases abstractas** — `Entidad`, `Personaje`, `Enemigo`, `Item`, `Habilidad`
- **Polimorfismo** — cada clase implementa `calcularDanioAtaque()` y `alSubirNivel()` de forma distinta
- **Encapsulación** — todos los atributos `private` con getters/setters controlados
- **Serialización** — guardado de partida con `java.io.Serializable`

```
src/juegorpg/
├── juegorpg/           → Punto de entrada (JuegoRPG.java)
├── modelo/
│   ├── Entidad.java
│   ├── personaje/  → Personaje, Guerrero, Mago, Arquero, Ladron
│   ├── enemigo/    → Enemigo, Goblin, Orco, Dragon, BossFinal
│   ├── item/       → Item, Arma, Armadura, Consumible, Inventario
│   └── habilidad/  → Habilidad, HabilidadFisica, HabilidadMagica
├── rng/            → GeneradorRNG
├── combate/        → SistemaCombate
├── narrativa/      → Nodo, Opcion, ArbolNarrativo
├── guardado/       → GestorGuardado
└── vista/          → Pantallas Swing
```

---

##  Cómo ejecutarlo

### Requisitos
- Java JDK 11 o superior → [Descargar JDK](https://www.oracle.com/java/technologies/downloads/)

### Desde terminal
```bash
git clone https://github.com/NUXX-G/JuegoRPG.git
cd JuegoRPG
javac -d out -sourcepath src src/juegorpg/main/JuegoRPG.java
java -cp out juegorpg.main.JuegoRPG
```

---

##  Estado del desarrollo

| Fase | Descripción | Estado |
|------|-------------|--------|
| Fase 1 | Modelo base — Personajes y jerarquía |  Terminado |
| Fase 2 | Enemigos | Terminado |
| Fase 3 | Items e Inventario |  Terminado |
| Fase 4 | Habilidades |  En Progreso |
| Fase 5 | RNG y Sistema de Combate |  Pendiente |
| Fase 6 | Narrativa ramificada |  Pendiente |
| Fase 7 | Sistema de guardado |  Pendiente |
| Fase 8 | Interfaz gráfica Swing |  Pendiente |
| Fase 9 | Integración final |  Pendiente |

---

##  Conceptos aplicados

Este proyecto fue construido para practicar y demostrar dominio de:

- Programación Orientada a Objetos en Java
- Diseño de jerarquías de herencia
- Clases abstractas e interfaces
- Polimorfismo y sobreescritura de métodos
- Serialización de objetos (`java.io`)
- Interfaces gráficas con `javax.swing`
- Gestión de proyectos con Git y GitHub

---

##  Autor

**Nelson Filipe Fardilha Karlsson**  
Estudiante de 1º CFGS DAM

---

##  Licencia

Proyecto educativo de uso libre. Puedes usarlo como referencia o base para tus propios proyectos.
