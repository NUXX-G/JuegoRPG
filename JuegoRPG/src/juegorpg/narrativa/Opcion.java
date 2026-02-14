package juegorpg.narrativa;

public class Opcion 
{
    private String texto;
    private String nodoDestino;
    
    public Opcion(String texto, String nodoDestino)
    {
        this.texto = texto;
        this.nodoDestino = nodoDestino;
    }

    public String getTexto() 
    {
        return texto;
    }

    public String getNodoDestino() 
    {
        return nodoDestino;
    }

    @Override
    public String toString() 
    {
        return "-" + texto;
    }
}
