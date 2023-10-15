package modelo.tablero;

import modelo.contenedor.*;
import modelo.haba.Haba;
import modelo.haba.IHaba;

import java.util.ArrayList;
import java.util.Arrays;

public class TableroJugador {
    private final int cantidadAgujeros=6;
    private  ArrayList<Contenedor> tablero;

    private boolean ultimaCayoVacio=false;
    private int posCayoVacio=-1;
    private boolean ultimaCayoZona= false;




    public TableroJugador(){
        inicializar();
    }

    public ArrayList<IContenedor> getTablero() {
        ArrayList<IContenedor> tablero = new ArrayList<>(this.tablero);
        return tablero;
    }

    private void inicializar(){
        tablero=new ArrayList<>();
        for(int i=0;i<cantidadAgujeros-1;i++){
            tablero.add(new Agujero());
        }
        tablero.add(new Zona());
    }

    public ArrayList<IHaba> repartirHabasTurno(int posicion){
        Contenedor contenedor=  tablero.get(posicion);
        ArrayList<IHaba> habas= contenedor.sacarHabas();
        repartir(posicion+1,habas);
        return habas;
    }

    public void repartirHabasTurno(ArrayList<IHaba> habas){repartir(0,habas);}

    private void repartir(int posicion, ArrayList<IHaba> habas){
        ultimaCayoVacio=false;
        ultimaCayoZona=false;
        while ((posicion<=cantidadAgujeros) && (!habas.isEmpty())){
            Contenedor contActual=tablero.get(posicion);
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
            tablero.get(posicion).agregar( habas.get(tam-1));
            posicion--;
            habas.remove(tam-1);
        }
    }
    public boolean isUltimaCayoVacio() {return ultimaCayoVacio;}

    public boolean isUltimaCayoZona() {return ultimaCayoZona;}

    public int getPosCayoVacio() {return posCayoVacio;}

    public ArrayList<IHaba> obtenerContenedor(int posicion) {
        return tablero.get(posicion).getHabas();
    }

    public void  sumarPuntos(ArrayList<IHaba> habas){
        for (IHaba haba:habas){
            tablero.get(cantidadAgujeros).agregar(haba);
        }
    }

    public int  obtenerPuntos(){
        return tablero.get(cantidadAgujeros).getCantidad();
    }


    public boolean noHayHabas(){
        int i=0;
        boolean noHayHabas=true;
        while ((i<tablero.size()) && (noHayHabas())){
            noHayHabas= tablero.get(i).estaVacio();
            i++;
        }
        return noHayHabas;
    }

    public void sumarHabasRestante(){
        ArrayList<IHaba> habas= new ArrayList<>();
        int i=0;
        while (i<cantidadAgujeros){
            habas.addAll(tablero.get(i).sacarHabas());
            i++;
        }
        tablero.get(cantidadAgujeros).agregar(habas);
    }






}
