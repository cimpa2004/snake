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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return Double.compare(coord.x, x) == 0 && Double.compare(coord.y, y) == 0;
    }


    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
