package modelo.contenedor;

import modelo.haba.Color;
import modelo.haba.Haba;

public class Agujero extends Contenedor{


    public Agujero() {
        super(TipoContenedor.Agujero);
        inicializar();
    }

    private void inicializar() {
        for (Color c:Color.values()){
            Haba haba=new Haba();
            haba.setColor(c);
            agregar(haba);
        }

    }

}
