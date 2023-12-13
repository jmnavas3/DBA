package pr3;

public class Coords {
    int x;
    int y;

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
