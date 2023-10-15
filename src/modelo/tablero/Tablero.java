package modelo.tablero;

import modelo.contenedor.IContenedor;
import modelo.haba.IHaba;
import modelo.jugador.IJugador;

import java.util.ArrayList;

public class Tablero {
    private ArrayList<TableroJugador> tableroJugadores;

    public Tablero(ArrayList<TableroJugador> tablerosJugadores){
        this.tableroJugadores=tablerosJugadores;
    }

    public ResultadoJugada  hacerJugada(int posicion,int jugadorTurno){
        if (!Posicion.validarPosicion(posicion)){ return ResultadoJugada.PosicioInvalida;}

        TableroJugador tableroTurno= tableroJugadores.get(0);
        TableroJugador tableroOponente=tableroJugadores.get(1);
        ResultadoJugada resultado = ResultadoJugada.Correcta;


        ArrayList<IHaba> habasRepartidas=tableroTurno.repartirHabasTurno(posicion);
        while (!habasRepartidas.isEmpty()){
            tableroOponente.repartirHabasOponente(habasRepartidas);
            tableroTurno.repartirHabasTurno(habasRepartidas);
        }
        if (tableroTurno.isUltimaCayoVacio()){
            RobarPuntos(tableroTurno.getPosCayoVacio(),tableroTurno,tableroOponente);
        }
        if (tableroTurno.isUltimaCayoZona()){
            resultado=ResultadoJugada.OtroTurno;
        }
        TableroJugador tableroConHabasRestante = comprobarVictoria(tableroTurno,tableroOponente); // si uno de los 2 jugadores no tiene habas , al otro le suma las habas que tiene en sus agujeros en la Zona en caso de tener.
        if(tableroConHabasRestante!=null){
            resultado=ResultadoJugada.Victoria;
            tableroConHabasRestante.sumarHabasRestante();
        }
        return resultado;
    }

    private void RobarPuntos(int posCayoVacio,TableroJugador turno,TableroJugador oponente) {
        ArrayList<IHaba> habas= oponente.obtenerContenedor(turno.getPosCayoVacio());
        turno.sumarPuntos(habas);
    }


    private TableroJugador comprobarVictoria(TableroJugador turno,TableroJugador oponete){
        if (turno.noHayHabas()) {
            return oponete;
        }
        if (oponete.noHayHabas()) {
            return turno;
        }
        return null;
    }


    public ArrayList<IContenedor> getTableros(boolean esPrimerJugador) {
        ArrayList<IContenedor> jugador0= tableroJugadores.get(0).getTablero();
        ArrayList<IContenedor> jugador1= tableroJugadores.get(1).getTablero();
        if (esPrimerJugador){
            jugador1.addAll(jugador0);
            return jugador1;

        }
        jugador0.addAll(jugador1);
        return jugador0;

    }
}
