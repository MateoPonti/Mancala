package modelo.tablero;

import modelo.contenedor.Agujero;
import modelo.contenedor.Contenedor;
import modelo.contenedor.TipoContenedor;
import modelo.contenedor.Zona;
import modelo.haba.IHaba;

import java.util.ArrayList;

public class TableroJugador {

    private final int cantidadAgujeros=6;
    private  Contenedor[] tablero;

    private boolean ultimaCayoVacio=false;

    private boolean ultimaCayoZona= false;




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
        ultimaCayoVacio=false;
        ultimaCayoZona=false;

        Contenedor contenedor=  tablero[posicion];
        ArrayList<IHaba> habas= contenedor.sacarHabas();


        posicion++;
        while ((posicion<=6) && (!habas.isEmpty())){
            Contenedor contActual=tablero[posicion];
            int tam= habas.size();
            ultimaCayoVacio=tam==1  && contActual.estaVacio() && contActual.getTipo()== TipoContenedor.Agujero;
            ultimaCayoZona= tam==1  && contActual.getTipo()== TipoContenedor.Zona;
            contActual.agregar( habas.get(tam-1));
            posicion++;
            habas.remove(tam-1);
        }
        return habas;
    }

    public ArrayList<IHaba> repartirHabasOponente(ArrayList<IHaba> habas){
        int posicion=5;
        while ((posicion>=0) && (!habas.isEmpty())){
            int tam= habas.size();
            tablero[posicion].agregar( habas.get(tam-1));
            posicion--;
            habas.remove(tam-1);
        }
        return habas;
    }


    public boolean isUltimaCayoVacio() {

        return ultimaCayoVacio;
    }

    public boolean isUltimaCayoZona() {
        return ultimaCayoZona;
    }
}
