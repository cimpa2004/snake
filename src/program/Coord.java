package program;

import java.awt.*;
import java.util.Objects;

public class Coord {
    public double x;
    public double y;
    public Coord(double x, double y){
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals(Object o) {
        Coord coord = (Coord) o;
        return x == coord.x && y == coord.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
