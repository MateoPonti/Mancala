package modelo.clasesJuego.partida;

import controlador.Notificacion;
import modelo.clasesJuego.tablero.ITableroJugador;
import modelo.clasesJuego.tablero.ResultadoJugada;
import modelo.clasesJuego.tablero.Tablero;
import modelo.clasesJuego.usuario.IUsuario;
import modelo.clasesJuego.usuario.Usuario;
import modelo.jugador.Jugador;

import java.io.Serializable;
import java.util.ArrayList;

public class Partida implements Serializable {
    private Jugador turno;

    private final ArrayList<Jugador> jugadores;

    private final Tablero tablero;

    private EstadoPartida estado;

    private String ganador;

    public Partida(ArrayList<IUsuario> usuarios) {
        jugadores=new ArrayList<>();
        for(IUsuario u : usuarios){
        jugadores.add(new Jugador(u.getId()));
        }
        turno=jugadores.get(0);
        tablero= new Tablero();
        estado=EstadoPartida.EnJuego;
    }


    public Notificacion hacerJugada(int posicion, IUsuario jugador){
        if (!isFinalizado()){
        if (isTurno(jugador)){
           ResultadoJugada resultado= tablero.hacerJugada(posicion,determinarJugador(turno),determinarOponente(turno));
           if (resultado==ResultadoJugada.Correcta){turnoSiguiente();}
           if (resultado==ResultadoJugada.Victoria){
               estado=EstadoPartida.Finalizado;
               ganador="Jugador 1"; // Asume que gano el jugador 1
               int puntosJugador1=tablero.devolverPuntosJugador(1);
               int puntosJugador2=tablero.devolverPuntosJugador(2);
               if(puntosJugador2>puntosJugador1){ganador="Jugador 2";} // pregunta si gano el jugador 2
               if(puntosJugador2==puntosJugador1){ganador="Empate.";} // se fija si hay empate
               return Notificacion.FINALIZOJUEGO;}
           return Notificacion.JUEGATURNO;
        }}

        return null;
    }




    public boolean isFinalizado(){
        return estado==EstadoPartida.Finalizado;
    }


    public ITableroJugador getTableroJugador(IUsuario jugador) {
        if (jugadores.get(0).equals(jugador)){
          return tablero.getTablero(0);
        }
        return tablero.getTablero(1);

    }

    public ITableroJugador getTableroOponente(IUsuario jugador) {
        if (jugadores.get(0).equals(jugador)){
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


    public String getTurnoActual() {
        int jugadorTurno=determinarJugador(turno)+1;
        return "Turno Actual: Jugador "+jugadorTurno;
    }
    public String getJugador(IUsuario usuario) {
        int jugadorTurno=determinarJugador(usuario)+1;
        return "Jugador "+jugadorTurno;
    }




    private int determinarJugador(Jugador j){
        if (jugadores.get(0).equals(j)){ return 0;}
        return 1;
    }
    private int determinarOponente(Jugador j){
        if (jugadores.get(0).equals(j)){ return 1;}
        return 0;
    }

    private int determinarJugador(IUsuario j){
        if (jugadores.get(0).equals(j)){ return 0;}
        return 1;
    }


    private Jugador getTurno() {
        return turno;
    }

    private boolean isTurno(IUsuario jugador){
        return  getTurno().equals(jugador);
    }



}