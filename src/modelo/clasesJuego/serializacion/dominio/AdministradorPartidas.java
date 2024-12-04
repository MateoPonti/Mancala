package modelo.clasesJuego.serializacion.dominio;

import modelo.clasesJuego.partida.Partida;

import java.util.ArrayList;
import java.util.Collection;

public class AdministradorPartidas {
    private static ArrayList<Partida> partidas;
    private static AdministradorPartidas instancia;


    public static AdministradorPartidas getInstancia(){
        if (instancia==null){
            instancia=new AdministradorPartidas();
        }
        return instancia;
    }






    public boolean estaVacio(){
        return partidas.isEmpty();
    }

    public boolean remove(Partida o){
        return partidas.remove(o);
    }





    public Partida get(int index){
        return partidas.get(index);
    }

    public int getTam(){
        return partidas.size();
    }

    public void add(Partida o) {
        partidas.add(o);
    }


    private AdministradorPartidas(){
        super();
        partidas=new ArrayList<>();
    }


}
