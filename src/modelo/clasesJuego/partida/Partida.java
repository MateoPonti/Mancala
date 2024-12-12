package modelo.clasesJuego.partida;

import controlador.Notificacion;
import modelo.clasesJuego.jugador.IJugador;
import modelo.clasesJuego.jugador.Jugador;
import modelo.clasesJuego.tablero.ITableroJugador;
import modelo.clasesJuego.tablero.ResultadoJugada;
import modelo.clasesJuego.tablero.Tablero;
import modelo.clasesJuego.usuario.IUsuario;

import java.io.Serializable;
import java.util.ArrayList;

public class Partida implements Serializable,IPartida {

    private static final long serialVersionUID=1;

    private Jugador turno;

    private final ArrayList<Jugador> jugadores;
    private final Tablero tablero;

    private EstadoPartida estado;

    private IJugador ganador;

    public Partida(ArrayList<IUsuario> usuarios) {
        jugadores=new ArrayList<>();
        int i=1;
        String nombreJ="Jugador ";
        for(IUsuario u : usuarios){

            jugadores.add(new Jugador(u.getId(),(nombreJ+i)));
            i++;
        }
        turno=jugadores.get(0);
        tablero= new Tablero();
        estado=EstadoPartida.EnJuego;}


    public Notificacion hacerJugada(int posicion, IUsuario jugador){
        if (!isFinalizado()){
            if (isTurno(jugador)){
                ResultadoJugada resultado= tablero.hacerJugada(posicion,determinarJugador(turno),determinarOponente(turno));
                if (resultado==ResultadoJugada.PosicionInvalida){return Notificacion.POSICIONINVALIDA;}
                if (resultado==ResultadoJugada.Correcta){
                    turnoSiguiente();}
                if (resultado==ResultadoJugada.Victoria){
                    estado=EstadoPartida.Finalizado;
                    ganador= jugadores.get(0); // Asume que gano el jugador 1
                    int puntosJugador1=tablero.devolverPuntosJugador(1);
                    int puntosJugador2=tablero.devolverPuntosJugador(2);
                    if(puntosJugador2>puntosJugador1){ganador=jugadores.get(1);} // pregunta si gano el jugador 2
                    if(puntosJugador2==puntosJugador1){ganador=new Jugador();} // es empate
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

    public IJugador getGanador() {
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


    public IJugador getTurnoActual() {
        int jugadorTurno=determinarJugador(turno);
        return jugadores.get(jugadorTurno);
    }
    public IJugador getJugador(IUsuario usuario) {
        int jugadorTurno=determinarJugador(usuario);
        return jugadores.get(jugadorTurno);
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


    @Override
    public boolean esta(IUsuario jugador) {
        return jugadores.get(0).equals(jugador) || jugadores.get(1).equals(jugador) ;
    }



}
