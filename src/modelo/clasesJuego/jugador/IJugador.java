package modelo.clasesJuego.jugador;

import modelo.clasesJuego.usuario.IUsuario;
import modelo.clasesJuego.usuario.Usuario;

public interface IJugador  {
     String getNombre();
     boolean equals(IUsuario usuario);
     boolean esValido();
}
