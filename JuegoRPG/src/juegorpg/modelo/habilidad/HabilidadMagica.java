package juegorpg.modelo.habilidad;

/**
 * Habilidad magica. El danio no depende del ataque fisico del usuario,
 * sino del bonus magico fijo que tiene la habilidad.
 * Formula: danioBase + bonusMagico. Siempre hace lo mismo independientemente
 * del stat de ataque del Mago. Solo la usan los Magos.
 *
 * @author Nelson Filipe Fardilha Karlsson
 * @version 1.0
 */
public class HabilidadMagica extends Habilidad 
{

    /**
     * Bonus de danio fijo magico que se suma al danio base.
     * No depende del ataque del personaje, es siempre el mismo valor.
     */
    private int bonusMagico;

    /**
     * Crea una habilidad magica con su bonus de danio magico.
     *
     * @param nombre      nombre de la habilidad
     * @param descripcion descripcion de lo que hace
     * @param danioBase   danio fijo base
     * @param costoMana   mana que cuesta usarla
     * @param bonusMagico bonus de danio magico adicional
     */
    public HabilidadMagica(String nombre, String descripcion, int danioBase, int costoMana, int bonusMagico) 
    {
        super(nombre, descripcion, danioBase, costoMana);
        this.bonusMagico = bonusMagico;
    }

    /**
     * Calcula el danio magico: danio base mas el bonus magico fijo.
     * El parametro ataqueUsuario se ignora completamente aqui,
     * porque el danio magico no escala con el ataque fisico.
     *
     * @param ataqueUsuario ignorado en habilidades magicas
     * @return danio total fijo de la habilidad magica
     */
    @Override
    public int calcularDanio(int ataqueUsuario) 
    {
        return getDanioBase() + bonusMagico;
    }

    /**
     * Extiende el toString con el tipo y el bonus magico.
     *
     * @return string con toda la info de la habilidad
     */
    @Override
    public String toString() 
    {
        return super.toString() + " | Tipo: Magica | Bonus magico: " + bonusMagico;
    }

    /** @return bonus de danio magico */
    public int getBonusMagico() 
    { 
        return bonusMagico; 
    }

    /** @param bonusMagico nuevo bonus magico */
    public void setBonusMagico(int bonusMagico) 
    { 
        this.bonusMagico = bonusMagico; 
    }
}