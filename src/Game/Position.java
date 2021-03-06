package Game;

import java.io.Serializable;

/**
 * Created by Морозов on 19.06.2016.
 */
public class Position implements Serializable {
    int i, j;

    public Position(int i, int j){
        this.i = i;
        this.j = j;
    }

    public Position(Position p){
        this.i=p.i;
        this.j=p.j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (i != position.i) return false;
        return j == position.j;

    }

    @Override
    public int hashCode() {
        int result = i;
        result = 31 * result + j;
        return result;
    }
}
