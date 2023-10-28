package modelo.clasesJuego.tablero;

import modelo.clasesJuego.contenedor.*;
import modelo.clasesJuego.haba.IHaba;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class TableroJugador implements Serializable,ITableroJugador {
    private final int cantidadAgujeros=6;
    private  ArrayList<Contenedor> tablero;

    private boolean ultimaCayoVacio=false;
    private int posCayoVacio=-1;
    private boolean ultimaCayoZona= false;




    public TableroJugador(){
        inicializar();
    }

    public ArrayList<IContenedor> getTablero() {
        return new ArrayList<>(this.tablero);
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
        ArrayList<IContenedor> agujerosDadoVuelta =getAgujeros();
        Collections.reverse(agujerosDadoVuelta);
        return agujerosDadoVuelta;
    }

    @Override
    public IContenedor getZona() {
        return tablero.get(cantidadAgujeros);
    }

    private void inicializar(){
        tablero=new ArrayList<>();
        for(int i=0;i<cantidadAgujeros;i++){
            tablero.add(new Agujero());
        }
        tablero.add(new Zona());
    }

    public ArrayList<IHaba> repartirHabas(int posicion){
        Contenedor contenedor=  tablero.get(posicion);
        ArrayList<IHaba> habas= contenedor.sacarHabas();
        repartir(posicion+1,habas);
        return habas;
    }

    public void repartirHabas(ArrayList<IHaba> habas){repartir(0,habas);}

    private void repartir(int posicion, ArrayList<IHaba> habas){
        ultimaCayoVacio=false;
        ultimaCayoZona=false;
        int tam;

        while ((posicion<=cantidadAgujeros) && (!habas.isEmpty())){
            Contenedor contActual=tablero.get(posicion);
            tam= habas.size();
            ultimaCayoVacio= tam==1  && contActual.estaVacio() && contActual.getTipo()== TipoContenedor.Agujero;
            if (ultimaCayoVacio){posCayoVacio=posicion;}
            ultimaCayoZona= tam==1  && contActual.getTipo()== TipoContenedor.Zona;
            contActual.agregar( habas.get(tam-1));
            posicion++;
            habas.remove(tam-1);
        }

    }


    public boolean isUltimaCayoVacio() {return ultimaCayoVacio;}

    public boolean isUltimaCayoZona() {return ultimaCayoZona;}

    public int getPosCayoVacio() {return posCayoVacio;}


    public boolean estaVacioContenedor(int posicion) {
        return obtenerContenedor(posicion).isEmpty();
    }

    public void  sumarPuntos(ArrayList<IHaba> habas){
        for (IHaba haba:habas){
            tablero.get(cantidadAgujeros).agregar(haba);
        }
    }

    public int  obtenerPuntos(){
        return tablero.get(cantidadAgujeros).getCantidad();
    }

    public ArrayList<IHaba> sacarHabas(int pos){
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
        ArrayList<IHaba> habas= new ArrayList<>();
        int i=0;
        while (i<cantidadAgujeros){
            habas.addAll(tablero.get(i).sacarHabas());
            i++;
        }
        tablero.get(cantidadAgujeros).agregar(habas);
    }


    private ArrayList<IHaba> obtenerContenedor(int posicion) {
        return tablero.get(posicion).getHabas();
    }






}
