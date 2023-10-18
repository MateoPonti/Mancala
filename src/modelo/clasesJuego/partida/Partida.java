package modelo.clasesJuego.partida;

import controlador.Notificacion;
import modelo.clasesJuego.contenedor.IContenedor;
import modelo.clasesJuego.jugador.IJugador;
import modelo.clasesJuego.jugador.Jugador;
import modelo.clasesJuego.tablero.ResultadoJugada;
import modelo.clasesJuego.tablero.Tablero;
import modelo.clasesJuego.tablero.TableroJugador;

import java.io.Serializable;
import java.util.ArrayList;

public class Partida implements Serializable {
    private Jugador turno;

    private ArrayList<Jugador> jugadores;

    private Tablero tablero;

    private EstadoPartida estado;

    private String ganador;

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
        if (!isFinalizado()){
        if (isTurno(jugador)){
           ResultadoJugada resultado= tablero.hacerJugada(posicion,determinarJugador(),determinarOponente());
           if (resultado==ResultadoJugada.Correcta){turnoSiguiente();}
           if (resultado==ResultadoJugada.Victoria){
               estado=EstadoPartida.Finalizado;
               ganador="Jugador 1, "+jugadores.get(0).getNombre(); // Asume que gano el jugador 1
               int puntosJugador1=tablero.devolverPuntosJugador(1);
               int puntosJugador2=tablero.devolverPuntosJugador(2);
               if(tablero.devolverPuntosJugador(2)>tablero.devolverPuntosJugador(1)){ganador="Jugador 2, "+jugadores.get(1).getNombre();} // pregunta si gano el jugador 2
               if(tablero.devolverPuntosJugador(2)==tablero.devolverPuntosJugador(1)){ganador="Empate.";} // se fija si hay empate
               return Notificacion.FINALIZOJUEGO;}
           return Notificacion.JUEGATURNO;
        }}

        return null;
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

    public String getGanador() {
        return ganador;
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
}