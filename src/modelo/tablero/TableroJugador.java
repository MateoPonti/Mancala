package modelo.tablero;

import modelo.contenedor.Agujero;
import modelo.contenedor.Contenedor;
import modelo.contenedor.Zona;

public class TableroJugador {

    private final int cantidadAgujeros=6;
    private  Contenedor[] tablero;

    public TableroJugador(){
        inicializar();
    }

    private void inicializar(){
        tablero=new Contenedor[cantidadAgujeros];
        for(int i=0;i<cantidadAgujeros-1;i++){
            tablero[i]=new Agujero();
        }
        tablero[cantidadAgujeros]=new Zona();
    }



}
