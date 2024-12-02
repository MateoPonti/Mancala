package modelo.clasesJuego.contenedor;



public class Agujero extends Contenedor{

    public Agujero() {
        super();
        inicializar();
    }

    private void inicializar() {
        final int cantHabas = 4;
        agregar(cantHabas);
    }

}
