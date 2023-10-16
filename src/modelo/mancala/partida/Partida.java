package modelo.mancala.partida;

import controlador.Notificacion;
import modelo.contenedor.IContenedor;
import modelo.jugador.IJugador;
import modelo.jugador.Jugador;
import modelo.tablero.ResultadoJugada;
import modelo.tablero.Tablero;
import modelo.tablero.TableroJugador;

import java.util.ArrayList;

public class Partida {
    private Jugador turno;

    private ArrayList<Jugador> jugadores;

    private Tablero tablero;

    private EstadoPartida estado;

    public Partida(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
        turno=jugadores.get(0);
        ArrayList<TableroJugador> tablerosJugadores = new ArrayList<>();
        tablerosJugadores.add(jugadores.get(0).getTableroJugador());
        tablerosJugadores.add(jugadores.get(1).getTableroJugador());
        tablero= new Tablero(tablerosJugadores);
        estado=EstadoPartida.EnJuego;

    }


    public Notificacion hacerJugada(int posicion, IJugador jugador){
        if (isTurno(jugador)){
           int j=determinarJugador();
           ResultadoJugada resultado= tablero.hacerJugada(posicion,j,determinarOponente());
           if (resultado!=ResultadoJugada.OtroTurno){turnoSiguiente();}
           if (resultado==ResultadoJugada.Victoria){
               estado=EstadoPartida.Finalizado;
               return Notificacion.FINALIZOJUEGO;}
           return Notificacion.JUEGATURNO;
        }

        return null;
    }

    private void turnoSiguiente() {
        int posicionActual=0;
        if(jugadores.get(1).equals(turno)) {posicionActual=1;}
        try{
            turno=jugadores.get(posicionActual+1);
        }
        catch (Exception e){
            turno=jugadores.get(0);
        }
    }

    private int determinarJugador(){
        if (jugadores.get(0).equals(turno)){ return 0;}
          return 1;
    }
    private int determinarOponente(){
        if (jugadores.get(0).equals(turno)){ return 1;}
        return 0;
    }



    public Jugador getTurno() {
        return turno;
    }
    public boolean isTurno(IJugador jugador){
        return  getTurno().equals(jugador);
    }

    public boolean isFinalizado(){
        return estado==EstadoPartida.Finalizado;
    }


    public ArrayList<IContenedor> getTableroJugador(IJugador jugador) {
        if (jugadores.get(0)==jugador){
          return tablero.getTablero(0);
        }
        return tablero.getTablero(1);

    }

    public ArrayList<IContenedor> getTableroOponente(IJugador jugador) {
        if (jugadores.get(0)==jugador){
            return tablero.getTablero(1);
        }
        return tablero.getTablero(0);

    }

}
