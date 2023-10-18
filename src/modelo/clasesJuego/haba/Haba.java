package modelo.clasesJuego.haba;

import java.io.Serializable;

public class Haba implements IHaba, Serializable {
    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
