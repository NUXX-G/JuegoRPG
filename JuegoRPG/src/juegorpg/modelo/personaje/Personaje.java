package juegorpg.modelo.personaje;
import java.util.ArrayList;
import juegorpg.modelo.Entidad;
import juegorpg.modelo.habilidad.Habilidad;
import juegorpg.modelo.item.Consumible;

public abstract class Personaje extends Entidad
{
    private int experiencia;
    private int experienciaSiguienteNivel;
    private int oro;
    private ArrayList<Habilidad> habilidades;
    
    public Personaje(String nombre, int vidaMaxima, int ataque, int defensa, int nivel) 
    {
        super(nombre, vidaMaxima, ataque, defensa, nivel);
        experiencia = 0;
        experienciaSiguienteNivel = 100;
        oro = 10;
        habilidades = new ArrayList<>();
    }
    
    public void ganarExperiencia(int cantidad)
    {
        experiencia += cantidad;
        
        if (experiencia >= experienciaSiguienteNivel) 
        {
            subirNivel();
        }
    }
    
    public void subirNivel()
    {
        setNivel(getNivel() + 1);
        experiencia = 0;
        experienciaSiguienteNivel = (int) (experienciaSiguienteNivel * 1.5);
        alSubirNivel();
    }  
    
    public void aprenderHabilidad(Habilidad habilidad)
    {
        habilidades.add(habilidad);
    }
    
    public Habilidad obtenerHabilidad(int indice)
    {
        if (indice < 0 || indice >= habilidades.size()) 
        {
            return null;
        }
        
        return habilidades.get(indice);
    }
    
    public void mostrarHabilidades()
    {
        String mostrarHabilidades = "== HABILIDADES ===" + "\n";

        if (habilidades.isEmpty()) 
        {
            mostrarHabilidades = "No tiene habilidades aprendidas.";
        }
        else
        {
            for (int i = 0; i < habilidades.size(); i++) 
            {
            mostrarHabilidades += "[" + (i + 1) + "] " + habilidades.get(i).getNombre() + " (Costo: " + habilidades.get(i).getCostoMana() + " MP)\n";
            }
        
            mostrarHabilidades += "==============";
        }

        
        System.out.println(mostrarHabilidades);
    }
    
    public String usarConsumible(Consumible consumible)
    {
        if (consumible.getEfectoVida() > 0) 
        {
            curar(consumible.getEfectoVida());
        }
        
        if (consumible.getEfectoMana() > 0)
        {
            if (this instanceof Mago) 
            {
                Mago mago = (Mago) this;
                mago.setManaActual(mago.getManaActual() + consumible.getEfectoMana());
                if (mago.getManaActual() > mago.getManaMaximo()) 
                {
                    mago.setManaActual(mago.getManaMaximo());
                }
            }
        }
        
        String mensajeConsumible = getNombre() + " uso " + consumible.getNombre() + "! +" + consumible.getEfectoVida() + " HP +" + consumible.getEfectoMana() + " MP";
        
        return mensajeConsumible;
    }
    
    protected abstract void alSubirNivel(); 

    public ArrayList<Habilidad> getHabilidades() 
    {
        return habilidades;
    }
    
    public int getExperiencia() 
    {
        return experiencia;
    }

    public void setExperiencia(int experiencia) 
    {
        this.experiencia = experiencia;
    }

    public int getExperienciaSiguienteNivel() 
    {
        return experienciaSiguienteNivel;
    }

    public void setExperienciaSiguienteNivel(int experienciaSiguienteNivel) 
    {
        this.experienciaSiguienteNivel = experienciaSiguienteNivel;
    }

    public int getOro() 
    {
        return oro;
    }

    public void setOro(int oro) 
    {
        this.oro = oro;
    }

    @Override
    public String toString() 
    {
        return super.toString() + " | EXP: " + experiencia + "/" + experienciaSiguienteNivel + " | ORO: " + oro; 
    }
}
