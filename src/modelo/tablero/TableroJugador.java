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
    private int posCayoVacio=-1;
    private boolean ultimaCayoZona= false;




    public TableroJugador(){
        inicializar();
    }

    private void inicializar(){
        tablero=new Contenedor[cantidadAgujeros+1];
        for(int i=0;i<cantidadAgujeros-1;i++){
            tablero[i]=new Agujero();
        }
        tablero[cantidadAgujeros]=new Zona();
    }

    public ArrayList<IHaba> repartirHabasTurno(int posicion){
        Contenedor contenedor=  tablero[posicion];
        ArrayList<IHaba> habas= contenedor.sacarHabas();
        repartir(posicion+1,habas);
        return habas;
    }

    public void repartirHabasTurno(ArrayList<IHaba> habas){repartir(0,habas);}

    private void repartir(int posicion, ArrayList<IHaba> habas){
        ultimaCayoVacio=false;
        ultimaCayoZona=false;
        while ((posicion<=cantidadAgujeros) && (!habas.isEmpty())){
            Contenedor contActual=tablero[posicion];
            int tam= habas.size();
            ultimaCayoVacio=tam==1  && contActual.estaVacio() && contActual.getTipo()== TipoContenedor.Agujero;
            if (ultimaCayoVacio){posCayoVacio=posicion;}
            ultimaCayoZona= tam==1  && contActual.getTipo()== TipoContenedor.Zona;
            contActual.agregar( habas.get(tam-1));
            posicion++;
            habas.remove(tam-1);
        }
    }

    public void repartirHabasOponente(ArrayList<IHaba> habas){
        int posicion=cantidadAgujeros-1;
        while ((posicion>=0) && (!habas.isEmpty())){
            int tam= habas.size();
            tablero[posicion].agregar( habas.get(tam-1));
            posicion--;
            habas.remove(tam-1);
        }
    }
    public boolean isUltimaCayoVacio() {return ultimaCayoVacio;}

    public boolean isUltimaCayoZona() {return ultimaCayoZona;}

    public int getPosCayoVacio() {return posCayoVacio;}

    public ArrayList<IHaba> obtenerContenedor(int posicion) {
        return tablero[posicion].getHabas();
    }

    public void  sumarPuntos(ArrayList<IHaba> habas){
        for (IHaba haba:habas){
            tablero[cantidadAgujeros].agregar(haba);
        }
    }

    public int  obtenerPuntos(){
        return tablero[cantidadAgujeros].getCantidad();
    }
}
