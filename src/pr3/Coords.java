package pr3;

import java.io.Serializable;

public class Coords implements Serializable {
    int x;
    int y;
    
    public Coords () {
        this.x = -1;
        this.y = -1;
    }
    
    public Coords (String message) {
        this.x = Character.getNumericValue(message.charAt(0));
        this.y = Character.getNumericValue(message.charAt(1));
    }
    
    public boolean validar(){
        return this.x >= 0 && this.y >= 0 && this.x < 10 && this.y < 10;
    }

    public boolean equals(Object o) {
        Coords c = (Coords) o;
        return c.x == x && c.y == y;
    }

    public Coords(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "x:" + this.x + " y:" + this.y;
    }
}
