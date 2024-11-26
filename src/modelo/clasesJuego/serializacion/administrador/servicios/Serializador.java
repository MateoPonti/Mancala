package modelo.clasesJuego.serializacion.administrador.servicios;

import java.io.*;
import java.util.ArrayList;

public class Serializador {
    private String nombreArchivo;


    public Serializador(String nombreArchivo){
        super();
        this.nombreArchivo=nombreArchivo;
    }

    public boolean writeOneObject(Object obj){
        boolean respuesta = false;
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo));
            oos.writeObject(obj);
            oos.close();
            respuesta=true;
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return respuesta;
    }

    public Object[] readObjects(){
        Object[] respuesta;
        ArrayList<Object> listOfObject = new ArrayList<Object>();
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo));
            Object r  = ois.readObject();
            while (r!=null){

                listOfObject.add(r);
                r=ois.readObject();
            }
            ois.close();

        }
        catch (ClassNotFoundException | IOException ignored) {
        }
        if (!listOfObject.isEmpty()){
            respuesta = new Object[listOfObject.size()];
            int count =  0;
            for(Object o : listOfObject){
                respuesta[count++] = o;
            }
        }
        else {
            respuesta = null;
        }
        return respuesta;
    }

    public boolean addOneObject(Object obj){
        boolean respuesta = false;

        try {
            AddableObjectOutputStream oos = new AddableObjectOutputStream(new FileOutputStream(nombreArchivo,true));
            oos.writeObject(obj);
            oos.close();
            oos.close();
        } catch (IOException e) {
           e.printStackTrace();
        }
        return respuesta;
    }

    public Object readFirstObject() {
      Object respuesta = null;
      try{
          ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo));
          respuesta = ois.readObject();
      } catch (IOException | ClassNotFoundException e) {
          throw new RuntimeException(e);
      }
       return respuesta;
    }


}
