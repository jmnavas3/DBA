package pr3;

import java.io.Serializable;

public class Coords implements Serializable {
    int x;
    int y;
    private String mess;
    
    public Coords () {
        this.x = -1;
        this.y = -1;
    }
    
    public Coords (String message) {
        this.mess = message;
        if (message.length() == 2) {
            this.x = toInt(0);
            this.y = toInt(1);
        }

        if (message.length() == 3) {
            this.x = toInt(0)*10;
            this.x += toInt(1);
            this.y = toInt(2);
            if (this.x > 39) {
                this.x = toInt(0);
                this.y = toInt(1)*10;
                this.y += toInt(2);
            }
        }

        if (message.length() == 4) {
            this.x = toInt(0)*10;
            this.x += toInt(1);
            this.y = toInt(2)*10;
            this.y += toInt(3);
        }
    }

    public int toInt(int pos) {
        return Character.getNumericValue(this.mess.charAt(pos));
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
        String str_x = Integer.toString(this.x);
        String str_y = Integer.toString(this.y);
        return str_x+str_y;
    }
    
    public String mostrar() {
        return "x:" + x + " y:" + y;
    }
}
