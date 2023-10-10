package modelo.tablero;

import modelo.contenedor.Agujero;
import modelo.contenedor.Contenedor;
import modelo.contenedor.Zona;
import modelo.haba.IHaba;

import java.util.ArrayList;

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


    public ArrayList<IHaba> repartirHabasTurno(int posicion){
        Contenedor contenedor=  tablero[posicion];
        ArrayList<IHaba> habas= contenedor.sacarHabas();
        while ((posicion<=7) && (!habas.isEmpty())){
            tablero[posicion].agregar( habas.get(habas.size()-1));
            posicion++;
            habas.remove(habas.size()-1);
        }
        return habas;
    }

    public ArrayList<IHaba> repartirHabasOponente(ArrayList<IHaba> habas){
        int posicion=6;
        while ((posicion>0) && (!habas.isEmpty())){
            tablero[posicion].agregar( habas.get(habas.size()-1));
            posicion--;
            habas.remove(habas.size()-1);
        }
        return habas;
    }




}
