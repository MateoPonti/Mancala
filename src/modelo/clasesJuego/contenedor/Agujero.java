package modelo.clasesJuego.contenedor;

import modelo.clasesJuego.haba.Color;
import modelo.clasesJuego.haba.Haba;

public class Agujero extends Contenedor{


    public Agujero() {
        super(TipoContenedor.Agujero);
        inicializar();
    }

    private void inicializar() {
        int i=0;

        for (Color c:Color.values()){
            Haba haba=new Haba();
            haba.setColor(c);
            agregar(haba);
            i++;
            if (i==4){break;}
        }

    }

}
