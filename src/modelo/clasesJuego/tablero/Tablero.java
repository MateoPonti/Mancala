package modelo.clasesJuego.tablero;

import modelo.clasesJuego.contenedor.IContenedor;
import modelo.clasesJuego.haba.IHaba;

import java.io.Serializable;
import java.util.ArrayList;

public class Tablero implements Serializable {
    private ArrayList<TableroJugador> tableroJugadores;

    public Tablero(ArrayList<TableroJugador> tablerosJugadores){
        this.tableroJugadores=tablerosJugadores;
    }

    public ResultadoJugada  hacerJugada(int posicion,int jugadorTurno,int jugadorOponente){
        posicion--;

        if (!Posicion.validarPosicion(posicion)){ return ResultadoJugada.PosicioInvalida;}

        TableroJugador tableroTurno= tableroJugadores.get(jugadorTurno);
        TableroJugador tableroOponente=tableroJugadores.get(jugadorOponente);
        ResultadoJugada resultado = ResultadoJugada.Correcta;


        if (tableroTurno.estaVacioContenedor(posicion)){
            return ResultadoJugada.PosicioInvalida;} // comprueba que el jugador no haya elegido una posicion donde no haya habas
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
        ArrayList<IHaba> habas= oponente.sacarHabas(turno.getPosCayoVacio());
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


    public ArrayList<IContenedor> getTablero(int indice)
    {
        return tableroJugadores.get(indice).getTablero();
    }

    public int devolverPuntosJugador(int jugador)
    {
        return tableroJugadores.get(jugador-1).obtenerPuntos();
    }
}