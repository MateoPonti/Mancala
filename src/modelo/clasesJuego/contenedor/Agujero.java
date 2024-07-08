package modelo.clasesJuego.contenedor;



public class Agujero extends Contenedor{

    public Agujero() {
        super();
        inicializar();
    }

    private void inicializar() {
        final int cantHabas = 1;
        agregar(cantHabas);
    }

}
