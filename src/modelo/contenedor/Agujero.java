package modelo.contenedor;

public class Agujero extends Contenedor{

    public Agujero() {
        super(TipoContenedor.Agujero);
    }

    public void limpiar(){
        getHabas().clear();
    }
}
