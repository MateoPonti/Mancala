package test;

import modelo.clasesJuego.tablero.TableroJugador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;


class TableroJugadorTest {

    TableroJugador tablero;

    @BeforeEach
     void setUp() throws Exception{
        tablero= new TableroJugador();
    }

    @Test
    void probarQueRepartirLaPrimeraPosicionMuevaUnHabaACadaContenedor(){
        int pos=0;
        int cantHabas=4;
        tablero.repartirHabas(pos);
        boolean condicion=true;
        for (int p=pos+1; p<=pos+cantHabas; p++){
            condicion= (tablero.getAgujeros().get(p).getCantidad()==cantHabas+1) && condicion;
        }
        Assertions.assertTrue(condicion);

    }

    @Test
    void probarQueUltimaHabaCayoEnUnContenedorVacio(){
        tablero.repartirHabas(5);
        tablero.repartirHabas(6);
        System.out.println(tablero.getTablero().get(6).getCantidad());

    }


    @Test
     void probarQueRepartiTerceraPosicionPrimeraJugadaDaOtroTurno(){ // prueba que la posicion 3 (posicion relativa al tablero, en el tablero es la segunda) provoca otro turno en la primera jugada
        tablero.repartirHabas(2);
        Assertions.assertTrue(tablero.isUltimaCayoZona());
    }




}