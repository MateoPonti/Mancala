package modelo.clasesJuego.administrador;

import modelo.clasesJuego.usuario.IUsuario;
import modelo.clasesJuego.usuario.Usuario;

import java.util.ArrayList;

public class AdministradorUsuarios {

    public static  ArrayList<IUsuario> obtenerRank(){
        ArrayList<IUsuario> usuarios= new ArrayList<>();
        for (int i =  0 ; i<5;i++){
           Usuario u = new Usuario("Usuario"+i,"Contra",i);
            u.agregarEmpate();
            u.agregarVictoria();
            u.agregarDerrota();
            usuarios.add(u);
        }
        return usuarios;
    }
}
