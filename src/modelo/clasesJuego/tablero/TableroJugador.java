package modelo.clasesJuego.tablero;

import modelo.clasesJuego.contenedor.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class TableroJugador implements Serializable,ITableroJugador {
    @Serial
    private static final long serialVersionUID=1;

    private static final int cantidadAgujeros=6;
    private  ArrayList<Contenedor> tablero;

    private boolean ultimaCayoVacio=false;
    private int posCayoVacio=-1;
    private boolean ultimaCayoZona= false;




    public TableroJugador(){
        inicializar();
    }



    @Override
    public ArrayList<IContenedor> getAgujeros() {
        ArrayList<IContenedor> agujeros= new ArrayList<>();
        for(int i=0;i<cantidadAgujeros;i++){
            agujeros.add(tablero.get(i));
        }
        return agujeros;
    }

    @Override
    public ArrayList<IContenedor> getAgujerosVuelta() {
        ArrayList<IContenedor> agujerosDadoVuelta = getAgujeros();
        Collections.reverse(agujerosDadoVuelta);
        return agujerosDadoVuelta;
    }

    @Override
    public IContenedor getZona() {
        return tablero.get(cantidadAgujeros);
    }


    public int repartirHabasContenedor(int posicion){
        Contenedor contenedor=  tablero.get(posicion);
        int habas= contenedor.sacarHabas();
        habas= repartir(posicion+1,habas);
        return habas;
    }

    public int repartirHabas(int habas){
        return repartir(0,habas);}




    public int repartirHabasOponente(int habas) {
        int posicion=0;
        while ((posicion<cantidadAgujeros) && (habas>0)){
            Contenedor contActual=tablero.get(posicion);
            contActual.agregar();
            posicion++;
            habas--;
        }
        return habas;
    }


    public boolean isUltimaCayoVacio() {return ultimaCayoVacio;}

    public boolean isUltimaCayoZona() {return ultimaCayoZona;}

    public int getPosCayoVacio() {return posCayoVacio;}


    public boolean estaVacioContenedor(int posicion) {
        return obtenerContenedor(posicion)==0;
    }

    public void  sumarPuntos(int habas){
        tablero.get(cantidadAgujeros).agregar(habas);
    }

    public int  obtenerPuntos(){
        return tablero.get(cantidadAgujeros).getHabas();
    }

    public int sacarHabas(int pos){
        return tablero.get(pos).sacarHabas();
    }


    public boolean noHayHabas(){
        int i=0;
        boolean nHayHabas=true;
        while ((i<=tablero.size()-2) && (nHayHabas)){
            nHayHabas= tablero.get(i).estaVacio();
            i++;
        }
        return nHayHabas;
    }




    public void sumarHabasRestante(){
        int habas=0;
        int i=0;
        while (i<cantidadAgujeros){
            habas+=(tablero.get(i).sacarHabas());
            i++;
        }
        tablero.get(cantidadAgujeros).agregar(habas);
    }


    private void inicializar(){
        tablero=new ArrayList<>();
        for(int i=0;i<cantidadAgujeros;i++){
            tablero.add(new Agujero());
        }
        tablero.add(new Zona());
    }




    private int repartir(int posicion, int habas){
        ultimaCayoVacio=false;
        ultimaCayoZona=false;
        int tam;

        while ((posicion<=cantidadAgujeros) && (habas>0)){
            Contenedor contActual=tablero.get(posicion);
            ultimaCayoVacio= habas==1  && contActual.estaVacio() && (contActual instanceof Agujero);
            if (ultimaCayoVacio){posCayoVacio=posicion;}
            ultimaCayoZona= habas==1  && (contActual instanceof Zona);
            contActual.agregar();
            posicion++;
            habas--;
        }

        return  habas;

    }

    private int obtenerContenedor(int posicion) {
        return tablero.get(posicion).getHabas();
    }



}
