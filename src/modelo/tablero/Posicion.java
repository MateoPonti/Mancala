package modelo.tablero;

public class Posicion {


    public static  boolean validarPosicion( int pos){
        return (pos>0 && pos<7);
    }


    public static  boolean validarPosicion( char pos){
        return (validarPosicion((int) 65 - Character.toUpperCase(pos)));
    }
}
