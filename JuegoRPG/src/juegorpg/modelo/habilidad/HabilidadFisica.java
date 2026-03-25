package juegorpg.modelo.habilidad;

/**
 * Habilidad fisica. El danio escala con el ataque del usuario.
 * La formula es: danioBase + (ataque * multiplicador).
 * Cuanto mas ataque tenga el personaje, mas hace esta habilidad.
 * La usan el Guerrero, el Arquero y el Ladron.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class HabilidadFisica extends Habilidad 
{

    /**
     * Multiplicador que se aplica al stat de ataque del usuario.
     * Por ejemplo, 2.0 significa que suma el doble del ataque al danio base.
     */
    private double multiplicadorAtaque;

    /**
     * Crea una habilidad fisica con su multiplicador de ataque.
     *
     * @param nombre              nombre de la habilidad
     * @param descripcion         descripcion de lo que hace
     * @param danioBase           danio fijo que siempre se suma
     * @param costoMana           mana que cuesta usarla
     * @param multiplicadorAtaque cuanto multiplica el stat de ataque del usuario
     */
    public HabilidadFisica(String nombre, String descripcion, int danioBase, int costoMana, double multiplicadorAtaque) 
    {
        super(nombre, descripcion, danioBase, costoMana);
        this.multiplicadorAtaque = multiplicadorAtaque;
    }

    /**
     * Calcula el danio fisico: danio base mas el ataque del usuario multiplicado.
     * Formula: danioBase + (ataqueUsuario * multiplicadorAtaque).
     *
     * @param ataqueUsuario stat de ataque del personaje
     * @return danio total de la habilidad fisica
     */
    @Override
    public int calcularDanio(int ataqueUsuario) 
    {
        return (int)(getDanioBase() + (ataqueUsuario * multiplicadorAtaque));
    }

    /**
     * Extiende el toString con el tipo y el multiplicador.
     *
     * @return string con toda la info de la habilidad
     */
    @Override
    public String toString() 
    {
        return super.toString() + " | Tipo: Fisica | Multiplicador ATQ: " + multiplicadorAtaque;
    }

    /** @return multiplicador de ataque de esta habilidad */
    public double getMultiplicadorAtaque() 
    { 
        return multiplicadorAtaque; 
    }

    /** @param multiplicadorAtaque nuevo multiplicador */
    public void setMultiplicadorAtaque(double multiplicadorAtaque) 
    { 
        this.multiplicadorAtaque = multiplicadorAtaque; 
    }
}