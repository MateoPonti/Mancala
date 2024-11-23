package modelo.clasesJuego.tablero;


import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tablero implements Serializable {
    @Serial
    private static final long serialVersionUID=1;

    private final ArrayList<TableroJugador> tableroJugadores;

    public Tablero(){
        this.tableroJugadores= new ArrayList<>();
        tableroJugadores.add(new TableroJugador());
        tableroJugadores.add(new TableroJugador());
    }

    public ResultadoJugada  hacerJugada(int posicion,int jugadorTurno,int jugadorOponente){
        posicion--;

        if (!Posicion.validarPosicion(posicion)){ return ResultadoJugada.PosicioInvalida;}

        TableroJugador tableroTurno= tableroJugadores.get(jugadorTurno);
        TableroJugador tableroOponente=tableroJugadores.get(jugadorOponente);
        ResultadoJugada resultado = ResultadoJugada.Correcta;


        if (tableroTurno.estaVacioContenedor(posicion)){
            return ResultadoJugada.PosicioInvalida;} // comprueba que el jugador no haya elegido una posicion donde no haya habas


        int  habasRepartidas=tableroTurno.repartirHabasP(posicion);

        while (habasRepartidas>0){
            habasRepartidas = tableroOponente.repartirHabasOponente(habasRepartidas);
            habasRepartidas = tableroTurno.repartirHabas(habasRepartidas);
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
        Map<Integer, Integer> parejas = new HashMap<>();
        parejas.put(0, 5);
        parejas.put(5, 0);
        parejas.put(2, 3);
        parejas.put(3, 2);
        parejas.put(4, 1);
        parejas.put(1, 4);

        int habas= oponente.sacarHabas(parejas.get(posCayoVacio));
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


    public ITableroJugador getTablero(int indice)
    {
        return  tableroJugadores.get(indice);
    }

    public int devolverPuntosJugador(int jugador)
    {
        return tableroJugadores.get(jugador-1).obtenerPuntos();
    }
}
